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
    private JButton b1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel Window_Title_Panel;
    private JLabel Window_Title_Lable;
    private ImageIcon image;


    private String[] Money_Array = {"USD", "BYN", "CNY", "EUR"};
    private JComboBox jComboBox1;
    private JComboBox jComboBox2;

    private JLabel label1;
    private JLabel label2;

    private JTextField JTF1;
    private JTextField JTF2;


    private JButton Sumbit;
    private JButton Clear;
    private JButton Change;
    private JButton set_rate;
    private JButton insert;

    private String xuanzhong1;
    private String xuanzhong2;
    private double huilv;
    private  double num1;
    private double num2;

    public  double stringToInt(String intstr)
    {
        Double Adouble;
        Adouble = Double.parseDouble(intstr);
        return Adouble;
    }
    //int 转 String
    public String doubleToString(double value)
    {
        Double aDouble = new Double(value);
        return aDouble.toString();

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
        for(String item : Money_Array){
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
        for(String item : Money_Array){
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

        insert =new JButton("insert");
        insert.setFont(f1);

        set_rate =new JButton("set_rate");
        set_rate.setFont(f1);

        Sumbit = new JButton("Request");
        Sumbit.setFont(f1);
        Sumbit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = JTF1.getText();
                try {
                    huilv = stringToInt(RateAPI.getHuilv());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                num1 = stringToInt(str);
                num2 = huilv * num1;
                String str2 = doubleToString(num2);
                JTF2.setText(str2);
            }
        });

        Clear = new JButton("clear");
        Clear.setFont(f1);
        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jComboBox1.setSelectedIndex(0);
                jComboBox2.setSelectedIndex(0);
                JTF1.setText("0");
                JTF2.setText("0");
            }
        });
        Change = new JButton("change");
        Change.setFont(f1);
        Change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index1 =  jComboBox1.getSelectedIndex();
                int index2 =  jComboBox2.getSelectedIndex();
                jComboBox1.setSelectedIndex(index2);
                jComboBox2.setSelectedIndex(index1);
                String str = JTF1.getText();
                try {
                    huilv = stringToInt(RateAPI.getHuilv());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                num1 = stringToInt(str);
                num2 = huilv * num1;
                String str2 = doubleToString(num2);
                JTF2.setText(str2);
            }
        });

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
        b1 = new JButton("back");
        b1.setFont(f1);
        b1.addActionListener(this);
        insert.addActionListener(this);
        set_rate.addActionListener(this);

        if(MainFrame.getPrivilege().equals("costomer")){
            insert.setVisible(false);
            set_rate.setVisible(false);
        }

        jPanel1.add(b1);
        jPanel1.add(Sumbit);
        jPanel1.add(Clear);
        jPanel1.add(Change);
        jPanel1.add(insert);
        jPanel1.add(set_rate);
        this.add(Window_Title_Panel,BorderLayout.NORTH);
        this.add(jPanel4, BorderLayout.CENTER);
        this.add(jPanel1, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1){
            this.setVisible(false);
            new MainFrame().setVisible(true);
        }
        if (e.getSource() == insert){
            insertRate();
        }if(e.getSource() == set_rate){
            new set_Rate_Frame();
        }
    }

    public void insertRate(){
        PreparedStatement ps=null;
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            ArrayList<String> RATE = new ArrayList();

            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("yyyy-MM-dd");
            Date date = new Date();
            String daydate = sdf.format(date);


            for(int i = 0;i<Money_Array.length;i++){
                RATE.add(getUSDRATE(Money_Array[i]));
            }
            String sql="insert into day_rate (USD,BYN,CNY,EUR,submission_date) values(?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1, RATE.get(0));
            ps.setString(2, RATE.get(1));
            ps.setString(3, RATE.get(2));
            ps.setString(4, RATE.get(3));
            ps.setString(5, daydate);
            int resultSet=ps.executeUpdate();
            if(resultSet>0){
                //如果插入成功，则打印success
                //System.out.println("Sucess");
            }else{
                //如果插入失败，则打印Failure
                //System.out.println("Failure");
            }
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String getUSDRATE(String money) throws IOException {
        String rate;
        RateAPI.setHuobi1("USD");
        RateAPI.setHuobi2(money);
        rate = RateAPI.getHuilv();
        return rate;
    }

}

