package exs.mod;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Tutor extends Persona{

    
    public Tutor(String nombre, String priApellido, String segApellido, int id, int telefono, String email, int genero) {
        super(nombre, priApellido, segApellido, id, telefono, email, genero);
        
    }    

   

    public Object[] toArray() {
        Object[] array = new Object[5];
        array[0] = id;
        array[1] = _getGenero();
        array[2] = nombre + " " + priApellido + " " + segApellido;
        array[3] = telefono;
        array[4] = email;
        return array;
    }
    
}
