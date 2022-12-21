package Deposit;

import Exchange_MAIN.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class depositController extends JPanel implements ActionListener {

    private JButton back;		// 各处理按钮
    private JButton set_LIMIT;
    private USER_deposit_Frame user_deposit_frame;
    depositController(USER_deposit_Frame user_deposit_frame1){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        user_deposit_frame = user_deposit_frame1;
        back = new JButton("back");
        set_LIMIT = new JButton("set limit");


        if (!MainFrame.getPrivilege().equals("administrator")) {
            set_LIMIT.setVisible(false);

        }
        this.add(back);
        this.add(set_LIMIT);

        if(MainFrame.getPrivilege().equals("costomer")){
            set_LIMIT.setVisible(false);
        }
        back.addActionListener(this);
        set_LIMIT.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            new MainFrame();
            user_deposit_frame.setVisible(false);
        }if(e.getSource() == set_LIMIT){
            new set_LIMIT_Frame();
        }
    }
}
