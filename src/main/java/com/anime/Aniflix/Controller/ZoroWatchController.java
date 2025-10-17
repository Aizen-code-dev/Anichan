package com.anime.Aniflix.Controller;

import com.anime.Aniflix.Model.zorowatch.ZoroWatchResponse;
import com.anime.Aniflix.Service.ZoroWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zoro")
public class ZoroWatchController {

    private final ZoroWatchService zoroWatchService;

    @Autowired
    public ZoroWatchController(ZoroWatchService zoroWatchService) {
        this.zoroWatchService = zoroWatchService;
    }
    // ✅ Page route (renders Thymeleaf)
    @GetMapping("/watch")
    public String showWatchPage(@RequestParam("episodeId") String episodeId, Model model) {
        model.addAttribute("episodeId", episodeId);
        return "watchEpisode";
    }

    // ✅ API endpoint (returns JSON)
    @GetMapping("/api/watch")
    @ResponseBody
    public ZoroWatchResponse getEpisodeStream(@RequestParam("episodeId") String episodeId,
                                              @RequestParam(defaultValue = "vidcloud") String server) {
        return zoroWatchService.getEpisodeStream(episodeId, server);
    }
}



