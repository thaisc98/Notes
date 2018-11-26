package com.example.thaiscontreras.notes;

import android.content.Context;

import java.util.ArrayList;

public class ListNote {

    private static ArrayList<Nota> notas;


    public static ArrayList<Nota> get(Context context) {
        if(notas == null) {
            notas = NotesDB.loadNotes(context);
        }
        return notas;
    }

    public static void nueva(String titulo, String texto) {
        Nota nota = new Nota(titulo, texto);
        notas.add(nota);
    }

    public static void modify(int position, String titulo, String texto) {
        Nota nota = notas.get(position);
        nota.setTitulo(titulo);
        nota.setTexto(texto);
    }

    public static Nota getNote(int position){
        return notas.get(position);
    }
}
