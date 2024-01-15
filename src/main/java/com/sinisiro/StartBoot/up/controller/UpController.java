package com.sinisiro.StartBoot.up.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/up")
/**
 * [24.1.11 매체효율화 쿠쿠닥스 업로드 모듈 테스트
 */
public class UpController {

    @RequestMapping(value="/init",  method = RequestMethod.GET)
    public String index(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        // 현재 시간
        LocalTime now = LocalTime.now();
        // 포맷 정의하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
        // 포맷 적용하기
        String formatedNow = now.format(formatter);

        return "up/index";
    }

    @RequestMapping(value="/convert",  method = RequestMethod.GET)
    public String convert(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        // 현재 시간
        LocalTime now = LocalTime.now();
        // 포맷 정의하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
        // 포맷 적용하기
        String formatedNow = now.format(formatter);

        return "up/convert";
    }

    @RequestMapping(value="/api/upload",  method = RequestMethod.POST)
    public @ResponseBody String upload(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){
        System.out.println("test upload");


        return "success";
    }
}
