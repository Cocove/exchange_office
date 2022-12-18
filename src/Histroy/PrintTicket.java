package Histroy;

import org.jdatepicker.JDatePicker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

public class PrintTicket {
    public PrintTicket(JDatePicker date, String name, String sum, String sum1, String in, String out, String work){
        File file = new File("Справка о совершенной транзакции.txt");
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException var9) {
            var9.printStackTrace();
        }


        String daterussian = date.getModel().getValue().toString();
        String time = Calendar.getInstance().getTime().toString();
        printWriter.println(" Справка выдана " + name + " о том что он совершил сделку в обменном пункте ИП 'Захарчик'");
        printWriter.println("");
        String var10001 = daterussian.substring(8, 10);
        printWriter.println(" Дата - " + var10001 + "-" + daterussian.substring(4, 7) + "-" + daterussian.substring(24, 28));
        printWriter.println(" Время - " + time.substring(11, 19));
        var10001 = sum;
        printWriter.println(" Клиент внес - " + var10001 + " (" + in + ")");
        var10001 = sum1;
        printWriter.println(" Клиент получил - " + var10001 + " (" + out + ")");
        printWriter.println(" Сотрудник обслуживщий клиента - " + work);
        printWriter.println("");
        printWriter.println("");
        printWriter.println("           Подпись сотрудника:                                    Подпись получателя:");
        printWriter.println("           ___________________                                    ___________________");
        printWriter.println("");
        printWriter.println("");
        printWriter.println("                                      Обязательно к ознакомлению:\n\n    1.\tЧек является лишь подтверждением вашей транзакции (не представляет собой никакой стоимости).\n    2.\tCтоимость облигаций рассчитывается исходя из открытых данных «НацБанк РБ».\n");
        printWriter.close();
        System.out.println("Если печать не началась, то проверьте подключение принетера");

        try {
            Runtime.getRuntime().exec("notepad Справка о совершенной транзакции.txt");
        } catch (IOException var8) {
            var8.printStackTrace();
        }


    }
}
