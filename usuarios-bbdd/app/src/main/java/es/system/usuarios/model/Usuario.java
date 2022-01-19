package es.system.usuarios.model;

import android.content.ContentValues;

import es.system.usuarios.database.contract.UsuarioContract;

/**
 * Clase usuario que interactua en la app
 */
public class Usuario {
    //todo: podria darse el caso que existan discrepancias entre las propierades de
    // la clase del modelo y los de la clase *Entry correspondiente.
    String id;
    String nombre;

    /**
     * Constructor por defecto de la clase
     */
    public Usuario(){}

    /**
     * Constructor de la clase con parametros
     * @param id identificador unico del elementno (DNI por ejemplo)
     * @param nombre del Usuario
     */
    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Funcion encargada de transformar un objeto del modelo a un elemeno
     * que interactua con la BBDD
     * @return Usuario con formato ContentValues
     */
    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(UsuarioContract.UsuarioEntry._ID, this.id);
        values.put(UsuarioContract.UsuarioEntry.COLUMN_NAME, this.nombre);
        return values;
    }

}
