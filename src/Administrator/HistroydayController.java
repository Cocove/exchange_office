package Administrator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistroydayController  extends JPanel implements ActionListener {
    private JButton back;        // 各处理按钮
    HistroyDay_Frame histroyDayFrame;
    HistroydayController(HistroyDay_Frame histroyDayFrame1){
        histroyDayFrame = histroyDayFrame1;
        back = new JButton("back");
        this.add(back);
        back.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new administrator_Frame();
            histroyDayFrame.setVisible(false);
        }
    }
}
