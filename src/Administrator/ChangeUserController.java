package Administrator;

import Exchange_MAIN.dbUserDATA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ChangeUserController  extends JPanel implements ActionListener {
    private static user_frame userFrame;
    public static JButton b1;
    Change_User_Permissions_Frame changeUserPermissionsFrame;
    ChangeUserController(Change_User_Permissions_Frame changeUserPermissionsFrame1){
        this.setLayout(new FlowLayout());
        changeUserPermissionsFrame = changeUserPermissionsFrame1;
        b1 = new JButton("submit");

        this.add(b1);
        b1.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            dbUserDATA.delect_user();
            changeUserPermissionsFrame.setVisible(false);
            userFrame.setVisible(false);
            new user_frame();

        }
    }

    public static void getFarFrame(user_frame userFrame1) {
        userFrame = userFrame1;
        //System.out.println("父窗口");
    }
}
