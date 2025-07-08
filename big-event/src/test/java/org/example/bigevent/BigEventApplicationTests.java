package org.example.bigevent;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.example.bigevent.config.AliOssConfig;
import org.example.bigevent.utils.JwtGenerateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

@SpringBootTest
class BigEventApplicationTests {

    @Test
    void contextLoads() {
        String usernamePattern = "^\\S{5,16}$";
        String passwordPattern = "^\\S{5,16}$";
        Pattern usernamepattern = Pattern.compile(usernamePattern);
        Pattern passwordpattern = Pattern.compile(passwordPattern);
        System.out.println("12334".matches(usernamePattern));
    }

    @Test
    public void testJwtGenerate(){
        Map<String, String>map = new HashMap<>();
        map.put("id","1");
        map.put("username","admin");
        String secretKey = "1234";
        String jwt = JwtGenerateUtil.generateJwt("user", map, Algorithm.HMAC256(secretKey), 1);
        System.out.println(jwt);
    }

    @Test
    public void testJwtParse(){
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoiYWRtaW4ifSwiZXhwIjoxNzUxNTEwNjMzfQ.UtEMXyKJYqtna2fiuAtf1_WZFqJBO2k1WmUrO6nMY3k";
//        Map<String, Claim>maps = JwtGenerateUtil.parseJwt(token, Algorithm.HMAC256("1234"));
//        maps.forEach((k,v)->{System.out.println(k+":"+v);});
//        System.out.println(maps.get("user").asMap().get("username"));
    }

    @Test
    void testEmailPattern(){
        String emailPattern = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        System.out.println("a_az@aa.com".matches(emailPattern));
    }

    @Test
    void testStaticValue(){
        System.out.println(AliOssConfig.getBucketName());
        System.out.println(AliOssConfig.getRegion());
        System.out.println(AliOssConfig.getEndpoint());
    }

    @Test
    void testUUID(){
        System.out.println(AliOssConfig.getEndpoint());
        System.out.println(AliOssConfig.getEndpoint().lastIndexOf("/"));
    }

}
