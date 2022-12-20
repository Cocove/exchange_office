package Deposit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dbUSEdeposit {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    private static List<String> user_name = new ArrayList();
    private static List<String> user_usd = new ArrayList();
    private static List<String> user_eur = new ArrayList();
    private static List<String> user_uah = new ArrayList();
    private static List<Date> m_date = new ArrayList();
    private static List<String> user_cny = new ArrayList();
    private static List<String> user_byr = new ArrayList();



    public static void getHistory(){

        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;


            sql = "SELECT * FROM exchange_office_user";
            ResultSet rs = stmt.executeQuery(sql);
            int Index = 0;

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                user_name.add(rs.getString("user_name"));
                user_usd.add(rs.getString("user_USD"));
                user_cny.add(rs.getString("user_CNY"));
                user_eur.add(rs.getString("user_EUR"));
                user_byr.add(rs.getString("user_BYR"));





                // 输出数据
                /*System.out.print("user_name: " + user_name.get(Index));
                System.out.print(", user_USD: " + user_usd.get(Index));
                System.out.print(",  user_CNY: " + user_cny.get(Index));

                System.out.print(",  user_EUR: " + user_eur.get(Index));
                System.out.print(",  user_BYR: " + user_byr.get(Index));
                System.out.print(",  user_UAH: " + user_uah.get(Index));

                System.out.print("\n");*/
                Index++;
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
