/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class Jfc extends JFrame{
    public Jfc() {
      
    }
    
     public String guardar(){
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
              System.out.println("You chose to open this file: " +
              chooser.getSelectedFile().getName());              
        } 
        return chooser.getSelectedFile().getPath();
     }
}
