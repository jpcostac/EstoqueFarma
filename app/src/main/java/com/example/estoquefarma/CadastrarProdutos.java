package com.example.estoquefarma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;

public class CadastrarProdutos extends AppCompatActivity {

    private EditText nomeProduto, valorProduto, quantidade;
    private Spinner spinnerCategoria;
    private Button cadastrarProduto;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produtos);

        nomeProduto = findViewById(R.id.nomeProduto);
        valorProduto = findViewById(R.id.valorProduto);
        quantidade = findViewById(R.id.quantidade);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        cadastrarProduto = findViewById(R.id.cadastrarProduto);

        database = new Database(this);

        cadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = nomeProduto.getText().toString();
                String valorStr = valorProduto.getText().toString();
                String qtdStr = quantidade.getText().toString();

                if (nome.isEmpty() || valorStr.isEmpty() || qtdStr.isEmpty()){
                    Toast.makeText(CadastrarProdutos.this, "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
                    return;
                }

                double valor = Double.parseDouble(valorStr);
                int qtd = Integer.parseInt(qtdStr);
                String categoria = spinnerCategoria.getSelectedItem().toString();

                boolean sucesso = database.inserirProduto(nome, valor, qtd, categoria);

                if(sucesso){
                    Toast.makeText(CadastrarProdutos.this, "Produto cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    nomeProduto.setText("");
                    valorProduto.setText("");
                    quantidade.setText("");
                }
                Intent intent = new Intent(CadastrarProdutos.this, Produtos.class);
                startActivity(intent);
            }
        });
    }

    public class Produto implements Serializable{
        private String nome;
        private double valor;
        private int quantidade;
        private String categoria;

        // Construtor e métodos get/set
        public Produto(String nome, double valor, int quantidade, String categoria) {
            this.nome = nome;
            this.valor = valor;
            this.quantidade = quantidade;
            this.categoria = categoria;
        }
        public String getNome() {
            return nome;
        }

        public double getValor() {
            return valor;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public String getCategoria() {
            return categoria;
        }
    }
}