package Administrator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class HistroyDay_Frame extends JFrame implements ActionListener {


    private final int my_width = 600;
    private final int my_height = 400;


    private DefaultTableModel tableModel;        // 默认显示的表格

    private JTable table;        // 表格

    private JPanel panelUP;    //增加信息的面板


    // 数据库的用户名与密码，需要根据自己的设置

    private HistroydayController histroydayController;


    public HistroyDay_Frame() {
        this.setTitle("daily history");
        this.setSize(my_width, my_height);
        this.setLocationRelativeTo(null);

        Vector rowData = dbHistroyday.getRows();
        // 取得haha数据库的aa表的表头数据
        Vector columnNames = dbHistroyday.getHead();

        panelUP = new JPanel();        // 新建按钮组件面板
        panelUP.setLayout(new FlowLayout(FlowLayout.LEFT));    // 设置面板的布局方式

        // 将各按钮组件依次添加到面板中


        // 新建表格
        tableModel = new DefaultTableModel(rowData, columnNames);
        table = new JTable(tableModel);

        JScrollPane s = new JScrollPane(table);


        histroydayController = new HistroydayController(this);

        // 将面板和表格分别添加到窗体中
        this.add(panelUP, BorderLayout.CENTER);
        this.add(s);
        this.add(histroydayController, BorderLayout.SOUTH);


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




