package com.example.StartBoot.start.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
//    private static final Logger logger = LogManager.getLogger(HomeController.class);


    // 기본 페이지 설정되어어 있으면 이쪽을 index.html에 우선한다.
    @GetMapping("/")
    public String home() {
        log.info("111");

        return "start/home";
    }
}
