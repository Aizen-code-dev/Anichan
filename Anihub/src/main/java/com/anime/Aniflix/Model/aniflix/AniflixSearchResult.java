package com.anime.Aniflix.Model.aniflix;


public class AniflixSearchResult {
    private String id;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    private int malId;
    private Title title;
    private String status;
    private String image;
    private String cover;
    private int popularity;
    private String description;
    private int rating;
    private String[] genres;


    public static class Title {
        private String romaji;
        private String english;
        private String nativeName; // 'native' is a Java keyword, so rename safely

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

        public String getNativeName() {
            return nativeName;
        }

        public String getUserPreferred() {
            return userPreferred;
        }

        public void setUserPreferred(String userPreferred) {
            this.userPreferred = userPreferred;
        }

        private String userPreferred;

        // If the API still uses the word "native", use @JsonProperty:
        @com.fasterxml.jackson.annotation.JsonProperty("native")
        public void setNativeName(String nativeName) {
            this.nativeName = nativeName;
        }
    }
}
