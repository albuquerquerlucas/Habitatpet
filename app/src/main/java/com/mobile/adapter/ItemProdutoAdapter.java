package com.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.R;
import com.mobile.entity.Produto;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mrluke on 14/10/2016.
 */

public class ItemProdutoAdapter extends ArrayAdapter<Produto> {

    public ItemProdutoAdapter(Context context, List<Produto> listaProdutos) {
        super(context, 0, listaProdutos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View itemView = convertView;

        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_produto_lista, parent, false);
        }

        Produto produto = getItem(position);

        TextView txtNomeProduto = (TextView) itemView.findViewById(R.id.nomeProduto);
        txtNomeProduto.setText(produto.getNomeProduto());

        TextView txtReferencia = (TextView) itemView.findViewById(R.id.referencia);
        txtReferencia.setText(produto.getReferencia());

        ImageView imgProduto = (ImageView) itemView.findViewById(R.id.imgProduto);

        Picasso.with(getContext())
                .load(produto.getCaminhoFotoProduto())
                .into(imgProduto);

        return itemView;
    }
}
