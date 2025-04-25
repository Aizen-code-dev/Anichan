package com.anime.Aniflix.Controller;

import com.anime.Aniflix.Model.zorowatch.ZoroWatchResponse;
import com.anime.Aniflix.Service.ZoroWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/watch")
public class WatchEpisodeController {

    private final ZoroWatchService zoroWatchService;

    @Autowired
    public WatchEpisodeController(ZoroWatchService zoroWatchService) {
        this.zoroWatchService = zoroWatchService;
    }

    @GetMapping("/{episodeId}")
    public String watchEpisode(@PathVariable String episodeId, Model model) {
        ZoroWatchResponse episodeStream = zoroWatchService.getEpisodeStream(episodeId, "vidcloud");
        model.addAttribute("episodeStream", episodeStream);
        return "watchEpisode"; // This will render watchEpisode.html template
    }

}
