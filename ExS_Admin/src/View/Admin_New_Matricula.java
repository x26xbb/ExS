package View;

//import Controller.Gestor_Est;
import Controller.Gestor;
import View.Util.Util;
import exs.logs.err.Log;
import exs.mod.Estudiante;
import exs.mod.Grupo;
import exs.mod.Matricula;
import exs.mod.Tutoria;
import exs.mod.var.Matricula_Var;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Admin_New_Matricula extends javax.swing.JFrame {

    private Grupo grupo = null;
    private Tutoria tutoria = null;
    private Matricula matricula = null;
    private Estudiante estudiante = null;
    private Admin_Select_Estudiante selector = null;

    public Admin_New_Matricula(Grupo g, Tutoria t, Matricula m) {
        this.grupo = g;
        this.tutoria = t;
        this.matricula = m;
        selector = new Admin_Select_Estudiante();
        initComponents();
        init();
    }

    private void init() {
        try {
            t_grupo.setEditable(false);
            t_grupo.setEnabled(false);
            t_grupo.setText(grupo.getNum());
            t_tutoria.setEditable(false);
            t_tutoria.setEnabled(false);
            t_tutoria.setText(tutoria.toString());
            t_est.setEditable(false);
            sp_cant.setModel(new SpinnerNumberModel(1, 1, 10, 1));
            l_nrc.setVisible(false);
            l_nota.setVisible(false);
            setImages();
            fill_combos();
            if (matricula != null) {
                setTitle("Editar Matrícula");
                l_top.setText("Modificar Matrícula");
                l_gre.setText("Ingrese los datos para modificar una matricula y luego presione el botón de Editar.");
                b_add.setText("Editar");
                sp_cant.setValue(matricula.getVeces());
                t_nrc.setText(matricula.getNrc() + "");
                t_nota.setText(matricula.getNota() + "");
                estudiante =  Gestor.getInstancia().getEstudiante(matricula.getEstudiante());
                setEstudiante();
                set_estado_combo();
                t_est.setEnabled(false);
            } else {
                setTitle("Agregar Matrícula");




                selector.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowDeactivated(WindowEvent e) {
                        super.windowDeactivated(e);
                        change_Est();
                    }
                });
            }

        } catch (Exception e) {
            Log.SendLog(e.getMessage());
        }


    }

    private void setImages() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("img/elogo.png"));
            this.setIconImage(img);
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
        }

    }

    private void select_est() {
        int x = 250 + getX();
        int y = 150 + getY();
        selector.setChange(false);
        selector.setLocation(x, y);
        selector.update_table();
        selector.setVisible(true);

    }

    private void setEstudiante() {
        if (estudiante == null) {
            t_est.setText("Haga Click aquí para seleccionar...");
        } else {
            t_est.setText(estudiante.getNombre() + " " + estudiante.getPriApellido() + " "
                    + estudiante.getSegApellido().charAt(0) + " " + estudiante.getId());
        }
    }

    private void change_Est() {
        if (selector.isChange()) {
            int i = selector.getSeleccion();
            if (i == -1) {
                estudiante = null;
            } else {
                estudiante = Gestor.getInstancia().getEstudiantes().get(i);
            }
            setEstudiante();
        }
    }

    private void set_estado_combo() {
        if (matricula != null) {
            String estado = matricula.getEstado();
            if (estado.equals(Matricula_Var.ACTIVO)) {
                cb_estado.setSelectedIndex(0);
            } else {
                if (estado.equals(Matricula_Var.INACTIVO)) {
                    cb_estado.setSelectedIndex(1);
                } else {
                    cb_estado.setSelectedIndex(2);
                }
            }
        }
    }

    private void fill_combos() {
        cb_estado.addItem(Matricula_Var.ACTIVO);
        cb_estado.addItem(Matricula_Var.INACTIVO);
        cb_estado.addItem(Matricula_Var.SUSPENDIDO);
    }

    private void send() {
        if (tutoria != null && grupo != null && estudiante != null
                && !t_grupo.getText().equals("") && !t_tutoria.getText().equals("")
                && !t_nrc.getText().equals("") && !t_nota.getText().equals("")) {
            if (!l_nrc.isVisible() && !l_nota.isVisible()) {
                setVisible(false);
                JOptionPane.showMessageDialog(rootPane,
                    "El comprobante de matrícula y los detalles del curso serán enviados a al correo del estudiante.",
                    "Matrícula", JOptionPane.INFORMATION_MESSAGE);
                if (matricula == null) {
                    Gestor.getInstancia().insert_matricula(
                    new Matricula(grupo.getNum(), estudiante.getId() + "", null, 
                    (String)cb_estado.getSelectedItem(), 0.0f, Integer.parseInt(t_nrc.getText()), (Integer) sp_cant.getValue()),
                    estudiante, grupo, tutoria);
                    Gestor.getInstancia().revisarSiRetiro(estudiante.getId(),grupo.getTcod());
                } else {
                    matricula.setEstado((String)cb_estado.getSelectedItem());
                    matricula.setNota(Float.parseFloat(t_nota.getText()));
                    matricula.setNrc(Integer.parseInt( t_nrc.getText() ));
                    matricula.setVeces( (Integer)sp_cant.getValue() );                    
                    Gestor.getInstancia().update_matricula(matricula, estudiante, grupo, tutoria);
                }
                
                close();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Verifique el formato de los datos indicados..");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe completar todos los espacios en blanco.");
        }

    }

    private void close() {
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        l_top = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        b_add = new javax.swing.JButton();
        b_cancel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sp_cant = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        t_nrc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        l_gre = new javax.swing.JLabel();
        l_nrc = new javax.swing.JLabel();
        t_tutoria = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        t_grupo = new javax.swing.JTextField();
        t_est = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        t_nota = new javax.swing.JTextField();
        l_nota = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cb_estado = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(getPreferredSize());
        setResizable(false);

        l_top.setFont(new java.awt.Font("Tekton Pro Ext", 1, 24)); // NOI18N
        l_top.setText("Agregar Matrícula");

        jSeparator1.setEnabled(false);

        b_add.setText("Agregar");
        b_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_addActionPerformed(evt);
            }
        });

        b_cancel.setText("Cancelar");
        b_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelActionPerformed(evt);
            }
        });

        jLabel2.setText("Tutoría");

        jLabel4.setText("Cantidad de veces matriculado");

        jLabel5.setText("NRC");

        t_nrc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chk_nrc(evt);
            }
        });

        jLabel6.setText("Estudiante ");

        l_gre.setText("Ingrese los datos para agregar una matricula y luego presione el botón de Agregar.");

        l_nrc.setForeground(new java.awt.Color(153, 0, 0));
        l_nrc.setText("El NRC debe ser únicamente números.");

        jLabel7.setText("Grupo");

        t_est.setEditable(false);
        t_est.setText("Haga Click aquí para seleccionar...");
        t_est.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_est(evt);
            }
        });

        jLabel8.setText("Nota");

        t_nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                key_nota(evt);
            }
        });

        l_nota.setForeground(new java.awt.Color(153, 0, 0));
        l_nota.setText("La nota debe ser únicamente números.");

        jLabel1.setText("Estado");

        cb_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(sp_cant, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(t_est)
                                            .addComponent(t_tutoria)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel8))
                                                .addGap(62, 62, 62))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(52, 52, 52)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(t_nrc)
                                            .addComponent(t_nota)
                                            .addComponent(cb_estado, 0, 171, Short.MAX_VALUE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel7)
                                        .addGap(30, 30, 30)
                                        .addComponent(t_grupo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(l_nota)
                                            .addComponent(l_nrc)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(325, 325, 325)
                                .addComponent(b_add, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b_cancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(103, 103, 103)
                                        .addComponent(jLabel3))))))
                    .addComponent(l_gre)
                    .addComponent(l_top))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(l_top)
                .addGap(3, 3, 3)
                .addComponent(l_gre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(t_tutoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(t_grupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(t_est, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(sp_cant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t_nrc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(l_nrc)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(t_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l_nota))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_add)
                            .addComponent(b_cancel))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelActionPerformed
        close();
    }//GEN-LAST:event_b_cancelActionPerformed

    private void b_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_addActionPerformed
        send();
    }//GEN-LAST:event_b_addActionPerformed

    private void chk_nrc(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chk_nrc
        l_nrc.setVisible(!Util.checkIsNumber(t_nrc.getText()));
    }//GEN-LAST:event_chk_nrc

    private void mouse_est(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouse_est
        if(matricula == null ){
            select_est();
        }
    }//GEN-LAST:event_mouse_est

    private void key_nota(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_key_nota
        l_nota.setVisible(!Util.checkIsFloat(t_nota.getText()));
    }//GEN-LAST:event_key_nota
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_add;
    private javax.swing.JButton b_cancel;
    private javax.swing.JComboBox cb_estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel l_gre;
    private javax.swing.JLabel l_nota;
    private javax.swing.JLabel l_nrc;
    private javax.swing.JLabel l_top;
    private javax.swing.JSpinner sp_cant;
    private javax.swing.JTextField t_est;
    private javax.swing.JTextField t_grupo;
    private javax.swing.JTextField t_nota;
    private javax.swing.JTextField t_nrc;
    private javax.swing.JTextField t_tutoria;
    // End of variables declaration//GEN-END:variables
}
