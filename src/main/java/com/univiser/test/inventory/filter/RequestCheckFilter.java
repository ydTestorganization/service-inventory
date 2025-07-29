package com.univiser.test.inventory.filter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@Slf4j
public class RequestCheckFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

         String requestURI = request.getRequestURI();
         String requestIp=request.getRemoteAddr();
         String localIp=request.getLocalAddr();
         String method=request.getMethod();
         String requestId=request.getHeader("x-request-id");
         log.info("requestURI: {}, requestIp: {}, localIp: {}, method: {}, requestId: {}", requestURI, requestIp, localIp, method, requestId);
//         if(StringUtils.isBlank(requestId)){
//             response.setHeader("x-request-id", String.valueOf(System.currentTimeMillis()));
//             response.setStatus(400);
//             response.setHeader("Content-Type", "application/json");
//             response.setHeader("encoding", "UTF-8");
//             response.getWriter().write("{\"code\":400,\"message\":\"requestId is empty\"}");
//
//         }
//         else{
             MDC.put("x-request-id", requestId);
             filterChain.doFilter(request, response);
//         }


    }
}
