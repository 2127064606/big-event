package org.example.bigevent.interceptor;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.bigevent.utils.JwtGenerateUtil;
import org.example.bigevent.utils.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截前...");
        String token = request.getHeader("token");
        if(token == null){
            throw new RuntimeException("请先登录");
        }
        try {
            //解析jwt
            Map<String, Claim>claimMap = JwtGenerateUtil.parseJwt(token, Algorithm.HMAC256(JwtGenerateUtil.secretKey));
            Map<String, Object>maps = claimMap.get("user").asMap();
            //放入ThreadLocal
            ThreadLocalUtil.set(maps);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("放行后...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清理当前ThreadLocal
        ThreadLocalUtil.remove();
    }
}
