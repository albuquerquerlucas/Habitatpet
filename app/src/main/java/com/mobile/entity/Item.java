package com.mobile.entity;

import java.io.Serializable;

/**
 * Created by Mrluke on 16/11/2016.
 */

public class Item implements Serializable {

    private String descricao;
    private int quantidade;

    public Item(String descricao, int quantidade) {
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString(){
        return "" + getQuantidade() + "  " + getDescricao() + ";";
    }
}
