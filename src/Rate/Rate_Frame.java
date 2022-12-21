package Rate;
//实行查询汇率并且设置汇率
import Exchange_MAIN.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Rate_Frame extends JFrame implements ActionListener {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    private final int my_width = 600;
    private final int my_height = 400;

    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel Window_Title_Panel;
    private JLabel Window_Title_Lable;
    private ImageIcon image;

    RateController rateController;


    private static JComboBox jComboBox1;
    private static JComboBox jComboBox2;

    private JLabel label1;
    private JLabel label2;

    private static JTextField JTF1;
    private static JTextField JTF2;




    private String xuanzhong1;
    private String xuanzhong2;





    public static String getjComboBox1Item() {
        return jComboBox1.getSelectedItem().toString();
    }

    public static void setjComboBox1(String str) {
        Rate_Frame.jComboBox1.setSelectedItem(str);
    }

    public static String getjComboBox2Item() {
        return jComboBox2.getSelectedItem().toString();
    }

    public static void setjComboBox2(String str) {
        Rate_Frame.jComboBox2.setSelectedItem(str);
    }

    public static String getJTF1() {
        return JTF1.getText();
    }

    public static void setJTF1(String str) {
        Rate_Frame.JTF1.setText(str);
    }

    public static String getJTF2() {
        return JTF2.getText();
    }

    public static void setJTF2(String str) {
        Rate_Frame.JTF2.setText(str);
    }

    public static int getjComboBox1Index() {
        return jComboBox1.getSelectedIndex();
    }

    public static int getjComboBox2Index() {
        return jComboBox2.getSelectedIndex();
    }
    public static void setjComboBox1Index(int Index) {
        jComboBox1.setSelectedIndex(Index);
    }

    public static void setjComboBox2Index(int Index) {
        jComboBox2.setSelectedIndex(Index);
    }

    public Rate_Frame(){

        this.setTitle("Query exchange rate interface");
        this.setSize(my_width, my_height);
        this.setLocationRelativeTo(null);
        image = new ImageIcon("src/exchange/icon.png");
        this.setIconImage(image.getImage());
        this.setLayout(new BorderLayout(0,50));



        Color myWhite = new Color(0, 200, 0);
        Window_Title_Panel = new JPanel();
        Dimension preferredSize = new Dimension(600,100);
        Window_Title_Panel.setBackground(myWhite);
        Window_Title_Panel.setPreferredSize(preferredSize);
        Window_Title_Panel.setLayout(new BorderLayout());

        Window_Title_Lable = new JLabel("Check the exchange rate");
        Font f = new Font("Times New Roman",Font.PLAIN,40);
        Font f1 = new Font("Times New Roman",Font.PLAIN,14);
        Window_Title_Lable.setFont(f);
        Window_Title_Lable.setHorizontalAlignment(SwingConstants.CENTER);
        Window_Title_Panel.add(Window_Title_Lable);



        jComboBox1 = new JComboBox<String>();
        for(String item : dbRate.Money_Array){
            jComboBox1.addItem(item);
        }
        jComboBox1.setPreferredSize(new Dimension(50,30));
        jComboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange() == ItemEvent.SELECTED){
                    xuanzhong1 =  (String) jComboBox1.getSelectedItem();
                    RateAPI.setHuobi1(xuanzhong1);

                }
            }
        });
        jComboBox2 = new JComboBox<String>();
        for(String item : dbRate.Money_Array){
            jComboBox2.addItem(item);
        }
        jComboBox2.setPreferredSize(new Dimension(50,30));
        jComboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange() == ItemEvent.SELECTED){
                    xuanzhong2 =  (String) jComboBox2.getSelectedItem();
                    RateAPI.setHuobi2(xuanzhong2);

                }
            }
        });

        label1 = new JLabel("Enter the amount you want to exchange:");
        label1.setFont(f1);
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        JTF1 = new JTextField(10);
        JTF1.setText("0");

        label2 = new JLabel("Amount you exchanged:");
        label2.setFont(f1);
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        JTF2 = new JTextField(10);
        JTF2.setText("0");


        rateController = new RateController(this);

        jPanel2 = new JPanel();

        jPanel2.add(label1);
        jPanel2.add(jComboBox1);
        jPanel2.add(JTF1);

        jPanel3 = new JPanel();
        jPanel3.add(label2);
        jPanel3.add(jComboBox2);
        jPanel3.add(JTF2);

        jPanel4 = new JPanel();
        jPanel4.setLayout(new GridLayout(2,1));
        jPanel4.add(jPanel2);
        jPanel4.add(jPanel3);

        jPanel1 = new JPanel();

        this.add(Window_Title_Panel,BorderLayout.NORTH);
        this.add(jPanel4, BorderLayout.CENTER);
        this.add(rateController, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }



}

