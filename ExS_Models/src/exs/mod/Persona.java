package exs.mod;

/**
 *
 * @author Kevin Villalobos A.
 */
public abstract class Persona {

    public Persona(String nombre, String priApellido, String segApellido, int id, int telefono, String email, int genero) {
        this.nombre = nombre;
        this.priApellido = priApellido;
        this.segApellido = segApellido;
        this.id = id;
        this.telefono = telefono;
        this.email = email;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPriApellido() {
        return priApellido;
    }

    public void setPriApellido(String priApellido) {
        this.priApellido = priApellido;
    }

    public String getSegApellido() {
        return segApellido;
    }

    public void setSegApellido(String segApellido) {
        this.segApellido = segApellido;
    }
    
    public String getFullName(){
        return nombre + " "+ priApellido  + " " + segApellido ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String _getGenero() {
        switch(genero){
            case 1: return "Masculino"; 
            case 2: return "Femenino"; 
            case 3: return "Otro"; 
            default: return "Otro"; 
        }
    }

    @Override
    public String toString() {
        return id + " " + getFullName();
    }
    
    protected String nombre;
    protected String priApellido;
    protected String segApellido;
    protected int id;
    protected int telefono;
    protected String email;
    protected int genero;
}