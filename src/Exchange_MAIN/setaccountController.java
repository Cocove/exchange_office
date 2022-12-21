package Exchange_MAIN;

import Deposit.dbUserDeposit;
import Histroy.PrinrFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class setaccountController extends JPanel implements ActionListener {
    private JButton b1;
    set_up_account setUpAccount;

    setaccountController(set_up_account setUpAccount1){
        setUpAccount = setUpAccount1;
        b1 = new JButton("submit");
        this.add(b1);
        b1.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (set_up_account.getJTF1().length() == 0) {
                JOptionPane.showMessageDialog(null, "请输入需要操作的账号", "标题", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    if(dbUserDATA.existacc(set_up_account.getJTF1())){
                        if(set_up_account.userFrame == null){
                            setacc();
                            setUpAccount.setVisible(false);
                            new MainFrame();
                        }else{
                            set_up_account.userFrame.setVisible(false);
                            setacc();
                            setUpAccount.setVisible(false);
                            new MainFrame();
                        }

                    }else {
                        JOptionPane.showMessageDialog(null, "请输入正确的账号", "标题", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }


        }
    }
    private void setacc() {
        PrinrFrame.setAcc1(set_up_account.getJTF1());
        dbUserDeposit.setAcc(set_up_account.getJTF1());
    }

}
