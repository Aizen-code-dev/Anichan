package com.anime.Aniflix.Controller;


import com.anime.Aniflix.Model.Trending.TrendingWrapper;
import com.anime.Aniflix.Model.populer.PopularAnimeWrapper;
import com.anime.Aniflix.Model.DownloadResponse;
import com.anime.Aniflix.Service.TrendingService;
import com.anime.Aniflix.Service.PopularService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.anime.Aniflix.Service.DownloaderService;

@Controller
public class HomeController {

    private final TrendingService trendingService;
    private final PopularService popularService;
    private final DownloaderService downloaderService;


    @Autowired
    public HomeController(TrendingService trendingService, PopularService popularService , DownloaderService downloaderService) {
        this.trendingService = trendingService;
        this.popularService = popularService;
        this.downloaderService=downloaderService;
    }

    @GetMapping("/")
    public String showIndexPage(
            @RequestParam(defaultValue = "1") int page,
            Model model) {

        int perPage = 12;

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
//

    @GetMapping("/Recent")
    public String Recent(){return "index";}
    // Popular Page with fetching logic
    @GetMapping("/popular")
    public String popular(@RequestParam(defaultValue = "1") int page, Model model) {
        int perPage = 12;
        PopularAnimeWrapper popularWrapper = popularService.getPopularAnime(page, perPage).block();
        model.addAttribute("popularAnime", popularWrapper.getResults());
        model.addAttribute("currentPage", page);
        model.addAttribute("hasNextPopular", popularWrapper.isHasNextPage());
        return "popular";
    }

    // Top Airing Page with trending anime fetching
    @GetMapping("/top-airing")
    public String topairing(@RequestParam(defaultValue = "1") int page, Model model) {
        int perPage = 12;
        TrendingWrapper trendingWrapper = trendingService.getTrendingAnime(page, perPage).block();
        model.addAttribute("topAiringAnime", trendingWrapper.getResults()); // match the template
        model.addAttribute("currentPage", page);
        model.addAttribute("hasNextTop", trendingWrapper.isHasNextPage());
        return "topairing";
    }


    @GetMapping("/music-downloader")
public String showMusicDownloader() {
    return "music-downloader";
}

    @PostMapping("/music-downloader")
    public String downloadMusic(@RequestParam("url") String url, Model model) {
        DownloadResponse result = downloaderService.downloadMusic(url);
        model.addAttribute("result", result);
        return "music-downloader";
    }


    @GetMapping("/video-downloader")
    public String showVideoDownloader() {
        return "video-downloader";
    }

    @PostMapping("/video-downloader")
    public String downloadVideo(@RequestParam("url") String url, Model model) {
        DownloadResponse result = downloaderService.downloadVideo(url);
        model.addAttribute("result", result);
        return "video-downloader";
    }

    @GetMapping("/about")
    public String about() { return "about"; }
    @GetMapping("/privacy")
    public String privacy() { return "privacy"; }
    @GetMapping("/terms") public String terms() { return "terms"; }
    @GetMapping("/disclaimer") public String disclaimer() { return "disclaimer"; }
    @GetMapping("/contact") public String contact() { return "contact"; }
    @GetMapping("/dmca")
    public String dmca() {
        return "dmca";
    }

}
