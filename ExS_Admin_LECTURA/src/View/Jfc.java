/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.alee.laf.WebLookAndFeel;
import exs.logs.err.Log;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

/**
 *
 * @author Administrator
 * esta clase se tenia que hacer porque el WebAndLookFeel
 * no soporta bien el JFileChooser y es necesari tenerlo
 */
public class Jfc{

    public Jfc() {
         try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex) {
            Log.SendLog(ex.getMessage());
        }
        JFileChooser ch = new JFileChooser();   
    }

    
     public String guardar(){        
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showSaveDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
             setGUI();
             return chooser.getSelectedFile().getPath();           
        } else{
            setGUI();
            return null;
        }     
        
     }

    private void setGUI() {
    try {
            javax.swing.UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (Exception ex) {
            Log.SendLog(ex.getMessage());
        }
    }
}
