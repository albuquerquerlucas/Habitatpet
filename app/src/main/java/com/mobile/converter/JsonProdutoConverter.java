package com.mobile.converter;

import com.mobile.entity.Produto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mrluke on 13/10/2016.
 */

public class JsonProdutoConverter {

    public static List<Produto> converteJson(String json) throws JSONException{

        List<Produto> listaProdutos = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject objectJson = (JSONObject) jsonArray.get(i);
            String nomeProduto = objectJson.getString("nome");
            String referencia = objectJson.getString("referencia");
            String descricaoProduto = objectJson.getString("descricao");
            String marca = objectJson.getString("marca");
            String modelo = objectJson.getString("modelo");
            String statusProduto = objectJson.getString("status");
            String caminhoFotoProduto = objectJson.getString("fotoProduto");

            listaProdutos.add(new Produto(nomeProduto, referencia, descricaoProduto, marca, modelo,
                    statusProduto, caminhoFotoProduto));
        }
        return listaProdutos;
    }
}
