package Administrator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Delect_User_Frame extends JFrame implements ActionListener {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    private user_frame userFrame;

    private final int my_width = 400;
    private final int my_height = 300;
    private ImageIcon image;
    private JTextField JTF1;
    private JPanel jPanel;

    private JPanel jPanel1;
    private JPanel jPanel2;
    public static JButton b1;


    private JLabel jLabel;


    Delect_User_Frame() {
        this.setTitle("delete user interface");
        this.setSize(my_width, my_height);
        this.setLocationRelativeTo(null);

        image = new ImageIcon("src/exchange/icon.png");
        this.setIconImage(image.getImage());
        this.setLayout(new BorderLayout());
        JTF1 = new JTextField(10);
        jLabel = new JLabel("delect account id: ");

        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout());
        jPanel2 = new JPanel();
        jPanel2.setLayout(new FlowLayout());
        jPanel1.add(jLabel);
        jPanel1.add(JTF1);

        jPanel.add(jPanel1);

        b1 = new JButton("submit");

        jPanel2.add(b1);


        this.add(jPanel1, BorderLayout.CENTER);
        this.add(jPanel2, BorderLayout.SOUTH);
        b1.addActionListener(this);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            delect_user();
            this.setVisible(false);
            userFrame.setVisible(false);
            new user_frame();

        }
    }

    public String getJTF1() {
        return JTF1.getText();
    }

    private void delect_user(){
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps=null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //连接数据库
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            //实例化Statement对象
            stmt = conn.createStatement();
            String sql;
            sql="DELETE FROM user_acc_pss WHERE user_id=?";//向login表里删除数据
            //注：几个问号几个ps.setString，上面的语句中只有一个?,所以下面只有一个ps.setString
            ps=conn.prepareStatement(sql);//删除数据预处理
            ps.setString(1, getJTF1());//第1个问号的值"5433"
            ps.executeUpdate();//执行删除数据
            sql="alter table user_acc_pss drop column user_id";
            ps=conn.prepareStatement(sql);
            ps.executeUpdate();
            sql="alter table user_acc_pss add user_id INT(15) not null primary key auto_increment first";
            ps=conn.prepareStatement(sql);
            ps.executeUpdate();
            // 完成后关闭
            ps.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        //System.out.println("数据删除成功");

    }



    public void getFarFrame(user_frame userFrame){
        this.userFrame = userFrame;
        //System.out.println("父窗口");
    }
}


