package Histroy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistroyController extends JPanel implements ActionListener {
    private JButton back;		// 各处理按钮
    private JButton delect;
    private HistroyFrame dbHistroyFrame;
    HistroyController(HistroyFrame dbHistroyFrame1){
        dbHistroyFrame = dbHistroyFrame1;
        back = new JButton("back");
        delect = new JButton("delect");
        this.add(back);
        this.add(delect);

        back.addActionListener(this);
        delect.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            new PrinrFrame();
            dbHistroyFrame.setVisible(false);
        }else if(e.getSource() == delect){
            new Delect_Histroy_Frame().getFarFrame(dbHistroyFrame);
        }
    }
}
