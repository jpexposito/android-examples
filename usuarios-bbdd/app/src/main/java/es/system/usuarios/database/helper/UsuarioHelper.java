package es.system.usuarios.database.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import es.system.usuarios.database.contract.UsuarioContract;
import es.system.usuarios.model.Usuario;

public class UsuarioHelper extends ComunHelper{

    public UsuarioHelper(Context context) {
        super(context);
    }

    /**
     * Medodo encargado de realizar la insercion
     * @param usuario que se utiliza en la operacion
     */
    public boolean add(Usuario usuario) {
        return super.add(UsuarioContract.UsuarioEntry.TABLE_NAME,
                usuario.toContentValues());
    }

    /**
     * Funcion encargada de realizar la actualizacion de un elemento
     * @param usuario con los datos a actualizar
     * @return True en caso de que la actualizacion haya sido correcta.
     * False en caso contrario
     */
    public boolean update(Usuario usuario) {
        String selection = UsuarioContract.UsuarioEntry._ID + " = ?";
        String[] selectionArgs = { usuario.getNombre() };
        return super.update(
                UsuarioContract.UsuarioEntry.TABLE_NAME,
                usuario.toContentValues(),
                selection,
                selectionArgs);
    }

    /**
     * Funcion que realiza la eliminacion de un elemento sobre la table
     * @param usuario que se va a eliminar
     * @return True en caso de que la eliminación haya sido correcta. False
     * en caso contrario
     */
    public boolean delete(Usuario usuario) {
        String selection = UsuarioContract.UsuarioEntry._ID + " = ?";
        String[] selectionArgs = { usuario.getId() };
        return super.delete(
                UsuarioContract.UsuarioEntry.TABLE_NAME,
                selection,
                selectionArgs);
    }

    /**
     * Funcion que realiza la busqueda de un elemento a traves de su id
     * @param id del usuario
     * @return con los tados del usuario
     */
    public Usuario searchOne(String id) {
        Usuario usuario = null;
        String selection = UsuarioContract.UsuarioEntry._ID + " = ?";
        String[] selectionArgs = { id };
        Cursor cursor = super.search(UsuarioContract.UsuarioEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs, null);        if (cursor != null) {
            while(cursor.moveToNext()) {
                usuario = new Usuario(cursor.getString(cursor.getColumnIndexOrThrow(
                        UsuarioContract.UsuarioEntry._ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(
                                UsuarioContract.UsuarioEntry.COLUMN_NAME)));
            }
            cursor.close();
        }
        return usuario;
    }

    /**
     * Funcion que realiza la busqueda de todos los usuarios
     * @return lista de usuarios
     */
    public List<Usuario> searchAll() {
        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = super.search(UsuarioContract.UsuarioEntry.TABLE_NAME);
        if (cursor != null) {
            while(cursor.moveToNext()) {
                usuarios.add(new Usuario(cursor.getString(cursor.getColumnIndexOrThrow(
                        UsuarioContract.UsuarioEntry._ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(
                                UsuarioContract.UsuarioEntry.COLUMN_NAME))));
            }
            cursor.close();
        }
        return usuarios;
    }

}
