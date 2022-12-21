package Administrator;

import Exchange_MAIN.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Change_User_Permissions_Frame extends JFrame implements ActionListener {


    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";



    private final int my_width = 400;
    private final int my_height = 300;
    private ImageIcon image;
    private static JTextField JTF1;
    private JPanel jPanel;

    private JPanel jPanel1;
    private JPanel jPanel2;


    private ChangeUserController changeUserController;

    private static JComboBox comboBox;
    private JLabel jLabel;


    Change_User_Permissions_Frame() {
        this.setTitle("change personal permissions");
        this.setSize(my_width, my_height);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.white);

        image = new ImageIcon("src/exchange/icon.png");
        this.setIconImage(image.getImage());
        this.setLayout(new BorderLayout());
        JTF1 = new JTextField(10);
        jLabel = new JLabel("delect account id: ");

        comboBox = new JComboBox();
        String[] strArr = {"costomer", "employee", "administrator"};

        for (String item : strArr)
        {
            comboBox.addItem(item);

        }
        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout());
        jPanel2 = new JPanel();

        jPanel1.add(jLabel);
        jPanel1.add(JTF1);
        jPanel1.add(comboBox);

        jPanel.add(jPanel1);

        changeUserController = new ChangeUserController(this);


        this.add(jPanel1, BorderLayout.CENTER);
        this.add(changeUserController, BorderLayout.SOUTH);


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static String getJTF1() {
        return JTF1.getText();
    }
    public static String getCombox(){return comboBox.getSelectedItem().toString();}


}





