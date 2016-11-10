package com.mobile.converter;

import com.mobile.entity.Peixe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mrluke on 13/10/2016.
 */

public class JsonConverter {

    public static List<Peixe> converteJson(String json) throws JSONException{

        List<Peixe> listaPeixes = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject objectJson = (JSONObject) jsonArray.get(i);
            String nome = objectJson.getString("nome");
            String caminhoFoto = objectJson.getString("fotoPeixe");
            String nomePeixeCientifico = objectJson.getString("nomeCientifico");
            String descricaoPeixe = objectJson.getString("descricao");
            String alimentacao = objectJson.getString("alimentacao");
            String phPeixe = objectJson.getString("ph");
            String temperaturaPeixe = objectJson.getString("temperatura");
            String tamanhoPeixe = objectJson.getString("tamanho");
            String posicaoNoAquarioPeixe = objectJson.getString("posicaoNoAquario");
            String tipoPeixe = objectJson.getString("tipo");
            String statusPeixe = objectJson.getString("status");

            listaPeixes.add(new Peixe(nome, caminhoFoto, nomePeixeCientifico, descricaoPeixe,
                    alimentacao, phPeixe, temperaturaPeixe, tamanhoPeixe, posicaoNoAquarioPeixe, tipoPeixe, statusPeixe));
        }
        return listaPeixes;
    }
}
