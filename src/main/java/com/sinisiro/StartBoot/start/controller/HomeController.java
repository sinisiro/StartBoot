package com.sinisiro.StartBoot.start.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class HomeController {
//    private static final Logger logger = LogManager.getLogger(HomeController.class);
    private ObjectMapper objectMapper = new ObjectMapper();

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
    //ASSA를 루트나 .well-known 실물파일이 아닌 요청시 json으로 리턴하게끔  처리함.
    //jsonParser: string->json 변환
    @RequestMapping("/.well-known/apple-app-site-association")
    public @ResponseBody void assa(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException {

        //application/json으로 리턴
        res.setContentType("application/json");
//        res.setCharacterEncoding("utf-8");



        String assa = "{\n" +
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
                "}\n";

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(assa);


        res.getWriter().write(jsonObject.toJSONString());
    }
}
