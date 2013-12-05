/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exs.db;

/**
 *
 * @author Administrator
 */
public class Historico {
    String lugar,nomCurso,estado, num, tcod, anio,ciclo,horario,ced,nombre,pa,sa;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    int tipo;//1 est 2 tutor

    
     public Historico( String num,String nomCurso, String tcod, String anio, String ciclo, String horario,String estado,String ced, String nombre,String pa,String sa,int tipo,String lugar) {
        this.nomCurso = nomCurso;
        this.num = num;
        this.tcod = tcod;
        this.anio = anio;
        if(Integer.parseInt(ciclo)==0) {
            this.ciclo = "I";
        } else {
            if(Integer.parseInt(ciclo)==1){
                this.ciclo = "II";
            }else{
                this.ciclo = "III";
            }
        }
        this.horario = horario;
        if(tipo==1) this.ced = " de Estudiante: "+ced;
        else this.ced = " de Tutor: "+ced;
        this.nombre = nombre;
        this.pa=pa;
        this.sa=sa;
        this.estado=estado;
        this.tipo=tipo;
        this.lugar=lugar;
    }

    
      public Object[] toArray() {
        Object[] array = {nomCurso, num,tcod,horario,ciclo,anio,estado,lugar};
        return array;
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public String getSa() {
        return sa;
    }

    public void setSa(String sa) {
        this.sa = sa;
    }
      
    public String getNomCurso() {
        return nomCurso;
    }

    public void setNomCurso(String nomCurso) {
        this.nomCurso = nomCurso;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTcod() {
        return tcod;
    }

    public void setTcod(String tcod) {
        this.tcod = tcod;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCed() {
        return ced;
    }

    public void setCed(String ced) {
        this.ced = ced;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
