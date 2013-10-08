package View;

import Controller.Gestor;
import exs.logs.err.Log;
import exs.mod.Carrera;
import exs.mod.Estudiante;
import exs.mod.Grupo;
import exs.mod.Tutoria;
import exs.mod.var.Estudiante_Var;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java_to_excel.Excel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Query_Main extends javax.swing.JFrame implements Observer {

    private Gestor controller = Gestor.getInstancia();
    private static int MIN_H = 600;
    private static int MIN_W = 975;
//    private TableRowSorter sorterTutorias;
//    private TableRowSorter sorterEstudiantes;

    /**
     * Creates new form Main
     */
    public Query_Main() {
        this.excel = new Excel();      
        if (controller.isUsable()) {
            initComponents();
            initCombos();
            setImages();
            controller.addObserver(this);
        } else {
            setVisible(false);
            JOptionPane.showMessageDialog(null, "No se puede continuar con la ejecucion del programa...", "DB Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void setImages() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("img/elogo.png"));
            this.setIconImage(img);
            l_ima.setIcon(new ImageIcon("img/front_admin.jpg"));
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
        }
        fixIma();
        setMaximumSize(new Dimension(MIN_W, MIN_H));
    }

    //Metodos de consulta
    private void initCombos() {
        cb_est_carreras.setMaximumSize(new Dimension(195, 20));
        fill_combo_sede_est();
        fill_combo_tutorias();
    }

    private void fill_combo_tutorias() {
        cb_tuto_grupos.setEnabled(false);
        ArrayList<Tutoria> tutorias = controller._getTutorias();
        for (int i = 0; i < tutorias.size(); i++) {
            cb_tuto.addItem(tutorias.get(i).getNom());
        }
    }

    private void fill_combo_sede_est() {
        cb_est_carreras.setEnabled(false);
        for (int i = 0; i < Estudiante_Var.SEDES.length; i++) {
            cb_est_sedes.addItem(Estudiante_Var.getSede(i));
        }
    }

    private void update_combo_est_carreras() {
        if (cb_est_sedes.getSelectedIndex() <= 0) {
            cb_est_carreras.setEnabled(false);
        } else {
            cb_est_carreras.setEnabled(true);
            int sede = cb_est_sedes.getSelectedIndex() - 1;
            carreras_sede = controller.getCarreras_Sede(sede);
            cb_est_carreras.removeAllItems();
            cb_est_carreras.addItem("--");
            for (int i = 0; i < carreras_sede.size(); i++) {
                cb_est_carreras.addItem(carreras_sede.get(i).getNombre());
            }
        }
    }
    
    private void update_combo_tuto_grupos(){
        if (cb_tuto.getSelectedIndex() <= 0) {
            cb_tuto_grupos.setEnabled(false);
        } else {
            cb_tuto_grupos.setEnabled(true);
            String tutoria = controller._getTutorias().get(cb_tuto.getSelectedIndex() - 1).getCod();
            ArrayList<Grupo> grupos = controller.getGrupos(tutoria);
            cb_tuto_grupos.removeAllItems();
            cb_tuto_grupos.addItem("--");
            for (int i = 0; i < grupos.size(); i++) {
                cb_tuto_grupos.addItem(grupos.get(i).getNum());
            }
        }
    }
    //Estudiantes

    private void fillTable_Est() {
        try {
            this.setTitle("Estudiantes  -  ExS");
            limpiaTabla(table_estudiantes);
            int sede = ((cb_est_sedes.getSelectedIndex() > 0)) ? cb_est_sedes.getSelectedIndex() - 1 : -1;
            int carrera = (cb_est_carreras.getSelectedIndex() > 0) ? carreras_sede.get(cb_est_carreras.getSelectedIndex() - 1).getNum() : -1;
            ArrayList<Estudiante> list = controller.getEstudiantes_Filter(t_est_cedula.getText(), t_est_nombre.getText(), sede, carrera);
            DefaultTableModel modelo = (DefaultTableModel) table_estudiantes.getModel();
            for (int i = 0; i < list.size(); i++) {
                Object[] array = list.get(i).toArray();
                array[8] = controller.getCarrera(Integer.parseInt((String) array[8]));
                modelo.addRow(array);
            }
            l_est_cant.setText(list.size() + "");
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
        }
    }
    
    //Estudiantes

    private void fillTable_Tuto() {
        try {
            this.setTitle("Tutorías  -  ExS");
            limpiaTabla(table_tutorias);
            ArrayList<Estudiante> list;
            if((cb_tuto.getSelectedIndex() > 0)){
                int tuto =  cb_tuto.getSelectedIndex() - 1;
                int grupo = (cb_tuto_grupos.getSelectedIndex() > 0) ? cb_tuto_grupos.getSelectedIndex() - 1: -1;
             list = controller.getEstudiantes_Filter(tuto, grupo);
            }
            else{
                list = controller._getEstudiantes();
            }
            if(chk_Mostrar.isSelected()){
                
            }
            
            DefaultTableModel modelo = (DefaultTableModel) table_tutorias.getModel();
            for (int i = 0; i < list.size(); i++) {
                Object[] array = list.get(i).toArray();
                array[8] = controller.getCarrera(Integer.parseInt((String) array[8]));
                modelo.addRow(array);
            }
            l_tuto_cant.setText(list.size() + "");
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
        }
    }
    private void chk_Change_M(){
        
    }
    //Util
    private void limpiaTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        while (modelo.getRowCount() != 0) {
            modelo.removeRow(0);
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

        l_ima = new javax.swing.JLabel();
        tab_pane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        cb_tuto = new javax.swing.JComboBox();
        l_est_tcant1 = new javax.swing.JLabel();
        l_tuto_cant = new javax.swing.JLabel();
        l_est_sede1 = new javax.swing.JLabel();
        scrpane5 = new javax.swing.JScrollPane();
        table_tutorias = new javax.swing.JTable();
        l_est_carrera1 = new javax.swing.JLabel();
        cb_tuto_grupos = new javax.swing.JComboBox();
        chk_Mostrar = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        l_est_tcant = new javax.swing.JLabel();
        cb_est_carreras = new javax.swing.JComboBox();
        l_est_carrera = new javax.swing.JLabel();
        l_est_nombre = new javax.swing.JLabel();
        t_est_nombre = new javax.swing.JTextField();
        l_est_ced = new javax.swing.JLabel();
        t_est_cedula = new javax.swing.JTextField();
        l_est_cant = new javax.swing.JLabel();
        l_est_sede = new javax.swing.JLabel();
        cb_est_sedes = new javax.swing.JComboBox();
        scrpane4 = new javax.swing.JScrollPane();
        table_estudiantes = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        l_ima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/front_admin.jpg"))); // NOI18N

        tab_pane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tab_paneStateChanged(evt);
            }
        });

        cb_tuto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--" }));
        cb_tuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tutoActionPerformed(evt);
            }
        });

        l_est_tcant1.setFont(new java.awt.Font("Tekton Pro Ext", 0, 14)); // NOI18N
        l_est_tcant1.setText("Cantidad de Estudiantes:");

        l_tuto_cant.setFont(new java.awt.Font("Tekton Pro Ext", 0, 18)); // NOI18N
        l_tuto_cant.setText("00");

        l_est_sede1.setText("Tutoría");

        table_tutorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Genero", "Nombre", "Télefono", "Celular", "E-Mail", "Beca", "Sede", "Carrera"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_tutorias.getTableHeader().setReorderingAllowed(false);
        scrpane5.setViewportView(table_tutorias);

        l_est_carrera1.setText("Grupo");

        cb_tuto_grupos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--" }));
        cb_tuto_grupos.setMaximumSize(getPreferredSize());
        cb_tuto_grupos.setPreferredSize(new java.awt.Dimension(195, 25));
        cb_tuto_grupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tuto_gruposActionPerformed(evt);
            }
        });

        chk_Mostrar.setText("Sólo mostrar ciclo actual. ");
        chk_Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_Mostrarchk_Change(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/page_white_excel.png"))); // NOI18N
        jButton2.setToolTipText("Haga Click Aqui para Exportar a Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrpane5, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(l_tuto_cant, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_est_tcant1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l_est_sede1)
                    .addComponent(cb_tuto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l_est_carrera1)
                    .addComponent(cb_tuto_grupos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chk_Mostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(l_est_tcant1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_tuto_cant)
                .addGap(3, 3, 3)
                .addComponent(l_est_sede1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_tuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(l_est_carrera1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_tuto_grupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chk_Mostrar)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrpane5, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab_pane.addTab("Tutorías", jPanel2);

        l_est_tcant.setFont(new java.awt.Font("Tekton Pro Ext", 0, 14)); // NOI18N
        l_est_tcant.setText("Cantidad de Estudiantes:");

        cb_est_carreras.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--" }));
        cb_est_carreras.setMaximumSize(getPreferredSize());
        cb_est_carreras.setPreferredSize(new java.awt.Dimension(195, 25));
        cb_est_carreras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_est_carrerasActionPerformed(evt);
            }
        });

        l_est_carrera.setText("Carrera");

        l_est_nombre.setText("Nombre");

        t_est_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_est_nombreKeyReleased(evt);
            }
        });

        l_est_ced.setText("Cédula");

        t_est_cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_est_cedulaKeyReleased(evt);
            }
        });

        l_est_cant.setFont(new java.awt.Font("Tekton Pro Ext", 0, 18)); // NOI18N
        l_est_cant.setText("00");

        l_est_sede.setText("Sede");

        cb_est_sedes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--" }));
        cb_est_sedes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_est_sedesActionPerformed(evt);
            }
        });

        table_estudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Genero", "Nombre", "Télefono", "Celular", "E-Mail", "Beca", "Sede", "Carrera"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_estudiantes.getTableHeader().setReorderingAllowed(false);
        scrpane4.setViewportView(table_estudiantes);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/page_white_excel.png"))); // NOI18N
        jButton3.setToolTipText("Haga Click Aqui para exportar a Excel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrpane4, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(l_est_cant, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l_est_carrera)
                            .addComponent(l_est_nombre)
                            .addComponent(l_est_ced))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(t_est_cedula, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(t_est_nombre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cb_est_sedes, javax.swing.GroupLayout.Alignment.LEADING, 0, 116, Short.MAX_VALUE)
                                .addComponent(l_est_sede, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cb_est_carreras, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE))
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l_est_tcant))
                        .addGap(0, 15, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(l_est_tcant)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_est_cant)
                        .addGap(3, 3, 3)
                        .addComponent(l_est_sede)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_est_sedes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(l_est_carrera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_est_carreras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(l_est_nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(t_est_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(l_est_ced)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(t_est_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrpane4, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
                .addContainerGap())
        );

        tab_pane.addTab("Estudiantes", jPanel1);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/una_logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(0, 0, 0))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(l_ima)
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(l_ima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addComponent(tab_pane)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tab_paneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tab_paneStateChanged
        update_tables();
    }//GEN-LAST:event_tab_paneStateChanged

    private void cb_tutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tutoActionPerformed
       update_combo_tuto_grupos();
       fillTable_Tuto();
    }//GEN-LAST:event_cb_tutoActionPerformed

    private void cb_tuto_gruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tuto_gruposActionPerformed
        fillTable_Tuto();
    }//GEN-LAST:event_cb_tuto_gruposActionPerformed

    private void chk_Mostrarchk_Change(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_Mostrarchk_Change
        chk_Change_M();
    }//GEN-LAST:event_chk_Mostrarchk_Change

    private void cb_est_sedesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_est_sedesActionPerformed
        update_combo_est_carreras();
        fillTable_Est();
    }//GEN-LAST:event_cb_est_sedesActionPerformed

    private void t_est_cedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_est_cedulaKeyReleased
        fillTable_Est();
    }//GEN-LAST:event_t_est_cedulaKeyReleased

    private void t_est_nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_est_nombreKeyReleased
        fillTable_Est();
    }//GEN-LAST:event_t_est_nombreKeyReleased

    private void cb_est_carrerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_est_carrerasActionPerformed
        fillTable_Est();
    }//GEN-LAST:event_cb_est_carrerasActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            this.guardarEstudiantesExcel();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            this.guardarTutoriasExcel();
    }//GEN-LAST:event_jButton2ActionPerformed

    
    Excel excel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cb_est_carreras;
    private javax.swing.JComboBox cb_est_sedes;
    private javax.swing.JComboBox cb_tuto;
    private javax.swing.JComboBox cb_tuto_grupos;
    private javax.swing.JCheckBox chk_Mostrar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel l_est_cant;
    private javax.swing.JLabel l_est_carrera;
    private javax.swing.JLabel l_est_carrera1;
    private javax.swing.JLabel l_est_ced;
    private javax.swing.JLabel l_est_nombre;
    private javax.swing.JLabel l_est_sede;
    private javax.swing.JLabel l_est_sede1;
    private javax.swing.JLabel l_est_tcant;
    private javax.swing.JLabel l_est_tcant1;
    private javax.swing.JLabel l_ima;
    private javax.swing.JLabel l_tuto_cant;
    private javax.swing.JScrollPane scrpane4;
    private javax.swing.JScrollPane scrpane5;
    private javax.swing.JTextField t_est_cedula;
    private javax.swing.JTextField t_est_nombre;
    private javax.swing.JTabbedPane tab_pane;
    private javax.swing.JTable table_estudiantes;
    private javax.swing.JTable table_tutorias;
    // End of variables declaration//GEN-END:variables
    //Atributos
    private ArrayList<Carrera> carreras_sede = null;
    //

    @Override
    public void update(Observable o, Object arg) {
        update_tables();
    }

    private void update_tables() {

        int i = tab_pane.getSelectedIndex();
        switch (i) {
            case 0:
                fillTable_Est();
                break;
            case 1:
                fillTable_Tuto();
                break;
            default:
                fillTable_();

        }
        fixIma();
    }

    private void fillTable_() {

        JOptionPane.showMessageDialog(rootPane, "Se actualiza table OTRO!!");
    }

    private void fixIma() {
        l_ima.setLocation((this.getWidth() / 2) - (l_ima.getWidth() / 2), l_ima.getY());
    }
    
    
    private void guardarTutoriasExcel(){
         Jfc j=new Jfc();
         String path=j.guardar();
         if(path!=null)
            excel.guardar(table_tutorias,path);
    }

    private void guardarEstudiantesExcel() {
        Jfc j=new Jfc();
        String path=j.guardar();
         if(path!=null)
            excel.guardar(table_estudiantes,path);
    }
    
//    private void setOrdenadoresTablas() {
//        sorterTutorias = new TableRowSorter(table_tutorias.getModel());
//        table_tutorias.setRowSorter(sorterTutorias);
//        sorterEstudiantes = new TableRowSorter(table_estudiantes.getModel());
//        table_estudiantes.setRowSorter(sorterEstudiantes);
//    }
}