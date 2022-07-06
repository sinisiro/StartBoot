package com.sinisiro.StartBoot.start.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
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



        log.info("=[앱이 시작되었습니다.]= ["+ LocalDate.now() +" " + formatedNow +"]");
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


    /**
     * 파일업로드 샘플
     테이블 : fileInfo
     */

    @RequestMapping(value="/fileUploadForm",  method = RequestMethod.GET)
    public String fileuploadForm(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "test/fileUploadForm";
    }

    @RequestMapping(value="/fileUpload",  method = RequestMethod.POST)
    public String uploadSingle(@RequestParam("files") MultipartFile file, Model model) throws Exception {
//        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
//        String basePath = rootPath + "/";
//        String filePath = basePath + "/" + file.getOriginalFilename();
//        File dest = new File(filePath);
//
//        log.info("rootPath:"+rootPath);
//        log.info("basePath:"+basePath);
//        log.info("filePath:"+filePath);
//        log.info(System.getProperty("java.io.tmpdir"));
//
//
//        file.transferTo(dest); // 파일 업로드 작업 수행

        //2번째 방법
        // DB 저장
        //1. 디비 저장
//        FileInfo fileInfo = new FileInfo();
//        fileInfo.setFileNm(file.getOriginalFilename());

        //2. 빌더 패턴으로 저장
//        FileInfo fileInfo = FileInfo.builder()
//                .fileNm(file.getOriginalFilename())
//                .build();
//
//        totalTestRepository.save(fileInfo);

        System.out.println("file name: " + file.getName());
        System.out.println("file original name: " + file.getOriginalFilename());

        String UPLOADED_FOLDER = "./upload/";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        log.info(path.toString());
        Files.write(path, bytes);

        model.addAttribute("fileName", file.getOriginalFilename());


        return "test/fileUploadForm";
    }


    @RequestMapping(value="/fileUploadJson",  method = RequestMethod.POST)
    public @ResponseBody String uploadJson(@RequestParam("files") MultipartFile file,
                                           @RequestParam("senderName") String senderName,
                                           Model model) throws Exception {
        log.info(senderName);
//        String UPLOADED_FOLDER = "./upload/";
//        byte[] bytes = file.getBytes();
//        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//        log.info(path.toString());
//        Files.write(path, bytes);
//
//        model.addAttribute("fileName", file.getOriginalFilename());


        return "success";
    }

    //MultipartHttpServletRequest 로 파일 받기.
    @RequestMapping(value="/fileUploadJson2",  method = RequestMethod.POST)
    public @ResponseBody String uploadJson2(MultipartHttpServletRequest req,  HttpServletResponse res,
                                            Model model) throws Exception {
        log.info("request 로 전달받기");
        System.out.println(req.getParameter("senderName"));
        Iterator<String> iterator = req.getFileNames();
        MultipartFile multipartFile = null;

        while(iterator.hasNext()){
            multipartFile = req.getFile(iterator.next());
            System.out.println(multipartFile.getOriginalFilename());
        }

//        String UPLOADED_FOLDER = "./upload/";
//        byte[] bytes = file.getBytes();
//        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//        log.info(path.toString());
//        Files.write(path, bytes);
//
//        model.addAttribute("fileName", file.getOriginalFilename());


        return "success";
    }




}
