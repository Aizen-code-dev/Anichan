package com.anime.Aniflix.Service;

import com.anime.Aniflix.Model.Anime.AnimeDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AnimeService {

    private final RestTemplate restTemplate;

    @Autowired
    public AnimeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Fetch Anime Details
    public AnimeDetail getAnimeDetails(String animeId) {
        String url = "https://anichanapi.onrender.com/meta/anilist/info/" + animeId + "?provider=zoro";
        return restTemplate.getForObject(url, AnimeDetail.class);
    }


}
