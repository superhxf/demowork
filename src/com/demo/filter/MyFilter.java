package com.demo.filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
     System.out.println("初始化过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)  {
        //获取请求的路径\
        System.out.println("进入过滤器中");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        System.out.println("session"+request.getSession().getAttribute("username"));
        if(request.getRequestURI().contains(".jsp") && !request.getRequestURI().contains("index")){
            if(request.getSession().getAttribute("username")== null || request.getSession().getAttribute("username").equals("")){

                try {
                    response.sendRedirect("/index.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
