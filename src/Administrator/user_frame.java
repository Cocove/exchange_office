package Administrator;

import Exchange_MAIN.dbUserDATA;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class user_frame extends JFrame implements ActionListener {
    private final int my_width = 600;
    private final int my_height = 400;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private DefaultTableModel tableModel;		// 默认显示的表格


    private JTable table;		// 表格

    private JPanel panelUP;	//增加信息的面板
    private JPanel panelDown;

    private userframeController userframeController;

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";


    public user_frame(){
        this.setTitle("User Account Interface");
        this.setSize(my_width,my_height);
        this.setLocationRelativeTo(null);

        Vector rowData = dbUserDATA.getRows();
        // 取得haha数据库的aa表的表头数据
        Vector columnNames = dbUserDATA.getHead();

        panelUP = new JPanel();		// 新建按钮组件面板
        panelUP.setLayout(new FlowLayout(FlowLayout.LEFT));	// 设置面板的布局方式

        panelDown = new JPanel();		// 新建按钮组件面板
        panelDown.setLayout(new FlowLayout(FlowLayout.CENTER));	// 设置面板的布局方式

        // 将各按钮组件依次添加到面板中

        userframeController = new userframeController(this);
        // 新建表格
        tableModel = new DefaultTableModel(rowData,columnNames);
        table = new JTable(tableModel);

        JScrollPane s = new JScrollPane(table);




        // 将面板和表格分别添加到窗体中
        this.add(panelUP,BorderLayout.CENTER);
        this.add(s);

        this.add(userframeController,BorderLayout.SOUTH);


        this.setVisible(true);
        //表格筛选功能 暂时不加
       /* TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);
        table.setRowSorter(sorter);
        //实现过滤，search为正则表达式，该方法支持参数为null和空字符串，因此不用对输入进行校验
        String search = "CNY";
        sorter.setRowFilter(RowFilter.regexFilter(search));*/
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
