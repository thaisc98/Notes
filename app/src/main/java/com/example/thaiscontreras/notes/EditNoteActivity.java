package com.example.thaiscontreras.notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {

    private Nota original;
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
            original = ListNote.getNote(position);
            editTitulo.setText(original.getTitulo());
            editText.setText(original.getTexto());

        }else{

            original = new Nota();
        }
    }

    private boolean Cambios(){

        String titulo = editTitulo.getText().toString();
        String texto = editText.getText().toString();
        return !original.getTitulo().equals(titulo) || !original.getTexto().equals(texto);

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

    @Override
    public void onBackPressed() {

        if(Cambios()){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.confirm);
            builder.setMessage(R.string.aviso);
            builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditNoteActivity.super.onBackPressed();
                }
            });
            builder.setNegativeButton(R.string.continuar,null);
            builder.create().show();

        }else{

            super.onBackPressed();
        }


    }
}
