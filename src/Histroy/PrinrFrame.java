package Histroy;


import Exchange_MAIN.MainFrame;
import Rate.RateAPI;
import Rate.get_Rate;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;


public class PrinrFrame extends JFrame implements ActionListener {
    private final int my_width = 600;
    private final int my_height = 400;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";

    private static ArrayList<String> Person;
    private static ArrayList<String> money;

    public static String getAcc() {
        return acc;
    }

    public static void setAcc(String acc) {
        PrinrFrame.acc = acc;
    }

    static String acc;

    public static String getAcc1() {
        return acc1;
    }

    public static void setAcc1(String acc1) {
        PrinrFrame.acc1 = acc1;
    }

    static String acc1;


    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JLabel JL;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;

    private JLabel jLabel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    //图片；
    private JLabel jLabel3;
    private JTextField JTF1;
    private JTextField JTF2;
    private JTextField JTF3;
    private JTextField JTF4;
    private ImageIcon image;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JPanel jPanel10;
    private UtilDateModel model;
    private Properties p;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;

    private String name;

    private double huilv;

    private double num1;
    private double num2;
    private String Privilege;

    private String xuanzhong1 = "USD";
    private String xuanzhong2 = "USD";

    public PrinrFrame() {

        this.setTitle("transaction interface");
        this.setSize(my_width, my_height);
        this.setLocationRelativeTo(null);

        Privilege = MainFrame.getPrivilege();
        model = new UtilDateModel();
        //model.setDate(20,04,2014);
        // Need this...
        p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        // Don't know about the formatter, but there it is...
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());


        image = new ImageIcon("src/exchange/icon.png");
        this.setIconImage(image.getImage());

        Color myWhite = new Color(0, 200, 0);
        Container con = this.getContentPane();
        con.setBackground(Color.white);

        Font f = new Font("Times New Roman", Font.PLAIN, 40);
        Font f1 = new Font("Times New Roman", Font.PLAIN, 25);
        Dimension preferredSize1 = new Dimension(600, 100);
        setLayout(new BorderLayout(0, 10));

        //上方文字
        jPanel1 = new JPanel();
        jPanel1.setBackground(myWhite);
        jPanel1.setPreferredSize(preferredSize1);
        jPanel1.setLayout(new BorderLayout());

        JL = new JLabel("transaction interface");

        JL.setFont(f);
        JL.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(JL);


        jPanel2 = new JPanel();
        jPanel2.setLayout(new GridLayout(6, 1));
        jPanel2.setBackground(Color.white);

        jPanel5 = new JPanel();
        jPanel5.setBackground(Color.white);
        jPanel5.setLayout(new FlowLayout(FlowLayout.CENTER));

        jPanel6 = new JPanel();
        jPanel6.setBackground(Color.white);
        jPanel6.setLayout(new FlowLayout(FlowLayout.CENTER));

        jPanel7 = new JPanel();
        jPanel7.setBackground(Color.white);
        jPanel7.setLayout(new FlowLayout(FlowLayout.CENTER));

        jPanel8 = new JPanel();
        jPanel8.setBackground(Color.white);
        jPanel8.setLayout(new FlowLayout(FlowLayout.CENTER));

        jPanel9 = new JPanel();
        jPanel9.setBackground(Color.white);
        jPanel9.setLayout(new FlowLayout(FlowLayout.CENTER));

        jPanel10 = new JPanel();
        jPanel10.setBackground(Color.white);
        jPanel10.setLayout(new FlowLayout(FlowLayout.CENTER));

        jPanel4 = new JPanel();
        jPanel4.setBackground(Color.white);
        jPanel4.setLayout(new FlowLayout(FlowLayout.CENTER));

        jLabel = new JLabel("Заполните");
        jLabel.setFont(f1);
        jLabel1 = new JLabel("Фамилия клиента:");
        jLabel2 = new JLabel("Фамилия сотрудника:");
        JTF1 = new JTextField(10);
        JTF2 = new JTextField(10);
        JTF3 = new JTextField(10);
        JTF4 = new JTextField(10);
        comboBox1 = new JComboBox();
        comboBox2 = new JComboBox();
        String[] strArr = {"USD", "EUR", "CNY", "BYN"};

