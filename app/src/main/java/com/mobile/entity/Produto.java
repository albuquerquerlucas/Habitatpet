package com.mobile.entity;

import java.io.Serializable;

/**
 * Created by Mrluke on 14/10/2016.
 */

public class Produto implements Serializable {

    private String nomeProduto;
    private String referencia;
    private String descricaoProduto;
    private String marca;
    private String modelo;
    private String statusProduto;
    private String caminhoFotoProduto;

    public Produto(String nomeProduto, String referencia, String descricaoProduto, String marca, String modelo,
                   String statusProduto,String caminhoFotoProduto) {
        this.nomeProduto = nomeProduto;
        this.referencia = referencia;
        this.descricaoProduto = descricaoProduto;
        this.marca = marca;
        this.modelo = modelo;
        this.statusProduto = statusProduto;
        this.caminhoFotoProduto = caminhoFotoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getStatusProduto() {
        return statusProduto;
    }

    public void setStatusProduto(String statusProduto) {
        this.statusProduto = statusProduto;
    }

    public String getCaminhoFotoProduto() {
        return caminhoFotoProduto;
    }

    public void setCaminhoFotoProduto(String caminhoFotoProduto) {
        this.caminhoFotoProduto = caminhoFotoProduto;
    }
}
