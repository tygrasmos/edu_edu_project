package ru.mos.tygras.eve.planned_assistance.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/main")
    public String pageMain(){
        return "mainPage";
    }

    @GetMapping("/")
    public String indexPage() {
        return "indexPage";
    }
}

