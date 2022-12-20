package Histroy;

import java.sql.*;
import java.util.ArrayList;

public class set_daliy_histroy {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";


    static ArrayList<String> buy_Money_list = new ArrayList();
    static ArrayList<Date> m_date = new ArrayList();


    static ArrayList<String> buy_Money_list1 = new ArrayList();
    static ArrayList<Date> m_date1 = new ArrayList();


    public static void set_daliy_histroy() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            clearList();
            setArraylist(stmt);
            for (int i = 0; i < m_date.size(); i++) {
                if (i != 0 && m_date.get(i).equals(m_date.get(i-1))) {
                    continue;
                }
                zhengli(stmt,m_date.get(i));
            }
            panduan(conn, stmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void panduan(Connection conn, Statement stmt) throws SQLException {
        for (int i = 0; i < m_date1.size(); i++) {

           // System.out.println(m_date1.get(i));
            if (existdate(stmt, m_date1.get(i))) {
               // System.out.println(0);
                //updatetabel(stmt, name_list.get(i),m_date.get(i));
                update1(conn, buy_Money_list1.get(i), m_date1.get(i));
            } else {
                //System.out.println(1);
                //inserttabel(stmt, name_list.get(i),m_date.get(i))
                insert1(conn, buy_Money_list1.get(i), m_date1.get(i));
            }
        }
    }


    public static void zhengli(Statement stmt,  Date M_date) throws SQLException {
        String sql;

        String currency = null;
        String str;
        sql = "SELECT user_get_usd FROM user_limit where submission_date = '" + M_date + "'";
        ResultSet rs = stmt.executeQuery(sql);
        double sum1 = 0;
        // 展开结果集数据库

        while (rs.next()) {
            // 通过字段检索
            str = rs.getString("user_get_usd");
            sum1 += stringToDouble(str);

        }

        m_date1.add(M_date);
        str = doubleToString(sum1);
        buy_Money_list1.add(str);

    }


    //获取histroy
    public static void setArraylist(Statement stmt) throws SQLException {
        String sql;
        sql = "SELECT user_get_usd, submission_date FROM user_limit";
        ResultSet rs = stmt.executeQuery(sql);
        int Index = 0;
        // 展开结果集数据库
        while (rs.next()) {
            // 通过字段检索
            buy_Money_list.add(rs.getString("user_get_usd"));
            m_date.add(rs.getDate("submission_date"));
            Index++;
        }
        // 完成后关闭
        rs.close();
    }


    //判断是否存在该名字
    public static boolean existdate(Statement stmt, Date date) throws SQLException {
        String sql;
        sql = "SELECT 1 FROM day_amout WHERE mdate = '" + date + "' LIMIT 1";
        ResultSet rs = stmt.executeQuery(sql);
        Integer exist = 0;
        if (rs.next()) {
            exist = rs.getInt(1);
        }
        if (exist == 0) {
            return false;
        } else {
            return true;
        }
    }


    public static double stringToDouble(String doublestr) {
        Double Adouble;
        Adouble = Double.parseDouble(doublestr);
        return Adouble;
    }

    //int 转 String
    public static String doubleToString(double value) {
        Double aDouble = new Double(value);
        return aDouble.toString();
    }

    private static void clearList() {
        buy_Money_list.clear();
        m_date.clear();
        buy_Money_list1.clear();
        m_date1.clear();
    }


    public static void insert1(Connection connection,String getUSD, Date date1) {
        PreparedStatement ps = null;
        //String getMoney = getBYNrate(getUSD, Currency);
        try {
            //3.create Statement
            String sql = "insert into day_amout (amount,mdate) values(?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, getUSD);
            ps.setDate(2, date1);
            //4.excuteUpdate
            int resultSet = ps.executeUpdate();
            if (resultSet > 0) {
                //如果插入成功，则打印success
                //System.out.println("Sucess");
            } else {
                //如果插入失败，则打印Failure
                //System.out.println("Failure");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void update1(Connection connection, String getUSD, Date date) {
        PreparedStatement ps = null;
        //String getMoney = getBYNrate(getUSD, currency);
        try {
            //3.create Statement
            String sql = "UPDATE day_amout SET amount = ?   WHERE  mdate = '" + date + "'";
            ps = connection.prepareStatement(sql);
            ps.setString(1, getUSD);
            //4.excuteUpdate
            int resultSet = ps.executeUpdate();
            if (resultSet > 0) {
                //如果插入成功，则打印success
                //System.out.println("Sucess");


            } else {
                //如果插入失败，则打印FailureD
                //System.out.println("Failure");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
