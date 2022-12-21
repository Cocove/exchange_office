package Administrator;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class dbHistroyday {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "1230zxc..";
    public static Vector getRows() {
        Connection conn;
        PreparedStatement preparedStatement = null;

        Vector rows = null;
        try {
            Class.forName(JDBC_DRIVER);    //连接驱动
            conn = DriverManager.getConnection(DB_URL, USER, PASS);    //连接数据库
//			if(!conn.isClosed())
//				System.out.println("成功连接数据库");
            preparedStatement = conn.prepareStatement("select * from day_amout ");
            ResultSet result1 = preparedStatement.executeQuery();

            rows = new Vector();

            ResultSetMetaData rsmd = result1.getMetaData();

            while (result1.next()) {
                rows.addElement(getNextRow(result1, rsmd));
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
    public static Vector getHead() {
        Connection conn;
        PreparedStatement preparedStatement = null;
        Vector columnHeads = null;
        try {
            Class.forName(JDBC_DRIVER);        //连接驱动
            conn = DriverManager.getConnection(DB_URL, USER, PASS);    //连接数据库
//			if(!conn.isClosed())
//				System.out.println("成功连接数据库");
            preparedStatement = conn.prepareStatement("select * from day_amout");
            ResultSet result1 = preparedStatement.executeQuery();

            boolean moreRecords = result1.next();
            if (!moreRecords)
                JOptionPane.showMessageDialog(null, "结果集中无记录");

            columnHeads = new Vector();
            ResultSetMetaData rsmd = result1.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                columnHeads.addElement(rsmd.getColumnName(i));

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            //System.out.println("未成功加载驱动。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //System.out.println("未成功打开数据库。");
            e.printStackTrace();
        }
        return columnHeads;
    }

    // 得到数据库中下一行数据
    private static Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
        Vector currentRow = new Vector();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            currentRow.addElement(rs.getString(i));
        }
        return currentRow;
    }
}
