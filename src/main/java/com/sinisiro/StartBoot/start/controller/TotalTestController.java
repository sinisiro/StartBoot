package com.sinisiro.StartBoot.start.controller;

import com.sinisiro.StartBoot.util.AES256Cipher;
import com.sinisiro.StartBoot.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
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

    //MultipartHttpServletRequest 로 파일 받기.
    @RequestMapping(value="/fileUploadJson3",  method = RequestMethod.POST)
    public @ResponseBody Map uploadJson3(HttpServletRequest req,  HttpServletResponse res,
                                            Model model) throws Exception {
        log.info("img_b64Data 로 전달받기");

        Map<String, Object> resultData = new HashMap<>();

//        log.info(req.getParameter("img_b64Data"));
        String img_b64Data = req.getParameter("img_b64Data");
        String seq = req.getParameter("seq");

//        String img_b64Data = URLDecoder.decode(img_b64DataEncode, "UTF-8");

//        log.info(img_b64Data);


        String fileExtension = ImageUtil.getImageType(img_b64Data);

        String UPLOADED_FOLDER = "./upload/";
        // 현재 시간
        LocalTime now = LocalTime.now();
        // 포맷 정의하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String formatedNow = now.format(formatter);

        ImageUtil.base64StringToJpeg(img_b64Data,UPLOADED_FOLDER+formatedNow+seq+"."+fileExtension);

        Thread.sleep(1000);     //로딩바 딜레이주기.

        resultData.put("method","fileUploadJson3");
        resultData.put("seq",seq);
        resultData.put("result","Y");
        resultData.put("result_cd","0000");

        return resultData; 


//        String UPLOADED_FOLDER = "./upload/";
//        byte[] bytes = file.getBytes();
//        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//        log.info(path.toString());
//        Files.write(path, bytes);
//
//        model.addAttribute("fileName", file.getOriginalFilename());

    }



    //AES256 암호화
    @RequestMapping(value="/getAES256",  method = RequestMethod.POST)
    public @ResponseBody Map getAES256(@RequestBody Map requestMap) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        Map<String, Object> resultData = new HashMap<>();
        String reqVal = (String) requestMap.get("val");
        String gubun = (String) requestMap.get("gubun");

        AES256Cipher a256 = AES256Cipher.getInstance();
        String result;
        if(gubun.equals("enc")){
            result = AES256Cipher.AES_Encode(reqVal);
        }
        else{
            result = AES256Cipher.AES_Decode(reqVal);
        }



        resultData.put("val",result);
        resultData.put("method","getAES256");
        resultData.put("result","성공");
        resultData.put("result_cd","0000");

        return resultData;
    }

    //base64
    @RequestMapping(value="/getBASE64",  method = RequestMethod.POST)
    public @ResponseBody Map getBASE64(@RequestBody Map requestMap) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        Map<String, Object> resultData = new HashMap<>();

        String reqVal = (String) requestMap.get("val");
        String gubun = (String) requestMap.get("gubun");

        byte[] resultBytes;

        if(gubun.equals("enc")){
            // Base64 인코딩
            byte[] targetBytes = reqVal.getBytes();
            Base64.Encoder encoder = Base64.getEncoder();
            resultBytes = encoder.encode(targetBytes);
        }
        else{
            Base64.Decoder decoder = Base64.getDecoder();
            resultBytes = decoder.decode(reqVal.getBytes());
        }


        resultData.put("val",new String(resultBytes));
        resultData.put("method","getBASE64");
        resultData.put("result","성공");
        resultData.put("result_cd","0000");

        return resultData;
    }


    //base64
    @RequestMapping(value="/getAESBASE",  method = RequestMethod.POST)
    public @ResponseBody Map getAESBASE(@RequestBody Map requestMap) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        Map<String, Object> resultData = new HashMap<>();

        String result ="";      //최종 리턴값
        String temp ="";
        String reqVal = (String) requestMap.get("val");
        String gubun = (String) requestMap.get("gubun");

        byte[] resultBytes;

        AES256Cipher a256 = AES256Cipher.getInstance();
        try {
            if (gubun.equals("enc")) {
                temp = AES256Cipher.AES_Encode(reqVal);
                // Base64 인코딩
                byte[] targetBytes = temp.getBytes();
                Base64.Encoder encoder = Base64.getEncoder();
                resultBytes = encoder.encode(targetBytes);
                result = new String(resultBytes);
            } else {
                Base64.Decoder decoder = Base64.getDecoder();
                resultBytes = decoder.decode(reqVal.getBytes());
                result = AES256Cipher.AES_Decode(new String(resultBytes));
            }

            resultData.put("val", result);
            resultData.put("method", "getBASE64");
            resultData.put("result", "성공");
            resultData.put("result_cd", "0000");
        } catch (Exception e) {
            log.info(e.toString());
            resultData.put("val", result);
            resultData.put("method", "getBASE64");
            resultData.put("result", "실패");
            resultData.put("result_cd", "1111");
        }

        return resultData;
    }

    //데몬에서 api호출하는 테스트용 API
    @RequestMapping(value="/testAPI",  method = RequestMethod.POST)
    public @ResponseBody Map testAPI(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){
        Map<String, Object> resultData = new HashMap<>();

        System.out.println(req.getParameter("num"));

        resultData.put("method", "testAPI");
        resultData.put("result", "성공");


        return resultData;
    }

    /**
     * 파일업로드시 리사이징 샘플테스트(주행거리 블랙박스 테스트용)
     * 업로드시 javascript를 이용한 리사이징 테스트
     */

    @RequestMapping(value="/fileUploadResizing",  method = RequestMethod.GET)
    public String fileUploadResizing(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "test/fileUploadResizing";
    }
    //MultipartHttpServletRequest 로 파일 받기.
    @RequestMapping(value="/fileUploadMultiPartResized",  method = RequestMethod.POST)
    public @ResponseBody String fileUploadMultiPartResized(MultipartHttpServletRequest req,  HttpServletResponse res,
                                            Model model) throws Exception {
        log.info("request 로 전달받기");


        Iterator<String> iterator = req.getFileNames();
        MultipartFile multipartFile = null;

        while(iterator.hasNext()){
            multipartFile = req.getFile(iterator.next());
            System.out.println(multipartFile.getOriginalFilename());
        }

        String UPLOADED_FOLDER = "./uploadResized/";

        File Folder = new File(UPLOADED_FOLDER);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try{
                Folder.mkdir(); //폴더 생성합니다.
                System.out.println("폴더가 생성되었습니다.");
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }else {
            System.out.println("이미 폴더가 생성되어 있습니다.");

        }

        byte[] bytes = multipartFile.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + multipartFile.getOriginalFilename());
        log.info(path.toString());
        Files.write(path, bytes);

        File file = new File(UPLOADED_FOLDER+multipartFile.getOriginalFilename());

        Object[] imgFile = new Object[1];

        FileInputStream fis = new FileInputStream(file);
        byte [] fileByte = new byte[(int) file.length()];
        fis.read(fileByte);
        imgFile[0] = fileByte;
        fis.close();
        fis = null;


        HttpSession session = req.getSession();
        session.setAttribute("imgfile", imgFile);

        return "success";
    }

    @RequestMapping(value="/fileUploadResizingEnd",  method = RequestMethod.POST)
    public ModelAndView fileUploadResizingEnd(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){
        System.out.println("fileUploadResizingEnd");

        HttpSession session = req.getSession();
        Object[] imgfile =  (Object[]) session.getAttribute("imgfile");
        String encoded ="";

        if(imgfile != null){
            byte[] file = (byte[]) imgfile[0];
            encoded = DatatypeConverter.printBase64Binary(file);
            //22.12.16 request객체에 담으면 tymeleaf에서 받을수가 없다. ModelAndView로 담으면 값이 넘어간다.
            req.setAttribute("imgsrc", "data:image/jpeg;base64,"+encoded);
        }

//        System.out.println(req.getAttribute("imgsrc"));


        mav.setViewName("/test/fileUploadResizingEnd");
        mav.addObject("imgsrc","data:image/jpeg;base64,"+encoded);

        return mav;
//        return "test/fileUploadResizingEnd";
    }


    //23.01.31 chatGPT가 알려준 파일 업로드
    @RequestMapping(value="/fileUploadGPT",  method = RequestMethod.GET)
    public String fileUploadGPT(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "test/fileUploadGPT";
    }


    //23.02.03 ToastUI 테스트
    @RequestMapping(value="/ToastUI",  method = RequestMethod.GET)
    public String ToastUI(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "test/ToastUI";
    }

    //24.01.05 Chart.js  테스트
    @RequestMapping(value="/chartJS",  method = RequestMethod.GET)
    public String chartJS(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "test/chartJS";
    }

    //24.01.05 Chart.js  테스트
    @RequestMapping(value="/grade_m",  method = RequestMethod.GET)
    public String grade_m(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "test/grade_m";
    }

    //23.05.03 clickjacking 공격 테스트
    //https://ko.javascript.info/clickjacking
    @RequestMapping(value="/clickjacking",  method = RequestMethod.GET)
    public String clickjacking(ModelAndView mav, HttpServletRequest req, HttpServletResponse res){

        return "test/clickjacking";
    }

    //23.05.03 clickjacking 공격 테스트


}
