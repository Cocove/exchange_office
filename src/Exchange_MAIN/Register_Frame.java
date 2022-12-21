package Exchange_MAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register_Frame extends JFrame implements ActionListener {


    private dbUserDATA dbUserdata = new dbUserDATA();
    private Register_Controller register_controller;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    private final int my_width = 400;
    private final int my_height = 300;
    private ImageIcon image;
    private static JTextField JTF1;
    private static JTextField JTF2;
    private static JTextField JTF3;
    private JPanel jPanel;
    private JPanel jPanel1;
    private JPanel jPanel2;

    private JPanel jPanel3;

    private JPanel jPanel4;


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


        register_controller = new Register_Controller(this);

        jPanel1.add(jLabel);
        jPanel1.add(JTF1);

        jPanel2.add(jLabel1);
        jPanel2.add(JTF2);
        jPanel3.add(jLabel2);
        jPanel3.add(JTF3);


        jPanel.add(jPanel1);
        jPanel.add(jPanel2);
        jPanel.add(jPanel3);



        this.add(jPanel, BorderLayout.CENTER);
        this.add(register_controller, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static String getJTF1() {
        return JTF1.getText();
    }
    public static String getJTF2() {
        return JTF2.getText();
    }
    public static String getJTF3() {
        return JTF3.getText();
    }




}

