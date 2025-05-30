package com.anime.Aniflix.Controller;

import com.anime.Aniflix.Model.Trending.TrendingWrapper;
import com.anime.Aniflix.Model.populer.PopularAnimeWrapper;
import com.anime.Aniflix.Service.TrendingService;
import com.anime.Aniflix.Service.PopularService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final TrendingService trendingService;
    private final PopularService popularService;

    @Autowired
    public HomeController(TrendingService trendingService, PopularService popularService) {
        this.trendingService = trendingService;
        this.popularService = popularService;
    }

    @GetMapping("/")
    public String showIndexPage(
            @RequestParam(defaultValue = "1") int page,
            Model model) {

        int perPage = 6;

        // Fetch Trending Anime
        TrendingWrapper trendingWrapper = trendingService.getTrendingAnime(page, perPage).block();
        model.addAttribute("trendingAnime", trendingWrapper.getResults());

        // Fetch Popular Anime
        PopularAnimeWrapper popularWrapper = popularService.getPopularAnime(page, perPage).block();
        model.addAttribute("popularAnime", popularWrapper.getResults());

        model.addAttribute("currentPage", page);
        model.addAttribute("hasNextTrending", trendingWrapper.isHasNextPage());
        model.addAttribute("hasNextPopular", popularWrapper.isHasNextPage());

        return "index";
    }
}
