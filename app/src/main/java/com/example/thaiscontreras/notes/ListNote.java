package com.example.thaiscontreras.notes;

import java.util.ArrayList;

public class ListNote {

    private static ArrayList<Nota> notas;


    public static ArrayList<Nota> get() {
        if(notas == null) {
            notas = new ArrayList<>();
            notas.add(new Nota("Hola", "klkio"));
            notas.add(new Nota("jeje", "sdadsa"));
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
