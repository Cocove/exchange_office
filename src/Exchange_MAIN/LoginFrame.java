package Exchange_MAIN;

import Deposit.dbUserDeposit;
import Histroy.PrinrFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class LoginFrame extends JFrame implements ActionListener{

    private dbUserDATA userDATA = new dbUserDATA();
    private LoginController loginController;

    private final int my_width = 600;
    private final int my_height = 400;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel Window_Title_Lable;

    private static JTextField JTF1;
    private static JTextField JTF2;

    private JButton b1;
    private JButton b2;

    private String str1;
    private String str2;

    private JPanel jPanel;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel Window_Title_Panel;
    LoginFrame(){

        this.setTitle("login interface");
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
        Window_Title_Lable = new JLabel("login interface");
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
        loginController = new LoginController(this);

        this.add(Window_Title_Panel,BorderLayout.NORTH);
        this.add(jPanel,BorderLayout.CENTER);
        this.add(loginController,BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public static String getJTF1() {
        return JTF1.getText().toString();
    }

    public static String getJTF2() {
        return JTF2.getText().toString();
    }






    public static void main(String[] args) throws IOException {
        //主窗口
        JFrame MainFrame = new LoginFrame();
        MainFrame.setVisible(true);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            try {
                if(userDATA.exist_acc(getJTF1())){
                    if(userDATA.correct_pss(getJTF1(), getJTF2())){
                        //System.out.println("登陆成功");
                        this.setVisible(false);
                        PrinrFrame.setAcc(getJTF1());
                        MainFrame.setAcc(getJTF1());
                        MainFrame.setPrivilege(userDATA.Privilege(getJTF1()));

                        Deter_user_permiss();
                    }else{
                        //System.out.println("密码错误");
                        JOptionPane.showMessageDialog(null, "wrong password", "error",JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    //System.out.println("不存在此用户名");
                    JOptionPane.showMessageDialog(null, "User does not exist", "error",JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }else if(e.getSource() == b2){
            new Register_Frame();
        }
    }

    private void Deter_user_permiss() throws ClassNotFoundException, SQLException {
        if (!userDATA.Privilege(getJTF1()).equals("costomer")) {
            new set_up_account();
        } else {
            PrinrFrame.setAcc1(this.getJTF1());
            dbUserDeposit.setAcc(this.getJTF1());
            new MainFrame();
        }
    }

}
