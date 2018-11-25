package com.example.thaiscontreras.notes;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    private NotasAdapter Adapter;
    private ArrayList<Nota> notas;
    private ListView listNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        notas = new ArrayList<>();
        notas.add(new Nota("Hola","klkio"));
        notas.add(new Nota("jeje","sdadsa"));
        Adapter = new NotasAdapter();

        listNotes = (ListView)findViewById(R.id.ListNotes);
        listNotes.setAdapter(Adapter);
    }
    private class NotasAdapter extends ArrayAdapter<Nota>{
        NotasAdapter() {
            super(NotesActivity.this,R.layout.item_lista_notas,notas);
        }

        @NonNull
        @Override
        public View getView(int position,View convertView, ViewGroup parent) {
            View result = convertView;
            if(result == null){
                LayoutInflater inflater = getLayoutInflater();
                result =inflater.inflate(R.layout.item_lista_notas,parent,false);
            }

            Nota nota = getItem(position);
            TextView titulo = (TextView) result.findViewById(R.id.titulo);
            titulo.setText(nota.getTitulo());
            TextView resumen = (TextView) result.findViewById(R.id.resumen);
            resumen.setText(nota.getTexto());

            return result;
        }
    }


}
