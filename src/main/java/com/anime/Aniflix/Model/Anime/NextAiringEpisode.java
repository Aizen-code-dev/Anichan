package com.anime.Aniflix.Model.Anime;


public class NextAiringEpisode {
    private long airingTime;
    private long timeUntilAiring;
    private int episode;

    public long getAiringTime() {
        return airingTime;
    }

    public void setAiringTime(long airingTime) {
        this.airingTime = airingTime;
    }

    public long getTimeUntilAiring() {
        return timeUntilAiring;
    }

    public void setTimeUntilAiring(long timeUntilAiring) {
        this.timeUntilAiring = timeUntilAiring;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }
}
