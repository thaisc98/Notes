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
        if(intent.hasExtra("titulo")){
            editTitulo.setText(intent.getStringExtra("titulo"));
            editText.setText(intent.getStringExtra("texto"));
            position  = intent.getIntExtra("position",-1);
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
               Intent data = new Intent();
               data.putExtra("titulo",editTitulo.getText().toString());
               data.putExtra("texto",editText.getText().toString());
               setResult(RESULT_OK,data);
               if(position != -1){
                   data.putExtra("position",position);
               }
               finish();
                return true;

           default:
               return super.onOptionsItemSelected(item);
       }

    }
}
