package exs.db;

import exs.logs.err.Log;
import exs.mod.Carrera;
import exs.mod.Estudiante;
import exs.mod.Grupo;
import exs.mod.Matricula;
import exs.mod.Retirado;
import exs.mod.Tutor;
import exs.mod.Tutoria;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin Villalobos A.
 */
public abstract class ConnBase {

    public ConnBase(String path) {
        readProps(path);
    }

    private void readProps(String path) {
        props = null;
        try {
                   // File arc=new File(path);
                    //FileInputStream fileIn = new FileInputStream(arc);
                    //ObjectInputStream in = new ObjectInputStream(fileIn);                

                     props = new ConnProps();//(ConnProps) in.readObject();
            
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
            Log.SendLog(path + " erroneo");
        }
    }

    public abstract void connect();

    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException exception) {
            Log.SendLog(exception.getMessage());
        }
    }

    public ConnProps getProps() {
        return props;
    }

    public void setProps(ConnProps props) {
        this.props = props;
    }

    public boolean testConn() {
        if (props == null) {
            return false;
        } else {
            connect();
            if (conn == null) {
                return false;
            } else {
                try {
                    stmt = conn.createStatement();
                    rset = stmt.executeQuery(Querys.TEST);
                    if (!rset.next()) {
                        return false;
                    }
                    rset.close();
                    stmt.close();
                    disconnect();
                } catch (Exception ex) {
                    Log.SendLog(ex.getMessage());
                }

            }
        }
        return true;
    }

