package com.khanhhoang.currencyconversion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConversionController {
    @GetMapping("/converter")
        public String index(){
        return "index";
    }

    @PostMapping("/usd")
    public String submit(@RequestParam String usd, Model model){
        float result = Float.parseFloat(usd)*24000;
        model.addAttribute("usd",usd);
        model.addAttribute("result",result);
        return "result";
    }
}
