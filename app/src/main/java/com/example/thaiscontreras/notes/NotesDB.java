package com.example.thaiscontreras.notes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Notation;

import java.util.ArrayList;

public class NotesDB {

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


        public NotesDbHelper(Context context){
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

     public static ArrayList<Nota> loadNotes(Context context){
         ArrayList<Nota> resultado = new ArrayList<>();

         if(helper == null){
             helper = new NotesDbHelper(context);
         }

        SQLiteDatabase db = helper.getReadableDatabase();
         String[] columnas = { "id","titulo","texto" };

         Cursor c = db.query(
                 "Notes",columnas,null,null,null,null,
                 null);

         if(c != null && c.getCount() > 0 ){
             while(c.moveToNext()){
                // long id = c.getLong(c.getColumnIndexOrThrow("id"));
                 String titulo = c.getString(c.getColumnIndexOrThrow("titulo"));
                 String texto = c.getString(c.getColumnIndexOrThrow("texto"));
                 resultado.add(new Nota(titulo,texto));

             }
         }

         if(c != null){
             c.close();
         }
         db.close();

        return resultado;
     }


}
