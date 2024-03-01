package com.ugb.controlesbasicosugb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    private static final String dbname="Amigos";
    private static final int v=1;
    private static final String SQLdb = "CREATE TABLE amigos (idAmigo int primary key autoincrement, Nombre text, Dirección text, Telefono text," +
            "Email text, Dui text)";

    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, v);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sqLiteDatabase.execSQL(SQLdb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //actualizar la estructura de la base de datos
    }

    public String admininistrar_Amigos(String accion, String[] datos ){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "";
            if (accion == "nuevo") {
                sql = "INSERT INTO Amigos (Nombre, Direccion, Telefono, Email, Dui) Values ('" + datos[1] + "','" + datos[2] + "', '" + datos[3] + "', '" + datos[4] + "', '" + datos[5] + "')";
            } else if (accion == "modificar") {
                sql = "UPDATE Amigos Set Nombre='" + datos[1] + "',Direccion='" + datos[2] + "',Telefono='" + datos[3] + "',Email='" + datos[4] + "',Dui='" + datos[5] + "'WHERE IdAmigos'" + datos[0] + "'";

            } else if (accion == "eliminar") {
                sql = "DELETE FROM Amigos WHERE IdAmigos'" + datos[0] + "'";
            }
            db.execSQL(sql);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
