package Deposit;

import Exchange_MAIN.dbUserDATA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class set_LIMIT_Frame extends JFrame implements ActionListener {


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
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JButton b1;
    Connection conn = null;
    Statement stmt = null;
    String name = null;
    String money = null;

    set_LIMIT_Frame(){

        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            this.setTitle("Set User Limit_get_money Interface");
            this.setSize(my_width,my_height);
            this.setLocationRelativeTo(null);

            image = new ImageIcon("src/exchange/icon.png");
            this.setIconImage(image.getImage());


            this.setLayout(new BorderLayout());

            JTF1 = new JTextField(10);
            JTF2 = new JTextField(10);
            jLabel1 = new JLabel("name:");
            jLabel2 = new JLabel("money:");
            name = JTF1.getText();
            money = JTF2.getText();

            jPanel =new JPanel();
            jPanel.setLayout(new FlowLayout());


            jPanel1 =new JPanel();
            jPanel1.setLayout(new FlowLayout());
            jPanel1.add(jLabel1);
            jPanel1.add(JTF1);

            jPanel2 =new JPanel();
            jPanel2.setLayout(new FlowLayout());
            jPanel2.add(jLabel2);
            jPanel2.add(JTF2);
            jPanel.add(jPanel1);
            jPanel.add(jPanel2);

            b1 = new JButton("submit");
            this.add(jPanel,BorderLayout.CENTER);
            this.add(b1,BorderLayout.SOUTH);

            b1.addActionListener(this);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            name = getJTF1();
            money = getJTF2();
            dbUserDATA.exchange_LIMIT(conn,name,money);
            //System.out.println(getJTF1());
        }
    }

    public String getJTF1() {
        return JTF1.getText();
    }

    public String getJTF2() {
        return JTF2.getText();
    }


}
