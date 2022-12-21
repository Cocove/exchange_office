package Administrator;

import Exchange_MAIN.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminController extends JPanel implements ActionListener {

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;

    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;

    private administrator_Frame administrator;

    adminController(administrator_Frame administrator1){


        this.setLayout(new GridLayout(3, 1));
        this.setBackground(Color.white);

        Dimension preferredSize = new Dimension(200, 50);
        administrator = administrator1;

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
        b1 = new JButton("histroy_day_personl");
        b1.setFocusPainted(false);
        b3 = new JButton("user_date");
        b4 = new JButton("back");
        b2 = new JButton("histroy_day");

        b1.setPreferredSize(preferredSize);
        b2.setPreferredSize(preferredSize);
        b3.setPreferredSize(preferredSize);
        b4.setPreferredSize(preferredSize);
        jPanel4.add(b1);

        jPanel5.add(b2);

        jPanel6.add(b3);

        jPanel7.add(b4);
        this.add(jPanel4);
        this.add(jPanel5);
        this.add(jPanel6);
        this.add(jPanel7);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            administrator.setVisible(false);
            new HistroyDay_person_Frame();
        }
        if (e.getSource() == b3) {
            administrator.setVisible(false);
            new user_frame();
        }
        if (e.getSource() == b4) {
            administrator.setVisible(false);
            new MainFrame();
        }if(e.getSource()==b2){
            administrator.setVisible(false);
            new HistroyDay_Frame();
        }
    }
}
