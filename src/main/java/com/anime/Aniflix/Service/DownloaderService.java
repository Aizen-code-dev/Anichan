package com.anime.Aniflix.Service;

import com.anime.Aniflix.Model.DownloadResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class DownloaderService {

    private static final String YT_DLP = "yt-dlp";  // path if needed: "C:/yt-dlp/yt-dlp.exe"

    // ------------------------------------------------------------
    // MAIN VIDEO DOWNLOAD
    // ------------------------------------------------------------
    public DownloadResponse downloadVideo(String url) {
        DownloadResponse response = runYtDlp(url);
        if (response.isError()) return response;

        try {
            JSONArray formats = response.getFormats();
            String best = null;

            for (int i = 0; i < formats.length(); i++) {
                JSONObject f = formats.getJSONObject(i);
                if (f.has("url") && f.has("ext") && f.getString("ext").equals("mp4")) {
                    best = f.getString("url");
                    break;
                }
            }

            if (best != null) {
                response.setDownloadUrl(best);
                response.setType("mp4");
            } else {
                response.setError(true);
                response.setTitle("No MP4 format available");
            }

        } catch (Exception e) {
            response.setError(true);
            response.setTitle("Video parsing error");
        }

        return response;
    }

    // ------------------------------------------------------------
    // AUDIO DOWNLOAD (MP3)
    // ------------------------------------------------------------
    public DownloadResponse downloadMusic(String url) {
        DownloadResponse response = runYtDlp(url);
        if (response.isError()) return response;

        try {
            JSONArray formats = response.getFormats();
            String bestAudio = null;

            for (int i = 0; i < formats.length(); i++) {
                JSONObject f = formats.getJSONObject(i);

                if (f.has("url") && f.getString("acodec") != null && !f.getString("acodec").equals("none")) {
                    bestAudio = f.getString("url");
                    break;
                }
            }

            if (bestAudio != null) {
                response.setDownloadUrl(bestAudio);
                response.setType("audio");
            } else {
                response.setError(true);
                response.setTitle("No audio format found");
            }

        } catch (Exception e) {
            response.setError(true);
            response.setTitle("Music parsing error");
        }

        return response;
    }

    // ------------------------------------------------------------
    // yt-dlp PROCESS EXECUTION
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

            String output = reader.lines().reduce("", (a, b) -> a + b);

            int exit = process.waitFor();
            if (exit != 0 || output.isEmpty()) {
                response.setError(true);
                response.setTitle("yt-dlp failed to fetch details");
                return response;
            }

            JSONObject json = new JSONObject(output);

            // BASIC INFO
            String title = json.optString("title", "Unknown");
            String videoId = json.optString("id", null);
            String thumbnail = json.optString("thumbnail", null);

            response.setError(false);
            response.setTitle(title);
            response.setVideoId(videoId);
            response.setThumbnail(thumbnail);
            response.setOriginalUrl(videoUrl);

            // FORMATS (All quality + size + URLs)
            if (json.has("formats")) {
                response.setFormats(json.getJSONArray("formats"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setError(true);
            response.setTitle("yt-dlp execution error");
        }

        return response;
    }
}
