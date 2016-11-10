package com.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.R;
import com.mobile.entity.Planta;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mrluke on 14/10/2016.
 */

public class ItemPlantaAdapter extends ArrayAdapter<Planta> {

    public ItemPlantaAdapter(Context context, List<Planta> listaPlanta) {
        super(context, 0, listaPlanta);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View itemView = convertView;

        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_planta_lista, parent, false);
        }

        Planta planta = getItem(position);

        TextView txtNomePlanta = (TextView) itemView.findViewById(R.id.nomePlanta);
        txtNomePlanta.setText(planta.getNomePlanta());

        TextView txtNomeCientificoPlanta = (TextView) itemView.findViewById(R.id.nomeCientificoPlanta);
        txtNomeCientificoPlanta.setText(planta.getNomeCientificoPlanta());

        ImageView imgPlanta = (ImageView) itemView.findViewById(R.id.imgPlanta);

        Picasso.with(getContext())
                .load(planta.getCaminhoFotoPlanta())
                .into(imgPlanta);

        return itemView;
    }

}
