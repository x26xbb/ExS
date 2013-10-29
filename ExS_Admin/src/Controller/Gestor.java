package Controller;

import exs.db.ConnBase;
import exs.db.Historico;
import exs.db.MySql_ConnGestor;
import exs.logs.err.Log;
import exs.mail.SendEmail;
import exs.mod.Carrera;
import exs.mod.Estudiante;
import exs.mod.Grupo;
import exs.mod.Matricula;
import exs.mod.Persona;
import exs.mod.Tutor;
import exs.mod.Tutoria;
import exs.mod.var.Matricula_Var;
import exs.pdf.PDF_Writer;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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

    public void del_tutoria(Tutoria t) {
        db_gestor.det_tutoria(t);
        _notify();
    }

    public void insert_tutoria(Tutoria t) {
        db_gestor.insert_tutoria(t);
        _notify();
    }

    public void update_tutoria(Tutoria t) {
        db_gestor.update_tutoria(t);
        _notify();
    }

    public boolean is_Cod_Used(String cod) {
        for (int i = 0; i < tutorias.size(); i++) {
            if (cod.equals(tutorias.get(i).getCod())) {
                return true;
            }
        }
        return false;
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

    public void insert_tutor(Tutor t) {
        db_gestor.insert_tutor(t);
        _notify();
    }

    public void update_tutor(Tutor t) {
        db_gestor.update_tutor(t);
        _notify();
    }

    public void del_tutor(int i) {
        Tutor t = tutores.get(i);
        db_gestor.det_tutor(t);
        _notify();
    }

//Grupos
    public int getCantidadGrupos(String cod) {
        return db_gestor.getCantidadGrupo(cod);
    }

    public ArrayList<Grupo> getGrupos(String cod) {
        return db_gestor.getGrupos(cod);
    }

    public void insert_grupo(Grupo g) {
        db_gestor.insert_grupo(g);
        _notify();
    }

    public void update_grupo(Grupo g) {
        db_gestor.update_grupo(g);
        _notify();
    }

    public void del_grupo(Grupo g) {
        db_gestor.det_grupo(g);
        _notify();
    }

    //
    //Estudiantes
    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes = db_gestor.getEstudiantes();
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

    public void insert_estudiante(Estudiante e) {
        db_gestor.insert_estudiante(e);
        _notify();
    }

    public void update_estudiante(Estudiante e) {
        db_gestor.update_estudiante(e);
        _notify();
    }

    public void del_estudiante(int i) {
        Estudiante e = estudiantes.get(i);
        db_gestor.det_estudiante(e);
        _notify();
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

    public void del_carrera(int i) {
        Carrera t = carrreras.get(i);
        db_gestor.det_carrera(t);
        _notify();
    }

    public void insert_carrera(Carrera t) {
        db_gestor.insert_carrera(t);
        _notify();
    }

    public void update_carrera(Carrera t) {
        db_gestor.update_carrera(t);
        _notify();
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

    private void SendPDF(Matricula m, Estudiante estudiante, Grupo grupo, Tutoria t) {
        String path = "tem/" + m.getEstudiante() + "_" + t.getNom() + ".pdf";
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

    public void insert_matricula(Matricula m, Estudiante estudiante, Grupo grupo, Tutoria t) {
        m.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
        db_gestor.insert_matricula(m);
        SendPDF(m, estudiante, grupo, t);
        _notify();
    }

    public void update_matricula(Matricula m, Estudiante estudiante, Grupo grupo, Tutoria t) {
        db_gestor.update_matricula(m);
        SendPDF(m, estudiante, grupo, t);
        _notify();
    }

    public void del_matricula(Matricula m) {
        db_gestor.det_matricula(m);
        _notify();
    }

    //
    //Util    
    public boolean is_Id_Usable(int cod, ArrayList<Persona> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if (cod == lista.get(i).getId()) {
                return false;
            }
        }
        return true;
    }

    private void _notify() {
        setChanged();
        notifyObservers();
    }
    private boolean usable = false;
    static Gestor instancia = null;
    private ConnBase db_gestor = new MySql_ConnGestor("conf/props.exs");
    private ArrayList<Tutoria> tutorias = null;
    private ArrayList<Tutor> tutores = null;
    private ArrayList<Estudiante> estudiantes = null;
    private ArrayList<Carrera> carrreras = null;

    public ArrayList<Historico> getHistorico(int id,int tipo) {
        return db_gestor.getHistorico(id,tipo);
    } 

    public Tutoria getTutorias(String s) {
        int cont=1;
        if(tutorias!=null){
            Tutoria aux=tutorias.get(0);
            while(aux!=null){
                if(aux.getCod().equals(s)) {
                    return aux;
                }else{
                   aux=tutorias.get(cont);
                   cont++;
                }
            }
        }
        return null;        
    }
    
    public Tutor getTutorPorCed(int cedula){
        int cont=1;;
        if(tutores!=null){
            Tutor aux=tutores.get(0);
            while(aux!=null){
                if(aux.getId()==cedula) {
                    return aux;
                }else{
                   aux=tutores.get(cont);
                   cont++;
                }
            }
        }
        return null; 
    }
    
    public boolean existenTutorias(int anio,String ciclo,String cod){
        int numciclo;
        if(ciclo.equals("I")){
            numciclo=0;
        }else{
            if(ciclo.equals("II")){
                numciclo=1;
            }else{
                numciclo=2; 
            }
        }
        return db_gestor.existenTutorias(anio,numciclo,cod);
    }
    
    public boolean esTutora(int anio,String ciclo,String cedula){
        int numciclo;
            if(ciclo.equals("I")){
                numciclo=0;
            }else{
                if(ciclo.equals("II")){
                    numciclo=1;
                }else{
                    numciclo=2; 
                }
            }
            return db_gestor.esTutora(anio,numciclo,cedula);
    }
    
    
     public boolean esEstudiante(int anio,String ciclo,String cedula){
        int numciclo;
            if(ciclo.equals("I")){
                numciclo=0;
            }else{
                if(ciclo.equals("II")){
                    numciclo=1;
                }else{
                    numciclo=2; 
                }
            }
            return db_gestor.esEstudiante(anio,numciclo,cedula);
    }
     
     public Grupo getGrupo(String cod){
         return db_gestor.getGrupo(cod);
     }
    
}
