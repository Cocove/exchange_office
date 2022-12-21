package Administrator;


import Exchange_MAIN.MainFrame;
import Histroy.PrinrFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class administrator_Frame extends JFrame implements ActionListener {


    private static String acc;

    public static String getAcc() {
        return acc;
    }

    public static void setAcc(String acc) {
        administrator_Frame.acc = acc;
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

    private adminController adminController;

    private JLabel jLabel3;
    private ImageIcon image;

    public administrator_Frame() {


        this.setTitle("administrator interface");
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

        Window_Title_Lable = new JLabel("administrator interface");
        Font f = new Font("Times New Roman", Font.PLAIN, 40);
        Window_Title_Lable.setFont(f);
        Window_Title_Lable.setHorizontalAlignment(SwingConstants.CENTER);
        Window_Title_Panel.add(Window_Title_Lable);





        adminController = new adminController(this);





        jPanel3 = new JPanel();
        jPanel3.setBackground(Color.white);
        jPanel3.setLayout(new BorderLayout());
        image.setImage(image.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        jLabel3 = new JLabel(image);
        jLabel3.setSize(50, 50);
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel3.add(jLabel3);


        this.add(Window_Title_Panel, BorderLayout.NORTH);
        this.add(adminController, BorderLayout.CENTER);
        this.add(jPanel3, BorderLayout.EAST);


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void actionPerformed(ActionEvent e) {

    }


}
