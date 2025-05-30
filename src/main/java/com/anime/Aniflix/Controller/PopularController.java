//package com.anime.Aniflix.Controller;
//
//
//import com.anime.Aniflix.Model.populer.PopularAnimeWrapper;
//import com.anime.Aniflix.Service.PopularService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class PopularController {
//
//    private final PopularService popularService;
//
//    @Autowired
//    public PopularController(PopularService popularService) {
//        this.popularService = popularService;
//    }
//
//    @GetMapping("/")
//    public String showPopularAnimePage(
//            @RequestParam(defaultValue = "1") int page,
//            Model model) {
//
//        int perPage = 6;
//        PopularAnimeWrapper popularWrapper = popularService.getPopularAnime(page, perPage).block();
//
//        model.addAttribute("popularAnime", popularWrapper.getResults());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("hasNext", popularWrapper.isHasNextPage());
//
//        return "index";// Make sure popular.html exists in your templates
//    }
//}
