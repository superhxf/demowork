package com.demo.servlet;

import com.demo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  模拟网页常见的两种请求获取 一种是POST | 一种是GET
 */
public class MyServlet extends javax.servlet.http.HttpServlet {
    private UserService  service = new UserService();
    /**
     * 模拟的是POST请求
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws IOException
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("post请求");
        //现在通过请求对象获取请求的内容
        String strName = request.getParameter("name");
        String strSex = request.getParameter("sex");
        String strPassword = request.getParameter("password");
        System.out.println("name"+strName+",sex:"+strSex);
        if(service.userLogin(strName,strPassword)){
            response.sendRedirect("/servicePage/loginError.jsp");
        }else{
            response.sendRedirect("/servicePage/loginSucc.jsp");
        }
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
        //get 请求转发使用post方法
        doPost(request,response);
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

    //servlet 其他的方法 也是可以需要去了解的 但是基本的 请求转发 get post 方法使用的是最多的。

}
