package exs.mod;

import exs.mod.var.Estudiante_Var;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Estudiante extends Persona{
 
    

//    public Estudiante(String nombre, String priApellido, String segApellido, int id, int celular, int telefono, String email, int genero, int beca, int sede, int carrera) {
//        super(nombre, priApellido, segApellido, id, telefono, email, genero);
//       
//        this.celular = celular;        
//        this.beca = beca;
//        this.sede = sede;
//        this.carrera = carrera;
//    }
    public Estudiante(String nombre, String priApellido, String segApellido, int id, int celular, int telefono, String email,int sede,int carrera,String tutoria,String horario, int genero,int beca,String ciclo) {
        super(nombre, priApellido, segApellido, id, telefono, email, genero);
        this.celular = celular;        
        this.beca = beca;
        this.sede = sede;
        this.carrera = carrera;
        this.tutoria=tutoria;
        this.horario=horario;
        this.ciclo=ciclo;
    }

    public Estudiante(String nom, String pape, String sape, int parseInt, int cel, int tel, String mail, int gen, int beca, int sede, int carrera) {
        super(nom, pape, sape, parseInt, cel, mail, gen);
       
        this.celular = cel;        
        this.beca = beca;
        this.sede = sede;
        this.carrera = carrera;   
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

    public String getTutoria() {
        return tutoria;
    }

    public void setTutoria(String tutoria) {
        this.tutoria = tutoria;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
     
    
    public Object[] toArray() {
        Object[] array = new Object[11];
        array[0] = id;
        array[1] = _getGenero();
        array[2] = nombre + " " + priApellido + " " + segApellido;
        array[3] = telefono;
        array[4] = celular;
        array[5] = email;
        array[6] = Estudiante_Var.getBeca(beca);
        array[7] = Estudiante_Var.getSede(sede);
        array[8] = carrera + "";
        array[9] = tutoria + "";
        array[10]= horario;
        array[11]=ciclo;
        return array;
    }
    
     public Object[] toArray2() {
        Object[] array = new Object[10];
        array[0] = id;        
        array[1] = nombre + " " + priApellido + " " + segApellido;
        array[2] = telefono;
        array[3] = celular;
        array[4] = email;   
        array[5] = tutoria + "";
        array[6]= horario;
        array[7]=ciclo;
        array[8] = carrera + "";          
        array[9] = Estudiante_Var.getSede(sede);
        return array;
    }
   
    
    private int celular;    
    private int beca;
    private int sede;
    private int carrera;
    private String tutoria;
    private String horario;
    private String ciclo;
}