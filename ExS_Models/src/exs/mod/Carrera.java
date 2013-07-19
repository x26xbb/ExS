package exs.mod;

import exs.mod.var.Estudiante_Var;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Carrera {

    public Carrera(int num, String nombre, int sede) {
        this.num = num;
        this.nombre = nombre;
        this.sede = sede;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSede() {
        return sede;
    }

    public void setSede(int sede) {
        this.sede = sede;
    }

    public Object[] toArray() {
        Object[] array = {num, nombre, Estudiante_Var.getSede(sede)};
        return array;
    }

    @Override
    public String toString() {
        return num + " " + nombre;
    }
    private int num;
    private String nombre;
    private int sede;
}
