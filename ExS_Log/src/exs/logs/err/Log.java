package exs.logs.err;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Log {

    public static void SendLog(String msj) {
        Calendar h = Calendar.getInstance();
        h.get(Calendar.MONTH);
        h.get(Calendar.DAY_OF_MONTH);
        h.get(Calendar.YEAR);
        String path = "log/" +(h.get(Calendar.MONTH) + 1)+"-"+ h.get(Calendar.DAY_OF_MONTH) + "-" + h.get(Calendar.YEAR) +"MM/DD/YYYY"+ ".lgg";
        System.out.println("HOLAAAAAAA"+path + "\t" + msj);
        try {          
            BufferedWriter out = new BufferedWriter(new FileWriter(path, true));            
            out.write(h.getTime().toString() + "\t\t"+ msj+"\n");
            out.close();            
        } catch (IOException i) {
            System.err.println(i.getMessage());
        }
    }
}
