package com.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  模拟网页常见的两种请求获取 一种是POST 一种是GET
 */
public class MyServlet extends javax.servlet.http.HttpServlet {

    /**
     * 模拟的是POST请求
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws IOException
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("post请求");
    }


    /**
     * 模拟的是GET请求
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws IOException
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("get请求");
    }

    /**
     *  重写的service 方法 进行请求的分发
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strMethod  = request.getMethod();
        //进行请求的分发
        System.out.println("进行请求方法的分发，请求方法名称是："+strMethod);
        if("POST".equals(strMethod)){
            doPost(request,response);
        }else if("GET".equals(strMethod)){
            doGet(request,response);
        }
       // super.service(req, resp);
    }

}
