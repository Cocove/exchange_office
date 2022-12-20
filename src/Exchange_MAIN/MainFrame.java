package Exchange_MAIN;

import Administrator.administrator;
import Deposit.db_USER_deposit_Frame;
import Histroy.PrinrFrame;
import Rate.Rate_Frame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {


    public static String acc;


    public static String getAcc() {
        return acc;
    }

    public static void setAcc(String acc) {
        MainFrame.acc = acc;
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
    private JButton b5;
    private JButton b6;
    private PrinrFrame PF;
    private JLabel Window_Title_Lable;
    private JPanel Window_Title_Panel;
    private JPanel Window_Button_Panel;
    private JPanel jPanel3;

    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JLabel jLabel3;
    private ImageIcon image;

    public MainFrame() {
        this.setTitle("exchange office");
        this.setSize(my_width, my_height);
        this.setLocationRelativeTo(null);

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

        Window_Title_Lable = new JLabel("exchange office");
        Font f = new Font("Times New Roman", Font.PLAIN, 40);
        Window_Title_Lable.setFont(f);
        Window_Title_Lable.setHorizontalAlignment(SwingConstants.CENTER);
        Window_Title_Panel.add(Window_Title_Lable);

        Window_Button_Panel = new JPanel();
        Window_Button_Panel.setLayout(new GridLayout(4, 1));
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
        Window_Button_Panel.add(jPanel4);
        Window_Button_Panel.add(jPanel5);
        Window_Button_Panel.add(jPanel6);
        Window_Button_Panel.add(jPanel7);
        Window_Button_Panel.add(jPanel8);
        Window_Button_Panel.add(jPanel9);

        jPanel3 = new JPanel();
        jPanel3.setBackground(Color.white);
        jPanel3.setLayout(new BorderLayout());
        image.setImage(image.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        jLabel3 = new JLabel(image);
        jLabel3.setSize(50, 50);
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel3.add(jLabel3);

        if (!getPrivilege().equals("administrator")) {
            jPanel7.setVisible(false);

        }
        if(getPrivilege().equals("costomer")){
            jPanel9.setVisible(false);
        }


        this.add(Window_Title_Panel, BorderLayout.NORTH);
        this.add(Window_Button_Panel, BorderLayout.CENTER);
        this.add(jPanel3, BorderLayout.EAST);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);

        //System.out.println(this.acc);

        //System.out.println(Privilege);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            this.setVisible(false);
            new PrinrFrame();
        }
        if (e.getSource() == b2) {
            this.setVisible(false);
            new Rate_Frame();
        }
        if (e.getSource() == b3) {
            this.setVisible(false);
            new db_USER_deposit_Frame();
        }
        if (e.getSource() == b4) {
            this.setVisible(false);
            new administrator();
            //Limit_get_Money.LimitMoney();
        }
        if (e.getSource() == b5) {
            this.setVisible(false);
            new LoginFrame();

        }
        if (e.getSource() == b6) {
            new set_up_account().getFarFrame(this);
        }
    }
}
