package Exchange_MAIN;

import Histroy.PrinrFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {


    private MainFrame_Controller mainFrame_controller;
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

    private PrinrFrame PF;
    private JLabel Window_Title_Lable;
    private JPanel Window_Title_Panel;
    private JPanel Window_Button_Panel;
    private JPanel jPanel3;


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


        mainFrame_controller = new MainFrame_Controller(this);

        jPanel3 = new JPanel();
        jPanel3.setBackground(Color.white);
        jPanel3.setLayout(new BorderLayout());
        image.setImage(image.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        jLabel3 = new JLabel(image);
        jLabel3.setSize(50, 50);
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel3.add(jLabel3);




        this.add(Window_Title_Panel, BorderLayout.NORTH);
        this.add(mainFrame_controller, BorderLayout.CENTER);
        this.add(jPanel3, BorderLayout.EAST);


        //System.out.println(this.acc);

        //System.out.println(Privilege);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void actionPerformed(ActionEvent e) {

    }
}
