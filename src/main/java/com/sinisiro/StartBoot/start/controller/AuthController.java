//package com.sinisiro.StartBoot.start.controller;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * JWT 서버 소스
// */
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private static final String SECRET_KEY = "sinisiro";
//    private static final String REFRESH_SECRET_KEY = "refresh_secret_key"; // Refresh Token을 위한 별도의 시크릿 키
//    private static final long EXPIRATION_TIME = 60; // Access Token의 유효 기간 (예: 1시간)
//    private static final long REFRESH_EXPIRATION_TIME = 86400; // Refresh Token의 유효 기간 (예: 24시간)
//
//    // 사용자의 토큰 저장소 (실제 사용 시에는 데이터베이스에 저장하는 것이 좋습니다)
//    private Map<String, String> tokenStorage = new HashMap<>();
//
//
//    @PostMapping("/login")
//    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
//        String username = credentials.get("username");
//        String password = credentials.get("password");
//
//        System.out.println("username:"+username);
//        System.out.println("password:"+password);
//        // Perform authentication logic (e.g., verify username and password)
//
//        // 사용자 검증 로직 구현
//        boolean isValidUser = authenticateUser(username, password);
//        if (!isValidUser) {
//            throw new RuntimeException("Invalid username or password"); // Handle unauthorized access
//        }
//
//        // Assuming authentication is successful, generate and return an Access Token and Refresh Token
//        String accessToken = createJwtToken(username, EXPIRATION_TIME, SECRET_KEY);
//        String refreshToken = createJwtToken(username, REFRESH_EXPIRATION_TIME, REFRESH_SECRET_KEY);
//
//        // Store the Refresh Token (In production, store it securely, e.g., in a database)
//        tokenStorage.put(username, refreshToken);
//
//        String storedRefreshToken = tokenStorage.get(username);
//        System.out.println("storedRefreshTokenLogin ["+storedRefreshToken+"]");
//
//
//        Map<String, String> response = new HashMap<>();
//        response.put("access_token", accessToken);
//        response.put("refresh_token", refreshToken);
//
//        return response;
//    }
//
//    @PostMapping("/logout")
//    public Map<String, String> logout(@RequestBody Map<String, String> credentials) {
//        System.out.println("===logOut 수행===");
//        String username = credentials.get("username");
//
//
//        System.out.println("삭제전:"+tokenStorage.toString());
//        // Refresh Token 무효화 로직
//        tokenStorage.remove(username); // 또는 블랙리스트에 추가
//        System.out.println("삭제후:"+tokenStorage.toString());
//        System.out.println("===logOut 종료===");
//
//        Map<String, String> response = new HashMap<>();
//
//        response.put("code","0000");
//        response.put("message","logOut success");
//
//        return response;
//    }
//
//    private String createJwtToken(String username, long expirationTime, String secretKey) {
//        Date now = new Date();
//        Date expirationDate = new Date(now.getTime() + expirationTime * 1000);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
//
//    @PostMapping("/refresh-token")
//    public Map<String, String> refreshToken(@RequestBody Map<String, String> refreshTokenRequest) {
//        String username = refreshTokenRequest.get("username");
//        String refreshToken = refreshTokenRequest.get("refresh_token");
//
//        System.out.println("username: ["+username+"]");
//        System.out.println("refreshToken: ["+refreshToken+"]");
//
//        // Verify the Refresh Token (using the Refresh Token secret key)
//        try {
//            Claims claims = Jwts.parser().setSigningKey(REFRESH_SECRET_KEY).parseClaimsJws(refreshToken).getBody();
//            String storedRefreshToken = tokenStorage.get(username);
//
//            System.out.println("storedRefreshToken["+storedRefreshToken+"]");
//
//
//            if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
//                throw new RuntimeException("Invalid Refresh Token"); // Handle unauthorized access
//            }
//
//            // Generate a new Access Token (reuse the same username)
//            String newAccessToken = createJwtToken(username, EXPIRATION_TIME, SECRET_KEY);
//
//            System.out.println("newAccessToken["+newAccessToken+"]");
//            Map<String, String> response = new HashMap<>();
//            response.put("access_token", newAccessToken);
//            return response;
//        } catch (ExpiredJwtException e) {
//            throw new RuntimeException("Refresh Token has expired");
//        }
//    }
//
//    // Example protected API endpoint that requires JWT authentication
//    @PostMapping("/protected-api")
//    public Map<String, String> protectedApi(@RequestHeader("Authorization") String authorizationHeader,
//                               @RequestBody Map<String, String> data) {
//        // JWT 토큰 추출
//        String token = authorizationHeader.replace("Bearer ", "");
//        Map<String, String> res = new HashMap<>();
//        // Verify JWT token
//        try {
//            //이 부분에서 jwt accessToken이 만료되면 바로 catch문으로 떨어진다.
//            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//            String username = claims.getSubject();
//            Date expiration = claims.getExpiration();
//
//            // 유효기간 확인
//            Date now = new Date();
//            System.out.println("expiration:"+ expiration);
//            System.out.println("now:"+ now);
//
//            //여기 수행되지 않고, 바로 catch 문 수행된다.
//            if (expiration.before(now)) {
//                // 토큰의 유효기간이 지났을 경우 처리 로직
//                res.put("code","9999");
//                res.put("message","Token has expired");
////            return "Token has expired"; // 예시로 만료된 토큰 처리에 대한 응답 메시지를 반환하도록 하였습니다.
//
//            }
//
//            String message = data.get("message");
//            System.out.println("message: "+message);
//
//            System.out.println("token:"+token);
//            System.out.println(expiration.toString());
//            // Perform actions for the authenticated user
//
//            res.put("code","0000");
//            res.put("message","Protected API called by user: " + username);
//
//            return  res;
//        }
//        catch(ExpiredJwtException e){
//
//            System.out.println(e.toString());
//            res.put("code","9991");
//            res.put("message","로그인 되어있지 않음(유효기간 만료)");
//            return  res;
//        }
//    }
//
//
//
//    // 사용자 검증 로직
//    private boolean authenticateUser(String username, String password) {
//        // 실제 사용자 인증 로직 구현
//        // 예를 들어, 데이터베이스에서 사용자 정보를 확인하거나 외부 인증 서비스와 통신할 수 있음
//        // 이 예제에서는 간략하게 사용자명과 비밀번호를 확인하도록 가정
//        return username.equals("sinisiro") && password.equals("1234");
//    }
//
//
//}
