package Histroy;
//更新用户每日取钱量
import Rate.get_Rate;

import java.sql.*;
import java.util.ArrayList;

public class Limit_get_Money {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    static ArrayList<String> id_list = new ArrayList();
    static ArrayList<String> name_list = new ArrayList();
    static ArrayList<String> buy_Money_list = new ArrayList();
    static ArrayList<String> buy_Currency_list = new ArrayList();
    static ArrayList<Date> m_date = new ArrayList();

    static ArrayList<String> id_list1 = new ArrayList();
    static ArrayList<String> name_list1 = new ArrayList();
    static ArrayList<String> buy_Money_list1 = new ArrayList();
    static ArrayList<String> buy_Currency_list1 = new ArrayList();
    static ArrayList<Date> m_date1 = new ArrayList();

    static ArrayList<String> sum = new ArrayList();

    public static void LimitMoney() {
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


            for(int i = 0; i < name_list.size(); i++){
                if(i > 0 && name_list.get(i).equals(name_list.get(i-1)) && m_date.get(i).equals(m_date.get(i-1))){
                    continue;
                }
                zhengli(stmt,name_list.get(i),m_date.get(i));
            }
            for(int i = 0; i < name_list1.size(); i++){
                //System.out.println(name_list1.get(i));
            }
            panduan(conn,stmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void panduan(Connection conn, Statement stmt) throws SQLException {
        for(int i = 0;i < name_list1.size(); i++){

            //System.out.println(m_date1.get(i));
            if(existNamedate(stmt,name_list1.get(i),m_date1.get(i))){
                //if(existdate(stmt,m_date1.get(i))){
                    //System.out.println(0);
                    //updatetabel(stmt, name_list.get(i),m_date.get(i));
                    update1(conn,name_list1.get(i),buy_Money_list1.get(i),buy_Currency_list1.get(i),m_date1.get(i));
               /* }else {
                    //inserttabel(stmt, name_list.get(i),m_date.get(i))
                    System.out.println(1);
                    insert1(conn,name_list1.get(i),buy_Money_list1.get(i),buy_Currency_list1.get(i),m_date1.get(i));
                }*/
            }else{
                //System.out.println(2);
                insert1(conn,name_list1.get(i),buy_Money_list1.get(i),buy_Currency_list1.get(i),m_date1.get(i));
            }
        }
    }



    public static void zhengli(Statement stmt, String M_name, Date M_date) throws SQLException {
        String sql;

        String currency = null;
        String str;
        sql = "SELECT trade_id, buy_currency, customer_buy FROM histroy where customer = '" + M_name +"' and submission_date = '"+ M_date + "'";
        ResultSet rs = stmt.executeQuery(sql);
        double sum1 = 0;
        // 展开结果集数据库

        while (rs.next()) {
            // 通过字段检索
            currency = rs.getString("buy_currency");
            str = (rs.getString("customer_buy"));

            sum1 += stringToDouble(getBYNrate(str,currency));
        }


        name_list1.add(M_name);
        m_date1.add(M_date);
        buy_Currency_list1.add(currency);
        str = doubleToString(sum1);
        buy_Money_list1.add(str);

        //System.out.println(name_list1.get(0));

    }



    //获取histroy
    public static void setArraylist(Statement stmt) throws SQLException {
        String sql;
        sql = "SELECT trade_id, customer, buy_currency, customer_buy, submission_date FROM histroy";
        ResultSet rs = stmt.executeQuery(sql);
        int Index = 0;
        // 展开结果集数据库
        while (rs.next()) {
            // 通过字段检索
            id_list.add(rs.getString("trade_id"));
            name_list.add(rs.getString("customer"));
            buy_Currency_list.add(rs.getString("buy_currency"));
            buy_Money_list.add(rs.getString("customer_buy"));
            m_date.add(rs.getDate("submission_date"));
            Index++;
        }
        // 完成后关闭
        rs.close();
    }



    //判断是否存在该名字
    public static boolean existName(Statement stmt,String name) throws SQLException {
        String sql;
        sql = "SELECT 1 FROM user_limit WHERE user_name = '"+ name +"'  LIMIT 1";
        ResultSet rs = stmt.executeQuery(sql);
        Integer exist = 0;
        if (rs.next()) {
            exist = rs.getInt(1);
        }
        if(exist == 0)
        {
            return false;
        } else {
            return true;
        }

    }

    public static boolean existNamedate(Statement stmt,String name, Date date) throws SQLException {
        String sql;
        sql = "SELECT 1 FROM user_limit WHERE user_name = '"+ name +"' and submission_date = '"+ date +"' LIMIT 1";
        ResultSet rs = stmt.executeQuery(sql);
        Integer exist = 0;
        if (rs.next()) {
            exist = rs.getInt(1);
        }
        if(exist == 0)
        {
            return false;
        } else {
            return true;
        }

    }
    //判断是否存在该名字
    public static boolean existdate(Statement stmt, Date date) throws SQLException {
        String sql;
        sql = "SELECT 1 FROM user_limit WHERE submission_date = '"+ date +"' LIMIT 1";
        ResultSet rs = stmt.executeQuery(sql);
        Integer exist = 0;
        if (rs.next()) {
            exist = rs.getInt(1);
        }
        if(exist == 0)
        {
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

    private static void clearList(){
        id_list.clear();
        name_list.clear();
        buy_Currency_list.clear();
        buy_Money_list.clear();
        m_date.clear();
        id_list1.clear();
        name_list1.clear();
        buy_Currency_list1.clear();
        buy_Money_list1.clear();
        m_date1.clear();
    }


    public static void insert1(Connection connection, String name, String getUSD,String Currency, Date date){
        PreparedStatement ps=null;
        //String getMoney = getBYNrate(getUSD, Currency);
        try {
            //3.create Statement
            String sql="insert into user_limit (user_name,user_get_usd,user_get_limit,submission_date) values(?,?,?,?)";
            ps=connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, getUSD);
            ps.setString(3, "BYN");
            ps.setDate(4, date);
            //4.excuteUpdate
            int resultSet=ps.executeUpdate();
            if(resultSet>0){
                //如果插入成功，则打印success
                //System.out.println("Sucess");
            }else{
                //如果插入失败，则打印Failure
                //System.out.println("Failure");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void update1(Connection connection, String name, String getUSD,String currency, Date date){
        PreparedStatement ps=null;
        //String getMoney = getBYNrate(getUSD, currency);
        try {
            //3.create Statement
            String sql="UPDATE user_limit SET user_get_usd = ? ,user_get_limit = ?  WHERE user_name= '"+name+"' and submission_date = '"+date+"'";
            ps=connection.prepareStatement(sql);
            ps.setString(1, getUSD);
            ps.setString(2, "BYN");
            //4.excuteUpdate
            int resultSet=ps.executeUpdate();
            if(resultSet>0){
                //如果插入成功，则打印success
                //System.out.println("Sucess");
                //System.out.println(name);

            }else{
                //如果插入失败，则打印FailureD
                //System.out.println("Failure");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getBYNrate(String getUSD, String currency){
        double rate = 0;
        String Rate = null;
        if(currency.equals("BYN")){
            rate = stringToDouble(getUSD);
        }else{
            String huobi1 = currency;
            String huobi2 = "BYN";
            double rate1 = 0;
            rate1 = new get_Rate().get_rate(huobi1,huobi2);
            rate = rate1 * stringToDouble(getUSD);
        }
        Rate = doubleToString(rate);
        return Rate;
    }



}

