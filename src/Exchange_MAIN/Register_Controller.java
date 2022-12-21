package Exchange_MAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Register_Controller extends JPanel implements ActionListener {
    private JButton b1;
    Register_Frame register_frame;

    Register_Controller(Register_Frame register_frame1){
        register_frame = register_frame1;
        b1 = new JButton("submit");
        this.add(b1);
        b1.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                if(dbUserDATA.existacc(Register_Frame.getJTF2())){
                    JOptionPane.showMessageDialog(null, "存在此用户名", "标题",JOptionPane.WARNING_MESSAGE);
                }else {
                    dbUserDATA.register_user();
                    register_frame.setVisible(false);
                    new LoginFrame();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
}
