package com.example.estoquefarma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        TextView textView = findViewById(R.id.textView3);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this,"Por favor preencha todos os campos",Toast.LENGTH_SHORT).show();
                }else{
                    if(verificarCredenciais(email, password)) {
                        Intent intent = new Intent(MainActivity.this, TelaInicial.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this,"Dados inválidos",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, CriarConta.class);
                startActivity(intent);
            }
        });
    }

    private boolean verificarCredenciais(String email, String password){
        ArrayList<Usuario> listaUsuarios = CriarConta.getListaUsuarios(); // Certifique-se de que este método está implementado em CriarConta.

        for (Usuario usuario : listaUsuarios){
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
