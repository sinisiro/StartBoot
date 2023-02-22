package com.sinisiro.StartBoot.start.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class HomeController {
//    private static final Logger logger = LogManager.getLogger(HomeController.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    // 기본 페이지 설정되어어 있으면 이쪽을 index.html에 우선한다.
    @RequestMapping("/")
    public String home() {
        log.info("home start");


        return "start/home";
    }

    //[[22.12.26] 걸음걸이 jsonArray 서버쪽 parsing
    @RequestMapping("/jsonParsing")
    public @ResponseBody String jsonArrayParsing() throws ParseException {

        String responseJsonArray =
                "[{\"stepCnt\":3969,\"dateWalk\":\"20221209\"},{\"stepCnt\":5388,\"dateWalk\":\"20221210\"},{\"stepCnt\":2170,\"dateWalk\":\"20221211\"},{\"stepCnt\":5297,\"dateWalk\":\"20221212\"},{\"stepCnt\":4894,\"dateWalk\":\"20221213\"},{\"stepCnt\":7094,\"dateWalk\":\"20221214\"},{\"stepCnt\":9548,\"dateWalk\":\"20221215\"},{\"stepCnt\":4097,\"dateWalk\":\"20221216\"},{\"stepCnt\":8464,\"dateWalk\":\"20221217\"},{\"stepCnt\":3087,\"dateWalk\":\"20221218\"},{\"stepCnt\":4498,\"dateWalk\":\"20221219\"},{\"stepCnt\":5885,\"dateWalk\":\"20221220\"},{\"stepCnt\":9173,\"dateWalk\":\"20221221\"},{\"stepCnt\":10180,\"dateWalk\":\"20221222\"},{\"stepCnt\":6730,\"dateWalk\":\"20221223\"},{\"stepCnt\":5020,\"dateWalk\":\"20221224\"},{\"stepCnt\":673,\"dateWalk\":\"20221225\"},{\"stepCnt\":2278,\"dateWalk\":\"20221226\"}]";

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseJsonArray);
        JSONArray jsonArray = (JSONArray)obj;

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObj = (JSONObject)jsonArray.get(i);

            System.out.print("[stepCNT] "+ jsonObj.get("stepCnt"));
            System.out.print("[dateWalk] "+ jsonObj.get("dateWalk"));
            System.out.println("");
            sb.append("[stepCNT] "+ jsonObj.get("stepCnt") + "[dateWalk] "+ jsonObj.get("dateWalk"));
            sb.append("\n");
        }

        return sb.toString();
    }


    //https://ohgyun.com/707 참고함.
    //Universal Link 서버 세팅 부분
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
