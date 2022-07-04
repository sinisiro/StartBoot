package com.sinisiro.StartBoot.webview.controller;

import com.sinisiro.StartBoot.webview.domain.CaptureLog;
import com.sinisiro.StartBoot.webview.service.NativeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/native")
public class NativeController {

    private final NativeService nativeService;

    //앱 <-> 네이티브 테스트
    @RequestMapping(value="")
    public String home(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "native/home";
    }

    //앱 <-> 네이티브 테스트
    @RequestMapping(value="/init")
    public String nativeTest(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "native/NativeTest";
    }

    //DB 접근 테스트.
    @RequestMapping(value="/dbAccess")
    public @ResponseBody List<Map<String, Object>> getMember() {
        log.info("dbAccess");
        log.info(nativeService.getMember().toString());

        return nativeService.getMember();
    }

    //DB 접근 테스트.
    @RequestMapping(value="/insertCaptureLog", method = RequestMethod.POST)
    public @ResponseBody Map insertCaptureLog(
            @RequestParam("logDate") String logDate
            ,@RequestParam("logTime") String logTime
            ,@RequestParam("screenId") String screenId) {
        log.info(logDate);
        log.info(logTime);
        log.info(screenId);


        Map<String, Object> resultData = new HashMap<>();
        CaptureLog captureLog = new CaptureLog();

        captureLog.setLogDate("20220418");
        captureLog.setLogTime("12000101");
        captureLog.setScreenId("TestWebView");

        nativeService.insertCaptureLog(captureLog);

        resultData.put("result","로그 적재 완료");
        resultData.put("result_cd","0");

        return resultData;

    }


    //앱 <-> 네이티브 테스트
    @RequestMapping(value="/AppCall")
    public String AppCall(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "native/AppCall";
    }

}
