package vistasexito;

import View.Admin_Main;
import com.alee.laf.WebLookAndFeel;
import exs.logs.err.Log;

/**
 *
 * @author Kevin Villalobos A.
 */
public class VistasExito {

    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (Exception ex) {
            Log.SendLog(ex.getMessage());
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Main().setVisible(true);
            }
        });
    }
}
