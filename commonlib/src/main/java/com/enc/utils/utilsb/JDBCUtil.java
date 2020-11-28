package com.enc.utils.utilsb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {
    static Connection conn=null;
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");  //加载数据库驱动
            String url="jdbc:mysql://192.168.0.122:3306/intelligent-community?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
            String user="root";
            String passWord="root";
            conn=(Connection)DriverManager.getConnection(url,user,passWord); //创建连接
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeAll(Statement ps, Connection conn, ResultSet rs){
        try{
            if(rs !=null){
                rs.close();
            }
            if(ps !=null){
                ps.cancel();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
