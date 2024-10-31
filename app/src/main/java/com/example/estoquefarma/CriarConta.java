package com.example.estoquefarma;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CriarConta extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        Button button = findViewById(R.id.criarConta);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(CriarConta.this,"Por favor preeencha todos os campos",Toast.LENGTH_SHORT).show();
                }else{
                    Usuario novoUsuario = new Usuario(email, password);
                    listaUsuarios.add(novoUsuario);

                    Toast.makeText(CriarConta.this,"Conta cadastrada com sucesso",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CriarConta.this, TelaInicial.class);
                    startActivity(intent);
                }
            }
        });
    }


    public static ArrayList<Usuario> getListaUsuarios(){
        return listaUsuarios;
    }
}