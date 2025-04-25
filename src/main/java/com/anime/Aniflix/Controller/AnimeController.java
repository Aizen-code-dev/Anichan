package com.anime.Aniflix.Controller;

import com.anime.Aniflix.Model.Anime.AnimeDetail;
import com.anime.Aniflix.Service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping("/anime-details/{animeId}")
    public String getAnimeDetail(@PathVariable("animeId") String animeId, Model model) {
        AnimeDetail animeDetail = animeService.getAnimeDetails(animeId);
        model.addAttribute("animeDetail", animeDetail);
        return "anime-details";
    }
  


}
