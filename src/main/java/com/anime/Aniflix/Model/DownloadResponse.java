package com.anime.Aniflix.Model;

import org.json.JSONArray;

public class DownloadResponse {

    private String title;
    private String videoId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    private String thumbnail;
    private String originalUrl;
    private String downloadUrl;
    private String type;
    private boolean error;

    private JSONArray formats; // NEW

    public JSONArray getFormats() {
        return formats;
    }

    public void setFormats(JSONArray formats) {
        this.formats = formats;
    }

    // all normal getters & setters...
}
