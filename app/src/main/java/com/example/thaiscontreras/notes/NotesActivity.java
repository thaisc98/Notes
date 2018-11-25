package com.example.thaiscontreras.notes;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    public static final int NUEVA_NOTA = 0;
    public static final int EDIT_NOTA = 1;
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

        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onEditNote(position);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch(requestCode) {
            case NUEVA_NOTA:
                if(resultCode == RESULT_OK){
                    String titulo = data.getStringExtra("titulo");
                    String texto = data.getStringExtra("texto");
                    Nota nota = new Nota(titulo, texto);
                    notas.add(nota);
                    Adapter.notifyDataSetChanged();
                }
                break;

            case EDIT_NOTA:
                if(resultCode == RESULT_OK){
                    String titulo =  data.getStringExtra("titulo");
                    String texto = data.getStringExtra("texto");
                    int position = data.getIntExtra("position",-1);
                    Nota nota = notas.get(position);
                    nota.setTitulo(titulo);
                    nota.setTexto(texto);
                    Adapter.notifyDataSetChanged();
                }
                break;

                default:
                    super.onActivityResult(requestCode, resultCode, data);
        }


    }

    private void onEditNote(int position) {
        Nota nota = notas.get(position);
        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra("titulo",nota.getTitulo());
        intent.putExtra("texto",nota.getTexto());
        intent.putExtra("position",position);
        startActivityForResult(intent,EDIT_NOTA);
    }


    public void onNuevaNota(View view) {
        Intent intent = new Intent(this, EditNoteActivity.class);
        startActivityForResult(intent, NUEVA_NOTA);
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
