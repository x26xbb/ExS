package exs.mod.var;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Estudiante_Var {

    //Sedes
    public static final String[] SEDES = {"Omar Dengo", "Interuniversitaria", "Chorotega - Campus Liberia", "Chorotega - Campus Nicoya", "Brunca - Campus Pérez Zeledón", "Brunca - Campus Coto", "Sarapiquí"};

    public static String getSede(int i) {
        return SEDES[i];
    }
    //Becas
    public static final String[] BECAS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "10* Ayuda Socioeconómica",};

    public static String getBeca(int i) {
        return BECAS[i];
    }
}
