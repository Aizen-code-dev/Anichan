package com.anime.Aniflix.Service;

import com.anime.Aniflix.Model.DownloadResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Service
public class DownloaderService {

    // Auto-detect yt-dlp path
    private static final String YT_DLP = getYtDlpPath();

    private static String getYtDlpPath() {
        // Windows default manual placement
        String winPath = "C:/yt-dlp/yt-dlp.exe";
        if (new File(winPath).exists()) return winPath;

        // Linux/Mac
        String unix = "/usr/local/bin/yt-dlp";
        if (new File(unix).exists()) return unix;

        return "yt-dlp"; // fallback to system PATH
    }

    // ------------------------------------------------------------
    // MAIN VIDEO (MP4)
    // ------------------------------------------------------------
    public DownloadResponse downloadVideo(String url) {
        DownloadResponse response = runYtDlp(url);
        if (response.isError()) return response;

        try {
            JSONArray formats = response.getFormats();
            String bestUrl = null;

            // Best MP4 selection
            for (int i = 0; i < formats.length(); i++) {
                JSONObject f = formats.getJSONObject(i);

                if (f.has("url") && "mp4".equals(f.optString("ext"))) {
                    bestUrl = f.optString("url");
                    break;
                }
            }

            if (bestUrl == null) {
                response.setError(true);
                response.setTitle("No MP4 format found");
            } else {
                response.setDownloadUrl(bestUrl);
                response.setType("mp4");
            }

        } catch (Exception e) {
            response.setError(true);
            response.setTitle("Video parsing failed");
        }

        return response;
    }

    // ------------------------------------------------------------
    // AUDIO (BEST MP3/AAC/WEBM AUDIO)
    // ------------------------------------------------------------
    public DownloadResponse downloadMusic(String url) {
        DownloadResponse response = runYtDlp(url);
        if (response.isError()) return response;

        try {
            JSONArray formats = response.getFormats();
            String bestAudioUrl = null;

            for (int i = 0; i < formats.length(); i++) {
                JSONObject f = formats.getJSONObject(i);
                String acodec = f.optString("acodec", "none");

                if (f.has("url") && !acodec.equals("none")) {
                    bestAudioUrl = f.optString("url");
                    break;
                }
            }

            if (bestAudioUrl == null) {
                response.setError(true);
                response.setTitle("No audio stream found");
            } else {
                response.setDownloadUrl(bestAudioUrl);
                response.setType("audio");
            }

        } catch (Exception e) {
            response.setError(true);
            response.setTitle("Audio parsing failed");
        }

        return response;
    }

    // ------------------------------------------------------------
    // yt-dlp Execution
    // ------------------------------------------------------------
    private DownloadResponse runYtDlp(String videoUrl) {
        DownloadResponse response = new DownloadResponse();

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    YT_DLP, "-j", videoUrl
            );
            pb.redirectErrorStream(true);

            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            String output = sb.toString();

            int exit = process.waitFor();
            if (exit != 0 || output.isEmpty()) {
                response.setError(true);
                response.setTitle("yt-dlp failed (not installed or wrong path)");
                return response;
            }

            JSONObject json = new JSONObject(output);

            // Basic info
            response.setError(false);
            response.setTitle(json.optString("title", "Unknown"));
            response.setVideoId(json.optString("id", null));
            response.setThumbnail(json.optString("thumbnail", null));
            response.setOriginalUrl(videoUrl);

            // Add list of formats
            if (json.has("formats")) {
                response.setFormats(json.getJSONArray("formats"));
            }

        } catch (Exception e) {
            response.setError(true);
            response.setTitle("Error executing ");
        }

        return response;
    }
}
