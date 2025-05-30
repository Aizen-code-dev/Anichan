//package com.anime.Aniflix.Controller;
//
//import com.anime.Aniflix.Model.Trending.TrendingAnime;
//import com.anime.Aniflix.Service.AniflixService;
//import com.anime.Aniflix.Service.TrendingService;
//import com.anime.Aniflix.Model.Trending.TrendingWrapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
//@Controller
//
//public class TrendingController {
//
//
//    private final TrendingService trendingService;
//
//    @Autowired  // Auto-wired dependency for service
//    public TrendingController(TrendingService trendingService) {
//        this.trendingService = trendingService;  // Constructor injection
//    }
//
//
//    @GetMapping("/")
//    public String showIndexPage(
//            @RequestParam(defaultValue = "1") int page, // Default page to 1
//            Model model) {
//
//        int perPage = 6; // You can adjust perPage as per your requirement
//        TrendingWrapper trendingWrapper = trendingService.getTrendingAnime(page, perPage).block();
//
//        // Pass the data to the view
//        model.addAttribute("trendingAnime", trendingWrapper.getResults());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("hasNext", trendingWrapper.isHasNextPage());
//
//
//        return "index"; // Ensure the template name is correct
//    }
//
//
//}
