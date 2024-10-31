package com.example.estoquefarma;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;
import com.example.estoquefarma.CadastrarProdutos.Produto;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Produtos extends AppCompatActivity {

    private ListView listViewProdutos;
    private CheckBox checkBoxMedicamentos;
    private CheckBox checkBoxHigiene;
    private CheckBox checkBoxFitness;
    private ArrayAdapter<String> adapter;
    private List<CadastrarProdutos.Produto> listaDeProdutos;
    private List<String> produtosFiltrados;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        listViewProdutos = findViewById(R.id.listView);
        checkBoxMedicamentos = findViewById(R.id.checkMedicamentos);
        checkBoxFitness = findViewById(R.id.checkFitness);
        checkBoxHigiene = findViewById(R.id.checkHigiene);
        Button buttonFiltrar = findViewById(R.id.filtro);

        database = new Database(this);

        listaDeProdutos = carregarProdutosDoBanco();

        if(listaDeProdutos.isEmpty()){
            Toast.makeText(this, "Nenhum produto disponível", Toast.LENGTH_SHORT).show();
        }

        produtosFiltrados = new ArrayList<>();
        for (CadastrarProdutos.Produto produto : listaDeProdutos){
            produtosFiltrados.add(produto.getNome());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtosFiltrados);
        listViewProdutos.setAdapter(adapter);

        buttonFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aplicarFiltro();
            }
        });
    }

    private List<CadastrarProdutos.Produto> carregarProdutosDoBanco() {
        List<CadastrarProdutos.Produto> produtos = new ArrayList<>();
        Cursor cursor = database.listarProdutos();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                double valor = cursor.getDouble(cursor.getColumnIndex("valor"));
                int quantidade = cursor.getInt(cursor.getColumnIndex("quantidade"));
                String categoria = cursor.getString(cursor.getColumnIndex("categoria"));

                CadastrarProdutos.Produto produto = new CadastrarProdutos.Produto(nome, valor, quantidade, categoria);
                produtos.add(produto);
            }
            cursor.close();
        }

        return produtos;
    }

    private void aplicarFiltro() {
        produtosFiltrados.clear();

        for (CadastrarProdutos.Produto produto : listaDeProdutos) {
            String categoria = produto.getCategoria();

            if ((checkBoxMedicamentos.isChecked() && categoria.equals("Medicamentos e Saude")) ||
                    (checkBoxFitness.isChecked() && categoria.equals("Fitness e Nutricao")) ||
                    (checkBoxHigiene.isChecked() && categoria.equals("Higiene e Beleza"))) {

                produtosFiltrados.add(produto.getNome());
            }
        }

        adapter.notifyDataSetChanged();
    }
}