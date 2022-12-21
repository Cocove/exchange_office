package Rate;

import Exchange_MAIN.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RateController extends JPanel implements ActionListener {

    Font f1 = new Font("Times New Roman",Font.PLAIN,14);
    private JButton b1;
    private JButton Sumbit;
    private JButton Clear;
    private JButton Change;
    private JButton set_rate;
    private JButton insert;
    Rate_Frame rate_frame;
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
    RateController(Rate_Frame rate_frame1){
        rate_frame = rate_frame1;
        insert =new JButton("insert");
        insert.setFont(f1);

        set_rate =new JButton("set_rate");
        set_rate.setFont(f1);

        Sumbit = new JButton("Request");
        Sumbit.setFont(f1);
        Sumbit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = rate_frame.getJTF1();
                try {
                    huilv = stringToInt(RateAPI.getHuilv());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                num1 = stringToInt(str);
                num2 = huilv * num1;
                String str2 = doubleToString(num2);
                rate_frame.setJTF2(str2);
            }
        });

        Clear = new JButton("clear");
        Clear.setFont(f1);
        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rate_frame.setjComboBox1Index(0);
                rate_frame.setjComboBox2Index(0);
                rate_frame.setJTF1("0");
                rate_frame.setJTF2("0");
            }
        });
        Change = new JButton("change");
        Change.setFont(f1);
        Change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index1 =  rate_frame.getjComboBox1Index();
                int index2 =  rate_frame.getjComboBox2Index();
                rate_frame.setjComboBox1Index(index2);
                rate_frame.setjComboBox2Index(index1);
                String str = rate_frame.getJTF1();
                try {
                    huilv = stringToInt(RateAPI.getHuilv());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                num1 = stringToInt(str);
                num2 = huilv * num1;
                String str2 = doubleToString(num2);
                rate_frame.setJTF2(str2);
            }
        });

        b1 = new JButton("back");
        b1.setFont(f1);
        b1.addActionListener(this);
        insert.addActionListener(this);
        set_rate.addActionListener(this);

        if(MainFrame.getPrivilege().equals("costomer")){
            insert.setVisible(false);
            set_rate.setVisible(false);
        }

        this.add(b1);
        this.add(Sumbit);
        this.add(Clear);
        this.add(Change);
        this.add(insert);
        this.add(set_rate);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1){
            rate_frame.setVisible(false);
            new MainFrame().setVisible(true);
        }
        if (e.getSource() == insert){
            dbRate.insertRate();
        }if(e.getSource() == set_rate){
            new set_Rate_Frame();
        }
    }
}
