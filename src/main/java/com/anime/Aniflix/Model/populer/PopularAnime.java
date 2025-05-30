package com.anime.Aniflix.Model.populer;

import java.util.List;
import java.util.Map;

public class PopularAnime {
    private String id;
    private int malId;
    private Title title;
    private String image;
    private String cover;
    private String description;
    private String status;
    private Integer rating;
    private Integer releaseDate;
    private List<String> genres;
    private Integer totalEpisodes;
    private Integer duration;
    private String type;

    // Nested title class
    public static class Title {
        private String romaji;
        private String english;
        private String nativeTitle;
        private String userPreferred;

        public String getRomaji() {
            return romaji;
        }

        public void setRomaji(String romaji) {
            this.romaji = romaji;
        }

        public String getEnglish() {
            return english;
        }

        public void setEnglish(String english) {
            this.english = english;
        }

        public String getNativeTitle() {
            return nativeTitle;
        }

        public void setNativeTitle(String nativeTitle) {
            this.nativeTitle = nativeTitle;
        }

        public String getUserPreferred() {
            return userPreferred;
        }

        public void setUserPreferred(String userPreferred) {
            this.userPreferred = userPreferred;
        }
    }

    // Getters & Setters for outer class
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMalId() {
        return malId;
    }

    public void setMalId(int malId) {
        this.malId = malId;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(Integer totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
