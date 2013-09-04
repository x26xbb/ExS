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
    String nomCurso, num, tcod, anio,ciclo,horario,ced,nombre,pa,sa;

    public Historico(String nomCurso, String num, String tcod, String anio, String ciclo, String horario, String ced, String nombre,String pa,String sa) {
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
        this.ced = ced;
        this.nombre = nombre;
        this.pa=pa;
        this.sa=sa;
    }

    
      public Object[] toArray() {
        Object[] array = {nomCurso, num,tcod,horario,ciclo,anio};
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
