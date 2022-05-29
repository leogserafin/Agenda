package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvAgenda;
    private AppCompatButton btn_add;
    private AgendaAdicao agendaAdicao = new AgendaAdicao();


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

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.MainFrameLayout, agendaAdicao).commit();
            }
        });
    }

    private void IniciarComponentes(){
        lvAgenda = findViewById(R.id.listViewAgenda);
        btn_add = findViewById(R.id.btnCadastrar);
    }
}