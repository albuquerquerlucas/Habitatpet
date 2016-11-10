package com.mobile.entity;

import java.io.Serializable;

/**
 * Created by Mrluke on 13/10/2016.
 */

public class Peixe implements Serializable {

    private String nome;
    private String caminhoFoto;
    private String nomePeixeCientifico;
    private String descricaoPeixe;
    private String alimentacao;
    private String phPeixe;
    private String temperaturaPeixe;
    private String tamanhoPeixe;
    private String posicaoNoAquarioPeixe;
    private String tipoPeixe;
    private String statusPeixe;

    public Peixe(String nome, String caminhoFoto, String nomePeixeCientifico, String descricaoPeixe,
                 String alimentacao, String phPeixe, String temperaturaPeixe, String tamanhoPeixe,
                 String posicaoNoAquarioPeixe, String tipoPeixe, String statusPeixe) {
        this.nome = nome;
        this.caminhoFoto = caminhoFoto;
        this.nomePeixeCientifico = nomePeixeCientifico;
        this.descricaoPeixe = descricaoPeixe;
        this.alimentacao = alimentacao;
        this.phPeixe = phPeixe;
        this.temperaturaPeixe = temperaturaPeixe;
        this.tamanhoPeixe = tamanhoPeixe;
        this.posicaoNoAquarioPeixe = posicaoNoAquarioPeixe;
        this.tipoPeixe = tipoPeixe;
        this.statusPeixe = statusPeixe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomePeixeCientifico() {
        return nomePeixeCientifico;
    }

    public void setNomePeixeCientifico(String nomePeixeCientifico) {
        this.nomePeixeCientifico = nomePeixeCientifico;
    }

    public String getDescricaoPeixe() {
        return descricaoPeixe;
    }

    public void setDescricaoPeixe(String descricaoPeixe) {
        this.descricaoPeixe = descricaoPeixe;
    }

    public String getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(String alimentacao) {
        this.alimentacao = alimentacao;
    }

    public String getPhPeixe() {
        return phPeixe;
    }

    public void setPhPeixe(String phPeixe) {
        this.phPeixe = phPeixe;
    }

    public String getTemperaturaPeixe() {
        return temperaturaPeixe;
    }

    public void setTemperaturaPeixe(String temperaturaPeixe) {
        this.temperaturaPeixe = temperaturaPeixe;
    }

    public String getTamanhoPeixe() {
        return tamanhoPeixe;
    }

    public void setTamanhoPeixe(String tamanhoPeixe) {
        this.tamanhoPeixe = tamanhoPeixe;
    }

    public String getPosicaoNoAquarioPeixe() {
        return posicaoNoAquarioPeixe;
    }

    public void setPosicaoNoAquarioPeixe(String posicaoNoAquarioPeixe) {
        this.posicaoNoAquarioPeixe = posicaoNoAquarioPeixe;
    }

    public String getTipoPeixe() {
        return tipoPeixe;
    }

    public void setTipoPeixe(String tipoPeixe) {
        this.tipoPeixe = tipoPeixe;
    }

    public String getStatusPeixe() {
        return statusPeixe;
    }

    public void setStatusPeixe(String statusPeixe) {
        this.statusPeixe = statusPeixe;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
