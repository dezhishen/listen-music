package com.dezhishen.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dezhishen
 */
@Controller
public class ThymeleafController {
    @RequestMapping("/player")
    public String hello() {
        return "index";
    }
}
