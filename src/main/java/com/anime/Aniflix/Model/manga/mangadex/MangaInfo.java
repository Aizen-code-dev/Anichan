package com.anime.Aniflix.Model.manga.mangadex;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

public class MangaInfo {

    private String id;
    private String title;

    // altTitles can be a list of strings or objects
    private List<Object> altTitles;

    @JsonDeserialize(using = DescriptionDeserializer.class)
    private String description;

    private List<String> genres;
    private List<String> themes;
    private String status;
    private Integer releaseDate;
    private String image;

    private List<MangaChapter> chapters;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Object> getAltTitles() {
        return altTitles;
    }

    public void setAltTitles(List<Object> altTitles) {
        this.altTitles = altTitles;
    }

    public String getDescription() {
        return description != null ? description : "";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<MangaChapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<MangaChapter> chapters) {
        this.chapters = chapters;
    }
}
