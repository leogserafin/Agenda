package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.agenda.database.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvAgenda;
    private FloatingActionButton btn_add;
    private DBHelper db;
    private ArrayList<String> listaAgenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        IniciarComponentes();

        db=new DBHelper(this);

        listaAgenda = new ArrayList<>();

       listarContatos();

       /*
        listaAgenda.add("Bruno");
        listaAgenda.add("Leonardo");
        listaAgenda.add("Marco");
        listaAgenda.add("Matheus");
*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listaAgenda
        );

        lvAgenda.setAdapter(adapter);
    }

    private void listarContatos() {
        Cursor c = db.SelectAll_Contato();
        c.moveToFirst();
        if(c.getCount()>0){
            listaAgenda.clear();
            do{
                int id = c.getInt(c.getColumnIndex("id"));
                String nome = c.getString(c.getColumnIndex("nome"));
                String tel = c.getString(c.getColumnIndex("tel"));
                int tipo = c.getInt(c.getColumnIndex("tipo"));

                listaAgenda.add(nome);
            }while(c.moveToNext());
        }
    }

    private void IniciarComponentes(){
        lvAgenda = findViewById(R.id.listViewAgenda);
        btn_add = findViewById(R.id.floatingActionButtonAdd);
    }
}