package exs.mod;

import exs.mod.var.Estudiante_Var;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Estudiante extends Persona{
 
    

    public Estudiante(String nombre, String priApellido, String segApellido, int id, int celular, int telefono, String email, int genero, int beca, int sede, int carrera) {
        super(nombre, priApellido, segApellido, id, telefono, email, genero);
       
        this.celular = celular;        
        this.beca = beca;
        this.sede = sede;
        this.carrera = carrera;
    }
    public Estudiante(String nombre, String priApellido, String segApellido, int id, int celular, int telefono, String email, int genero) {
        super(nombre, priApellido, segApellido, id, telefono, email, genero);
        this.celular = celular;        
        this.beca = 0;
        this.sede = 0;
        this.carrera = 0;
    }

    

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    

    public int getBeca() {
        return beca;
    }

    public void setBeca(int beca) {
        this.beca = beca;
    }

    public int getSede() {
        return sede;
    }

    public void setSede(int sede) {
        this.sede = sede;
    }

    public int getCarrera() {
        return carrera;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
    }
     
 
    public Object[] toArray() {
        Object[] array = new Object[9];
        array[0] = id;
        array[1] = _getGenero();
        array[2] = nombre + " " + priApellido + " " + segApellido;
        array[3] = telefono;
        array[4] = celular;
        array[5] = email;
        array[6] = Estudiante_Var.getBeca(beca);
        array[7] = Estudiante_Var.getSede(sede);
        array[8] = carrera + "";
        return array;
    }
    
    
    private int celular;    
    private int beca;
    private int sede;
    private int carrera;
    
}
