package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvAgenda;
    private FloatingActionButton btn_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        IniciarComponentes();

        ArrayList<String> listaAgenda = new ArrayList<>();

        listaAgenda.add("Bruno");
        listaAgenda.add("Leonardo");
        listaAgenda.add("Marco");
        listaAgenda.add("Matheus");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listaAgenda
        );

        lvAgenda.setAdapter(adapter);
    }

    private void IniciarComponentes(){
        lvAgenda = findViewById(R.id.listViewAgenda);
        btn_add = findViewById(R.id.floatingActionButtonAdd);
    }
}