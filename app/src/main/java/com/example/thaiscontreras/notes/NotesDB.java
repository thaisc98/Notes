package com.example.thaiscontreras.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class NotesDB {

    private static Context  context;

    public static void setContext(Context context){

         NotesDB.context = context;

    }


    static class NotesDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "notes.db";

        private static final int DATABASE_VERSION = 1;

        private static final String SQL_CREATE_TABLA_NOTES;

        static {
            SQL_CREATE_TABLA_NOTES = "CREATE TABLE Notes (" +
                    "id INTEGER PRIMARY KEY," +
                    "titulo TEXT," +
                    "texto TEXT" +
                    ")";
        }


        public NotesDbHelper(){
            super(context,DATABASE_NAME,null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLA_NOTES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // no hay que hacer nada aqui..
        }

    }

    private static NotesDbHelper helper;

    public static NotesDbHelper  getHelper(){

        if(helper == null){
            helper = new NotesDbHelper();
        }

        return helper;
    }

     public static ArrayList<Nota> loadNotes(){
         ArrayList<Nota> resultado = new ArrayList<>();



        SQLiteDatabase db = getHelper().getReadableDatabase();
         String[] columnas = { "id","titulo","texto" };

         Cursor c = db.query(
                 "Notes",columnas,null,null,null,null,
                 null);

         if(c != null && c.getCount() > 0 ){
             while(c.moveToNext()){
                 long id = c.getLong(c.getColumnIndexOrThrow("id"));
                 String titulo = c.getString(c.getColumnIndexOrThrow("titulo"));
                 String texto = c.getString(c.getColumnIndexOrThrow("texto"));
                 resultado.add(new Nota(id,titulo,texto));

             }
         }

         if(c != null){
             c.close();
         }
         db.close();

        return resultado;
     }

    public static Nota nueva(String titulo, String texto) {
         Nota resultado = new Nota(titulo, texto);

         SQLiteDatabase db = getHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Titulo", titulo);
        values.put("texto", texto);
        long id = db.insert("Notes", null, values);
        db.close();

        resultado.setId(id);
        return resultado;
    }

    public static void actualiza(Nota nota) {


        SQLiteDatabase db = getHelper().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Titulo", nota.getTitulo());
        values.put("texto", nota.getTexto());

        String where = "id = ?";
        String [] args = {Long.toString(nota.getId())};

        db.update("Notes", values, where, args);
        db.close();

    }

    public static void borrar (Nota nota){

        SQLiteDatabase db = getHelper().getWritableDatabase();

        String where = "id = ?";
        String [] args = {Long.toString(nota.getId())};

        db.delete("Notes", where, args);
        db.close();
    }


}
