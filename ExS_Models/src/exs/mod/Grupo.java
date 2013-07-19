package exs.mod;

import exs.mod.var.Grupo_Var;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Grupo {

    public Grupo(String num, String tid, String tcod, String lugar, int anio, int ciclo, String horario, String estado) {
        this.num = num;
        this.tid = tid;
        this.tcod = tcod;
        this.lugar = lugar;
        this.anio = anio;
        this.ciclo = ciclo;
        this.horario = horario;
        this.estado = estado;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTcod() {
        return tcod;
    }

    public void setTcod(String tcod) {
        this.tcod = tcod;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getCiclo() {
        return ciclo;
    }

    public String _getCiclo() {
        return Grupo_Var.CICLOS[ciclo];
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public char _getDia() {
        return horario.charAt(0);
    }

    public String getDia() {
        char dia = _getDia();
        switch (dia) {
            case Grupo_Var.LUNES_CHAR:
                return Grupo_Var.LUNES;
            case Grupo_Var.MARTES_CHAR:
                return Grupo_Var.MARTES;
            case Grupo_Var.MIERCOLES_CHAR:
                return Grupo_Var.MIERCOLES;
            case Grupo_Var.JUEVES_CHAR:
                return Grupo_Var.JUEVES;
            case Grupo_Var.VIERNES_CHAR:
                return Grupo_Var.VIERNES;
            case Grupo_Var.SABADO_CHAR:
                return Grupo_Var.SABADO;
        }
        return "";
    }

    public int getHora() {
        String h = horario.substring(1);
        return Integer.parseInt(h);
    }

    public String getHoraString() {
        int hora = getHora();
        return (hora > 12) ? (hora - 12) + ":00 pm." : hora + ":00 am.";
    }

    public String _getHorario() {
        return getDia() + " " + getHoraString();
    }

    public Object[] toArray() {
        Object[] array = new Object[8];
        array[0] = num;
        array[1] = tid;
        array[2] = anio;
        array[3] = _getCiclo();
        array[4] = _getHorario();
        array[5] = estado;
        array[6] = lugar;
        return array;
    }
    private String num;
    private String tid;
    private String tcod;
    private String lugar;
    private int anio;
    private int ciclo;
    private String horario;
    private String estado;
}
