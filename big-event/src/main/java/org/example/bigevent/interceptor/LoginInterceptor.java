package org.example.bigevent.interceptor;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.bigevent.exception.LoginErrorException;
import org.example.bigevent.utils.JwtGenerateUtil;
import org.example.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截前...");
        String token = request.getHeader("token");
        if(token == null){
            response.setStatus(401);
            throw new LoginErrorException("请先登录");
        }
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String token1 = operations.get("token");
        if(!token.equals(token1)){
            response.setStatus(401);
            throw new LoginErrorException("登录超时！请重新登录");
        }
        try {
            //解析jwt
            Map<String, Claim>claimMap = JwtGenerateUtil.parseJwt(token, Algorithm.HMAC256(JwtGenerateUtil.secretKey));
            Map<String, Object>maps = claimMap.get("user").asMap();
            //放入ThreadLocal
            ThreadLocalUtil.set(maps);
        } catch (Exception e) {
            response.setStatus(401);
            throw new LoginErrorException(e.getMessage());
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
