package com.ssm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MoneyUtils {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/ssm02_train?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "tongyin258";

    public static float getMoney(String username){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        float money = 0.0f;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            String sql = "select money from users where username='"+username+"'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                money = rs.getFloat("money");
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return money;
    }

    public static void main(String[] args) {
        System.out.println("1233: "+MoneyUtils.getMoney("user"));
    }
}
