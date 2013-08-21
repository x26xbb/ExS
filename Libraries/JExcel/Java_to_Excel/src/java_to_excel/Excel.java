/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_to_excel;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Excel {

    private String inputFile;

    
    public Excel(){
    }
    
    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void write(JTable table_estudiantes) throws IOException, WriteException {
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Reporte", 0);
        WritableSheet excelSheet = workbook.getSheet(0);     
        JTableHeader th = table_estudiantes.getTableHeader();  
        TableColumnModel tcm = th.getColumnModel();  
        int col=0;
        excelSheet.getSettings().setDefaultColumnWidth(20);
        
        for(int fila = 0, y = tcm.getColumnCount(); fila < y; fila++){  
          TableColumn tc = tcm.getColumn(fila);  
          excelSheet.addCell(new Label(col,0, String.valueOf(tc.getHeaderValue()))); 
          System.out.println("Column name = "+tc.getHeaderValue()+", width = "+tc.getWidth());  
          col++;
        }          
        for(int i=0;i< table_estudiantes.getColumnCount();i++){	
            for(int j=0;j<table_estudiantes.getRowCount();j++){
		Object objeto=table_estudiantes.getValueAt(j,i);
			excelSheet.addCell(new Label(i, j+1, String.valueOf(objeto)));        
            }
        }
        workbook.write();
        workbook.close();
    }
    
    public void guardar(JTable tabla,String path) {
        try {            
            this.setOutputFile(path+".xls");
            this.write(tabla);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}