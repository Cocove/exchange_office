package Histroy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DelectHistroyController extends JPanel implements ActionListener {

    public static JButton b1;
    Delect_Histroy_Frame delectHistroyFrame;

    DelectHistroyController(Delect_Histroy_Frame delectHistroyFrame1){
        delectHistroyFrame = delectHistroyFrame1;
        b1 = new JButton("submit");
        this.add(b1);
        b1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            dbHistory.delect_histroy();
            delectHistroyFrame.setVisible(false);
            Delect_Histroy_Frame.userFrame.setVisible(false);
            new HistroyFrame();
            Limit_get_Money.LimitMoney();

        }
    }
}
