package com.sinisiro.StartBoot.start.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/test")
public class TotalTestController {

    @RequestMapping(value="/init",  method = RequestMethod.GET)
    public String index(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        // 현재 시간
        LocalTime now = LocalTime.now();
        // 포맷 정의하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
        // 포맷 적용하기
        String formatedNow = now.format(formatter);



        log.info("=[앱이 시작되었습니다]= "+ LocalDate.now() + formatedNow);
        return "test/init";
    }
    //앱 <-> 네이티브 테스트
    @RequestMapping(value="/native",  method = RequestMethod.GET)
    public String nativeTest(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "test/NativeTest";
    }


    @RequestMapping(value="/popup",  method = RequestMethod.GET)
    public String popup(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "test/popup";
    }


    //22.05.09 get방식 파라미터 구분안되는거 테스트
    //http://localhost:8090/test/testGetParam?num=GhYsMkN7udlmlgEGDfUYB7pc0jn/jPIIshuXVnfMPDI=&a=BZgEavEGNDNy8vXkTDdAlw==&pt=

    @RequestMapping(value="/testGetParam",  method = RequestMethod.GET)
    public @ResponseBody
    String testGetParam(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){
        System.out.println(req.getParameter("num"));
        System.out.println(req.getParameter("a"));
        return "testGetParam";
    }

    //22.03 redirect 테스트
    //A사이트->B사이트로 post rediret 하고싶을때, temp page로 넘기고 redirect 시키자.
    //참고 https://okky.kr/article/501050
    @RequestMapping(value="/redirectTest",  method = RequestMethod.GET)
    public String redirectTest(Model model, HttpServletRequest req) {
        Map<String, Object> map = new HashMap<String,Object>();

        model.addAttribute("param1","p3");
        model.addAttribute("param2","p2");


        //return "redirect:http://test.co.kr/test";


        return "test/redirectTest";

    }

    @RequestMapping(value="/redirectTarget",  method = RequestMethod.POST)
    public String redirectTarget(HttpServletRequest request) {
        String param1= request.getParameter("param1");
        String param2= request.getParameter("param2");

        System.out.println("###param1: " +param1);        //'p1' 출력
        System.out.println("###param2: " +param2);        //'p2' 출력

        return "start/home";

    }

//https://choonse.com/2022/01/14/605/
    @RequestMapping(value="/targetTest",  method = RequestMethod.GET)
    public String targetTest(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "test/targetTest";
    }

}
