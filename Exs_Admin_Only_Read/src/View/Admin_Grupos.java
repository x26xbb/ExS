package View;

import Controller.Gestor;
import exs.logs.err.Log;
import exs.mod.Grupo;
import exs.mod.Tutoria;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java_to_excel.Excel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maynor Song Lara
 */
public class Admin_Grupos extends javax.swing.JFrame implements Observer {

    //Attributes
    private Tutoria tutoria = null;
    private Gestor controller = null;
    private ArrayList<Grupo> grupos = null;

    public Admin_Grupos(Tutoria tuto) {
        if (tuto == null) {
            JOptionPane.showMessageDialog(rootPane, "La tutoría seleccionada es incorrecta!");
            Log.SendLog("La tutoría seleccionada es incorrecta!");
            this.setVisible(false);
        } else {
            this.tutoria = tuto;
            this.controller = Gestor.getInstancia();
            initComponents();
            setImages();
            Gestor.getInstancia().addObserver(this);
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    close();
                }
            });
            update_table(true);
        }
    }


    private void close() {
        Gestor.getInstancia().deleteObserver(this);
        dispose();
    }

    private void setImages() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("img/elogo.png"));
            this.setIconImage(img);
            l_tuto.setText("Grupos - " + tutoria.toString());
            this.setTitle("Grupos - " + tutoria.toString());
            b_grupo_matriculas.setIcon(new ImageIcon("img/g_add.png"));

        } catch (Exception e) {
            Log.SendLog(e.getMessage());
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

        l_tuto = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        scrpane1 = new javax.swing.JScrollPane();
        table_grupos = new javax.swing.JTable();
        b_grupo_matriculas = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationByPlatform(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                fix_Size(evt);
            }
        });

        l_tuto.setFont(new java.awt.Font("Tekton Pro Ext", 1, 24)); // NOI18N
        l_tuto.setText("Grupos - ");

        table_grupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Tutor", "Año", "Ciclo", "Horario", "Estado", "Lugar", "Matrículas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_grupos.getTableHeader().setReorderingAllowed(false);
        scrpane1.setViewportView(table_grupos);

        b_grupo_matriculas.setText("Matrículas");
        b_grupo_matriculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_grupo_matriculasActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/page_white_excel.png"))); // NOI18N
        jButton1.setToolTipText("Haga Click Aqui para Exportar a Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_tuto)
                        .addContainerGap(764, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrpane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b_grupo_matriculas, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(l_tuto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_grupo_matriculas, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrpane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fix_Size(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_fix_Size
        if (getWidth() < 850) {
            setSize(850, getHeight());
        }
        if (getHeight() < 515) {
            setSize(getWidth(), 515);
        }
    }//GEN-LAST:event_fix_Size

    private void b_grupo_matriculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_grupo_matriculasActionPerformed

        ///CON CHECK CONSULTA=SIN CHECK!ERROR
        int i = table_grupos.getSelectedRow();
        if (i >= 0) {
//            
            Grupo u = this.grupos.get(i);
            new Admin_Matriculas(u, tutoria).setVisible(true);
           }else{
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar primero una tutoría de la tabla");
            }
//       }
//
    }//GEN-LAST:event_b_grupo_matriculasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.guardarTutoriasExcel();
    }//GEN-LAST:event_jButton1ActionPerformed


    private void update_table(boolean query) {
        if (query) {
            if (grupos != null) {
                grupos.clear();
            }

            grupos = controller.getGrupos(tutoria.getCod());

        }
       
        DefaultTableModel modelo = (DefaultTableModel) table_grupos.getModel();
        while (modelo.getRowCount() != 0) {
            modelo.removeRow(0);
        }

        for (int i = 0; grupos != null && i < grupos.size(); i++) {
            Object[] array = grupos.get(i).toArray();
            array[1] = controller.getTutor((String) array[1]).toString();
            array[7] = controller.getCantidadMatriculas((String)array[0]);
                modelo.addRow(array);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_grupo_matriculas;
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel l_tuto;
    private javax.swing.JScrollPane scrpane1;
    private javax.swing.JTable table_grupos;
    // End of variables declaration//GEN-END:variables
    Excel excel=new Excel();
    
    @Override
    public void update(Observable o, Object arg) {
        update_table(true);
    }
    
      private void guardarTutoriasExcel() {
       Jfc j=new Jfc();
       String path=j.guardar();
       if(path!=null)
        excel.guardar(table_grupos,path);
    }
}
