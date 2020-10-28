/**
 *
 * @author Marcos
 */
package com.proyectohotel.capa3dominio;
public class Cliente {
    private String apellido;
    private String codigocliente;
    private String correo ;
    private String nacionalidad;
    private String nombre;
    private String numeroIdentidad;
    private String telefono;
    private String tipoDocumento;

    public Cliente(String apellido, String codigocliente, String correo, String nacionalidad, String nombre, String numeroIdentidad, String telefono, String tipoDocumento) {
        this.apellido = apellido;
        this.codigocliente = codigocliente;
        this.correo = correo;
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
        this.numeroIdentidad = numeroIdentidad;
        this.telefono = telefono;
        this.tipoDocumento = tipoDocumento;
    }
    public Cliente(){}

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodigocliente() {
        return codigocliente;
    }

    public void setCodigocliente(String codigocliente) {
        this.codigocliente = codigocliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroIdentidad() {
        return numeroIdentidad;
    }

    public void setNumeroIdentidad(String numeroIdentidad) {
        this.numeroIdentidad = numeroIdentidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
}
