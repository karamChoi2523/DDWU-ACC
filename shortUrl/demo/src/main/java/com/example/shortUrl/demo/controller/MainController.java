package com.example.shortUrl.demo.controller;

import com.example.shortUrl.demo.domain.Url;
import com.example.shortUrl.demo.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UrlService urlService;

    @GetMapping("/")
    public String index() {
        return "thyme/index";
    }

    @PostMapping("/saveUrl")
    public ModelAndView insertUrl(@RequestParam String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("thyme/index");

        Optional<Url> optionalUrl = urlService.findByOriginLink(url);
        if(optionalUrl.isEmpty()){
            try {
                Url originUrl = urlService.insertUrl(url);
                modelAndView.addObject("saveUrlToDB", "DB에 저장 성공!");
            } catch (Exception e){
                modelAndView.addObject("saveUrlToDB", "DB에 저장 실패!");
            }
        }else
            modelAndView.addObject("saveUrlToDB", "DB에 이미 url이 존재합니다!");

        return modelAndView;
    }

    @PostMapping("/findUrl")
    public ModelAndView findUrl(@RequestParam String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("thyme/index");

        Optional<Url> optionalUrl = urlService.findByOriginLink(url);
        if(optionalUrl.isEmpty()){
            modelAndView.addObject("foundUrl", "알 수 없는 URL입니다.");
            return modelAndView;
        }

        int idx = url.lastIndexOf('/');

        String changedUrl=url.substring(0, idx+1);
        changedUrl += String.valueOf(optionalUrl.get().getId());

        modelAndView.addObject("foundUrl", changedUrl);
        modelAndView.addObject("originUrl", url);


        return modelAndView;
    }
}
