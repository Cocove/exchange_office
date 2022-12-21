package Administrator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userframeController extends JPanel implements ActionListener {
    private JButton back;		// 各处理按钮
    private JButton delect;
    private JButton change;
    user_frame userFrame;
    userframeController(user_frame userFrame1){
        userFrame = userFrame1;
        back = new JButton("back");
        delect = new JButton("delect");
        change = new JButton("change");
        this.add(back);
        this.add(delect);
        this.add(change);
        back.addActionListener(this);
        delect.addActionListener(this);
        change.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            new administrator_Frame();
            userFrame.setVisible(false);
        }else if(e.getSource() == delect){
            new Delect_User_Frame().getFarFrame(userFrame);
            //this.setVisible(false);
        }else if(e.getSource() == change){
            new Change_User_Permissions_Frame();
            ChangeUserController.getFarFrame(userFrame);
        }
    }
}
