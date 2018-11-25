package com.example.thaiscontreras.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private ArrayList<Nota> notas;
    private ListView listNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        notas = new ArrayList<>();
        notas.add(new Nota("Hola","klk"));
        notas.add(new Nota("jeje","sdadsa"));

        listNotes = (ListView)findViewById(R.id.ListNotes);
        listNotes.setAdapter(new ArrayAdapter<>(
                    this,android.R.layout.simple_list_item_1, notas
        ));


    }

}
