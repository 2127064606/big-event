package org.example.bigevent.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public class JwtGenerateUtil {

    public static String secretKey = "pjPp";

    public static String generateJwt(String header, Map<String,?>claims, Algorithm algSign, Integer expireTime){
        return JWT.create().withClaim(header, claims).withExpiresAt(new Date(System.currentTimeMillis()+expireTime*60*60*1000)).sign(algSign);
    }

    public static Map<String, Claim> parseJwt(String token, Algorithm algSign){
        JWTVerifier jwtVerifier = JWT.require(algSign).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim>claims = decodedJWT.getClaims();
        return claims;
    }


}
