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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
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

        // Get anime details for title
        AnimeDetail animeDetail = animeService.getAnimeDetails(animeId);

        // Handle missing stream
        if (episodeStream == null || episodeStream.getSources() == null || episodeStream.getSources().isEmpty()) {
            try {
                String titleToEncode = animeDetail.getTitle().getOrDefault(
                        "english",
                        animeDetail.getTitle().values().stream().findFirst().orElse("Anime")
                );
                String encodedTitle = URLEncoder.encode(titleToEncode, StandardCharsets.UTF_8.toString());

                // Redirect directly to HiAnime safely
                return "redirect:https://hianime.to/search?keyword=" + encodedTitle;

            } catch (Exception e) {
                return "redirect:https://hianime.to";
            }
        }

        // Normal playback
        model.addAttribute("episodeStream", episodeStream);
        model.addAttribute("animeDetail", animeDetail);
        model.addAttribute("selectedEpisodeId", episodeId);
        return "watchEpisode";
    }
}
