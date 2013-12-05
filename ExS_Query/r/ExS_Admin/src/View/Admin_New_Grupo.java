package View;

import Controller.Gestor;
import exs.logs.err.Log;
import exs.mod.Grupo;
import exs.mod.Tutor;
import exs.mod.Tutoria;
import exs.mod.var.Grupo_Var;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Admin_New_Grupo extends javax.swing.JFrame {

    private Admin_Select_Tutor selector;
    private Tutoria tutoria = null;
    private Tutor tutor = null;
    private Grupo update = null;
    private ArrayList<Grupo> grupos = null;
    private int num = 0;
    private static final String EMPTY = "--";

    public Admin_New_Grupo(Tutoria t, Grupo update, ArrayList<Grupo> grupos) {
        initComponents();

        this.update = update;
        this.tutoria = t;
        this.grupos = grupos;
        num = grupos.size() + 1;
        selector = new Admin_Select_Tutor();
        setImages();
        fill_combos();
        init();

    }

    private void init() {
        try {
            if (update != null) {
                t_grupoNum.setEditable(false);
                t_grupoNum.setEnabled(false);
                chk_Mostrar.setVisible(false);
                cb_Anio.setVisible(false);
                cb_Ciclo.setVisible(false);
                l_Anio.setVisible(false);
                l_Ciclo.setVisible(false);
                t_grupoNum.setText(update.getNum());
                tutor = Gestor.getInstancia().getTutor(update.getTid());
                setTutor();
                setEstadoSelected();
                setDiaSelected();
                setHoraSelected();
                t_lugar.setText(update.getLugar());
                this.setTitle("Editar Grupo");
                l_tuto.setText("Modificar Grupo");
                l_text.setText("Ingrese los datos para modificar un grupo y luego presione el botón de Editar.");
                b_add.setText("Editar");


            } else {
                this.setTitle("Nuevo Grupo");
            }
            selector.addWindowListener(new WindowAdapter() {
                @Override
                public void windowDeactivated(WindowEvent e) {
                    super.windowDeactivated(e);
                    change_Tutor();
                }
            });
            t_Tutoria.setText(tutoria.toString());
            t_Tutor.setEditable(false);
            chk_Mostrar.setSelected(true);
            grupoMostrar(false);
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
        }
    }

    private void change_Tutor() {
        if (selector.isChange()) {
            int i = selector.getSeleccion();
            if (i == -1) {
                tutor = null;
            } else {
                tutor = Gestor.getInstancia().getTutores().get(i);

            }
            setTutor();
        }
    }

    private void setTutor() {
        if (tutor == null) {
            t_Tutor.setText("Haga Click aquí para seleccionar...");
        } else {
            t_Tutor.setText(tutor.getNombre() + " " + tutor.getPriApellido() + " " + tutor.getSegApellido().charAt(0) + " " + tutor.getId());
        }
    }

    private void fill_combo_horas() {
        try {
            cb_Hora.addItem(EMPTY);
            for (int i = Grupo_Var.HORA; i < 13; i++) {
                cb_Hora.addItem("" + i + ":00 am.");
            }
            for (int i = 1; i < Grupo_Var.HORA; i++) {
                cb_Hora.addItem("" + i + ":00 pm.");
            }
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
        }
    }

    private void fill_combo_Estado() {
        try {
            cb_Estado.addItem(Grupo_Var.ABIERTO);
            cb_Estado.addItem(Grupo_Var.FINALIZADO);
            cb_Estado.addItem(Grupo_Var.SUSPENDIDO);
            cb_Estado.addItem(Grupo_Var.SIN_CUPO);
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
        }

    }

    private void fill_combos() {
        try {
            int anio = Grupo_Var.getAnio();
            int ciclo = Grupo_Var.getCiclo();
            for (int i = -2; i < 3; i++) {
                cb_Anio.addItem("" + (anio + i));
            }
            for (int i = 0; i < Grupo_Var.CICLOS.length; i++) {
                cb_Ciclo.addItem("" + Grupo_Var.CICLOS[i]);
            }

            cb_Anio.setSelectedIndex(2);
            cb_Ciclo.setSelectedIndex(ciclo);
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
        }
        fill_combo_horas();
        fill_combo_Estado();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        l_tuto = new javax.swing.JLabel();
        l_text = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        b_add = new javax.swing.JButton();
        b_can = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        t_grupoNum = new javax.swing.JTextField();
        t_Tutoria = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        chk_Mostrar = new javax.swing.JCheckBox();
        l_Ciclo = new javax.swing.JLabel();
        l_Anio = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        t_Tutor = new javax.swing.JTextField();
        cb_Ciclo = new javax.swing.JComboBox();
        cb_Anio = new javax.swing.JComboBox();
        rb_lunes = new javax.swing.JRadioButton();
        rb_martes = new javax.swing.JRadioButton();
        rb_miercoles = new javax.swing.JRadioButton();
        rb_jueves = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        rb_viernes = new javax.swing.JRadioButton();
        rb_sabado = new javax.swing.JRadioButton();
        cb_Hora = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cb_Estado = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        t_lugar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        l_tuto.setFont(new java.awt.Font("Tekton Pro Ext", 1, 24)); // NOI18N
        l_tuto.setText("Agregar Grupo");

        l_text.setText("Ingrese los datos para agregar un grupo y luego presione el botón de Agregar.");

        b_add.setText("Agregar");
        b_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_addActionPerformed(evt);
            }
        });

        b_can.setText("Cancelar");
        b_can.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_canActionPerformed(evt);
            }
        });

        jLabel2.setText("Número");

        t_grupoNum.setEditable(false);

        t_Tutoria.setEditable(false);

        jLabel4.setText("Tutoría");

        chk_Mostrar.setText("Ciclo en Curso");
        chk_Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_Mostrar(evt);
            }
        });

        l_Ciclo.setText("Ciclo");

        l_Anio.setText("Año");

        jLabel5.setText("Tutor");

        t_Tutor.setText("Haga Click aquí para seleccionar...");
        t_Tutor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_tutor(evt);
            }
        });

        cb_Ciclo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        cb_Ciclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                change_num(evt);
            }
        });

        cb_Anio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        buttonGroup1.add(rb_lunes);
        rb_lunes.setText("Lunes");

        buttonGroup1.add(rb_martes);
        rb_martes.setText("Martes");

        buttonGroup1.add(rb_miercoles);
        rb_miercoles.setText("Miércoles");

        buttonGroup1.add(rb_jueves);
        rb_jueves.setText("Jueves");

        jLabel1.setText("Horario");

        buttonGroup1.add(rb_viernes);
        rb_viernes.setText("Viernes");

        buttonGroup1.add(rb_sabado);
        rb_sabado.setText("Sábado");

        cb_Hora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jLabel3.setText("Estado");

        cb_Estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jLabel6.setText("Lugar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(b_add)
                .addGap(18, 18, 18)
                .addComponent(b_can)
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l_tuto)
                    .addComponent(l_text))
                .addContainerGap(310, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(t_Tutoria, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_grupoNum, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(chk_Mostrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(l_Ciclo)
                            .addComponent(l_Anio))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb_Ciclo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_Anio, 0, 100, Short.MAX_VALUE)))
                    .addComponent(t_Tutor, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rb_lunes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rb_viernes)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rb_sabado))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rb_martes)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rb_miercoles)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rb_jueves))))
                            .addComponent(jLabel1)
                            .addComponent(cb_Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(t_lugar)
                                .addGap(57, 57, 57))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(l_tuto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(t_grupoNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(t_Tutoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_Hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(t_Tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(chk_Mostrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rb_lunes)
                            .addComponent(rb_martes)
                            .addComponent(rb_miercoles)
                            .addComponent(rb_jueves))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rb_viernes)
                            .addComponent(rb_sabado))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_Anio)
                            .addComponent(cb_Anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_Ciclo)
                            .addComponent(cb_Ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(cb_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(t_lugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_add)
                    .addComponent(b_can))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_addActionPerformed
        send();
    }//GEN-LAST:event_b_addActionPerformed

    private void b_canActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_canActionPerformed
        close();
    }//GEN-LAST:event_b_canActionPerformed

    private void chk_Mostrar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_Mostrar
        if (chk_Mostrar.isSelected()) {
            grupoMostrar(false);
            cb_Anio.setSelectedIndex(2);
            cb_Ciclo.setSelectedIndex(Grupo_Var.getCiclo());
        } else {
            grupoMostrar(true);
        }
    }//GEN-LAST:event_chk_Mostrar

    private void mouse_tutor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouse_tutor
        select_tutor();
    }//GEN-LAST:event_mouse_tutor

    private void change_num(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_change_num
        update_num();
    }//GEN-LAST:event_change_num

    private void change_numero() {
        num = (num < 255) ? num + 1 : 1;
    }

    private void update_num() {
        if (update == null) {
            String _num = tutoria.getCod() + "-" + cb_Ciclo.getSelectedItem() + ((String) cb_Anio.getSelectedItem()).substring(2)
                    + "-" + ((num > 99) ? num : "0" + num);
            if (!isAvailable(_num)) {
                change_numero();
                update_num();
            } else {
                t_grupoNum.setText(_num);
            }
        }
    }

    private boolean isAvailable(String cod) {
        for (int i = 0; i < grupos.size(); i++) {
            if (cod.equals(grupos.get(i).getNum())) {
                return false;
            }
        }
        return true;
    }

    private void grupoMostrar(boolean b) {
        l_Anio.setEnabled(b);
        l_Ciclo.setEnabled(b);
        cb_Anio.setEnabled(b);
        cb_Ciclo.setEnabled(b);
    }

    private void select_tutor() {
        int x = 275 + getX();
        int y = 170 + getY();
        selector.setChange(false);
        selector.setLocation(x, y);
        selector.update_table();
        selector.setVisible(true);

    }

    private void close() {
        dispose();
    }

    private void setHoraSelected() {
        int hora = update.getHora();
        cb_Hora.setSelectedIndex(hora - Grupo_Var.HORA + 1);

    }

    private void setEstadoSelected() {
        String estado = update.getEstado();
        int e = (estado.equals(Grupo_Var.ABIERTO)) ? 0 : (estado.equals(Grupo_Var.FINALIZADO)) ? 1 : (estado.equals(Grupo_Var.SUSPENDIDO)) ? 2 : 3;
        cb_Estado.setSelectedIndex(e);

    }

    private void setDiaSelected() {
        char dia = update._getDia();
        switch (dia) {
            case Grupo_Var.LUNES_CHAR:
                rb_lunes.setSelected(true);
                break;
            case Grupo_Var.MARTES_CHAR:
                rb_martes.setSelected(true);
                break;
            case Grupo_Var.MIERCOLES_CHAR:
                rb_miercoles.setSelected(true);
                break;
            case Grupo_Var.JUEVES_CHAR:
                rb_jueves.setSelected(true);
                break;
            case Grupo_Var.VIERNES_CHAR:
                rb_viernes.setSelected(true);
                break;
            case Grupo_Var.SABADO_CHAR:
                rb_sabado.setSelected(true);
                break;
        }
    }

    private boolean isDiaSelected() {
        return (rb_lunes.isSelected() || rb_martes.isSelected() || rb_miercoles.isSelected()
                || rb_jueves.isSelected() || rb_viernes.isSelected() || rb_sabado.isSelected());
    }

    private boolean isHoraSelected() {
        return !cb_Hora.getSelectedItem().equals(EMPTY);
    }

    private String getHorario() {
        String horario = "";
        horario += (rb_lunes.isSelected()) ? Grupo_Var.LUNES_CHAR
                : (rb_martes.isSelected()) ? Grupo_Var.MARTES_CHAR
                : (rb_miercoles.isSelected()) ? Grupo_Var.MIERCOLES_CHAR
                : (rb_jueves.isSelected()) ? Grupo_Var.JUEVES_CHAR
                : (rb_viernes.isSelected()) ? Grupo_Var.VIERNES_CHAR : Grupo_Var.SABADO_CHAR;
        horario += "" + (Grupo_Var.HORA + cb_Hora.getSelectedIndex() - 1);
        return horario;
    }

    private void send() {
        if (tutor != null && tutoria != null && !t_grupoNum.getText().equals("") && !t_lugar.getText().equals("")
                && isDiaSelected() && isHoraSelected()) {
            if (update == null) {
                Gestor.getInstancia().insert_grupo(new Grupo(t_grupoNum.getText(), "" + tutor.getId(), tutoria.getCod(), t_lugar.getText(),
                        Integer.parseInt((String) cb_Anio.getSelectedItem()), cb_Ciclo.getSelectedIndex(), getHorario(), (String) cb_Estado.getSelectedItem()));
            } else {
                //Sets
                update.setTid(tutor.getId() + "");
                update.setHorario(getHorario());
                update.setAnio(Integer.parseInt((String) cb_Anio.getSelectedItem()));
                update.setCiclo(cb_Ciclo.getSelectedIndex());
                update.setEstado((String) cb_Estado.getSelectedItem());
                update.setLugar(t_lugar.getText());

                Gestor.getInstancia().update_grupo(update);
            }
            close();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe completar todos los espacios en blanco.");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_add;
    private javax.swing.JButton b_can;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cb_Anio;
    private javax.swing.JComboBox cb_Ciclo;
    private javax.swing.JComboBox cb_Estado;
    private javax.swing.JComboBox cb_Hora;
    private javax.swing.JCheckBox chk_Mostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel l_Anio;
    private javax.swing.JLabel l_Ciclo;
    private javax.swing.JLabel l_text;
    private javax.swing.JLabel l_tuto;
    private javax.swing.JRadioButton rb_jueves;
    private javax.swing.JRadioButton rb_lunes;
    private javax.swing.JRadioButton rb_martes;
    private javax.swing.JRadioButton rb_miercoles;
    private javax.swing.JRadioButton rb_sabado;
    private javax.swing.JRadioButton rb_viernes;
    private javax.swing.JTextField t_Tutor;
    private javax.swing.JTextField t_Tutoria;
    private javax.swing.JTextField t_grupoNum;
    private javax.swing.JTextField t_lugar;
    // End of variables declaration//GEN-END:variables
}
