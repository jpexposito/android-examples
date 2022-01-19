package es.system.usuarios.database.contract;

import android.provider.BaseColumns;

public class UsuarioContract {

    /**
     * Se define la sentencia para la creacion de la tabla de la bbdd
     */
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UsuarioEntry.TABLE_NAME + " (" +
                    UsuarioEntry._ID + " INTEGER PRIMARY KEY," +
                    UsuarioEntry.COLUMN_NAME + " TEXT)";

    /**
     * Se define la sentencia para la eliminacion de la tabla de la bbdd
     */
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UsuarioEntry.TABLE_NAME;

    /**
     * Constructor por defecto
     */
    private UsuarioContract(){
    }

    /**
     * Se realiza la defincion de las propiedades de la tabla
     * nombre: usuario
     * columna_id: se hereda de BaseColumns (String)
     * columna_name: se define el nombre de la columna (String)
     */
    public static class UsuarioEntry implements BaseColumns {
        private String name;
        public static final String TABLE_NAME = "usuario";
        public static final String COLUMN_NAME = "name";
    }
}
