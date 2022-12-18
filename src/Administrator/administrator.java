package Administrator;


import Exchange_MAIN.MainFrame;
import Histroy.PrinrFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class administrator extends JFrame implements ActionListener {


    private static String acc;

    public static String getAcc() {
        return acc;
    }

    public static void setAcc(String acc) {
        administrator.acc = acc;
    }

    private static String Privilege;

    public static String getPrivilege() {
        return Privilege;
    }

    public static void setPrivilege(String privilege) {
        Privilege = privilege;
    }

    private final int my_width = 600;
    private final int my_height = 400;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private PrinrFrame PF;
    private JLabel Window_Title_Lable;
    private JPanel Window_Title_Panel;
    private JPanel Window_Button_Panel;
    private JPanel jPanel3;

    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JLabel jLabel3;
    private ImageIcon image;

    public administrator() {


        this.setTitle("exchange office");
        this.setSize(my_width, my_height);
        this.setLocationRelativeTo(null);

        this.setAcc(MainFrame.getAcc());
        this.setPrivilege(MainFrame.getPrivilege());
        image = new ImageIcon("src/exchange/icon.png");
        this.setIconImage(image.getImage());

        Color myWhite = new Color(0, 200, 0);
        Container con = this.getContentPane();
        con.setBackground(Color.white);

        Dimension preferredSize = new Dimension(200, 50);
        Dimension preferredSize1 = new Dimension(600, 100);
        setLayout(new BorderLayout(0, 30));
        Window_Title_Panel = new JPanel();

        Window_Title_Panel.setBackground(myWhite);
        Window_Title_Panel.setPreferredSize(preferredSize1);
        Window_Title_Panel.setLayout(new BorderLayout());

        Window_Title_Lable = new JLabel("Обменник");
        Font f = new Font("Times New Roman", Font.PLAIN, 40);
        Window_Title_Lable.setFont(f);
        Window_Title_Lable.setHorizontalAlignment(SwingConstants.CENTER);
        Window_Title_Panel.add(Window_Title_Lable);

        Window_Button_Panel = new JPanel();
        Window_Button_Panel.setLayout(new GridLayout(3, 1));
        Window_Button_Panel.setBackground(Color.white);

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


        Window_Button_Panel.add(jPanel4);
        Window_Button_Panel.add(jPanel5);
        Window_Button_Panel.add(jPanel6);
        Window_Button_Panel.add(jPanel7);

        jPanel3 = new JPanel();
        jPanel3.setBackground(Color.white);
        jPanel3.setLayout(new BorderLayout());
        image.setImage(image.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        jLabel3 = new JLabel(image);
        jLabel3.setSize(50, 50);
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel3.add(jLabel3);


        this.add(Window_Title_Panel, BorderLayout.NORTH);
        this.add(Window_Button_Panel, BorderLayout.CENTER);
        this.add(jPanel3, BorderLayout.EAST);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            this.setVisible(false);
            new dbHistroyDay_person();
        }
        if (e.getSource() == b3) {
            this.setVisible(false);
            new user_frame();
        }
        if (e.getSource() == b4) {
            this.setVisible(false);
            new MainFrame();
        }if(e.getSource()==b2){
            this.setVisible(false);
            new dbHistroyDay();
        }
    }


}
