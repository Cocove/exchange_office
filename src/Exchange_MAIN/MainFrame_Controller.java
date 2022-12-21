package Exchange_MAIN;

import Administrator.administrator_Frame;
import Deposit.USER_deposit_Frame;
import Histroy.PrinrFrame;
import Rate.Rate_Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame_Controller extends JPanel implements ActionListener {

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;

    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;

    private MainFrame mainFrame;


    MainFrame_Controller(MainFrame mainFrame1){

        mainFrame = mainFrame1;

        this.setLayout(new GridLayout(4, 1));
        this.setBackground(Color.white);

        Dimension preferredSize = new Dimension(200, 50);

        jPanel4 = new JPanel();
        jPanel4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel4.setBackground(Color.white);
        jPanel5 = new JPanel();
        jPanel5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel5.setBackground(Color.white);
        jPanel6 = new JPanel();
        jPanel6.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel6.setBackground(Color.white);
        jPanel7 = new JPanel();
        jPanel7.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel7.setBackground(Color.white);
        jPanel8 = new JPanel();
        jPanel8.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel8.setBackground(Color.white);
        jPanel9 = new JPanel();
        jPanel9.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel9.setBackground(Color.white);


        b1 = new JButton("transaction interface");
        b1.setFocusPainted(false);
        b2 = new JButton("rate interface");
        b3 = new JButton("asset interface");
        b4 = new JButton("administrator");
        b5 = new JButton("exit");
        b6 = new JButton("exchange_user");

        b1.setPreferredSize(preferredSize);
        b2.setPreferredSize(preferredSize);
        b3.setPreferredSize(preferredSize);
        b4.setPreferredSize(preferredSize);
        b5.setPreferredSize(preferredSize);
        b6.setPreferredSize(preferredSize);

        jPanel4.add(b1);

        jPanel5.add(b2);

        jPanel6.add(b3);

        jPanel7.add(b4);

        jPanel8.add(b5);

        jPanel9.add(b6);

        this.add(jPanel4);
        this.add(jPanel5);
        this.add(jPanel6);
        this.add(jPanel7);
        this.add(jPanel8);
        this.add(jPanel9);

        if (!MainFrame.getPrivilege().equals("administrator")) {
            jPanel7.setVisible(false);

        }
        if(MainFrame.getPrivilege().equals("costomer")){
            jPanel9.setVisible(false);
        }



        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            mainFrame.setVisible(false);
            new PrinrFrame();
        }
        if (e.getSource() == b2) {
            mainFrame.setVisible(false);
            new Rate_Frame();
        }
        if (e.getSource() == b3) {
            mainFrame.setVisible(false);
            new USER_deposit_Frame();
        }
        if (e.getSource() == b4) {
            mainFrame.setVisible(false);
            new administrator_Frame();
            //Limit_get_Money.LimitMoney();
        }
        if (e.getSource() == b5) {
            mainFrame.setVisible(false);
            new LoginFrame();

        }
        if (e.getSource() == b6) {
            new set_up_account().getFarFrame(mainFrame);
        }
    }
}
