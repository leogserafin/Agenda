package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.collection.ArraySet;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.agenda.database.DBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static DBHelper db;
    private static ArrayList<String> listaAgenda;
    private ListView lvAgenda;
    private AppCompatButton btn_add;
    private AgendaAdicao agendaAdicao = new AgendaAdicao();
    private AgendaEdicao agendaEdicao = new AgendaEdicao();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        IniciarComponentes();

        db=new DBHelper(this);

        listaAgenda = new ArrayList<String>();

        listarContatos();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listaAgenda
        );

	   lvAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                ft.replace(R.id.MainFrameLayout, agendaEdicao).commit();
            }
        });

        lvAgenda.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.MainFrameLayout, agendaAdicao).commit();
            }
        });
    }

    public static void listarContatos() {
        Cursor c = db.SelectAll_Contato();
        c.moveToFirst();
        if(c.getCount()>0){
            listaAgenda.clear();
            do{
                int id = c.getInt(c.getColumnIndex("id"));
                String nome = c.getString(c.getColumnIndex("nome"));
                String tel = c.getString(c.getColumnIndex("tel"));
                String tipo = c.getString(c.getColumnIndex("tipo"));

                listaAgenda.add(nome);
            }while(c.moveToNext());
        }
    }

    private void IniciarComponentes(){
        lvAgenda = findViewById(R.id.listViewAgenda);
        btn_add = findViewById(R.id.btnCadastrar);
    }
}