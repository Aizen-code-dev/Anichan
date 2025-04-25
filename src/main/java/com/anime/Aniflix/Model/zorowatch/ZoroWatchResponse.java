package com.anime.Aniflix.Model.zorowatch;

import java.util.List;

public class ZoroWatchResponse {
    private ZoroHeaders headers;

    public ZoroHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(ZoroHeaders headers) {
        this.headers = headers;
    }

    public ZoroIntroOutro getIntro() {
        return intro;
    }

    public void setIntro(ZoroIntroOutro intro) {
        this.intro = intro;
    }

    public ZoroIntroOutro getOutro() {
        return outro;
    }

    public void setOutro(ZoroIntroOutro outro) {
        this.outro = outro;
    }

    public List<ZoroSource> getSources() {
        return sources;
    }

    public void setSources(List<ZoroSource> sources) {
        this.sources = sources;
    }

    public List<ZoroSubtitle> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<ZoroSubtitle> subtitles) {
        this.subtitles = subtitles;
    }

    private ZoroIntroOutro intro;
    private ZoroIntroOutro outro;
    private List<ZoroSource> sources;
    private List<ZoroSubtitle> subtitles;

    // Getters and Setters
}
