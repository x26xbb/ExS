package exs.mod;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Tutoria {

    public Tutoria(String cod, String nom) {
        this.cod = cod;
        this.nom = nom;
    }

    public Tutoria() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return cod + " " + nom;
    }

    public Object[] toArray() {
        Object[] array = new Object[2];
        array[0] = cod;
        array[1] = nom;
        return array;
    }
    
    private String cod;
    private String nom;
}
