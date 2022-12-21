package Exchange_MAIN;

import Deposit.dbUserDeposit;
import Histroy.PrinrFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class set_up_account extends JFrame implements ActionListener {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    static MainFrame userFrame;
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    private final int my_width = 400;
    private final int my_height = 300;
    private ImageIcon image;
    private static JTextField JTF1;
    private JPanel jPanel;
    private JPanel jPanel1;
    private JComboBox comboBox1;
    private JLabel jLabel;
    String name = null;

    private setaccountController setaccountController;

    set_up_account() {
        this.setTitle("Enter the account to be operated");
        this.setSize(my_width, my_height);
        this.setLocationRelativeTo(null);

        image = new ImageIcon("src/exchange/icon.png");
        this.setIconImage(image.getImage());
        this.setLayout(new BorderLayout());
        JTF1 = new JTextField(10);
        jLabel = new JLabel("account: ");

        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(jLabel);
        jPanel1.add(JTF1);
        jPanel.add(jPanel1);


        setaccountController = new setaccountController(this);

        this.add(jPanel, BorderLayout.CENTER);
        this.add(setaccountController, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static String getJTF1() {
        return JTF1.getText();
    }




    public void getFarFrame(MainFrame userFrame){
        this.userFrame = userFrame;
        //System.out.println("父窗口");
    }
}
    

