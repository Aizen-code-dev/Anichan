package com.anime.Aniflix.Model.populer;

import java.util.List;

public class PopularAnimeWrapper {
    private int currentPage;
    private boolean hasNextPage;
    private List<PopularAnime> results;

    // Getters and Setters
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<PopularAnime> getResults() {
        return results;
    }

    public void setResults(List<PopularAnime> results) {
        this.results = results;
    }
}
