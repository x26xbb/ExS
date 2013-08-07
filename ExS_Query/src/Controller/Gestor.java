package Controller;

import exs.db.ConnBase;
import exs.db.MySql_ConnGestor;
import exs.logs.err.Log;
import exs.mod.Carrera;
import exs.mod.Estudiante;
import exs.mod.Grupo;
import exs.mod.Matricula;
import exs.mod.Tutor;
import exs.mod.Tutoria;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Gestor extends Observable {

    public Gestor() {
        if (!db_gestor.testConn()) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al comunicar con la Base de Datos!!", "DB Error", JOptionPane.ERROR_MESSAGE);
        } else {
            usable = true;
        }
    }

    public boolean isUsable() {
        return usable;
    }

    public static Gestor getInstancia() {
        return (instancia == null) ? instancia = new Gestor() : instancia;
    }
//Tutorias

    public ArrayList<Tutoria> getTutorias() {
        return tutorias = db_gestor.getTutorias(false);//Aqui no lo mostramos ordenadas
    }

    public ArrayList<Tutoria> _getTutorias() {
        return (tutorias == null) ? getTutorias() : tutorias;
    }

    public void del_tutoria(int i) {
        Tutoria t = tutorias.get(i);
        db_gestor.det_tutoria(t);
        _notify();
    }

//Tutores
    public ArrayList<Tutor> getTutores() {
        return tutores = db_gestor.getTutores();
    }

    public ArrayList<Tutor> _getTutores() {
        return (tutores == null) ? getTutores() : tutores;
    }

    public Tutor getTutor(String id) {
        int tid = -1;
        try {
            tid = Integer.parseInt(id);
            for (int i = 0; i < _getTutores().size(); i++) {
                if (tid == _getTutores().get(i).getId()) {
                    return _getTutores().get(i);
                }
            }
        } catch (NumberFormatException e) {
            Log.SendLog("Id erronea " + id);
        }

        Log.SendLog("No se encontró el tutor con el id " + id);
        return null;
    }

//Grupos
    public int getCantidadGrupos(String cod) {
        return db_gestor.getCantidadGrupo(cod);
    }

    public ArrayList<Grupo> _getGrupos_S() {
        return grupos;
    }
    
    public ArrayList<Grupo> _getGrupos(String cod) {
        return (grupos == null) ? grupos = db_gestor.getGrupos(cod) : grupos;
    }
    public ArrayList<Grupo> getGrupos(String cod) {
        return grupos = db_gestor.getGrupos(cod);
    }

    //
    //Estudiantes
    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes = db_gestor.getEstudiantes();
    }

    public ArrayList<Estudiante> getEstudiantes_Filter(String cedula, String nombre, int sede, int carrera) {
        estudiantes_filtred = new ArrayList<>(_getEstudiantes());
        try {
            Estudiante tem;
            String cedula_est;
            for (int i = 0; i < estudiantes_filtred.size(); i++) {
                tem = estudiantes_filtred.get(i);
                cedula_est = tem.getId() + "";
                if ((sede != -1 && tem.getSede() != sede)
                        || (carrera != -1 && tem.getCarrera() != carrera)
                        || (!tem.getFullName().contains(nombre))
                        || (!cedula_est.contains(cedula))) {
                    estudiantes_filtred.remove(i--);
                }
            }
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
        }

        return estudiantes_filtred;
    }

    public ArrayList<Estudiante> getEstudiantes_Filter(int tutoria, int grupo) {
        String tutoria_s, grupo_s;
        
        tutoria_s = _getTutorias().get(tutoria).getCod();
        grupo_s = (grupo == -1 )? null : grupos.get(grupo).getNum();
        try {
            estudiantes_filtred = db_gestor.getEstudiantes_fromGrupo(tutoria_s, grupo_s);
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
        }

        return estudiantes_filtred;
    }

    public ArrayList<Estudiante> _getEstudiantes() {
        return (estudiantes == null) ? getEstudiantes() : estudiantes;
    }

    public Estudiante getEstudiante(String id) {
        int tid = -1;
        try {
            tid = Integer.parseInt(id);
            for (int i = 0; i < _getEstudiantes().size(); i++) {
                if (tid == _getEstudiantes().get(i).getId()) {
                    return _getEstudiantes().get(i);
                }
            }
        } catch (NumberFormatException e) {
            Log.SendLog("Id erronea " + id);
        }

        Log.SendLog("No se encontró el estudiante con el id " + id);
        return null;
    }

    //Carrarreas
    public ArrayList<Carrera> getCarreras(boolean ordenadas) {
        return carrreras = db_gestor.getCarreras(ordenadas);
    }

    public ArrayList<Carrera> _getCarreras(boolean ordenadas) {
        return (carrreras == null) ? getCarreras(ordenadas) : carrreras;
    }

    public String getCarrera(int num) {
        for (int i = 0; i < _getCarreras(false).size(); i++) {
            if (carrreras.get(i).getNum() == num) {
                return carrreras.get(i).getNombre();
            }
        }
        return null;
    }

    public ArrayList<Carrera> getCarreras_Sede(int sede) {
        ArrayList<Carrera> lista = new ArrayList<>();
        for (int i = 0; i < _getCarreras(false).size(); i++) {
            if (carrreras.get(i).getSede() == sede) {
                lista.add(carrreras.get(i));
            }
        }
        return lista;
    }

//Matriculas
    public int getCantidadMatriculas(String cod) {
        return db_gestor.getCantidadMatriculas(cod);
    }

    public ArrayList<Matricula> getMatriculas(String cod) {
        return db_gestor.getMatriculas(cod);
    }

    private void _notify() {
        setChanged();
        notifyObservers();
    }
    private boolean usable = false;
    static Gestor instancia = null;
    private ConnBase db_gestor = new MySql_ConnGestor("conf/props.exs");
    private ArrayList<Tutoria> tutorias = null;
    private ArrayList<Grupo> grupos = null;
    private ArrayList<Tutor> tutores = null;
    private ArrayList<Estudiante> estudiantes = null;
    private ArrayList<Estudiante> estudiantes_filtred = null;
    private ArrayList<Carrera> carrreras = null;
}
