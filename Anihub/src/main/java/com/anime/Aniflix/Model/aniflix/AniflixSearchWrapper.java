package com.anime.Aniflix.Model.aniflix;

public class AniflixSearchWrapper {
    private int currentPage;
    private boolean hasNextPage;
    private AniflixSearchResult[] results;  // Array of AniflixSearchResult

    // Constructor
    public AniflixSearchWrapper(int currentPage, boolean hasNextPage, AniflixSearchResult[] results) {
        this.currentPage = currentPage;
        this.hasNextPage = hasNextPage;
        this.results = results;
    }

    // Getter and Setter for currentPage
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    // Getter and Setter for hasNextPage
    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    // Getter and Setter for results
    public AniflixSearchResult[] getResults() {
        return results;
    }

    public void setResults(AniflixSearchResult[] results) {
        this.results = results;
    }
}
