package com.anime.Aniflix.Service;

import com.anime.Aniflix.Model.zorowatch.ZoroWatchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ZoroWatchService {

    private final RestTemplate restTemplate;

    @Autowired
    public ZoroWatchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ZoroWatchResponse getEpisodeStream(String episodeId, String server) {
        // URL encode episodeId to ensure special characters are handled properly
        String url = UriComponentsBuilder
                .fromHttpUrl("https://anichanapi.onrender.com/anime/zoro/watch")
                .queryParam("episodeId", episodeId)
                .queryParam("server", server)
                .toUriString();

        // Make the API call and return the response
        return restTemplate.getForObject(url, ZoroWatchResponse.class);
    }
}
