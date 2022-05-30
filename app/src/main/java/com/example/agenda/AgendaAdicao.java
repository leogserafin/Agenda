package com.example.agenda;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agenda.database.DBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgendaAdicao#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaAdicao extends Fragment {

    DBHelper db;
    private EditText et_nome, et_telefone;
    private Spinner sp_tipo;
    private AppCompatButton button_Voltar;
    private AppCompatButton button_Cadastrar;

    //    public AgendaAdicao() {
//        // Required empty public constructor
//    }
    public static AgendaAdicao newInstance(String param1, String param2) {
        AgendaAdicao fragment = new AgendaAdicao();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agenda_adicao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IniciarComponentes(view);

        db = new DBHelper(this.getContext());

        button_Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideFragment();
            }
        });

        button_Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = et_nome.getText().toString();
                String telefone = et_telefone.getText().toString();
                String tipo = sp_tipo.getSelectedItem().toString();
                if(nome.isEmpty() || telefone.isEmpty()){
                    Toast toast = Toast.makeText(getContext(), "Preencha os campos", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    db.Insert_Contato(nome, telefone, tipo);
                    MainActivity.listarContatos();
                    hideFragment();
                    et_nome.setText("");
                    et_telefone.setText("");
                }
            }
        });
    }


    private void hideFragment () {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.remove(this).commit();
    }

    private void IniciarComponentes(@NonNull View view){
        et_nome = view.findViewById(R.id.CContatoEditTextNome);
        et_telefone = view.findViewById(R.id.CContatoEditTextTelefone);
        sp_tipo = view.findViewById(R.id.spinner);
        button_Voltar = view.findViewById(R.id.btnCadastrarVoltar);
        button_Cadastrar = view.findViewById(R.id.btnCadastrarCadastrar);
    }
}