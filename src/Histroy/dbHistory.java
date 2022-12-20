package Histroy;
//对hitroy表进行更改和查询操作
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class dbHistory {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    //数据库获得数据
    private static List<String> m_employee = new ArrayList();
    private static List<String> m_customer = new ArrayList();
    private static List<String> m_customer_buy = new ArrayList();
    private static List<String> m_customer_get = new ArrayList();
    private static List<Date> m_date = new ArrayList();
    private static List<String> m_buy_currency= new ArrayList();
    private static List<String> m_get_currency = new ArrayList();




    public static void addHistory(String m_employee,String m_customer,String m_customer_buy,String m_customer_get,String m_buy_currency,String m_get_currency,String Date){
        Connection conn = null;
        PreparedStatement ps=null;

        SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd");


        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            String sql;
            sql =  "insert into histroy (employee,customer,buy_currency,customer_buy,get_currency,customer_get,submission_date) values(?,?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1, m_employee);
            ps.setString(2, m_customer);
            ps.setString(3, m_buy_currency);
            ps.setString(4, m_customer_buy);
            ps.setString(5, m_get_currency);
            ps.setString(6, m_customer_get);
            ps.setString(7, Date);

            int resultSet=ps.executeUpdate();
            // 展开结果集数据库
            if(resultSet>0){
                //如果插入成功，则打印success
                //System.out.println("Sucess");
            }else{
                //如果插入失败，则打印Failure
                //System.out.println("Failure");
            }
            // 完成后关闭
            ps.close();
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
                if(ps!=null) ps.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        //System.out.println("Goodbye!");
    }
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


            sql = "SELECT * FROM histroy";
            ResultSet rs = stmt.executeQuery(sql);
            int Index = 0;

            ResultSetMetaData rsmd = rs.getMetaData();
            //System.out.println(rsmd.getColumnCount());
            for(int i = 1; i <= rsmd.getColumnCount(); i++)
                //System.out.println(rsmd.getColumnName(i));

            // 展开结果集数据库


            m_employee.clear();
            m_customer.clear();
            m_buy_currency.clear();
            m_customer_buy.clear();
            m_get_currency.clear();
            m_customer_get.clear();
            m_date.clear();
                while(rs.next()){



                    // 通过字段检索
                    m_employee.add(rs.getString("employee"));
                    m_customer.add(rs.getString("customer"));
                    m_buy_currency.add(rs.getString("buy_currency"));
                    m_customer_buy.add(rs.getString("customer_buy"));
                    m_get_currency.add(rs.getString("get_currency"));
                    m_customer_get.add(rs.getString("customer_get"));
                    m_date.add(rs.getDate("submission_date"));




                    // 输出数据
                   /* System.out.print("m_employee: " + m_employee.get(Index));
                    System.out.print(", m_customer: " + m_customer.get(Index));
                    System.out.print(",  m_buy_currency: " + m_buy_currency.get(Index));
                    System.out.print(",  m_customer_buy: " + m_customer_buy.get(Index));
                    System.out.print(",  m_get_currency: " + m_get_currency.get(Index));
                    System.out.print(",  m_customer_get: " + m_customer_get.get(Index));
                    System.out.print(",  m_date: " + m_date.get(Index).toString());
                    System.out.print("\n");*/
                    Index++;

                }
                Index = 0;


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

    public static List<String> getM_employee() {
        return m_employee;
    }

    public static List<String> getM_customer() {
        return m_customer;
    }

    public static List<String> getM_customer_buy() {
        return m_customer_buy;
    }

    public static List<String> getM_customer_get() {
        return m_customer_get;
    }

    public static List<Date> getM_date() {
        return m_date;
    }
}
