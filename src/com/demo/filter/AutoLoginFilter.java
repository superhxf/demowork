package com.demo.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutoLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //自动登录过滤逻辑
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response  = (HttpServletResponse) servletResponse;
        //判断 是否是否非登录页面
        if(request.getRequestURI().contains("index")){
            filterChain.doFilter(request,response);
            return;
        }else{
            //从session中获取 用户是否已经登录
            if(request.getSession().getAttribute("username") != null){
                //同样需要放行
                System.out.println("session 放行");
                filterChain.doFilter(request,response);
                return;
            }else{
                //获取cookie中的用户数据 用于登录
                Cookie[] cookies = request.getCookies();
                boolean flag = false;
                if(cookies != null && cookies.length > 0 ) {
                    for (Cookie cookie : cookies) {
                        if("auto".equalsIgnoreCase(cookie.getName())) {
                            request.getSession().setAttribute("username", cookie.getValue().split("-")[0]);
                            request.getSession().setAttribute("password", cookie.getValue().split("-")[1]);
                            flag = true;
                            break;
                        }
                    }
                    //如果无法获取登录存储的用户信息 自然要踢回登陆页面
                    if(!flag){
                        System.out.println("session cookie 均不通过");
                        response.sendRedirect("/index.jsp");
                    }else {
                        System.out.println("cookie 放行");

                    }
                }
            }
        }
        //向下执行过滤器
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
