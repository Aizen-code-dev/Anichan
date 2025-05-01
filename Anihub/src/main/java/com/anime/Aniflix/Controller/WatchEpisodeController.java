package com.anime.Aniflix.Controller;

import com.anime.Aniflix.Model.Anime.AnimeDetail;
import com.anime.Aniflix.Model.zorowatch.ZoroWatchResponse;
import com.anime.Aniflix.Service.AnimeService;
import com.anime.Aniflix.Service.ZoroWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/watch")
//public class WatchEpisodeController {
//
//    private final ZoroWatchService zoroWatchService;
//
//    @Autowired
//    public WatchEpisodeController(ZoroWatchService zoroWatchService) {
//        this.zoroWatchService = zoroWatchService;
//    }
//
//    @GetMapping("/{episodeId}")
//    public String watchEpisode(@PathVariable String episodeId, Model model) {
//        ZoroWatchResponse episodeStream = zoroWatchService.getEpisodeStream(episodeId, "vidcloud");
//        model.addAttribute("episodeStream", episodeStream);
//        return "watchEpisode"; // This will render watchEpisode.html template
//        //newly added
//
//    }

@RequestMapping("/watch")
public class WatchEpisodeController {

    private final ZoroWatchService zoroWatchService;
    private final AnimeService animeService;

    @Autowired
    public WatchEpisodeController(ZoroWatchService zoroWatchService, AnimeService animeService) {
        this.zoroWatchService = zoroWatchService;
        this.animeService = animeService;
    }

    @GetMapping("/{animeId}/{episodeId}")
    public String watchEpisode(
            @PathVariable String animeId,
            @PathVariable String episodeId,
            Model model
    ) {
        // Get stream info
        ZoroWatchResponse episodeStream = zoroWatchService.getEpisodeStream(episodeId, "vidcloud");

        // Get anime details including episodes list
        AnimeDetail animeDetail = animeService.getAnimeDetails(animeId);

        // Send to template
        model.addAttribute("episodeStream", episodeStream);
        model.addAttribute("animeDetail", animeDetail);
        model.addAttribute("selectedEpisodeId", episodeId);

        return "watchEpisode"; // Render watchEpisode.html
    }
}