//Tutorias
    public ArrayList<Tutoria> getTutorias(boolean orderanas) {
        ArrayList<Tutoria> lista = new ArrayList<Tutoria>();
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rset = stmt.executeQuery((orderanas) ? Querys.GET_TUTORIAS_ORDEN : Querys.GET_TUTORIAS);
                while (rset.next()) {
                    String cod;
                    String nom;
                    cod = rset.getString(1);
                    nom = rset.getString(2);
                    lista.add(new Tutoria(cod, nom));
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return lista;
    }

    //Metodo para buscar si el cod de una tutoria ya esta siendo utilizado
//    public boolean getTutoria_cod(String cod) {
//        return test_Cod(String.format(Querys.GET_TUTORIAS_COD, cod));
//    }
    public boolean update_tutoria(Tutoria t) {
        return execute_update(String.format(Querys.UPDATE_TUTORIA, t.getNom(), t.getCod()));
    }

    public boolean det_tutoria(Tutoria t) {
        return execute_update(String.format(Querys.DEL_TUTORIA, t.getCod()));
    }

    public boolean insert_tutoria(Tutoria t) {
        return execute_update(String.format(Querys.INSERT_TUTORIA, t.getCod(), t.getNom()));
    }

//Tutores
    public ArrayList<Tutor> getTutores() {
        ArrayList<Tutor> lista = new ArrayList<Tutor>();
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rset = stmt.executeQuery(Querys.GET_TUTORES);
                while (rset.next()) {
                    String id;
                    String nom, pape, sape, mail;
                    int gen, tel;
                    id = rset.getString(1);
                    nom = rset.getString(2);
                    pape = rset.getString(3);
                    sape = rset.getString(4);
                    gen = rset.getInt(5);
                    tel = rset.getInt(6);
                    mail = rset.getString(7);
                    lista.add(new Tutor(nom, pape, sape, Integer.parseInt(id), tel, mail, gen));
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return lista;
    }

    public boolean update_tutor(Tutor t) {
        return execute_update(String.format(Querys.UPDATE_TUTOR, t.getNombre(),
                t.getPriApellido(), t.getSegApellido(),
                 t.getTelefono(), t.getEmail(), t.getId()));
    }

    public boolean det_tutor(Tutor t) {
        return execute_update(String.format(Querys.DEL_TUTOR, t.getId()));
    }

    public boolean insert_tutor(Tutor t) {
        return execute_update(String.format(Querys.INSERT_TUTOR, t.getId(),
                t.getNombre(), t.getPriApellido(), t.getSegApellido(),
                t.getGenero(), t.getTelefono(), t.getEmail()));
    }

    //Grupos
    public int getCantidadGrupo(String cod) {
        return getCantidad(String.format(Querys.GET_CANT_GRUPO, cod));            
    }

    public ArrayList<Grupo> getGrupos(String cod, int ciclo, int anio, String estado) {
        ArrayList<Grupo> lista = new ArrayList<Grupo>();
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                String sql = String.format(Querys.GET_GRUPOS_C, cod, ciclo, anio, estado);
                Log.SendLog(sql);
                rset = stmt.executeQuery(sql);
                while (rset.next()) {
                    String num, tid, tcod, horario, lugar;
                    num = rset.getString(1);
                    tid = rset.getString(2);
                    tcod = rset.getString(3);
                    lugar = rset.getString(4);
                    horario = rset.getString(7);
                    lista.add(new Grupo(num, tid, tcod, lugar, anio, ciclo, horario, estado));
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return lista;
    }

    public ArrayList<Grupo> getGrupos(String cod) {
        ArrayList<Grupo> lista = new ArrayList<Grupo>();
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                String sql = String.format(Querys.GET_GRUPOS, cod);
                Log.SendLog(sql);
                rset = stmt.executeQuery(sql);
                while (rset.next()) {
                    int anio, ciclo;
                    String num, tid, tcod, horario, estado, lugar;
                    num = rset.getString(1);
                    tid = rset.getString(2);
                    tcod = rset.getString(3);
                    lugar = rset.getString(4);
                    anio = rset.getInt(5);
                    ciclo = rset.getInt(6);
                    horario = rset.getString(7);
                    estado = rset.getString(8);
                    lista.add(new Grupo(num, tid, tcod, lugar, anio, ciclo, horario, estado));
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return lista;
    }

     
    public boolean update_grupo(Grupo g) {
        return execute_update(String.format(Querys.UPDATE_GRUPO, g.getTid(),
                g.getTcod(), g.getLugar(), g.getAnio(), g.getCiclo(), g.getHorario(), g.getEstado(), g.getNum()));
    }

    public boolean det_grupo(Grupo g) {
        return execute_update(String.format(Querys.DEL_GRUPO, g.getNum()));
    }

    public boolean insert_grupo(Grupo g) {
        return execute_update(String.format(Querys.INSERT_GRUPO, g.getNum(),
                g.getTid(), g.getTcod(), g.getLugar(), g.getAnio(),
                g.getCiclo(), g.getHorario(), g.getEstado()));
    }
//Estudiantes

   public ArrayList<Estudiante> getEstudiantesIndividual(){
        ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rset = stmt.executeQuery(Querys.GET_EST);
                while (rset.next()) {
                    String id;
                    String nom, pape, sape, mail;
                    int gen,sede,beca, cel, tel, carrera;
                    id = rset.getString(1);
                    nom = rset.getString(2);
                    pape = rset.getString(3);
                    sape = rset.getString(4);
                    tel = rset.getInt(6);
                    cel = rset.getInt(7);
                    mail = rset.getString(8);
                    carrera = rset.getInt(9);
                    sede = rset.getInt(10);
                    gen= rset.getInt(5);
                    beca= rset.getInt(11);
                    lista.add(new Estudiante(nom, pape, sape, Integer.parseInt(id), cel, tel, mail,gen,beca, sede,carrera));
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return lista;
    }
            
    public ArrayList<Estudiante> getEstudiantes() {
        ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rset = stmt.executeQuery(Querys.GET_ESTUDIANTES);
                while (rset.next()) {
                    String id;
                    String nom, pape, sape, mail, tutoria, horario,ciclo;
                    int gen=1,sede,beca, cel, tel, carrera;
                    id = rset.getString(1);
                    nom = rset.getString(2);
                    pape = rset.getString(3);
                    sape = rset.getString(4);
                    tel = rset.getInt(5);
                    cel = rset.getInt(6);
                    mail = rset.getString(7);
                    horario = rset.getString(8);
                    ciclo=getCicloRomano(rset.getInt(9));
                    carrera = rset.getInt(10);
                    tutoria = rset.getString(10);
                    
                    sede = rset.getInt(11);
                    //gen= rset.getInt(13);
                    beca= rset.getInt(11);
                    
                    lista.add(new Estudiante(nom, pape, sape, Integer.parseInt(id), cel, tel, mail, sede,carrera,tutoria,horario,gen,beca,ciclo));
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return lista;
    }
    
    public ArrayList<Estudiante> getEstudiantes_fromGrupo(String tutoria, String grupo) {
        ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
        connect();
        if (conn != null) {
            try {
                String query = (grupo == null) ? ("SELECT * FROM ESTUDIANTE, (SELECT EID FROM (SELECT EID, GNUM FROM MATRICULA )M, (SELECT NUM FROM GRUPO WHERE TCOD = '"+tutoria+"' )G WHERE G.NUM = M.GNUM )Q WHERE Q.EID = ESTUDIANTE.ID "):
                                                    ("SELECT * FROM ESTUDIANTE, (SELECT EID FROM MATRICULA WHERE MATRICULA.GNUM = '"+grupo+"' )M  WHERE M.EID = ESTUDIANTE.ID");
                Log.SendLog(query);
                stmt = conn.createStatement();
                rset = stmt.executeQuery(query);
                while (rset.next()) {
                    String id;
                    String nom, pape, sape, mail;
                    int gen, cel, tel, carrrera, sede, beca;
                    id = rset.getString(1);
                    nom = rset.getString(2);
                    pape = rset.getString(3);
                    sape = rset.getString(4);
                    gen = rset.getInt(5);
                    tel = rset.getInt(6);
                    cel = rset.getInt(7);
                    mail = rset.getString(8);
                    carrrera = rset.getInt(9);
                    sede = rset.getInt(10);
                    beca = rset.getInt(11);
                    lista.add(new Estudiante(nom, pape, sape, Integer.parseInt(id), cel, tel, mail, gen, beca, sede, carrrera));
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return lista;
    }

    public Estudiante getEstudiante(int id) {
        Estudiante estudiante = null;
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rset = stmt.executeQuery(String.format(Querys.GET_ESTUDIANTE, id));
                if (rset.next()) {
                    String nom, pape, sape, mail;
                    int gen,sede,beca, cel, tel, carrera;
                    nom = rset.getString(2);
                    pape = rset.getString(3);
                    sape = rset.getString(4);
                    gen= rset.getInt(5);
                    tel = rset.getInt(6);
                    cel = rset.getInt(7);
                    mail = rset.getString(8);
                    carrera = rset.getInt(9);
                    sede = rset.getInt(10);                    
                    beca= rset.getInt(11);
                    estudiante=new Estudiante(nom, pape, sape, id, cel, tel, mail,gen,beca ,sede,carrera);
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return estudiante;
    }
    
    public ArrayList<Historico> getHistorico(int id,int tipo) {
        ArrayList<Historico> lista = new ArrayList<>();
        connect(); 
        int cont=0;
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                if(tipo==1){
                 rset = stmt.executeQuery(String.format(Querys.GET_HISTORICO, id));
                }
                if(tipo==2){
                 rset = stmt.executeQuery(String.format(Querys.VER_CURSOS_TUTOR, id));
                }
                while (rset.next()) {
                    String lugar,ced,nomCurso, num, tcod, anio,ciclo,horario,estado,nombre,pa,sa;
                    nomCurso = rset.getString(1);
                    num = rset.getString(2);
                    tcod = rset.getString(3);
                    anio = rset.getString(4);
                    ciclo = rset.getString(5);
                    horario = rset.getString(6);
                    estado = rset.getString(7);
                    ced = rset.getString(8);
                    nombre = rset.getString(9);
                    pa = rset.getString(10);
                    sa = rset.getString(11);
                    lugar = rset.getString(12);
                    cont++;
                    lista.add(new Historico(nomCurso, num, tcod, anio,ciclo,horario,estado,ced,nombre,pa,sa,tipo,lugar));                 
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
                return null;
            }
        }
        if(cont==0) return null;
        
        return lista;
    }

    public boolean update_estudiante(Estudiante t) {

        return execute_update(String.format(Querys.UPDATE_ESTUDIANTE, t.getNombre(),
                t.getPriApellido(), t.getSegApellido(),t.getGenero()
                , t.getTelefono(), t.getCelular(), t.getEmail(), 
                t.getCarrera(), t.getSede(), t.getBeca(), t.getId()));
    }

    public boolean det_estudiante(Estudiante t) {
        return execute_update(String.format(Querys.DEL_ESTUDIANTE, t.getId()));
    }

    public boolean insert_estudiante(Estudiante t) {
        
        return execute_update(String.format(Querys.INSERT_ESTUDIANTE, t.getId(),
                t.getNombre(), t.getPriApellido(), t.getSegApellido(), 1,
                 t.getTelefono(), t.getCelular(), t.getEmail(), t.getCarrera(), t.getSede(), t.getBeca()));
   
    }

    //Carreras
    public ArrayList<Carrera> getCarreras(boolean ordenadas) {
        ArrayList<Carrera> lista = new ArrayList<Carrera>();
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rset = stmt.executeQuery((ordenadas) ? Querys.GET_CARRERAS_ORDEN : Querys.GET_CARRERAS);
                while (rset.next()) {
                    int num, sede;
                    String nom;
                    num = rset.getInt(1);
                    nom = rset.getString(2);
                    sede = rset.getInt(3);
                    lista.add(new Carrera(num, nom, sede));
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return lista;
    }

    public boolean update_carrera(Carrera t) {
        return execute_update(String.format(Querys.UPDATE_CARRERA, t.getNombre(), t.getSede(), t.getNum()));
    }

    public boolean det_carrera(Carrera t) {
        return execute_update(String.format(Querys.DEL_CARRERA, t.getNum()));
    }

    public boolean insert_carrera(Carrera t) {
        return execute_update(String.format(Querys.INSERT_CARRERA, t.getNum(), t.getNombre(), t.getSede()));
    }

    //Matriculas
    public int getCantidadMatriculas(String num) {
        return getCantidad(String.format(Querys.GET_CANT_MATRICULAS, num));            
    }
    public ArrayList<Matricula> getMatriculas(String grupo) {
        ArrayList<Matricula> lista = new ArrayList<Matricula>();
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rset = stmt.executeQuery(  String.format(Querys.GET_MATRICULAS, grupo ) );
                while (rset.next()) {
                    String  estudiante, estado;
                    float nota;
                    int nrc, veces;
                    Date fecha;
                   // grupo = rset.getString(1);
                    estudiante = rset.getString(1);
                    fecha = rset.getDate(3);
                    estado = rset.getString(4);
                    nota = rset.getFloat(5);
                    nrc = rset.getInt(6);
                    veces = rset.getInt(7);
                    lista.add(new Matricula(grupo, estudiante, fecha, estado, nota, nrc, veces));
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return lista;
    }

    public boolean update_matricula(Matricula m) {
        return execute_update(String.format(Querys.UPDATE_MATRICULA, m.getFecha().toString(),
                m.getEstado(), m.getNota(),
                m.getNrc(), m.getVeces(),
                m.getEstudiante(), m.getGrupo()));
    }

    public boolean det_matricula(Matricula m) {
        return execute_update(String.format(Querys.DEL_MATRICULA, m.getEstudiante(), m.getGrupo()));
    }

    public boolean insert_matricula(Matricula m) {
        return execute_update(String.format(Querys.INSERT_MATRICULA, m.getEstudiante(), m.getGrupo(),
                m.getFecha().toString() ,
                m.getEstado(), m.getNota(),
                m.getNrc(), m.getVeces()));
    }

    //
    //Util
    public int getCantidad(String sql) {
        int cant = 0;
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rset = stmt.executeQuery(sql);
                if (rset.next()) {
                    cant = rset.getInt(1);
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return cant;
    }
    
    public boolean execute_update(String sql) {
        Log.SendLog(sql);
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                int i = stmt.executeUpdate(sql);
                if (i != 0) {
                    return true;
                }
                stmt.close();
                disconnect();
            } catch (Exception e) {
                Log.SendLog(e.getMessage());
            }
        }
        return false;
    }

    public boolean test_Cod(String sql) {
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rset = stmt.executeQuery(sql);
                if (rset.next()) {
                    return true;
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return false;
    }
    
  
    //Connections Properties   
    protected ConnProps props = null;
    //Variables for connection
    protected Connection conn = null;
    protected Statement stmt = null;
    protected PreparedStatement pSt = null;
    protected ResultSet rset = null;
    protected CallableStatement cstm = null;

    public boolean existenTutorias(int anio,int ciclo,String cod){
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                String g=String.format(Querys.EXISTETUTORIA,cod, ciclo,anio ) ;
                rset = stmt.executeQuery( String.format(Querys.EXISTETUTORIA,cod, ciclo,anio ) );
                while (rset.next()) {
                    return true;
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return false;
    }
    
    public boolean esTutora(int anio,int ciclo,String cedula){
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
//                String g=String.format(Querys.EXISTETUTORA,cedula, ciclo,anio ) ;
                rset = stmt.executeQuery( String.format(Querys.EXISTETUTORA,cedula, ciclo,anio ) );
                while (rset.next()) {
                    return true;
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return false;
    }
    
    public boolean esEstudiante(int anio,int ciclo,String cedula){
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
//                String g=String.format(Querys.EXISTETUTORA,cedula, ciclo,anio ) ;
                rset = stmt.executeQuery( String.format(Querys.EXISTEESTUDIANTE,cedula, ciclo,anio ) );
                while (rset.next()) {
                    return true;
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return false;
    }
    
    
    public int getTutorPorCurso(String cod){
        connect();
        String num="";
        if (conn != null) {
            try {                
                stmt = conn.createStatement();
                String sql = String.format(Querys.GET_TUTOR_CURSO, cod);
                Log.SendLog(sql);
                rset = stmt.executeQuery(sql);                
                while (rset.next()) {                    
                    num = rset.getString(1);           
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return Integer.parseInt(num);
    }
    
    public Grupo getGrupo(String cod){
        connect();
        Grupo grupo=null;
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                String sql = String.format(Querys.GET_GRUPO, cod);
                Log.SendLog(sql);
                rset = stmt.executeQuery(sql);                
                while (rset.next()) {
                    int anio, ciclo;
                    String num, tid, tcod, horario, estado, lugar;
                    num = rset.getString(1);
                    tid = rset.getString(2);
                    tcod = rset.getString(3);
                    lugar = rset.getString(4);
                    anio = rset.getInt(5);
                    ciclo = rset.getInt(6);
                    horario = rset.getString(7);
                    estado = rset.getString(8);
                    grupo=new Grupo(num, tid, tcod, lugar, anio, ciclo, horario, estado);                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return grupo;
    }

    public int getCarrera(String string) {
        connect();        
         int num=0;
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                String sql = String.format(Querys.GET_CARRERA_NOMBRE, string);
                Log.SendLog(sql);
                rset = stmt.executeQuery(sql);                
                while (rset.next()) {                    
                    num = rset.getInt(1);
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return num;
    }


    public ArrayList<Estudiante> getEstudiantesFiltradosCicloCarreraAnio(int anio, String ciclo, int carrera) {
        String query="Select id,estudiante.nom,pape,sape,tel,cel,email," +
        "cid,tutoria.nom,horario,sede,genero,beca,ciclo "
                + "from estudiante,matricula,grupo,tutoria "
                    + " where matricula.eid=estudiante.id and "
                    + " estudiante.cid="+carrera+" and "
                    + " matricula.gnum=grupo.num and"
                    + " grupo.ciclo="+getCiclo(ciclo)+" and "
                    + " grupo.anio="+anio
                    +" and tcod=cod "
                    + " order by estudiante.cid,estudiante.nom";     
        return getEstudiantesFiltradosPor(query);     
                
    }
    
    public ArrayList<Estudiante> getEstudiantesFiltradosAnioCiclo(int anio, String ciclo) {
     String query="Select id,estudiante.nom,pape,sape,tel,cel,email," +
        "cid,tutoria.nom,horario,sede,genero,beca,ciclo" +
        " from estudiante,grupo,matricula,tutoria "
                    + " where matricula.eid=estudiante.id and "
                    + " matricula.gnum=grupo.num and"
                    + " grupo.ciclo="+getCiclo(ciclo)+" and "
                    + " grupo.anio="+anio
                    +" and tcod=cod "
                + " order by estudiante.cid,estudiante.nom";  
        return getEstudiantesFiltradosPor(query);         
    }
    
    
    public ArrayList<Estudiante> getEstudiantesFiltradosCarrera(int carrera) {
        String query="Select id,estudiante.nom,pape,sape,tel,cel,email," +
        "cid,tutoria.nom,horario,sede,genero,beca,ciclo" +
        " from estudiante,grupo,matricula,tutoria where id=eid and gnum=num"
         + " and tcod=cod and cid="+carrera
                + " order by estudiante.nom";  
        return getEstudiantesFiltradosPor(query); 
    }

    public ArrayList<Estudiante> getEstudiantesFiltradosAnio(String anio) {
     String query="select id,estudiante.nom,pape,sape,tel,cel,email," +
        "cid,tutoria.nom,horario,sede,genero,beca,ciclo" +
        " from estudiante,grupo,matricula,tutoria"
                + " where id=eid and gnum=num " +
            " and tcod=cod"
                  + " and year(fecha)="+anio
                  + " and id=eid"
                 + " order by estudiante.cid,estudiante.nom";  
        return getEstudiantesFiltradosPor(query);        
    }

    public ArrayList<Estudiante> getEstudiantesFiltradosPorCarreraYanio(String anio, int carreras) {
         String query="select distinct id,estudiante.nom,pape,sape,tel,"
            + "cel,email,cid,tutoria.nom,horario,sede,genero,beca,ciclo " +
            " from estudiante,grupo,matricula,tutoria "
            + "where id=eid and gnum=num " +
            " and tcod=cod and "
           + "year(fecha)="+anio+" and cid="+carreras
                + " order by estudiante.nom";  
        return getEstudiantesFiltradosPor(query);
    }
    
     public ArrayList<Estudiante> getEstudiantesFiltradosPor(String query) {
        ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
        connect();
        
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rset = stmt.executeQuery(query);
                while (rset.next()) {
                    String id;
                    String nom, pape, sape, mail, tutoria, horario,ciclo;
                    int gen,sede,beca, cel, tel, carrera;
                    id = rset.getString(1);
                    nom = rset.getString(2);
                    pape = rset.getString(3);
                    sape = rset.getString(4);
                    tel = rset.getInt(5);
                    cel = rset.getInt(6);
                    mail = rset.getString(7);
                    carrera = rset.getInt(8);
                    tutoria = rset.getString(9);
                    horario = rset.getString(10);
                    sede = rset.getInt(11);
                    gen= rset.getInt(12);
                    beca= rset.getInt(13);
                    ciclo=getCicloRomano(rset.getInt(14));
                    lista.add(new Estudiante(nom, pape, sape, Integer.parseInt(id), cel, tel, mail, sede,carrera,tutoria,horario,gen,beca,ciclo));
                }       
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return lista;
    }

    private int getCiclo(String ciclo) {
        int num;
        switch(ciclo){
            case "I": num=0;   break;
            case "II": num=1;  break;
            case "III":num=2;  break;
            default: num=0;break;             
        }
        return num;
    }

    public ArrayList<Estudiante> getEstudiantesFiltradosPorCiclo(String ciclo) {
        String query="select id,estudiante.nom,pape,sape,tel,cel,email," +
        "cid,tutoria.nom,horario,sede,genero,beca,ciclo "
          + "from estudiante,matricula,grupo,tutoria where ciclo="+getCiclo(ciclo)
         + " and id=eid and gnum=grupo.num and cod=tcod order by cid,nom";
        return getEstudiantesFiltradosPor(query);
    }

    public ArrayList<Estudiante> getEstudiantesFiltradosPorCicloCarrera(String ciclo, int carreras) {
        String query="select id,estudiante.nom,pape,sape,tel,cel,email," +
        "cid,tutoria.nom,horario,sede,genero,beca,ciclo "
          + " from estudiante,matricula,grupo,tutoria where ciclo="+getCiclo(ciclo)+
        " and cid="+carreras+
        " and eid=id and gnum=grupo.num and cod=tcod order by nom";
        return getEstudiantesFiltradosPor(query);    
    }

    public ArrayList<Estudiante> getEstudiantesFiltradosPorCicloAnio(String ciclo, String anio) {
        String query="select id,estudiante.nom,pape,sape,tel,cel,email," +
        "cid,tutoria.nom,horario,sede,genero,beca,ciclo "
          + "from estudiante,matricula,grupo,tutoria where ciclo="+getCiclo(ciclo)
                + " and tcod=cod and eid=id and num=gnum and year(fecha)="+anio
        + " order by estudiante.cid,estudiante.nom";  
        return getEstudiantesFiltradosPor(query);     }

    private String getCicloRomano(int aInt) {
            switch(aInt){
                case 0: return "I";
                case 1: return "II";
                case 2: return "III";
                default: return "I";
            }
    }

    public String getCelular(String estudiante) {
         connect();        
         String num="";
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                String sql = "Select cel from estudiante where id="+estudiante;
                Log.SendLog(sql);
                rset = stmt.executeQuery(sql);                
                while (rset.next()) {                    
                    num = rset.getString(1);
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return num;
    }

    public boolean addToRetirados(Matricula g,String motivo) {    
        boolean yaExiste=false;
        if (conn != null) {
            try {
                String tutoria=getCodTutoria(g.getGrupo());
                connect(); 
                stmt = conn.createStatement();
                String sql = "Select * from retiraron where eid='"+g.getEstudiante()+
                        "' and TUTORIACOD='"+tutoria+"'";
                Log.SendLog(sql);
                rset = stmt.executeQuery(sql);                 
                while (rset.next()) {     
                    yaExiste=true;
                }
                if(!yaExiste){
                    addTORetiradosTable(g,tutoria,motivo);
                }
                rset.close();
                stmt.close();
                disconnect();
                return yaExiste;
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return yaExiste;
    }

    private String getCodTutoria(String grupo) {
     connect();        
         String num="";
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                String sql = "Select tcod from grupo where num='"+grupo+"'";
                Log.SendLog(sql);
                rset = stmt.executeQuery(sql);                
                while (rset.next()) {                    
                    num = rset.getString(1);
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return num;
    }

    private void addTORetiradosTable(Matricula g, String tutoria, String motivo) {
        String sql = "Insert into Retiraron values"
                        + "('" + g.getEstudiante() + "','" + g.getFecha() + "',sysdate(),'"
                        + tutoria + "','" + motivo+"'"
                        + ")";
        execute_update(sql);    
    }

    public void revisarSiRetiro(int id, String tcod) {
        if (conn != null) {
            try {
                connect(); 
                stmt = conn.createStatement();
                String sql = "Select * from retiraron where eid='"+id+
                        "' and TUTORIACOD='"+tcod+"'";
                Log.SendLog(sql);
                rset = stmt.executeQuery(sql);                 
                while (rset.next()) {
                    sql="Delete from retiraron where eid='"+id+
                             "' and TUTORIACOD='"+tcod+"'";
                    execute_update(sql);    
                }
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
    }

    public ArrayList<Retirado> getRetirados() {
        ArrayList<Retirado> lista = new ArrayList<Retirado>();
        connect();
        
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rset = stmt.executeQuery(
                        "select eid,nom,pape,sape,fechamatricula,fecharetiro,"
                         + "tutoriacod,motivo,cel from retiraron , estudiante "
                         + "where eid=id");
                while (rset.next()) {
                    String eid;
                    String fechaMatricula, fechaRetiro, tutoriaCod, motivo,telefono;
                    
                    eid = rset.getString(1);
                    String nombre=rset.getString(2)+" "+rset.getString(3)+" "+rset.getString(4);
                    fechaMatricula = rset.getString(5);
                    fechaRetiro = rset.getString(6);
                    tutoriaCod = rset.getString(7);
                    motivo = rset.getString(8);
                    telefono=rset.getString(9);
                    lista.add(new Retirado(eid,motivo,fechaRetiro, fechaMatricula,nombre,tutoriaCod,telefono));
                }       
                rset.close();
                stmt.close();
                disconnect();
            } catch (Exception ex) {
                Log.SendLog(ex.getMessage());
            }
        }
        return lista;    }

    public void crearRespaldo(String path) {
            
        try {
            String executeCmd = "mysqldump -u " + props.getUser() + " -p" + props.getPassword()  + "  " + props.getDb() + " > " + path+ ".sql";
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            
            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                System.out.println("Backup Complete");
            } else {
                System.out.println("Backup Failure");
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ConnBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}












