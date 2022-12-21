package Histroy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delect_Histroy_Frame extends JFrame implements ActionListener {


    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    public static HistroyFrame userFrame;

    private final int my_width = 400;
    private final int my_height = 300;
    private ImageIcon image;
    private static JTextField JTF1;
    private JPanel jPanel;

    private JPanel jPanel1;
    private JPanel jPanel2;

    private DelectHistroyController delectHistroyController;

    private JLabel jLabel;


    Delect_Histroy_Frame() {
        this.setTitle("Delete history interface");
        this.setSize(my_width, my_height);
        this.setLocationRelativeTo(null);

        image = new ImageIcon("src/exchange/icon.png");
        this.setIconImage(image.getImage());
        this.setLayout(new BorderLayout());
        JTF1 = new JTextField(10);
        jLabel = new JLabel("delect histroy id: ");

        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout());
        jPanel2 = new JPanel();
        jPanel2.setLayout(new FlowLayout());
        jPanel1.add(jLabel);
        jPanel1.add(JTF1);

        jPanel.add(jPanel1);

        delectHistroyController = new DelectHistroyController(this);




        this.add(jPanel1, BorderLayout.CENTER);
        this.add(delectHistroyController, BorderLayout.SOUTH);


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static String getJTF1() {
        return JTF1.getText();
    }



    public static void getFarFrame(HistroyFrame userFram1) {
        userFrame = userFram1;
        //System.out.println("父窗口");
    }
}




