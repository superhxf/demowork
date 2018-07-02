package com.demo.dao;

import java.sql.*;
import java.util.*;

/**
 *   连接数据库进行数据的查询和认证
 */
public class LoginDao {
    private static String  userroot = "root";
    private static String passroot = "root";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/demoapi?serverTimezone=GMT";
    //静态快进行驱动加载
    {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *  定义查询基础类型 返回类型为 查询结果 可以实现代码的轻度复用
     * @param strSql
     * @return
     */
    public static List getRs(String strSql) {
        System.out.println(strSql);
        //定义变量
        Connection conn = null;
        //定义连接查询指令
        Statement state = null;
        //定义查询结果
        ResultSet rs = null;
        //定义返回值
        List list = null;
        //创建连接
        try {
            conn = DriverManager.getConnection(url, userroot, passroot);
            System.out.println("执行进度");
            //基于连接获取查询设置
            state = conn.createStatement();
            rs = state.executeQuery(strSql);
            //结果输入
             list = new ArrayList();
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭结果集
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
                if (!state.isClosed()) {
                    conn.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    /**
     *  /    获取 结果 个人登陆认证
     * @param username
     * @param password
     * @return
     */
    public static boolean verfyLogin(String username,String password){
        boolean result = false;
        String strsql = "select 1 from person where name = "+username+"";
        List list = getRs(strsql);
        if(!(list == null) && list.size() > 0 ){
            result =  true;
        }
        return result;
    }
    public static void main(String[] args){
        System.out.println(verfyLogin("'丹丹'","123"));
        System.out.println(getRs("select 1 from person where name = 'zhangsan'"));
    }
}
