package com.example.anderlechtsegazette.controllers;

import com.example.anderlechtsegazette.models.Article;
import com.example.anderlechtsegazette.services.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LocaleResolver localeResolver;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("articles", articleService.getLatestArticles());
        return "index";
    }

    @GetMapping("/new")
    public String newArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "new";
    }

    @PostMapping("/new")
    public String addNewArticle(@Valid @ModelAttribute("article") Article article, BindingResult result) {
        if (result.hasErrors()) {
            return "new";
        }
        articleService.saveArticle(article);
        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Article article = articleService.getArticleById(id);
        if (article == null) {
            return "redirect:/";
        }
        model.addAttribute("article", article);
        return "details";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/changeLanguage")
    public String changeLanguage(@RequestParam("lang") String lang, HttpServletRequest request, HttpServletResponse response) {
        Locale locale = new Locale(lang);
        localeResolver.setLocale(request, response, locale);


        String referer = request.getHeader("Referer");
        if (referer != null && !referer.isEmpty()) {
            return "redirect:" + referer;
        } else {
            return "redirect:/";
        }
    }
}
