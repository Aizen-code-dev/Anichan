package com.anime.Aniflix.Controller;

import com.anime.Aniflix.Model.manga.mangadex.Manga;
import com.anime.Aniflix.Model.manga.mangadex.MangaInfo;
import com.anime.Aniflix.Service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    // Landing Page (Your Provided HTML)
    @GetMapping
    public String mangaHome() {
        return "manga"; // landing page template below
    }

    // Search Manga
    @GetMapping("/search")
    public String searchManga(@RequestParam("query") String query, Model model) {
        List<Manga> results = mangaService.searchManga(query);
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "search"; // result page
    }

    // Manga Info Page
    @GetMapping("/info/{id}")
    public String getMangaInfo(@PathVariable String id, Model model) {
        MangaInfo info = mangaService.getMangaInfo(id);
        model.addAttribute("manga", info);

        return "info";
    }

    // Read Manga Chapter Page
    @GetMapping("/read/{chapterId}")
    public String readMangaChapter(@PathVariable String chapterId, Model model) {
        List<String> pages = mangaService.getChapterPages(chapterId);
        model.addAttribute("pages", pages);
        return "read";
    }
}
