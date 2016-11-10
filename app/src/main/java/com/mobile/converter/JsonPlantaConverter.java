package com.mobile.converter;

import com.mobile.entity.Planta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mrluke on 13/10/2016.
 */

public class JsonPlantaConverter {

    public static List<Planta> converteJson(String json) throws JSONException{

        List<Planta> listaPlantas = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject objectJson = (JSONObject) jsonArray.get(i);
            String nomePlanta = objectJson.getString("nome");
            String nomeCientificoPlanta = objectJson.getString("nomeCientifico");
            String crescimento = objectJson.getString("crescimento");
            String plantio = objectJson.getString("plantio");
            String posicaoPlanta = objectJson.getString("posicaoNoAquario");
            String phPlanta = objectJson.getString("ph");
            String temperaturaPlanta = objectJson.getString("temperatura");
            String tamanhoPlanta = objectJson.getString("tamanho");
            String substrato = objectJson.getString("substratoFertil");
            String co2 = objectJson.getString("co2");
            String emersao = objectJson.getString("suportaEmersao");
            String statusPlanta = objectJson.getString("status");
            String caminhoFotoPlanta = objectJson.getString("fotoPlanta");

            listaPlantas.add(new Planta(nomePlanta, nomeCientificoPlanta, crescimento, plantio, posicaoPlanta, phPlanta, temperaturaPlanta,
                    tamanhoPlanta, substrato, co2, emersao, statusPlanta, caminhoFotoPlanta));
        }
        return listaPlantas;
    }
}
