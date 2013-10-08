package exs.db;

import exs.logs.err.Log;
import exs.mod.Carrera;
import exs.mod.Estudiante;
import exs.mod.Grupo;
import exs.mod.Matricula;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
                    File arc=new File(path);
                    FileInputStream fileIn = new FileInputStream(arc);
                    ObjectInputStream in = new ObjectInputStream(fileIn);                

                     props = (ConnProps) in.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
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
                t.getGenero(), t.getTelefono(), t.getEmail(), t.getId()));
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

    
    public ArrayList<Estudiante> getEstudiantes() {
        ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
        connect();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rset = stmt.executeQuery(Querys.GET_ESTUDIANTES);
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
                    int gen, cel, tel, carrrera, sede, beca;
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
                    estudiante = new Estudiante(nom, pape, sape, id, cel, tel, mail, gen, beca, sede, carrrera);
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
                t.getPriApellido(), t.getSegApellido(),
                t.getGenero(), t.getTelefono(), t.getCelular(), t.getEmail(), t.getCarrera(), t.getSede(), t.getBeca(), t.getId()));
    }

    public boolean det_estudiante(Estudiante t) {
        return execute_update(String.format(Querys.DEL_ESTUDIANTE, t.getId()));
    }

    public boolean insert_estudiante(Estudiante t) {
        return execute_update(String.format(Querys.INSERT_ESTUDIANTE, t.getId(),
                t.getNombre(), t.getPriApellido(), t.getSegApellido(),
                t.getGenero(), t.getTelefono(), t.getCelular(), t.getEmail(), t.getCarrera(), t.getSede(), t.getBeca()));
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
}
