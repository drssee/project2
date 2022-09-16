package com.example.demo2.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Log4j2
public class UTF8Filter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("UTF8 filter......");
        //filtercahin의 매개변수로
        //httpservletreq,resp의 구현대상 인터페이스를 받기때문에 dofilter의 매개변수로는 servletreq,resp가 들어옴
        //그래서 http관련 설정을 하고 싶다면 다운캐스팅 해야함
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response);
    }
}
