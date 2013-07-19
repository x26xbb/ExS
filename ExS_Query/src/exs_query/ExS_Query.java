package exs_query;

import View.Query_Main;
import com.alee.laf.WebLookAndFeel;
import exs.logs.err.Log;

/**
 *
 * @author Kevin Villalobos A.
 */
public class ExS_Query {

public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (Exception ex) {
            Log.SendLog(ex.getMessage());
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Query_Main().setVisible(true);
            }
        });
    }
}