/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exs.mod;

/**
 *
 * @author UNA
 */
public class Retirado {
    String id,motivo,fechaRetiro,fechaMatricula,nombre,curso,celular;

    public Retirado(String id, String motivo, String fechaRetiro, String fechaMatricula, String nombre, String curso,String tel) {
        this.id = id;
        this.motivo = motivo;
        this.fechaRetiro = fechaRetiro;
        this.fechaMatricula = fechaMatricula;
        this.nombre = nombre;
        this.curso = curso;
        celular=tel;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFechaRetiro() {
        return fechaRetiro;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    
    
    public void setFechaRetiro(String fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public String getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(String fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
