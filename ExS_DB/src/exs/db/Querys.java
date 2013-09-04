package exs.db;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Querys {
    //Test

    public static String TEST = "SELECT 1 FROM DUAL";
    //
    //Tutorias
    public static String GET_TUTORIAS = "SELECT * FROM TUTORIA";
    public static String GET_TUTORIAS_ORDEN = "SELECT * FROM TUTORIA ORDER BY NOM";
    // public static String GET_TUTORIAS_COD = "SELECT * FROM TUTORIA WHERE COD = '%s'";
    public static String DEL_TUTORIA = "DELETE FROM TUTORIA WHERE COD = '%s'";
    public static String UPDATE_TUTORIA = "UPDATE TUTORIA SET NOM = '%s' WHERE COD = '%s'";
    public static String INSERT_TUTORIA = "INSERT INTO TUTORIA  (COD , NOM) VALUES ('%s' , '%s')";
    //
    //Tutores
    public static String GET_TUTORES = "SELECT * FROM TUTOR ORDER BY NOM";
    public static String GET_TUTORES_COD = "SELECT * FROM TUTOR WHERE ID = '%s'";
    public static String DEL_TUTOR = "DELETE FROM TUTOR WHERE ID = '%s'";
    public static String UPDATE_TUTOR = "UPDATE TUTOR SET NOM = '%s',   PAPE= '%s', SAPE= '%s', GENERO= '%d', TEL= '%d', EMAIL= '%s'  WHERE ID = '%s'";
    public static String INSERT_TUTOR = "INSERT INTO TUTOR  (ID , NOM, PAPE, SAPE, GENERO, TEL, EMAIL) VALUES ('%s' , '%s', '%s', '%s', '%d', '%d', '%s')";
    //
    //Grupos
    public static String GET_HISTORICO="select TUTORIA.NOM,GRUPO.NUM,GRUPO.TCOD,GRUPO.ANIO,GRUPO.CICLO,GRUPO.HORARIO,MATRICULA.EID,ESTUDIANTE.NOM,ESTUDIANTE.PAPE,ESTUDIANTE.SAPE FROM MATRICULA,GRUPO,TUTORIA,ESTUDIANTE WHERE MATRICULA.EID='%d' AND  MATRICULA.GNUM=GRUPO.NUM AND GRUPO.TCOD=TUTORIA.COD AND MATRICULA.EID=ESTUDIANTE.ID";
    public static String GET_CANT_GRUPO = "SELECT COUNT(*) FROM GRUPO WHERE TCOD = '%s' ";
    public static String GET_GRUPOS = "SELECT * FROM GRUPO WHERE TCOD = '%s' ";
    public static String GET_GRUPOS_C = "SELECT * FROM GRUPO WHERE TCOD = '%s'  AND CICLO= '%d' AND ANIO= '%d' AND ESTADO = '%s' ";
    public static String DEL_GRUPO = "DELETE FROM GRUPO WHERE NUM = '%s'";
    public static String UPDATE_GRUPO = "UPDATE GRUPO SET TID = '%s',   TCOD= '%s', LUGAR = '%s', ANIO= '%d', CICLO= '%d', HORARIO= '%s', ESTADO= '%s'  WHERE NUM = '%s'";
    public static String INSERT_GRUPO = "INSERT INTO GRUPO  (NUM , TID, TCOD, LUGAR ,ANIO, CICLO, HORARIO, ESTADO) VALUES ('%s' , '%s', '%s','%s', '%d', '%d', '%s', '%s')";
    //
    //Estudiantes 
    public static String GET_ESTUDIANTES = "SELECT * FROM ESTUDIANTE ORDER BY NOM";
    public static String GET_ESTUDIANTE = "SELECT * FROM ESTUDIANTE WHERE ID = '%d'";
    // public static String GET_TUTORES_COD = "SELECT * FROM TUTOR WHERE ID = '%s'";
    public static String DEL_ESTUDIANTE = "DELETE FROM ESTUDIANTE WHERE ID = '%s'";
    public static String UPDATE_ESTUDIANTE = "UPDATE ESTUDIANTE SET NOM = '%s',   PAPE= '%s', SAPE= '%s', GENERO= '%d', TEL= '%d', CEL = '%d',EMAIL= '%s', CID= '%d', SEDE= '%d', BECA= '%d'  WHERE ID = '%s'";
    public static String INSERT_ESTUDIANTE = "INSERT INTO ESTUDIANTE  (ID , NOM, PAPE, SAPE, GENERO, TEL, CEL, EMAIL, CID, SEDE, BECA) VALUES ('%s' , '%s', '%s', '%s', '%d','%d' ,'%d', '%s', '%d', '%d', '%d')";
    //
    //Carreras
    public static String GET_CARRERAS = "SELECT * FROM CARRERA";
    public static String GET_CARRERAS_ORDEN = "SELECT * FROM CARRERA ORDER BY CNOM";
    // public static String GET_CARRERAS_COD = "SELECT * FROM CARRERA WHERE CID = '%d'";
    public static String DEL_CARRERA = "DELETE FROM CARRERA WHERE CID = '%d'";
    public static String UPDATE_CARRERA = "UPDATE CARRERA SET CNOM = '%s', SEDE = '%d' WHERE CID = '%d'";
    public static String INSERT_CARRERA = "INSERT INTO CARRERA  (CID , CNOM, SEDE) VALUES ('%d' , '%s',  '%d')";
    //Matriculas
    public static String GET_CANT_MATRICULAS = "SELECT COUNT(*) FROM MATRICULA WHERE GNUM = '%s' ";
    public static String GET_MATRICULAS = "SELECT * FROM MATRICULA  WHERE GNUM = '%s'";
    public static String DEL_MATRICULA = "DELETE FROM MATRICULA WHERE EID = '%s'  AND GNUM = '%s' ";
    public static String UPDATE_MATRICULA = "UPDATE MATRICULA SET FECHA = '%s', ESTADO = '%s', NOTA = '%f' , NRC = '%d', VECES = '%d'  WHERE EID = '%s'  AND GNUM = '%s' ";
    public static String INSERT_MATRICULA = "INSERT INTO MATRICULA  (EID , GNUM, FECHA, ESTADO, NOTA, NRC, VECES) VALUES ('%s' , '%s',  '%s' , '%s', '%f', '%d', '%d' )";

        
}
