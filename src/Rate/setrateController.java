package Rate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class setrateController extends JPanel implements ActionListener {

    String name;
    String money;

    private JButton b1;
    set_Rate_Frame setRateFrame;
    setrateController(set_Rate_Frame setRateFrame1){
        setRateFrame = setRateFrame1;
        b1 = new JButton("submit");
        this.add(b1);
        b1.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            name = setRateFrame.getSelcte();
            money = setRateFrame.getJTF1();
            dbRate.exchange_Rate(setRateFrame.conn, name, money);
            //System.out.println(getJTF1());
        }
    }
}
