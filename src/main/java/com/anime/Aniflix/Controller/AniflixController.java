package com.anime.Aniflix.Controller;

import com.anime.Aniflix.Model.aniflix.AniflixSearchResult;
import com.anime.Aniflix.Model.aniflix.AniflixSearchWrapper;
import com.anime.Aniflix.Service.AniflixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/api/anime")
public class AniflixController {

    private final AniflixService aniflixService;

    @Autowired  // Auto-wired dependency for service
    public AniflixController(AniflixService aniflixService) {
        this.aniflixService = aniflixService;  // Constructor injection
    }



    // API endpoint for searching anime, returns AniflixSearchWrapper
    @GetMapping("/search/{query}")
    public Mono<AniflixSearchWrapper> search(@PathVariable String query) {
        return aniflixService.searchAnime(query);  // Return the wrapper containing results
    }

    // View for searching and displaying results in a Thymeleaf template
    @GetMapping("/search/view")
    public String searchView(@RequestParam("q") String query, Model model) {
        // Retrieve the AniflixSearchWrapper, not just the array of results
        AniflixSearchWrapper wrapper = aniflixService.searchAnime(query).block();

        // Add both the query and the results (extracted from the wrapper) to the model
        model.addAttribute("query", query);
        model.addAttribute("results", wrapper.getResults());  // Use the results from the wrapper
        return "search-results";  // This will render the view with results
    }
}
