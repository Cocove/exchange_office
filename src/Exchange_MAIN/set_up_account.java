package Exchange_MAIN;

import Administrator.user_frame;
import Deposit.db_USER_deposit_Frame;
import Histroy.PrinrFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class set_up_account extends JFrame implements ActionListener {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    MainFrame userFrame;
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    private final int my_width = 400;
    private final int my_height = 300;
    private ImageIcon image;
    private JTextField JTF1;
    private JPanel jPanel;
    private JPanel jPanel1;
    private JButton b1;
    private JComboBox comboBox1;
    private JLabel jLabel;
    String name = null;

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
            if (getJTF1().length() == 0) {
                JOptionPane.showMessageDialog(null, "请输入需要操作的账号", "标题", JOptionPane.WARNING_MESSAGE);
            } else if(existacc()){
                if(userFrame == null){
                    setacc();
                    this.setVisible(false);
                    new MainFrame();
                }else{
                    userFrame.setVisible(false);
                    setacc();
                    this.setVisible(false);
                    new MainFrame();
                }

            }else {
                JOptionPane.showMessageDialog(null, "请输入正确的账号", "标题", JOptionPane.WARNING_MESSAGE);
            }


        }
    }

    public String getJTF1() {
        return JTF1.getText();
    }

    private void setacc() {
        PrinrFrame.setAcc1(getJTF1());
        db_USER_deposit_Frame.setAcc(this.getJTF1());
    }

    private boolean existacc() {

        Connection conn;
        Statement stmt = null;
        PreparedStatement preparedStatement = null;
        Integer exist = 0;
        try {
            Class.forName(JDBC_DRIVER);    //连接驱动
            conn = DriverManager.getConnection(DB_URL, USER, PASS);    //连接数据库
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT 1 FROM user_acc_pss WHERE user_acc = '" + getJTF1() + "'  LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                exist = rs.getInt(1);
            }


        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            //.out.println("未成功加载驱动。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //System.out.println("未成功打开数据库。");
            e.printStackTrace();
        }
        if (exist == 0) {
            return false;
        } else {
            return true;
        }
    }
    public void getFarFrame(MainFrame userFrame){
        this.userFrame = userFrame;
        //System.out.println("父窗口");
    }
}
    

