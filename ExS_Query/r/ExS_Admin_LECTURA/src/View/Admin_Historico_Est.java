/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import exs.db.Historico;
import exs.mod.Grupo;
import java.util.ArrayList;
import java_to_excel.Excel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Administrator
 */
public class Admin_Historico_Est extends javax.swing.JFrame {
    private TableRowSorter sorterHistorico;

    /**
     * Creates new form Admin_Historico_Est
     */
    public Admin_Historico_Est(ArrayList datos) {
        initComponents();fillTableHistorico(datos); 
         sorterHistorico = new TableRowSorter(table_historico.getModel());
         table_historico.setRowSorter(sorterHistorico);
    }
    
      private void limpiaTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        while (modelo.getRowCount() != 0) {
            modelo.removeRow(0);
        }
    }

    private void fillTableHistorico(ArrayList<Historico> datos) {
        if(datos==null){ JOptionPane.showMessageDialog(null, "No existen registros para esta persona");return;}
        else{
            this.setTitle("Historial de Matricula de "+datos.get(0).getCed()+","+datos.get(0).getNombre()+datos.get(0).getPa()+datos.get(0).getSa()+" -  ExS");
            this.labelhistorialde_tipo.setText(datos.get(0).getCed()+", "+datos.get(0).getNombre()+" "+datos.get(0).getPa()+" "+datos.get(0).getSa());
            limpiaTabla(table_historico);
            DefaultTableModel modelo = (DefaultTableModel) table_historico.getModel();
            for (int i = 0; i < datos.size(); i++) {
                Object[] array = datos.get(i).toArray();
                modelo.addRow(array);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        labelhistorialde_tipo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_historico = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Historial de Matricula ");

        labelhistorialde_tipo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        table_historico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Tutoria", "Numero", "Horario", "Ciclo", "Anio", "Estado", "Lugar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_historico);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/page_white_excel.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ver Curso");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelhistorialde_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(287, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelhistorialde_tipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.guardarHistorial();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         int i = this.table_historico.getSelectedRow();      
        if (i >= 0) {
            String cod=null;
            cod=(String) table_historico.getModel().getValueAt(table_historico.getSelectedRow(),0);
            Grupo u = getGrupo(cod);
            if(u!=null){
                new Admin_Matriculas(u, Admin_Grupos.tutoria).setVisible(true);
            }else{
                            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar primero un grupo de la tabla");
            }
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private Grupo getGrupo(String cod) {
        return Admin_Main.controller.getGrupo(cod);
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Admin_Historico_Est.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Admin_Historico_Est.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Admin_Historico_Est.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Admin_Historico_Est.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Admin_Historico_Est().setVisible(true);
//            }
//        });
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelhistorialde_tipo;
    private javax.swing.JTable table_historico;
    // End of variables declaration//GEN-END:variables
    private Excel excel;
    
    private void guardarHistorial() {
         Jfc j=new Jfc();
         excel=new Excel();
         String path=j.guardar();
         if(path!=null){           
             excel.guardar(table_historico,path,this.jLabel1.getText()+this.labelhistorialde_tipo.getText());
    
         }
    }
}
