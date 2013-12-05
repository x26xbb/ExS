package exs.mod.var;

import java.util.Calendar;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Grupo_Var {

    //Metodos
    public static int getAnio() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getCiclo() {
        int mes = Calendar.getInstance().get(Calendar.MONTH);
        return (mes < 6) ? 0 : ((mes < 10) ? 1 : 2);
    }
    //Hora
    public static int HORA = 7;
    //Nom
    public static String LUNES = "Lunes";
    public static String MARTES = "Martes";
    public static String MIERCOLES = "Miércoles";
    public static String JUEVES = "Jueves";
    public static String VIERNES = "Viernes";
    public static String SABADO = "Sábado";
    //Nemo
    public static final char LUNES_CHAR = 'L';
    public static final char MARTES_CHAR = 'M';
    public static final char MIERCOLES_CHAR = 'I';
    public static final char JUEVES_CHAR = 'J';
    public static final char VIERNES_CHAR = 'V';
    public static final char SABADO_CHAR = 'S';
    //Ciclos
    public static final String[] CICLOS = {"I", "II", "III"};
    //Estados
    public static String ABIERTO = "Abierto";
    public static String FINALIZADO = "Finalizado";
    public static String SUSPENDIDO = "Suspendido";
    public static String SIN_CUPO = "Sin Cupo";
}
