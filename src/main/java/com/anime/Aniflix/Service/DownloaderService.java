package com.anime.Aniflix.Service;

import com.anime.Aniflix.Model.DownloadResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DownloaderService {

    private static final String API_KEY = "f549f42bd7msh061147b5591999dp1173f8jsn17aae55254a4";

    // ✅ MUSIC
    public DownloadResponse downloadMusic(String url) {
        DownloadResponse response = extractYouTubeInfo(url);
        if (response.isError()) return response;

        String videoId = response.getVideoId();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://youtube-mp36.p.rapidapi.com/dl?id=" + videoId))
                    .header("x-rapidapi-key", API_KEY)
                    .header("x-rapidapi-host", "youtube-mp36.p.rapidapi.com")
                    .GET()
                    .build();

            HttpResponse<String> httpResponse =
                    HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(httpResponse.body());

            if (json.has("link")) {
                response.setDownloadUrl(json.getString("link"));
                response.setType("mp3");
            } else {
                response.setError(true);
                response.setTitle("Failed to get MP3 download link");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setError(true);
            response.setTitle("Music API Failed");
        }

        return response;
    }

    // ✅ VIDEO FIX — DISPLAY INFO FIRST ✅
    public DownloadResponse downloadVideo(String url) {
        DownloadResponse response = extractYouTubeInfo(url);
        if (response.isError()) return response;

        String videoId = response.getVideoId();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://ytstream-download-youtube-videos.p.rapidapi.com/dl?id=" + videoId))
                    .header("x-rapidapi-key", API_KEY)
                    .header("x-rapidapi-host", "ytstream-download-youtube-videos.p.rapidapi.com")
                    .GET()
                    .build();

            HttpResponse<String> httpResponse =
                    HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(httpResponse.body());

            if (json.has("formats")) {
                JSONArray formats = json.getJSONArray("formats");
                String bestUrl = null;

                for (int i = 0; i < formats.length(); i++) {
                    JSONObject format = formats.getJSONObject(i);

                    if (format.has("url") &&
                            format.has("type") &&
                            format.getString("type").contains("mp4")) {
                        bestUrl = format.getString("url");
                        break; // ✅ first best match
                    }
                }

                if (bestUrl != null) {
                    response.setDownloadUrl(bestUrl);
                    response.setType("mp4");
                } else {
                    response.setError(true);
                    response.setTitle("No MP4 format found");
                }

            } else {
                response.setError(true);
                response.setTitle("Invalid video API response");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setError(true);
            response.setTitle("Video API Failed");
        }

        return response;
    }

    // ✅ Extract Info (no change)
    private DownloadResponse extractYouTubeInfo(String urlString) {
        DownloadResponse response = new DownloadResponse();

        try {
            String videoId = extractYouTubeId(urlString);
            if (videoId == null) {
                response.setError(true);
                response.setTitle("Invalid YouTube URL");
                return response;
            }

            URL url = new URL("https://www.youtube.com/watch?v=" + videoId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder html = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) html.append(line);
            reader.close();

            Matcher titleMatcher = Pattern.compile("<title>(.*?)</title>").matcher(html.toString());
            String title = titleMatcher.find() ? titleMatcher.group(1).replace("- YouTube", "").trim() : "Unknown Title";

            response.setTitle(title);
            response.setVideoId(videoId);
            response.setThumbnail("https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg");
            response.setOriginalUrl(urlString);
            response.setError(false);

        } catch (Exception e) {
            e.printStackTrace();
            response.setError(true);
            response.setTitle("Failed to fetch video info");
        }

        return response;
    }

    private String extractYouTubeId(String url) {
        String[] patterns = {
                "v=([0-9A-Za-z_-]{11})",
                "be/([0-9A-Za-z_-]{11})",
                "shorts/([0-9A-Za-z_-]{11})",
                "embed/([0-9A-Za-z_-]{11})"
        };

        for (String p : patterns) {
            Matcher m = Pattern.compile(p).matcher(url);
            if (m.find()) return m.group(1);
        }
        return null;
    }
}
