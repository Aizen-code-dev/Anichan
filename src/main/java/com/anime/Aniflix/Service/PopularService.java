package com.anime.Aniflix.Service;

import com.anime.Aniflix.Model.Trending.TrendingWrapper;
import com.anime.Aniflix.Model.populer.PopularAnimeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PopularService {
    private final WebClient webClient;

    @Autowired
    public PopularService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<PopularAnimeWrapper> getPopularAnime(int page, int perPage) {
        return webClient.get()
                .uri("/meta/anilist/popular?page={page}&perPage={perPage}", page, perPage)
                .retrieve()
                .bodyToMono(PopularAnimeWrapper.class);
    }

}