        for (String item : strArr) {
            comboBox1.addItem(item);
            comboBox2.addItem(item);
        }
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    xuanzhong1 = (String) comboBox1.getSelectedItem();
                    RateAPI.setHuobi1(xuanzhong1);

                }
            }
        });
        comboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    xuanzhong2 = (String) comboBox2.getSelectedItem();
                    RateAPI.setHuobi2(xuanzhong2);

                }
            }
        });
        jPanel5.add(jLabel);
        jPanel6.add(jLabel1);
        jPanel6.add(JTF1);
        jPanel7.add(jLabel2);
        jPanel7.add(JTF2);
        jPanel8.add(comboBox1);
        jPanel8.add(JTF3);
        jPanel9.add(comboBox2);
        jPanel9.add(JTF4);
        jPanel10.add(datePicker);

        jPanel2.add(jPanel5);
        jPanel2.add(jPanel6);
        jPanel2.add(jPanel7);
        jPanel2.add(jPanel8);
        jPanel2.add(jPanel9);
        jPanel2.add(jPanel10);

        //右侧图标
        jPanel3 = new JPanel();
        jPanel3.setBackground(Color.white);
        jPanel3.setLayout(new BorderLayout());
        image.setImage(image.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        jLabel3 = new JLabel(image);
        jLabel3.setSize(50, 50);
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel3.add(jLabel3);

        //返回打印按钮
        jPanel4 = new JPanel();
        jPanel4.setBackground(Color.white);
        jPanel4.setLayout(new FlowLayout(FlowLayout.CENTER));
        b1 = new JButton("back");
        b1.setFocusPainted(false);
        b2 = new JButton("trade");
        b3 = new JButton("find histroy");
        jPanel4.add(b1);
        jPanel4.add(b2);
        jPanel4.add(b3);


        //设置按钮可见
        if (Privilege.equals("costomer")) {
            b3.setVisible(false);
        }

        this.add(jPanel1, BorderLayout.NORTH);
        this.add(jPanel2, BorderLayout.CENTER);
        this.add(jPanel3, BorderLayout.EAST);
        this.add(jPanel4, BorderLayout.SOUTH);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        setname();
        getPersondate();


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //String 转 double
    public double stringToDouble(String doublestr) {
        Double Adouble;
        Adouble = Double.parseDouble(doublestr);
        return Adouble;
    }

    //double 转 String
    public String doubleToString(double value) {
        Double aDouble = new Double(value);
        return aDouble.toString();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            this.setVisible(false);
            new MainFrame().setVisible(true);
            System.out.println("back main");
        }
        if (e.getSource() == b2) {
            try {
                panduan();
                UpdateDate();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


        if (e.getSource() == b3) {
            dbHistory.getHistory();
            new dbHistroyFrame();
            this.setVisible(false);
            Limit_get_Money.LimitMoney();
            /*SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd");
            Date date = (Date) datePicker.getModel().getValue();
            String date1 = f.format(date);
            System.out.println(date1);*/
        }
    }


    public String getJTF1() {
        return JTF1.getText();
    }

    public String getJTF2() {
        return JTF2.getText();
    }

    public String getJTF3() {
        return JTF3.getText();
    }

    public String getJTF4() {
        return JTF4.getText();
    }

    public String getXuanzhong1() {
        return xuanzhong1;
    }

    public String getXuanzhong2() {
        return xuanzhong2;
    }

    private Date stringTodate(String datestr) throws ParseException {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(datestr);
        return date;
    }

    private String dateToString(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getdate = sdf.format(date);
        return getdate;
    }

    public void panduan() throws IOException, ParseException, ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName(JDBC_DRIVER);
        // 打开链接
        System.out.println("连接数据库...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        // 执行查询
        System.out.println(" 实例化Statement对象...");
        stmt = conn.createStatement();


        String name = JTF2.getText();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = (Date) datePicker.getModel().getValue();
        String date = f.format(date1);

        double limit = getLimitnum(stmt, name);

        if (existName(stmt, name, date)) {//如果查询到当日数据
            get_Rate gr = new get_Rate();
            String huobi1 = getmoneyCurreny(stmt, name, date);
            String huobi2 = "BYN";
            RateAPI.setHuobi1(getmoneyCurreny(stmt, name, date));
            RateAPI.setHuobi2("BYN");
            String rate = doubleToString(gr.get_rate(huobi1, huobi2));
            double ratenum1 = stringToDouble(rate);
            double buynum1 = getmoney(stmt, name, date);
            double sum1 = ratenum1 * buynum1;
            if (sum1 > limit) {
                JOptionPane.showMessageDialog(null, "超过限制", "标题", JOptionPane.WARNING_MESSAGE);
            } else {
                String str1;
                String str2;
                str1 = (String) comboBox1.getSelectedItem();
                str2 = (String) comboBox2.getSelectedItem();
                RateAPI.setHuobi1(str1);
                RateAPI.setHuobi2("BYN");
                rate = doubleToString(gr.get_rate(str1, "BYN"));
                double ratenum = stringToDouble(rate);
                double buynum = stringToDouble(JTF3.getText());
                double sumnum = ratenum * buynum;
                double sum = sum1 + sumnum;
                //获得兑换的钱
                if (sum > limit) {
                    JOptionPane.showMessageDialog(null, "超过限制", "标题", JOptionPane.WARNING_MESSAGE);
                } else {
                    SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
                    Date date2 = (Date) datePicker.getModel().getValue();
                    String date3 = f1.format(date2);
                    RateAPI.setHuobi2(str2);
                    rate = doubleToString(gr.get_rate(str1, str2));
                    ratenum = stringToDouble(rate);
                    buynum = stringToDouble(JTF3.getText());
                    sumnum = ratenum * buynum;
                    JTF4.setText(doubleToString(sumnum));
                    new PrintTicket(datePicker, JTF1.getText(), JTF3.getText(), JTF4.getText(), comboBox1.getSelectedItem().toString(), comboBox2.getSelectedItem().toString(), JTF2.getText());
                    dbHistory.addHistory(getJTF1(), getJTF2(), getJTF3(), getJTF4(), getXuanzhong1(), getXuanzhong2(), date3);
                    Limit_get_Money.LimitMoney();
                    set_daliy_histroy.set_daliy_histroy();
                }
            }
        } else {//如果查询不到当日数据
            get_Rate gr = new get_Rate();
            String str1;
            String str2;
            str1 = (String) comboBox1.getSelectedItem();
            str2 = (String) comboBox2.getSelectedItem();
            RateAPI.setHuobi1(str1);
            RateAPI.setHuobi2("BYN");
            String rate = doubleToString(gr.get_rate(str1, "BYN"));
            double ratenum = stringToDouble(rate);
            double buynum = stringToDouble(JTF3.getText());
            double sumnum = ratenum * buynum;
            //获得兑换的钱
            if (sumnum > limit) {
                JOptionPane.showMessageDialog(null, "超过限制", "标题", JOptionPane.WARNING_MESSAGE);
            } else {
                SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
                Date date2 = (Date) datePicker.getModel().getValue();
                String date3 = f1.format(date2);
                RateAPI.setHuobi2(str2);
                rate = doubleToString(gr.get_rate(str1, str2));
                ratenum = stringToDouble(rate);
                buynum = stringToDouble(JTF3.getText());
                sumnum = ratenum * buynum;
                JTF4.setText(doubleToString(sumnum));
                new PrintTicket(datePicker, JTF1.getText(), JTF3.getText(), JTF4.getText(), comboBox1.getSelectedItem().toString(), comboBox2.getSelectedItem().toString(), JTF2.getText());
                dbHistory.addHistory(getJTF1(), getJTF2(), getJTF3(), getJTF4(), getXuanzhong1(), getXuanzhong2(), date3);
                Limit_get_Money.LimitMoney();
                set_daliy_histroy.set_daliy_histroy();
            }
        }
        /* */
    }

    public double getmoney(Statement stmt, String name, String date) {
        double getmoney = 0;
        try {
            // 注册 JDBC 驱动
            String sql;
            sql = "SELECT user_get_usd FROM user_limit where user_name = '" + name + "' and submission_date = '" + date + "'";
            ResultSet rs = stmt.executeQuery(sql);
            int Index = 0;
            String str = null;
            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                str = rs.getString("user_get_usd");
            }

            getmoney = stringToDouble(str);
            // 完成后关闭
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getmoney;
    }

    public String getmoneyCurreny(Statement stmt, String name, String date) {
        String str = null;
        try {
            String sql;
            sql = "SELECT user_get_limit FROM user_limit where user_name = '" + name + "' and submission_date = '" + date + "'";
            ResultSet rs = stmt.executeQuery(sql);
            int Index = 0;
            while (rs.next()) {
                // 通过字段检索
                str = rs.getString("user_get_limit");
            }
            // 完成后关闭
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return str;
    }

    public double getLimitnum(Statement stmt, String name) throws SQLException {
        double getmoney;
        String sql;
        sql = "SELECT user_limit_day FROM user_acc_pss where user_name = '" + name + "'";
        ResultSet rs = stmt.executeQuery(sql);
        int Index = 0;
        String str = null;
        // 展开结果集数据库
        while (rs.next()) {
            // 通过字段检索
            str = rs.getString("user_limit_day");
        }

        getmoney = stringToDouble(str);
        // 完成后关闭
        rs.close();

        return getmoney;
    }

    public static boolean existName(Statement stmt, String name, String date) throws SQLException {
        String sql;
        sql = "SELECT 1 FROM user_limit WHERE user_name = '" + name + "' and submission_date = '" + date + "'  LIMIT 1";
        ResultSet rs = stmt.executeQuery(sql);
        Integer exist = 0;
        if (rs.next()) {
            exist = rs.getInt(1);
        }
        if (exist == 0) {
            return false;
        } else {
            return true;
        }

    }

    private void getPersondate() {
        Connection conn;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);    //连接驱动
            conn = DriverManager.getConnection(DB_URL, USER, PASS);    //连接数据库
            preparedStatement = conn.prepareStatement("select * from exchange_office_user where user_acc = '" + acc1 + "' ");
            ResultSet result1 = preparedStatement.executeQuery();

            money = new ArrayList();
            Person = new ArrayList();
            ResultSetMetaData rsmd = result1.getMetaData();
            for (int i = 3; i < rsmd.getColumnCount() - 1; i++) {

                money.add(rsmd.getColumnName(i).toString());
            }


            while (result1.next()) {
                for (int i = 0; i < money.size(); i++) {
                    System.out.println(result1.getString(money.get(i)));
                    Person.add(result1.getString(money.get(i)));
                }
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("未成功加载驱动。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("未成功打开数据库。");
            e.printStackTrace();
        }
    }


    public String getcomboBox1Text() {
        return comboBox1.getSelectedItem().toString();
    }

    public String getcomboBox2Text() {
        return comboBox2.getSelectedItem().toString();
    }


    private void UpdateDate() {
        Connection conn;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);    //连接驱动
            conn = DriverManager.getConnection(DB_URL, USER, PASS);    //连接数据库
            String sql = null;
            for (int i = 0; i < money.size(); i++) {
                if (money.get(i).equals(getcomboBox1Text())) {
                    sql = "UPDATE exchange_office_user SET " + money.get(i) + " = ?   WHERE user_acc= '" + acc1 + "'";
                    preparedStatement = conn.prepareStatement(sql);
                    double num = 0;
                    num = stringToDouble(Person.get(i)) - stringToDouble(getJTF3());
                    String num1 = doubleToString(num);
                    preparedStatement.setString(1, num1);
                    int resultSet = preparedStatement.executeUpdate();
                    if (resultSet > 0) {
                        //如果插入成功，则打印success
                        System.out.println("Sucess");
                    } else {
                        //如果插入失败，则打印Failure
                        System.out.println(resultSet);
                        System.out.println("Failure");
                    }
                    System.out.println(num1);
                }
                if (money.get(i).equals(getcomboBox2Text())) {
                    sql = "UPDATE exchange_office_user SET " + money.get(i) + " = ?   WHERE user_acc= '" + acc1 + "'";
                    preparedStatement = conn.prepareStatement(sql);
                    double num = 0;
                    num = stringToDouble(Person.get(i)) + stringToDouble(getJTF4());
                    String num1 = doubleToString(num);
                    preparedStatement.setString(1, num1);
                    int resultSet = preparedStatement.executeUpdate();
                    if (resultSet > 0) {
                        //如果插入成功，则打印success
                        System.out.println("Sucess");
                    } else {
                        //如果插入失败，则打印Failure
                        System.out.println(resultSet);
                        System.out.println("Failure");
                    }
                    System.out.println(num1);
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("未成功加载驱动。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("未成功打开数据库。");
            e.printStackTrace();
        }
    }

    private void setJTF2(String name) {
        JTF2.setText(name);
    }

    private void setJTF1(String name) {
        JTF1.setText(name);
    }

    private void setname() {
        Connection conn;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);    //连接驱动
            conn = DriverManager.getConnection(DB_URL, USER, PASS);    //连接数据库
            preparedStatement = conn.prepareStatement("select user_name from user_acc_pss where user_acc = '" + acc1 + "' ");
            ResultSet result1 = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = result1.getMetaData();
            while (result1.next()) {
                name = result1.getString("user_name");
            }
            setJTF2(name);
            if (!MainFrame.getPrivilege().equals("costomer")) {
                preparedStatement = conn.prepareStatement("select user_name from user_acc_pss where user_acc = '" + acc + "' ");
                result1 = preparedStatement.executeQuery();
                while (result1.next()) {
                    name = result1.getString("user_name");
                }
                setJTF1(name);
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("未成功加载驱动。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("未成功打开数据库。");
            e.printStackTrace();
        }
    }
}
