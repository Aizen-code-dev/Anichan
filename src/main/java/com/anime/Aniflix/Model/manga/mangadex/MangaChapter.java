package com.anime.Aniflix.Model.manga.mangadex;

public class MangaChapter {
    private String id;
    private String title;
    private String chapterNumber;
    private String volumeNumber;
    private Integer pages;
    private String releaseDate;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getChapterNumber() { return chapterNumber; }
    public void setChapterNumber(String chapterNumber) { this.chapterNumber = chapterNumber; }

    public String getVolumeNumber() { return volumeNumber; }
    public void setVolumeNumber(String volumeNumber) { this.volumeNumber = volumeNumber; }

    public Integer getPages() { return pages; }
    public void setPages(Integer pages) { this.pages = pages; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
}