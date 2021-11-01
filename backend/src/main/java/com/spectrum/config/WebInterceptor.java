package com.spectrum.config;

import com.spectrum.common.jwt.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!request.getRequestURI().equals("/api/users/login"))
        {
            System.out.println("api url pattern");
            String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
            String CheckLogoutToken = redisUtil.getData(bearer);
            if(CheckLogoutToken == null)
            {
                return true;
            }
            else
            {
                System.out.println("로그아웃되어있는 토큰");
                return false;
            }
        }
        else
        {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, @Nullable Exception arg3) throws Exception {
    }
}
