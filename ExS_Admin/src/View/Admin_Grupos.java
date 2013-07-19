package View;

import Controller.Gestor;
import exs.logs.err.Log;
import exs.mod.Grupo;
import exs.mod.Tutoria;
import exs.mod.var.Grupo_Var;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin Villalobos A.
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
            grupoMostrar(false);
            fill_combos();

            update_table(true);
        }
    }

    private void fill_combos() {
        try {
            int anio = Grupo_Var.getAnio();
            int ciclo = Grupo_Var.getCiclo();
            for (int i = -2; i < 3; i++) {
                cb_AnioModel.addElement("" + (anio + i));
            }
            for (int i = 0; i < Grupo_Var.CICLOS.length; i++) {
                cb_CicloModel.addElement("" + Grupo_Var.CICLOS[i]);
            }

            cb_Anio.setSelectedIndex(2);
            cb_Ciclo.setSelectedIndex(ciclo);
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
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

            b_grupo_add.setIcon(new ImageIcon("img/b_add.png"));

            b_grupo_edit.setIcon(new ImageIcon("img/b_up.png"));

            b_grupo_del.setIcon(new ImageIcon("img/b_del.png"));
            
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
        b_grupo_add = new javax.swing.JButton();
        b_grupo_edit = new javax.swing.JButton();
        b_grupo_del = new javax.swing.JButton();
        chk_Mostrar = new javax.swing.JCheckBox();
        l_Ciclo = new javax.swing.JLabel();
        cb_Ciclo = new javax.swing.JComboBox();
        l_Anio = new javax.swing.JLabel();
        cb_Anio = new javax.swing.JComboBox();
        b_grupo_matriculas = new javax.swing.JButton();

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

        b_grupo_add.setText("Agregar");
        b_grupo_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_grupo_addActionPerformed(evt);
            }
        });

        b_grupo_edit.setText("  Editar");
        b_grupo_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_grupo_editActionPerformed(evt);
            }
        });

        b_grupo_del.setText("Borrar");
        b_grupo_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_grupo_delActionPerformed(evt);
            }
        });

        chk_Mostrar.setText("Sólo mostrar del ciclo:");
        chk_Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_Change(evt);
            }
        });

        l_Ciclo.setText("Ciclo");

        cb_CicloModel = new javax.swing.DefaultComboBoxModel();
        cb_Ciclo.setModel(cb_CicloModel);
        cb_Ciclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listenerCombos(evt);
            }
        });

        l_Anio.setText("Año");

        cb_AnioModel = new javax.swing.DefaultComboBoxModel();
        cb_Anio.setModel(cb_AnioModel);
        cb_Anio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listenerCombos(evt);
            }
        });

        b_grupo_matriculas.setText("Matrículas");
        b_grupo_matriculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_grupo_matriculasActionPerformed(evt);
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
                        .addGap(0, 711, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrpane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_grupo_add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_grupo_edit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_grupo_del, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chk_Mostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(l_Anio)
                                    .addComponent(l_Ciclo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cb_Ciclo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cb_Anio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(b_grupo_matriculas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(l_tuto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(b_grupo_add, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_grupo_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_grupo_del, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_grupo_matriculas, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(chk_Mostrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_Ciclo)
                            .addComponent(cb_Ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_Anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l_Anio))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrpane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_grupo_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_grupo_addActionPerformed
        new Admin_New_Grupo(tutoria, null, grupos).setVisible(true);
    }//GEN-LAST:event_b_grupo_addActionPerformed

    private void b_grupo_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_grupo_editActionPerformed
        int i = table_grupos.getSelectedRow();
        if (i >= 0) {
            Grupo u = this.grupos.get(i);
            new Admin_New_Grupo(tutoria, u, grupos).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar primero una tutoría de la tabla");
        }
    }//GEN-LAST:event_b_grupo_editActionPerformed

    private void b_grupo_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_grupo_delActionPerformed
        del_grupo();
    }//GEN-LAST:event_b_grupo_delActionPerformed

    private void fix_Size(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_fix_Size
        if (getWidth() < 850) {
            setSize(850, getHeight());
        }
        if (getHeight() < 515) {
            setSize(getWidth(), 515);
        }
    }//GEN-LAST:event_fix_Size

    private void listenerCombos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listenerCombos
        update_table(false);
    }//GEN-LAST:event_listenerCombos

    private void chk_Change(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_Change
        chk_Change_M();
    }//GEN-LAST:event_chk_Change

    private void b_grupo_matriculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_grupo_matriculasActionPerformed

        int i = table_grupos.getSelectedRow();
        if (i >= 0) {
            Grupo u = this.grupos.get(i);
            new Admin_Matriculas(u, tutoria).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar primero una tutoría de la tabla");
        }

    }//GEN-LAST:event_b_grupo_matriculasActionPerformed

    private void chk_Change_M() {
        if (chk_Mostrar.isSelected()) {
            grupoMostrar(true);
        } else {
            grupoMostrar(false);
        }
        update_table(false);
    }

    private void grupoMostrar(boolean b) {
        l_Anio.setVisible(b);
        l_Ciclo.setVisible(b);
        cb_Anio.setVisible(b);
        cb_Ciclo.setVisible(b);
    }

    private void del_grupo() {
        int i = table_grupos.getSelectedRow();
        if (i >= 0) {
            Grupo g = this.grupos.get(i);
            String msj = "Seguro que desea borrar el grupo " + g.getNum() + "?";
            int r = JOptionPane.showConfirmDialog(rootPane, msj, "Borrar Grupo", JOptionPane.YES_NO_OPTION);
            if (r == 0) {
                controller.del_grupo(g);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar primero un grupo de la tabla");
        }
    }

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
            if (chk_Mostrar.isSelected()) {
                int anio = Integer.parseInt((String) cb_AnioModel.getSelectedItem());
                String ciclo = (String) cb_CicloModel.getSelectedItem();
                if (array[2].equals(anio) && array[3].equals(ciclo)) {
                    modelo.addRow(array);
                }
            } else {
                modelo.addRow(array);
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_grupo_add;
    private javax.swing.JButton b_grupo_del;
    private javax.swing.JButton b_grupo_edit;
    private javax.swing.JButton b_grupo_matriculas;
    private javax.swing.DefaultComboBoxModel cb_AnioModel;
    private javax.swing.JComboBox cb_Anio;
    private javax.swing.DefaultComboBoxModel cb_CicloModel;
    private javax.swing.JComboBox cb_Ciclo;
    private javax.swing.JCheckBox chk_Mostrar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel l_Anio;
    private javax.swing.JLabel l_Ciclo;
    private javax.swing.JLabel l_tuto;
    private javax.swing.JScrollPane scrpane1;
    private javax.swing.JTable table_grupos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        update_table(true);
    }
}
