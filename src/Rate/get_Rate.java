package Rate;

import java.sql.*;
import java.util.ArrayList;


public class get_Rate {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    static ArrayList<String> money_rate;
    static ArrayList<String> money;

    public get_Rate() {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement preparedStatement = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();


            getRows();




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static double get_rate(String huobi1,String huobi2){
        double rate1 = 0;
        double rate2 = 0;

        for(int i =0;i<money.size();i++){
            if(money.get(i).equals(huobi1)){
                rate1 = stringToDouble(money_rate.get(i));
            }if(money.get(i).equals(huobi2)){
                rate2= stringToDouble(money_rate.get(i));
            }
        }
        rate2 /= rate1;
        return rate2;

    }

    public static void getRows(){
        Connection conn;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);	//连接驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS);	//连接数据库
            preparedStatement = conn.prepareStatement("select * from day_rate order by id DESC limit 1 ");
            ResultSet result1 = preparedStatement.executeQuery();
            int Index = 0;
            money = new ArrayList();
            money_rate = new ArrayList();
            ResultSetMetaData rsmd = result1.getMetaData();
            for(int i = 2; i < rsmd.getColumnCount(); i++){
                money.add(rsmd.getColumnName(i).toString());
            }
            while(result1.next()){
                for(int i = 0; i < money.size(); i++){
                    money_rate.add(result1.getString(money.get(i)));
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("未成功加载驱动。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("未成功打开数据库。");
            e.printStackTrace();
        }
        //return currentRow;
    }
    public static double stringToDouble(String doublestr) {
        Double Adouble;
        Adouble = Double.parseDouble(doublestr);
        return Adouble;
    }
}
