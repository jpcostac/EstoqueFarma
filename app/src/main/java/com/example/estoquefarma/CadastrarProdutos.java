package com.example.estoquefarma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
    private ArrayList<Produto> listaDeProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produtos);

        nomeProduto = findViewById(R.id.nomeProduto);
        valorProduto = findViewById(R.id.valorProduto);
        quantidade = findViewById(R.id.quantidade);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        cadastrarProduto = findViewById(R.id.cadastrarProduto);
        listaDeProdutos = new ArrayList<>();

        cadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = nomeProduto.getText().toString();
                double valor = Double.parseDouble(valorProduto.getText().toString());
                int qtd = Integer.parseInt(quantidade.getText().toString());
                String categoria = spinnerCategoria.getSelectedItem().toString();


                Produto produto = new Produto(nome, valor, qtd, categoria);
                listaDeProdutos.add(produto);

                Intent intent = new Intent(CadastrarProdutos.this, Produtos.class);
                intent.putExtra("listaDeProdutos", listaDeProdutos);
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