package com.anime.Aniflix.Controller;

import com.anime.Aniflix.Model.Anime.AnimeDetail;
import com.anime.Aniflix.Model.Anime.Episode;
import com.anime.Aniflix.Service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

public class AnimeFilterController {

    private final AnimeService animeService;

    @Autowired
    public AnimeFilterController(AnimeService animeService) {
        this.animeService = animeService;
    }
    @GetMapping("/anime/details/{animeId}/episodes")
    public String filterEpisodes(@PathVariable("animeId") String animeId,
                                 @RequestParam("range") String range,
                                 Model model) {
        AnimeDetail animeDetail = animeService.getAnimeDetails(animeId);

        // Split range like "1-100"
        String[] bounds = range.split("-");
        int start = Integer.parseInt(bounds[0]);
        int end = Integer.parseInt(bounds[1]);

        // Filter episodes
        List<Episode> filtered = animeDetail.getEpisodes().stream()
                .filter(ep -> ep.getNumber() >= start && ep.getNumber() <= end)
                .collect(Collectors.toList());

        model.addAttribute("animeDetail", animeDetail);
        model.addAttribute("episodes", filtered);
        return "anime-detail";
    }


}
