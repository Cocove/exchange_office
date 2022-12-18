package Exchange_MAIN;

import Histroy.PrinrFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class LoginFrame extends JFrame implements ActionListener{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    private final int my_width = 600;
    private final int my_height = 400;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel Window_Title_Lable;

    private JTextField JTF1;
    private JTextField JTF2;

    private JButton b1;
    private JButton b2;

    private JPanel jPanel;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel Window_Title_Panel;
    LoginFrame(){

        this.setTitle("exchange office");
        this.setSize(my_width,my_height);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        Color myWhite = new Color(0, 200, 0);
        Container con=this.getContentPane();
        con.setBackground(Color.white);
        Dimension preferredSize1 = new Dimension(600,100);
        Window_Title_Panel = new JPanel();
        Window_Title_Panel.setBackground(myWhite);
        Window_Title_Panel.setPreferredSize(preferredSize1);
        Window_Title_Panel.setLayout(new BorderLayout());
        Window_Title_Lable = new JLabel();
        Window_Title_Lable = new JLabel("Обменник");
        Font f = new Font("Times New Roman",Font.PLAIN,40);
        Window_Title_Lable.setFont(f);
        Window_Title_Lable.setHorizontalAlignment(SwingConstants.CENTER);
        Window_Title_Panel.add(Window_Title_Lable);
        jLabel1 = new JLabel("account");
        jLabel2 = new JLabel("password");

        JTF1 = new JTextField(10);
        JTF2 = new JTextField(10);

        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(2,1) );
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel1.add(jLabel1);
        jPanel1.add(JTF1);
        jPanel2.add(jLabel2);
        jPanel2.add(JTF2);

        jPanel.add(jPanel1);
        jPanel.add(jPanel2);

        jPanel3 = new JPanel();
        jPanel3.setLayout(new FlowLayout() );
        b1 = new JButton("LOG");
        b2 = new JButton("register");
        jPanel3.add(b1);
        jPanel3.add(b2);

        this.add(Window_Title_Panel,BorderLayout.NORTH);
        this.add(jPanel,BorderLayout.CENTER);
        this.add(jPanel3,BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public String getJTF1() {
        return JTF1.getText().toString();
    }

    public String getJTF2() {
        return JTF2.getText().toString();
    }

    public static void main(String[] args) throws IOException {
        //主窗口
        JFrame MainFrame = new LoginFrame();
        MainFrame.setVisible(true);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    String Privilege(String acc) throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        String exist = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT privilege FROM user_acc_pss WHERE user_acc = '"+ acc +"'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                exist = rs.getString("privilege");
                //System.out.println(exist);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return exist;

    }

    private boolean exist_acc(String acc) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        Integer exist = 0;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            //sql = "SELECT 1 FROM user_acc_pss WHERE user_acc = '"+ "test" +"'  LIMIT 1";
            sql = "SELECT 1 FROM user_acc_pss WHERE user_acc = '"+ acc +"'  LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                exist = rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(exist == 0)
        {
            return false;
        } else {
            return true;
        }
    }

    private boolean correct_pss(String acc, String pss){
        Connection conn = null;
        Statement stmt = null;
        String exist = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT user_pss FROM user_acc_pss WHERE user_acc = '"+ acc +"'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                exist = rs.getString("user_pss");
                //System.out.println(exist);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(exist.equals(pss))
        {
            return true;
        } else {
            return false;
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            try {
                if(exist_acc(getJTF1())){
                    if(correct_pss(getJTF1(), getJTF2())){
                        System.out.println("登陆成功");
                        this.setVisible(false);
                        PrinrFrame.setAcc(getJTF1());
                        MainFrame.setAcc(getJTF1());
                        MainFrame.setPrivilege(Privilege(getJTF1()));

                        new MainFrame();
                    }else{
                        System.out.println("密码错误");
                        JOptionPane.showMessageDialog(null, "密码错误", "警告",JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    System.out.println("不存在此用户名");
                    JOptionPane.showMessageDialog(null, "用户不存在", "警告",JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }else if(e.getSource() == b2){
            new Register_Frame();
        }
    }


}
