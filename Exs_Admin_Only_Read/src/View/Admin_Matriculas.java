package View;

import Controller.Gestor;
import exs.logs.err.Log;
import exs.mod.Grupo;
import exs.mod.Matricula;
import exs.mod.Tutoria;
import exs.mod.var.Grupo_Var;
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
 * @author Kevin Villalobos A.
 */
public class Admin_Matriculas extends javax.swing.JFrame implements Observer {

    //Attributes
    private Grupo grupo = null;
    private Tutoria tutoria = null;
    private Gestor controller = null;
    private ArrayList<Matricula> matriculas = null;

    public Admin_Matriculas(Grupo g, Tutoria t) {
        excel=new Excel();
        if (g == null) {
            JOptionPane.showMessageDialog(rootPane, "El Grupo seleccionado es incorrecto!");
            Log.SendLog("El Grupo seleccionado es incorrecto!");
            this.setVisible(false);
        } else {
            this.grupo = g;
            this.tutoria = t;
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
            l_tuto.setText("Matrículas - " + grupo.getNum());
            this.setTitle("Matrículas - " + grupo.getNum());

//            b_matricula_add.setIcon(new ImageIcon("img/b_add.png"));
//
//            b_matricula_edit.setIcon(new ImageIcon("img/b_up.png"));
//
//            b_matricula_del.setIcon(new ImageIcon("img/b_del.png"));

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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationByPlatform(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                fix_Size(evt);
            }
        });

        l_tuto.setFont(new java.awt.Font("Tekton Pro Ext", 1, 24)); // NOI18N
        l_tuto.setText("Matricula - ");

        table_grupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Estudiante", "Fecha", "Estado", "NRC", "Veces Matrículado", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_grupos.getTableHeader().setReorderingAllowed(false);
        scrpane1.setViewportView(table_grupos);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/page_white_excel.png"))); // NOI18N
        jButton1.setToolTipText("Haga Click aqui para exportar la tabla a Excel");
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
                        .addContainerGap(744, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrpane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
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
                    .addComponent(scrpane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.guardarGrupoExcel();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void del_grupo() {
        int i = table_grupos.getSelectedRow();
        if (i >= 0) {
            Matricula g = this.matriculas.get(i);
            String msj = "Seguro que desea borrar la matricula del estudiante" + controller.getEstudiante(g.getEstudiante()).toString() + "?";
            int r = JOptionPane.showConfirmDialog(rootPane, msj, "Borrar Matrícula", JOptionPane.YES_NO_OPTION);
            if (r == 0) {
                controller.del_matricula(g);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar primero una matricula de la tabla");
        }
    }

    private void update_table(boolean query) {
        if (query) {
            if (matriculas != null) {
                matriculas.clear();
            }
            matriculas = controller.getMatriculas(grupo.getNum());

        }

        DefaultTableModel modelo = (DefaultTableModel) table_grupos.getModel();
        while (modelo.getRowCount() != 0) {
            modelo.removeRow(0);
        }

        for (int i = 0; matriculas != null && i < matriculas.size(); i++) {
            Matricula m = matriculas.get(i);
            Object[] array = new Object[6];
            array[0] = controller.getEstudiante(m.getEstudiante()).toString();
            array[1] = m.getFecha().toString();
            array[2] = m.getEstado();
            array[3] = m.getNrc();
            array[4] = m.getVeces();
            array[5] = m.getNota();
            modelo.addRow(array);

        }

    }
    
    Excel excel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel l_tuto;
    private javax.swing.JScrollPane scrpane1;
    private javax.swing.JTable table_grupos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        update_table(true);
    }
    
     private void guardarGrupoExcel() {
       Jfc j=new Jfc();
       String path=j.guardar();
       if(path!=null)
        excel.guardar(table_grupos,path);
    }
}