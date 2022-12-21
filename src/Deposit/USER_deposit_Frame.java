package Deposit;
//显示个人存款
import Exchange_MAIN.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;


public class USER_deposit_Frame extends JFrame implements ActionListener {
    private final int my_width = 600;
    private final int my_height = 400;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private DefaultTableModel tableModel;		// 默认显示的表格

    private JTable table;		// 表格

    private JPanel panelUP;	//增加信息的面板
    private JPanel panelDown;	//增加信息的面板

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";


    private depositController depositController;



    public USER_deposit_Frame(){
        this.setTitle("User Deposit Interface");
        this.setSize(my_width,my_height);
        this.setLocationRelativeTo(null);

        Vector rowData = dbUserDeposit.getRows();
        // 取得haha数据库的aa表的表头数据
        Vector columnNames = dbUserDeposit.getHead();

        panelUP = new JPanel();		// 新建按钮组件面板
        panelUP.setLayout(new FlowLayout(FlowLayout.LEFT));	// 设置面板的布局方式

        // 将各按钮组件依次添加到面板中

        depositController = new depositController(this);


        // 新建表格
        tableModel = new DefaultTableModel(rowData,columnNames);
        table = new JTable(tableModel);

        JScrollPane s = new JScrollPane(table);



        panelDown = new JPanel();		// 新建按钮组件面板
        panelDown.setLayout(new FlowLayout(FlowLayout.LEFT));

        // 将面板和表格分别添加到窗体中
        this.add(panelUP,BorderLayout.NORTH);
        this.add(s);
        this.add(depositController,BorderLayout.SOUTH);




        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
