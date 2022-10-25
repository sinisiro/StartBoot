package com.sinisiro.StartBoot.start.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class HomeController {
//    private static final Logger logger = LogManager.getLogger(HomeController.class);


    // 기본 페이지 설정되어어 있으면 이쪽을 index.html에 우선한다.
    @GetMapping("/")
    public String home() {
//        log.info("111");

        return "start/home";
    }

    // 기본 페이지 설정되어어 있으면 이쪽을 index.html에 우선한다.
    @PostMapping("/")
    public String home2() {
        log.info("111");

        return "start/home";
    }

    //https://ohgyun.com/707 참고함.
    @RequestMapping("/.well-known/apple-app-site-association")
    public @ResponseBody String assa(HttpServletRequest req, HttpServletResponse res) {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        return "{\n" +
                "    \"applinks\": {\n" +
                "        \"details\": [\n" +
                "            {\n" +
                "                \"appID\": \"2AG87FN595.com.mdbins.webViewSBT\",\n" +
                "                \"paths\": [\n" +
                "                    \"*\"\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }
}
