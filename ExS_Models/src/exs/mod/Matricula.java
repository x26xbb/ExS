package exs.mod;

import java.sql.Date;

/**
 *
 * @author Kevin Villalobos A.
 *
 */
public class Matricula {

    public Matricula(String grupo, String estudiante, Date fecha, String estado, float nota, int nrc, int veces) {
        this.grupo = grupo;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.estado = estado;
        this.nota = nota;
        this.nrc = nrc;
        this.veces = veces;
    }

    public int getVeces() {
        return veces;
    }

    public void setVeces(int veces) {
        this.veces = veces;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    private String grupo;
    private String estudiante;
    private Date fecha;
    private String estado;
    private float nota;
    private int nrc;
    private int veces;
}
