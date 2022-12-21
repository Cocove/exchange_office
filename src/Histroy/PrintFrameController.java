package Histroy;

import Exchange_MAIN.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class PrintFrameController extends JPanel implements ActionListener {

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private PrinrFrame prinrFrame;
    PrintFrameController(PrinrFrame prinrFrame1){
        prinrFrame = prinrFrame1;

        this.setBackground(Color.white);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        b1 = new JButton("back");
        b1.setFocusPainted(false);
        b2 = new JButton("trade");
        b3 = new JButton("find histroy");
        if (MainFrame.getPrivilege().equals("costomer")) {
            b3.setVisible(false);
        }
        this.add(b1);
        this.add(b2);
        this.add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            prinrFrame.setVisible(false);
            new MainFrame().setVisible(true);
            //System.out.println("back main");
        }
        if (e.getSource() == b2) {
            try {
                PrinrFrame.panduan();
                PrinrFrame.UpdateDate();
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
            new HistroyFrame();
            prinrFrame.setVisible(false);
            Limit_get_Money.LimitMoney();
            /*SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd");
            Date date = (Date) datePicker.getModel().getValue();
            String date1 = f.format(date);
            System.out.println(date1);*/
        }
    }


}
