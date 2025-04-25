package com.anime.Aniflix.Service;

import com.anime.Aniflix.Model.aniflix.AniflixSearchResult;
import com.anime.Aniflix.Model.aniflix.AniflixSearchWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AniflixService {

    private final WebClient webClient;

    @Autowired  // Autowire WebClient here
    public AniflixService(WebClient webClient) {
        this.webClient = webClient;  // Constructor injection
    }

    public Mono<AniflixSearchWrapper> searchAnime(String query) {
        return webClient.get()
                .uri("/meta/anilist/{query}", query)
                .retrieve()
                .bodyToMono(AniflixSearchWrapper.class);
    }


}
