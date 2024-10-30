package com.example.estoquefarma;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        listViewProdutos = findViewById(R.id.listView);
        checkBoxMedicamentos = findViewById(R.id.checkMedicamentos);
        checkBoxFitness = findViewById(R.id.checkFitness);
        checkBoxHigiene = findViewById(R.id.checkHigiene);
        Button buttonFiltrar = findViewById(R.id.filtro);

        listaDeProdutos = (ArrayList<CadastrarProdutos.Produto>) getIntent().getSerializableExtra("listaDeProdutos");
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

    private void aplicarFiltro(){
        produtosFiltrados.clear();

        for(CadastrarProdutos.Produto produto : listaDeProdutos){
            String categoria = produto.getCategoria();

            if((checkBoxMedicamentos.isChecked() && categoria.equals("Medicamentos e Saude")) ||
            (checkBoxFitness.isChecked() && categoria.equals("Fitness e Nutricao")) ||
            (checkBoxHigiene.isChecked() && categoria.equals("Higiene e Beleza"))){

                produtosFiltrados.add(produto.getNome());
            }
        }

        adapter.notifyDataSetChanged();

    }
}