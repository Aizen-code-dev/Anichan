package com.anime.Aniflix.Model.zorowatch;

public class ZoroHeaders {
    private String Referer;
    private String watchsb;

    public String getReferer() {
        return Referer;
    }

    public void setReferer(String referer) {
        Referer = referer;
    }

    public String getWatchsb() {
        return watchsb;
    }

    public void setWatchsb(String watchsb) {
        this.watchsb = watchsb;
    }

    public String getUserAgent() {
        return UserAgent;
    }

    public void setUserAgent(String userAgent) {
        UserAgent = userAgent;
    }

    private String UserAgent;

    // Getters and Setters
}
