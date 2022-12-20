package Exchange_MAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register_Frame extends JFrame implements ActionListener {


    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    private final int my_width = 400;
    private final int my_height = 300;
    private ImageIcon image;
    private JTextField JTF1;
    private JTextField JTF2;
    private JTextField JTF3;
    private JPanel jPanel;
    private JPanel jPanel1;
    private JPanel jPanel2;

    private JPanel jPanel3;

    private JPanel jPanel4;

    private JButton b1;
    private JComboBox comboBox1;
    private JLabel jLabel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    String name = null;

    Register_Frame() {
        this.setTitle("Registration interface");
        this.setSize(my_width, my_height);
        this.setLocationRelativeTo(null);

        image = new ImageIcon("src/exchange/icon.png");
        this.setIconImage(image.getImage());
        this.setLayout(new BorderLayout());
        JTF1 = new JTextField(10);
        JTF2 = new JTextField(10);
        JTF3 = new JTextField(10);
        jLabel = new JLabel("user_name: ");
        jLabel1 = new JLabel("user_account: ");
        jLabel2 = new JLabel("user_password: ");

        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3,1));

        jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout());
        jPanel2 = new JPanel();
        jPanel2.setLayout(new FlowLayout());
        jPanel3 = new JPanel();
        jPanel3.setLayout(new FlowLayout());
        jPanel4 = new JPanel();
        jPanel4.setLayout(new FlowLayout());



        jPanel1.add(jLabel);
        jPanel1.add(JTF1);

        jPanel2.add(jLabel1);
        jPanel2.add(JTF2);
        jPanel3.add(jLabel2);
        jPanel3.add(JTF3);


        jPanel.add(jPanel1);
        jPanel.add(jPanel2);
        jPanel.add(jPanel3);

        b1 = new JButton("submit");


        this.add(jPanel, BorderLayout.CENTER);
        this.add(b1, BorderLayout.SOUTH);
        b1.addActionListener(this);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                if(existacc(getJTF2())){
                    JOptionPane.showMessageDialog(null, "存在此用户名", "标题",JOptionPane.WARNING_MESSAGE);
                }else {
                    register_user();
                    this.setVisible(false);
                    new LoginFrame();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public String getJTF1() {
        return JTF1.getText();
    }
    public String getJTF2() {
        return JTF2.getText();
    }
    public String getJTF3() {
        return JTF3.getText();
    }

    private void register_user() {
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
            Date date=new Date();//此时date为当前的时间
            //System.out.println(date);
            SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");//设置当前时间的格式，为年-月-日
            String nowdate = dateFormat.format(date);

            stmt = conn.createStatement();
            String sql;
            sql = "insert into user_acc_pss (user_name,user_acc,user_pss,privilege,user_limit_day,registration_date) values(?,?,?,?,1000,NOW())";//向login表里删除数据
            //注：几个问号几个ps.setString，上面的语句中只有一个?,所以下面只有一个ps.setString
            ps=conn.prepareStatement(sql);
            ps.setString(1, getJTF1());
            ps.setString(2, getJTF2());
            ps.setString(3, getJTF3());
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


}

