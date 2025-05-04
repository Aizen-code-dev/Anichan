package com.anime.Aniflix.Service;

import com.anime.Aniflix.Model.Trending.TrendingWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TrendingService {

    private final WebClient webClient;

    @Autowired
    public TrendingService(WebClient webClient) {
        this.webClient = webClient;
    }

//    public Mono<TrendingWrapper> getTrendingAnime() {
//        return this.webClient.get()
//                .uri("/meta/anilist/trending") // Update this to match the actual API endpoint for trending anime
//                .retrieve()
//                .bodyToMono(TrendingWrapper.class);
//    }
public Mono<TrendingWrapper> getTrendingAnime(int page, int perPage) {
    return webClient.get()
            .uri("/meta/anilist/trending?page={page}&perPage={perPage}", page, perPage)
            .retrieve()
            .bodyToMono(TrendingWrapper.class);
}

}
