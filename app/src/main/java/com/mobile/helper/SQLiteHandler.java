package com.mobile.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobile.entity.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mrluke on 25/10/2016.
 */

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = "habitatdb";

    // Tabela de Usuários.
    private static final String NOME_TABELA = "usuarios";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_EMAIL = "email";
    private static final String COLUNA_UID = "id_unico";
    private static final String COLUNA_DATA_DE_CRIACAO = "data_de_criacao";

    // Tabela de Produtos.
    private static final String NOME_TABELA_PRODUTOS = "produtos";
    private static final String COLUNA_ID_PRODUTO = "id";
    private static final String COLUNA_ITEM_PRODUTO = "item";
    private static final String COLUNA_QUANTIDADE_PRODUTO = "quantidade";

    private static final String SQL_SELECT_ALL = "SELECT * FROM produtos";
    private String nomeItem;
    private int qtdItem;

    public SQLiteHandler(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SCRIPT_SQL = "CREATE TABLE " + NOME_TABELA + "("
                + COLUNA_ID + " INTEGER PRIMARY KEY,"
                + COLUNA_NOME + " TEXT,"
                + COLUNA_EMAIL + " TEXT UNIQUE,"
                + COLUNA_UID + " TEXT,"
                + COLUNA_DATA_DE_CRIACAO + " TEXT" + ")";

        db.execSQL(SCRIPT_SQL);

        String SCRIPT_SQL_PRODUTOS = "CREATE TABLE " + NOME_TABELA_PRODUTOS + "("
                + COLUNA_ID_PRODUTO + " INTEGER PRIMARY KEY,"
                + COLUNA_ITEM_PRODUTO + " TEXT,"
                + COLUNA_QUANTIDADE_PRODUTO + " INTEGER" + ")";

        db.execSQL(SCRIPT_SQL_PRODUTOS);

        Log.d(TAG, "Tabela criada no banco.");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA_PRODUTOS);

        // Create tables again
        onCreate(db);
    }

    public void salvarUsuario(String nome, String email, String idUnico, String data_de_criacao) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, nome);
        values.put(COLUNA_EMAIL, email);
        values.put(COLUNA_UID, idUnico);
        values.put(COLUNA_DATA_DE_CRIACAO, data_de_criacao);

        long id = db.insert(NOME_TABELA, null, values);
        db.close();

        Log.d(TAG, "Usuário salvo no banco de dados: " + id);
    }

    public HashMap<String, String> getUsuarioDetalhes() {

        HashMap<String, String> usuario = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + NOME_TABELA;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            usuario.put("nome", cursor.getString(1));
            usuario.put("email", cursor.getString(2));
            usuario.put("id_unico", cursor.getString(3));
            usuario.put("data_de_criacao", cursor.getString(4));
        }
        cursor.close();
        db.close();

        Log.d(TAG, "Usuário encontrado: " + usuario.toString());
        return usuario;
    }

    public void deletarUsuarios() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(NOME_TABELA, null, null);
        db.close();

        Log.d(TAG, "Deletado todos os dados do banco");
    }

    public void salvarProdutos(String item, int quantidade){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_ITEM_PRODUTO, item);
        values.put(COLUNA_QUANTIDADE_PRODUTO, quantidade);

        long id = db.insert(NOME_TABELA_PRODUTOS, null, values);
        db.close();

        Log.d(TAG, "Item Salvo com sucesso! " + id);
    }

    public ArrayList<Item> getAllItens(){

        ArrayList<Item> itens = new ArrayList<Item>();
        Item item;
        String selectQuery = "SELECT  * FROM " + NOME_TABELA_PRODUTOS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_SELECT_ALL, null);

        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            item = new Item(cursor.getString(1), Integer.parseInt(cursor.getString(2)));
            itens.add(item);
        }
        cursor.close();
        db.close();

        return itens;
    }

    public void deletarProdutos() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(NOME_TABELA_PRODUTOS, null, null);
        db.close();

        Log.d(TAG, "Deletado todos os itens do orçamento");
    }
}
