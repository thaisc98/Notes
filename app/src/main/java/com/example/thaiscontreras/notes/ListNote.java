package com.example.thaiscontreras.notes;

import android.content.Context;

import java.util.ArrayList;

public class ListNote {

    private static ArrayList<Nota> notas;


    public static ArrayList<Nota> get() {
        if(notas == null) {
            notas = NotesDB.loadNotes();
        }
        return notas;
    }

    public static Nota getNote(int position){
        return notas.get(position);
    }

    public static void nueva(String titulo, String texto) {
        Nota nota = NotesDB.nueva(titulo,texto);
        notas.add(nota);
    }

    public static void modify(int position, String titulo, String texto) {
        Nota nota = notas.get(position);
        nota.setTitulo(titulo);
        nota.setTexto(texto);
        NotesDB.actualiza(nota);

    }



    public static void borrar(int position) {

        Nota nota = notas.get(position);
        NotesDB.borrar(nota);
        notas.remove(position);

    }
}
