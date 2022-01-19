package es.system.usuarios.database.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import es.system.usuarios.database.contract.UsuarioContract;

/**
 * Clase que extiende de SQLiteOpenHelper. Se optimiza para cuando es
 * necesario tener mas de una tabla en la BBDD.
 */
public class ComunHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Usuario.db";

    public ComunHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Metodo encargado de realizar creacion de todas las tablas de la BBDD
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Se crea la tabla de la BBDD de usuarios
        sqLiteDatabase.execSQL(UsuarioContract.SQL_CREATE_ENTRIES);
        //todo: se existen mas tablas en la bbdd se deben de indicar aqui la creacion
    }

    /**
     * Metodo encargado de realizar la actualizacion de la bbdd
     * @param sqLiteDatabase
     * @param oldVersion version anterior
     * @param newVersion version a actualizar
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(UsuarioContract.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    /**
     * Medodo encargado de realizar la insercion
     * @param table en la que se realiza la insercion
     * @param contentValues valores que se insertan
     */
    public boolean add(String table, ContentValues contentValues) {
        boolean resultado = false;
        long fila = getWritableDatabase().insert(table, null,contentValues);
        if (fila != -1) {
            resultado = true;
        }
        return true;
    }

    /**
     * Funcion encargada de realizar una consulta sobre una tabla de la BBDD
     * @param table Nombre de la tabla
     * @param projection Conjunto de columnas que deseamos obtener
     * @param selection Columnas de la clausula Where
     * @param selectionArgs Valores de las columnas de la clausula Where
     * @param sortOrder odernacion que se desea
     * @return Cursor con el resultado de la operacion
     */
    public Cursor search(String table, String[] projection, String selection,
                         String[] selectionArgs,
                         String sortOrder) {
        return search(table, projection, selection,
                selectionArgs, null, null, sortOrder);
    }

    /**
     * Funcion que retorna un cursos con todos los elementos de la tabla
     * @param table nombre de la tabla
     * @return cursor con el resultado
     */
    public Cursor search(String table) {
        Cursor cursor = getReadableDatabase()
                .rawQuery("select * from " + table, null);
        cursor.moveToFirst();
        return cursor;
    }
    /**
     * Funcion encargada de realizar una consulta sobre una tabla de la BBDD
     * @param table Nombre de la tabla
     * @param projection Conjunto de columnas que deseamos obtener
     * @param selection Columnas de la clausula Where
     * @param selectionArgs Valores de las columnas de la clausula Where
     * @param groupBy Clausula groupBy. Agrupacion de resultados
     * @param having Clausula having
     * @param sortOrder odernacion que se desea
     * @return Cursor con el resultado de la operacion
     */
    public Cursor search(String table, String[] projection, String selection,
                         String[] selectionArgs,String groupBy, String having,
                         String sortOrder) {

        Cursor cursor = getReadableDatabase().query(
                table,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        return cursor;
    }

    /**
     * Funcion encargado de realizar la actualizacion sobre la bbbd
     * @param table nombre de la tabla sobre la que se actualiza
     * @param contentValues del objeto
     * @param selection filtro para identificar el elemento
     * @param selectionArgs argumentos para identificar
     * @return TRUE si se ha actualizado 1 o mas filas
     */
    public boolean update (String table,ContentValues contentValues,
                        String selection, String[] selectionArgs) {
        boolean resultado = false;

        int filasAfectadas = getWritableDatabase().update(
                table,
                contentValues,
                selection,
                selectionArgs);

        if (filasAfectadas > 0) {
            resultado = true;
        }
        return resultado;
    }

    /**
     * Funcion encargado de realizar la eliminacion sobre la bbbd
     * @param table nombre de la tabla sobre la que se actualiza
     * @param selection filtro para identificar el elemento
     * @param selectionArgs argumentos para identificar
     * @return TRUE si se ha actualizado 1 o mas filas
     */
    public boolean delete (String table, String selection, String[] selectionArgs) {
        boolean resultado = false;

        int filasAfectadas = getWritableDatabase().delete(
                table,
                selection,
                selectionArgs);

        if (filasAfectadas > 0) {
            resultado = true;
        }
        return resultado;
    }
}
