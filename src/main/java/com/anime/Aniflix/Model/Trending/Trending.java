package com.anime.Aniflix.Model.Trending;

import java.util.List;

public class Trending {
    private int currentPage;
    private boolean hasNextPage;
    private List<TrendingAnime> results;

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

    public List<TrendingAnime> getResults() {
        return results;
    }

    public void setResults(List<TrendingAnime> results) {
        this.results = results;
    }
}
