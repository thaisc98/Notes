package com.example.thaiscontreras.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {

    private int position = -1;
    private EditText editTitulo;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editTitulo = (EditText)findViewById(R.id.editTitulo);
        editText = (EditText)findViewById(R.id.editText);

        Intent intent = getIntent();
        position = intent.getIntExtra("position",-1);
        if(position != -1){
            Nota nota = ListNote.getNote(position);
            editTitulo.setText(nota.getTitulo());
            editText.setText(nota.getTexto());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_note_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch(item.getItemId()){
           case R.id.guardar:
               String titulo = editTitulo.getText().toString();
               String texto = editText.getText().toString();

               if(position != -1){
                  ListNote.modify(position,titulo,texto);
               } else {
                   ListNote.nueva(titulo,texto);
               }
               setResult(RESULT_OK);
               finish();
                return true;

           default:
               return super.onOptionsItemSelected(item);
       }

    }
}
