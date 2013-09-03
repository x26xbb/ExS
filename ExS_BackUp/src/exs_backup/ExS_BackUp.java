/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exs_backup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author AdministratorAPP PARA SERVIDOR
 * EN SERVIDOR(CMD): cd C:\Archivos de Programa\MySQL\MySQL Server 5.6\bin
 * mysqldump -u root -pexito@una exsdb > respaldoDD-MM-YY.sql
 */
public class ExS_BackUp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
      public boolean HacerRespaldo() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        java.util.Date date = new java.util.Date();
        String executeCmd = "mysqldump -u root -pexito@una exsdb > C:\\Documentos and Settings\\UNA\\respaldo"+dateFormat.format(date)+".sql";
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                System.out.println("Backup created successfully");
                JOptionPane.showMessageDialog(null, "Backup created successfully supuestamente");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Could not create the backup supuestamente");
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return false;
    }
}
