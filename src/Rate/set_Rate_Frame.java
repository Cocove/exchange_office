package Rate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class set_Rate_Frame extends JFrame implements ActionListener {


    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    private final int my_width = 400;
    private final int my_height = 300;
    private ImageIcon image;

    private JTextField JTF1;
    private JPanel jPanel;
    private JPanel jPanel1;

    private JComboBox comboBox1;
    Connection conn = null;
    Statement stmt = null;
    String name = null;
    String money = null;
    setrateController setrateController;

    set_Rate_Frame() {

        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            // System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            this.setTitle("Set the exchange rate interface");
            this.setSize(my_width, my_height);
            this.setLocationRelativeTo(null);

            image = new ImageIcon("src/exchange/icon.png");
            this.setIconImage(image.getImage());
            this.setLayout(new BorderLayout());
            JTF1 = new JTextField(10);
            name = JTF1.getText();
            comboBox1 = new JComboBox();
            String[] strArr = {"EUR", "CNY", "BYN"};
            for (String item : strArr) {
                comboBox1.addItem(item);
            }
            jPanel = new JPanel();
            jPanel.setLayout(new FlowLayout());

            jPanel1 = new JPanel();
            jPanel1.setLayout(new FlowLayout());
            jPanel1.add(comboBox1);
            jPanel1.add(JTF1);


            jPanel.add(jPanel1);

            setrateController = new setrateController(this);


            this.add(jPanel, BorderLayout.CENTER);
            this.add(setrateController, BorderLayout.SOUTH);


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

    }

    public String getJTF1() {
        return JTF1.getText();
    }


    public String getSelcte() {
        return comboBox1.getSelectedItem().toString();
    }



}
