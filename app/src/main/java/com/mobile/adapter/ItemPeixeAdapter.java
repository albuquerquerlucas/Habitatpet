package com.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.R;
import com.mobile.entity.Peixe;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mrluke on 13/10/2016.
 */

public class ItemPeixeAdapter extends ArrayAdapter<Peixe> {

    public ItemPeixeAdapter(Context context, List<Peixe> lista){
        super(context, 0, lista);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View itemView = convertView;

        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_peixe_lista, parent, false);
        }

        Peixe peixe = getItem(position);

        TextView txtNomePeixe = (TextView) itemView.findViewById(R.id.nomePeixe);
        txtNomePeixe.setText(peixe.getNome());

        TextView txtNomePeixeCientifico = (TextView) itemView.findViewById(R.id.nomePeixeCientifico);
        txtNomePeixeCientifico.setText(peixe.getNomePeixeCientifico());

        ImageView imgPeixe = (ImageView) itemView.findViewById(R.id.imgPeixe);

        Picasso.with(getContext())
                .load(peixe.getCaminhoFoto())
                .into(imgPeixe);

        return itemView;
    }
}
