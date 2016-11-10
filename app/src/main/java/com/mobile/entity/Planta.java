package com.mobile.entity;

import java.io.Serializable;

/**
 * Created by Mrluke on 14/10/2016.
 */

public class Planta implements Serializable {

    private String nomePlanta;
    private String nomeCientificoPlanta;
    private String crescimento;
    private String plantio;
    private String posicaoPlanta;
    private String phPlanta;
    private String temperaturaPlanta;
    private String tamanhoPlanta;
    private String substrato;
    private String co2;
    private String emersao;
    private String statusPlanta;
    private String caminhoFotoPlanta;

    public Planta(String nomePlanta, String nomeCientificoPlanta, String crescimento, String plantio, String posicaoPlanta,
                  String phPlanta, String temperaturaPlanta, String tamanhoPlanta, String substrato,
                  String co2, String emersao, String statusPlanta, String caminhoFotoPlanta) {
        this.nomePlanta = nomePlanta;
        this.nomeCientificoPlanta = nomeCientificoPlanta;
        this.crescimento = crescimento;
        this.plantio = plantio;
        this.posicaoPlanta = posicaoPlanta;
        this.phPlanta = phPlanta;
        this.temperaturaPlanta = temperaturaPlanta;
        this.tamanhoPlanta = tamanhoPlanta;
        this.substrato = substrato;
        this.co2 = co2;
        this.emersao = emersao;
        this.statusPlanta = statusPlanta;
        this.caminhoFotoPlanta = caminhoFotoPlanta;
    }

    public String getNomePlanta() {
        return nomePlanta;
    }

    public void setNomePlanta(String nomePlanta) {
        this.nomePlanta = nomePlanta;
    }

    public String getNomeCientificoPlanta() {
        return nomeCientificoPlanta;
    }

    public void setNomeCientificoPlanta(String nomeCientificoPlanta) {
        this.nomeCientificoPlanta = nomeCientificoPlanta;
    }

    public String getCrescimento() {
        return crescimento;
    }

    public void setCrescimento(String crescimento) {
        this.crescimento = crescimento;
    }

    public String getPlantio() {
        return plantio;
    }

    public void setPlantio(String plantio) {
        this.plantio = plantio;
    }

    public String getPosicaoPlanta() {
        return posicaoPlanta;
    }

    public void setPosicaoPlanta(String posicaoPlanta) {
        this.posicaoPlanta = posicaoPlanta;
    }

    public String getPhPlanta() {
        return phPlanta;
    }

    public void setPhPlanta(String phPlanta) {
        this.phPlanta = phPlanta;
    }

    public String getTemperaturaPlanta() {
        return temperaturaPlanta;
    }

    public void setTemperaturaPlanta(String temperaturaPlanta) {
        this.temperaturaPlanta = temperaturaPlanta;
    }

    public String getTamanhoPlanta() {
        return tamanhoPlanta;
    }

    public void setTamanhoPlanta(String tamanhoPlanta) {
        this.tamanhoPlanta = tamanhoPlanta;
    }

    public String getSubstrato() {
        return substrato;
    }

    public void setSubstrato(String substrato) {
        this.substrato = substrato;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getEmersao() {
        return emersao;
    }

    public void setEmersao(String emersao) {
        this.emersao = emersao;
    }

    public String getStatusPlanta() {
        return statusPlanta;
    }

    public void setStatusPlanta(String statusPlanta) {
        this.statusPlanta = statusPlanta;
    }

    public String getCaminhoFotoPlanta() {
        return caminhoFotoPlanta;
    }

    public void setCaminhoFotoPlanta(String caminhoFotoPlanta) {
        this.caminhoFotoPlanta = caminhoFotoPlanta;
    }
}
