package com.example.estoquefarma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ProdutosDB";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTableProduto = "CREATE TABLE produto(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, valor DOUBLE, quantidade INT, categoria TEXT)";

        db.execSQL(createTableProduto);
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS produto");
        onCreate(db);
    }

    public boolean inserirProduto(String nome, Double valor, int quantidade, String categoria){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long resultado = 0;
        values.put("nome", nome);
        values.put("valor", valor);
        values.put("quantidade", quantidade);
        values.put("categoria", categoria);

        try{
            int id = (int) db.insert("produto", null, values);
            if(id==-1 && this.inserirProduto(nome, valor, quantidade, categoria) == false){
                resultado=-1;
            }
        }catch (SQLException e){
            Log.e("Erro", e.getMessage());
        }finally {
            db.close();
        }
        return resultado != -1;
    }

    public Cursor listarProdutos(){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("SELECT * FROM produto", null);
    }
}
