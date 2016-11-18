package com.mobile.entity;

import android.content.SyncStatusObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mrluke on 16/11/2016.
 */

public class Pedido implements Serializable {

    private Item itens;
    private List<Item> listaItens = new ArrayList<Item>();

    public Pedido() {

    }

    public void adicionarPedido(Item itens){
        listaItens.add(itens);
    }

    public void mostraPedidos(){


        for (Item item : listaItens) {
            System.out.println(item);
            System.out.println("\nTotal de produtos: " + listaItens.size());
        }
    }

    public Item getItens() {
        return itens;
    }

    public void setItens(Item itens) {
        this.itens = itens;
    }

    public List<Item> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<Item> listaItens) {
        this.listaItens = listaItens;
    }
}
