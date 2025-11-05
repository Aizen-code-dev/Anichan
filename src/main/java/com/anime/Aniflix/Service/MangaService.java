package com.anime.Aniflix.Service;

import com.anime.Aniflix.Model.manga.mangadex.Manga;
import com.anime.Aniflix.Model.manga.mangadex.MangaInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;

import java.util.*;

@Service
public class MangaService {

    private final String BASE_URL = "https://anichanapi.onrender.com/manga/mangadex";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<Manga> searchManga(String query) {
        String url = BASE_URL + "/" + query;
        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

            List<Manga> mangas = new ArrayList<>();
            if (results != null) {
                for (Map<String, Object> item : results) {
                    Manga manga = new Manga();
                    manga.setId((String) item.get("id"));
                    manga.setTitle((String) item.get("title"));
                    manga.setImage((String) item.get("image"));
                    mangas.add(manga);
                }
            }
            return mangas;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public MangaInfo getMangaInfo(String id) {
        String url = BASE_URL + "/info/" + id;
        try {
            return restTemplate.getForObject(url, MangaInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getChapterPages(String chapterId) {
        String url = BASE_URL + "/read/" + chapterId;
        try {
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                    url,
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
            List<String> pages = new ArrayList<>();
            if (response.getBody() != null) {
                for (Map<String, Object> item : response.getBody()) {
                    pages.add((String) item.get("img"));
                }
            }
            return pages;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
