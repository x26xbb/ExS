package Controller;

import exs.db.ConnBase;
import exs.db.MySql_ConnGestor;
import exs.logs.err.Log;
import exs.mail.SendEmail;
import exs.mod.Carrera;
import exs.mod.Estudiante;
import exs.mod.Grupo;
import exs.mod.Matricula;
import exs.mod.Tutoria;
import exs.mod.var.Grupo_Var;
import exs.mod.var.Matricula_Var;
import exs.pdf.PDF_Writer;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Gestor_Est {

    public Gestor_Est() {
        if (!db_gestor.testConn()) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al comunicar con la Base de Datos!!", "DB Error", JOptionPane.ERROR_MESSAGE);
        } else {
            usable = true;
        }
    }

    public boolean isUsable() {
        return usable;
    }

    public static Gestor_Est getInstancia() {
        return (instancia == null) ? instancia = new Gestor_Est() : instancia;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }

    public Estudiante getEstudiante() {
        int tid = -1;
        try {
            tid = Integer.parseInt(getEst());
            return db_gestor.getEstudiante(tid);
        } catch (NumberFormatException e) {
            Log.SendLog("Id erronea " + getEst());
        }
        return null;
    }

    public void insert_estudiante(Estudiante e) {
        db_gestor.insert_estudiante(e);

    }

    public void update_estudiante(Estudiante e) {
        db_gestor.update_estudiante(e);

    }

    //
    //Carreras
    public ArrayList<Carrera> getCarreras() {
        return carrreras = db_gestor.getCarreras(true);
    }

    public ArrayList<Carrera> _getCarreras() {
        return (carrreras == null) ? getCarreras() : carrreras;
    }

    public String getCarrera(int num) {
        for (int i = 0; i < _getCarreras().size(); i++) {
            if (carrreras.get(i).getNum() == num) {
                return carrreras.get(i).getNombre();
            }
        }
        return null;
    }

    public ArrayList<Carrera> getCarreras_Sede(int sede) {
        ArrayList<Carrera> lista = new ArrayList<>();
        for (int i = 0; i < _getCarreras().size(); i++) {
            if (carrreras.get(i).getSede() == sede) {
                lista.add(carrreras.get(i));
            }
        }
        return lista;
    }

    //Tutorias
    public ArrayList<Tutoria> getTutorias() {
        return tutorias = db_gestor.getTutorias(true);//Aqui no lo mostramos ordenadas
    }

    public ArrayList<Tutoria> _getTutorias() {
        return (tutorias == null) ? getTutorias() : tutorias;
    }

    // Grupos
    public ArrayList<Grupo> getGrupos(String cod) {
        return grupos = db_gestor.getGrupos(cod, Grupo_Var.getCiclo(), Grupo_Var.getAnio(), Grupo_Var.ABIERTO);
    }

    public Grupo getGrupo(String num) {
        for (int i = 0; i < grupos.size(); i++) {
            if (num.equals(grupos.get(i).getNum())) {
                return grupos.get(i);
            }
        }
        return null;
    }

    //Matriculas
    public void insert_matricula(Matricula m, Estudiante estudiante, Grupo grupo, Tutoria t) {
        m.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
        String path = "tem/" + m.getEstudiante() + "_" +  t.getNom() + ".pdf";
        db_gestor.insert_matricula(m);
        try {
            if (PDF_Writer.CreateMatricula(estudiante, t.getNom(), getCarrera(estudiante.getCarrera()), grupo, path)) {
                String title, body;
                title = "Registro Tutoría " + t.getNom() + " - " + estudiante.getFullName();
                body = String.format(Matricula_Var.CORREO, estudiante.getNombre(), t.getNom());
                if (SendEmail.SendMail_Att(estudiante.getEmail(), title, body, path)) {
                    //Borrar o  algo mas?
                } else {
                    Log.SendLog("Error al enviar el correo - " + path + " - " + estudiante.getEmail());
                }
            } else {
                Log.SendLog("Error al crear el archivo " + path);
            }
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
        }

    }
    private boolean usable = false;
    private static Gestor_Est instancia = null;
    private ConnBase db_gestor = new MySql_ConnGestor("conf/props.exs");
    private String est = null;
    private ArrayList<Carrera> carrreras = null;
    private ArrayList<Tutoria> tutorias = null;
    private ArrayList<Grupo> grupos = null;
}
