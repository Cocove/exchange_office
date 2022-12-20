package Histroy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;


public class dbHistroyFrame extends JFrame implements ActionListener {
    private final int my_width = 600;
    private final int my_height = 400;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/codes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private DefaultTableModel tableModel;		// 默认显示的表格
    private JButton back;		// 各处理按钮
    private JButton delect;
    private JTable table;		// 表格

    private JPanel panelUP;	//增加信息的面板
    private JPanel panelDOWN;	//增加信息的面板


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1230zxc..";


    public dbHistroyFrame(){
        this.setTitle("Historical operation record interface");
        this.setSize(my_width,my_height);
        this.setLocationRelativeTo(null);

        Vector rowData = getRows();
        // 取得haha数据库的aa表的表头数据
        Vector columnNames = getHead();

        panelUP = new JPanel();		// 新建按钮组件面板
        panelUP.setLayout(new FlowLayout(FlowLayout.LEFT));	// 设置面板的布局方式
        panelDOWN = new JPanel();		// 新建按钮组件面板
        panelDOWN.setLayout(new FlowLayout(FlowLayout.LEFT));	// 设置面板的布局方式
        // 将各按钮组件依次添加到面板中


        // 新建表格
        tableModel = new DefaultTableModel(rowData,columnNames);
        table = new JTable(tableModel);

        JScrollPane s = new JScrollPane(table);


        back = new JButton("back");
        delect = new JButton("delect");
        panelDOWN.add(back);
        panelDOWN.add(delect);

        // 将面板和表格分别添加到窗体中
        this.add(panelUP,BorderLayout.CENTER);
        this.add(s);
        this.add(panelDOWN,BorderLayout.SOUTH);


        this.setVisible(true);
        //表格筛选功能 暂时不加
       /* TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);
        table.setRowSorter(sorter);
        //实现过滤，search为正则表达式，该方法支持参数为null和空字符串，因此不用对输入进行校验
        String search = "CNY";
        sorter.setRowFilter(RowFilter.regexFilter(search));*/
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        back.addActionListener(this);
        delect.addActionListener(this);
    }

    public static Vector getRows(){
        Connection conn;
        PreparedStatement preparedStatement = null;

        Vector rows = null;
        try {
            Class.forName(JDBC_DRIVER);	//连接驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS);	//连接数据库
//			if(!conn.isClosed())
//				System.out.println("成功连接数据库");
            preparedStatement = conn.prepareStatement("select * from histroy ");
            ResultSet result1 = preparedStatement.executeQuery();

            rows = new Vector();

            ResultSetMetaData rsmd = result1.getMetaData();

            while(result1.next()){
                rows.addElement(getNextRow(result1,rsmd));
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            //System.out.println("未成功加载驱动。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //System.out.println("未成功打开数据库。");
            e.printStackTrace();
        }

        return rows;
    }

    // 得到数据库表头
    public static Vector getHead(){
        Connection conn;
        PreparedStatement preparedStatement = null;
        Vector columnHeads = null;
        try {
            Class.forName(JDBC_DRIVER);		//连接驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS);	//连接数据库
//			if(!conn.isClosed())
//				System.out.println("成功连接数据库");
            preparedStatement = conn.prepareStatement("select * from histroy");
            ResultSet result1 = preparedStatement.executeQuery();

            boolean moreRecords = result1.next();
            if(!moreRecords)
                JOptionPane.showMessageDialog(null, "结果集中无记录");

            columnHeads = new Vector();
            ResultSetMetaData rsmd = result1.getMetaData();
            for(int i = 1; i <= rsmd.getColumnCount(); i++)
                columnHeads.addElement(rsmd.getColumnName(i));

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            //System.out.println("未成功加载驱动。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //System.out.println("未成功打开数据库。");
            e.printStackTrace();
        }
        return columnHeads;
    }
    // 得到数据库中下一行数据
    private static Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException{
        Vector currentRow = new Vector();
        for(int i = 1; i <= rsmd.getColumnCount(); i++){
            currentRow.addElement(rs.getString(i));
        }
        return currentRow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            new PrinrFrame();
            this.setVisible(false);
        }else if(e.getSource() == delect){
            new Delect_Histroy_Frame().getFarFrame(this);
        }
    }
}
