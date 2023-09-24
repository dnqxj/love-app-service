package com.orangemust.love.config;

import com.auth0.jwt.interfaces.Claim;
import com.orangemust.core.utils.JwtUtil;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 处理header头中token逻辑，并解析
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("authorization");
        if (token == null) {
            response.setStatus(401);
            response.getWriter().write("权限校验失败~");
            return false;
        }
        token = token.replace("Bearer ", "");
        Map<String, Claim> userData = JwtUtil.verifyToken(token);
        response.setCharacterEncoding("UTF-8");
        // 未解析出用户信息，返回状态码401
        if (userData == null) {
            response.setStatus(401);
            return false;
        }
        Integer userId = userData.get("userId").asInt();
        String userName = userData.get("userName").asString();
        // 拦截器 拿到用户信息，放到request中
        request.setAttribute("userId", userId);
        request.setAttribute("userName", userName);
        return true; // 只有返回true才会继续向下执行，返回false取消当前请求
    }
}