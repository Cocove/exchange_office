package Exchange_MAIN;

import Deposit.dbUserDeposit;
import Histroy.PrinrFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController extends JPanel  implements ActionListener {
    private dbUserDATA userDATA = new dbUserDATA();
    private JButton b1;
    private JButton b2;
    private String str1;
    private String str2;
    private LoginFrame loginFrame;


    LoginController(LoginFrame loginFrame1) {

        loginFrame = loginFrame1;

        b1 = new JButton("LOG");
        b2 = new JButton("register");
        this.add(b1);
        this.add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            try {
                if(userDATA.exist_acc(LoginFrame.getJTF1())){
                    if(userDATA.correct_pss(LoginFrame.getJTF1(), LoginFrame.getJTF2())){
                        //System.out.println("登陆成功");
                        loginFrame.setVisible(false);
                        PrinrFrame.setAcc(LoginFrame.getJTF1());
                        MainFrame.setAcc(LoginFrame.getJTF1());
                        MainFrame.setPrivilege(userDATA.Privilege(LoginFrame.getJTF1()));

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
        if (!userDATA.Privilege(LoginFrame.getJTF1()).equals("costomer")) {
            new set_up_account();
        } else {
            PrinrFrame.setAcc1(LoginFrame.getJTF1());
            dbUserDeposit.setAcc(LoginFrame.getJTF1());
            new MainFrame();
        }
    }
}
