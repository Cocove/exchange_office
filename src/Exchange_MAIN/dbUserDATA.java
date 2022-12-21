package Exchange_MAIN;

import Administrator.Change_User_Permissions_Frame;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class dbUserDATA {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    String Privilege(String acc) throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        String exist = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT privilege FROM user_acc_pss WHERE user_acc = '"+ acc +"'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                exist = rs.getString("privilege");
                //System.out.println(exist);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return exist;

    }

    public boolean exist_acc(String acc) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        Integer exist = 0;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            //sql = "SELECT 1 FROM user_acc_pss WHERE user_acc = '"+ "test" +"'  LIMIT 1";
            sql = "SELECT 1 FROM user_acc_pss WHERE user_acc = '"+ acc +"'  LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                exist = rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(exist == 0)
        {
            return false;
        } else {
            return true;
        }
    }

    public boolean correct_pss(String acc, String pss){
        Connection conn = null;
        Statement stmt = null;
        String exist = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT user_pss FROM user_acc_pss WHERE user_acc = '"+ acc +"'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                exist = rs.getString("user_pss");
                //System.out.println(exist);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(exist.equals(pss))
        {
            return true;
        } else {
            return false;
        }

    }

    public static void register_user() {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //连接数据库
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //实例化Statement对象
            String Privilege = "costomer";
            java.util.Date date=new Date();//此时date为当前的时间
            //System.out.println(date);
            SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");//设置当前时间的格式，为年-月-日
            String nowdate = dateFormat.format(date);

            stmt = conn.createStatement();
            String sql;
            sql = "insert into user_acc_pss (user_name,user_acc,user_pss,privilege,user_limit_day,registration_date) values(?,?,?,?,1000,NOW())";//向login表里删除数据
            //注：几个问号几个ps.setString，上面的语句中只有一个?,所以下面只有一个ps.setString
            ps=conn.prepareStatement(sql);
            ps.setString(1, Register_Frame.getJTF1());
            ps.setString(2, Register_Frame.getJTF2());
            ps.setString(3, Register_Frame.getJTF3());
            ps.setString(4, Privilege);

            //4.excuteUpdate
            int resultSet=ps.executeUpdate();
            if(resultSet>0){
                //如果插入成功，则打印success
                //System.out.println("Sucess");
            }else{
                //如果插入失败，则打印Failure
                //System.out.println("Failure");
            }
            // 完成后关闭
            ps.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        //System.out.println("数据删除成功");

    }
    public static boolean existacc(String acc) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        Integer exist = 0;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //连接数据库
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //实例化Statement对

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT 1 FROM user_acc_pss WHERE user_acc = '"+ acc +"'  LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                exist = rs.getInt(1);
            }

            // 完成后关闭

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        if(exist == 0)
        {
            return false;
        } else {
            return true;
        }
    }
    public static void exchange_LIMIT(Connection connection, String name, String getUSD){
        PreparedStatement ps=null;
        try {
            String sql="UPDATE user_acc_pss SET user_limit_day = ?  WHERE user_name= '"+ name +"'";
            ps=connection.prepareStatement(sql);
            ps.setString(1, getUSD);
            int resultSet=ps.executeUpdate();
            if(resultSet>0){
                //如果插入成功，则打印success
                //System.out.println("Sucess");
                //System.out.println(name);

            }else{
                //如果插入失败，则打印Failure
                // System.out.println("Failure");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void delect_user() {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //连接数据库
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //实例化Statement对象
            stmt = conn.createStatement();
            String sql;
            sql = "UPDATE user_acc_pss SET privilege = ?  WHERE user_id = '"+ Change_User_Permissions_Frame.getJTF1()+"'" ;//向login表里删除数据
            //注：几个问号几个ps.setString，上面的语句中只有一个?,所以下面只有一个ps.setString
            ps = conn.prepareStatement(sql);//删除数据预处理
            ps.setString(1, Change_User_Permissions_Frame.getCombox());//第1个问号的值"5433"
            ps.executeUpdate();//执行删除数据
            // 完成后关闭
            ps.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        //System.out.println("数据改变成功");

    }

    public static Vector getRows(){
        Connection conn;
        PreparedStatement preparedStatement = null;

        Vector rows = null;
        try {
            Class.forName(JDBC_DRIVER);	//连接驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS);	//连接数据库
//			if(!conn.isClosed())
//				System.out.println("成功连接数据库");
            preparedStatement = conn.prepareStatement("select * from user_acc_pss ");
            ResultSet result1 = preparedStatement.executeQuery();

            rows = new Vector();

            ResultSetMetaData rsmd = result1.getMetaData();

            while(result1.next()){
                rows.addElement(getNextRow(result1,rsmd));
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            //System.out.println("未成功加载驱动。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //System.out.println("未成功打开数据库。");
            e.printStackTrace();
        }

        return rows;
    }

    // 得到数据库表头
    public static Vector getHead(){
        Connection conn;
        PreparedStatement preparedStatement = null;
        Vector columnHeads = null;
        try {
            Class.forName(JDBC_DRIVER);		//连接驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS);	//连接数据库
//			if(!conn.isClosed())
//				System.out.println("成功连接数据库");
            preparedStatement = conn.prepareStatement("select * from user_acc_pss");
            ResultSet result1 = preparedStatement.executeQuery();

            boolean moreRecords = result1.next();
            if(!moreRecords)
                JOptionPane.showMessageDialog(null, "结果集中无记录");

            columnHeads = new Vector();
            ResultSetMetaData rsmd = result1.getMetaData();
            for(int i = 1; i <= rsmd.getColumnCount(); i++)
                columnHeads.addElement(rsmd.getColumnName(i));

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("未成功加载驱动。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("未成功打开数据库。");
            e.printStackTrace();
        }
        return columnHeads;
    }
    // 得到数据库中下一行数据
    private static Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException{
        Vector currentRow = new Vector();
        for(int i = 1; i <= rsmd.getColumnCount(); i++){
            currentRow.addElement(rs.getString(i));
        }
        return currentRow;
    }
}
