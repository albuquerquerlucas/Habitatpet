package com.mobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.R;
import com.mobile.helper.SQLiteHandler;
import com.mobile.helper.SessionManager;
import com.squareup.picasso.Picasso;

/**
 * Created by Mrluke on 27/10/2016.
 */

public class PlantasDetalhesFragment extends Fragment {

    private EditText edtQuantidade;
    private Button btnAddOrcamento;
    private SQLiteHandler db;
    private SessionManager session;
    String nomeItem, qtdItem;

    public PlantasDetalhesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plantas_detalhes_fragment, container, false);
        Bundle args = getArguments();

        db = new SQLiteHandler(getActivity().getApplicationContext());
        session = new SessionManager(getActivity().getApplicationContext());

        ImageView img = (ImageView) view.findViewById(R.id.iPlantas);
        TextView tx1 = (TextView) view.findViewById(R.id.txtDetPlantNome);
        TextView tx2 = (TextView) view.findViewById(R.id.txtDetPlantCientifico);
        TextView tx3 = (TextView) view.findViewById(R.id.txtDetPlantCrescimento);
        TextView tx4 = (TextView) view.findViewById(R.id.txtDetPlantPlantio);
        TextView tx5 = (TextView) view.findViewById(R.id.txtDetPlantPh);
        TextView tx6 = (TextView) view.findViewById(R.id.txtDetPlantTemperatura);
        TextView tx7 = (TextView) view.findViewById(R.id.txtDetPlantTamanho);
        TextView tx8 = (TextView) view.findViewById(R.id.txtDetPlantPosicaoNoAquario);
        TextView tx9 = (TextView) view.findViewById(R.id.txtDetEmersao);
        TextView tx10 = (TextView) view.findViewById(R.id.txtDetSubstrato);
        TextView tx11 = (TextView) view.findViewById(R.id.txtDetCo2);
        TextView tx12 = (TextView) view.findViewById(R.id.txtDetPlantDisponibilidade);

        edtQuantidade = (EditText) view.findViewById(R.id.edtQtdPeixes);
        btnAddOrcamento = (Button) view.findViewById(R.id.btnAddQtdPeixes);

        btnAddOrcamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                qtdItem = edtQuantidade.getText().toString();

                if(!qtdItem.isEmpty()){

                    db.salvarProdutos(nomeItem, Integer.parseInt(qtdItem));
                    edtQuantidade.setText("");
                    Toast.makeText(getActivity().getApplicationContext(), "Adicionado à lista de Orçamento.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Informe a quantidade.", Toast.LENGTH_LONG).show();
                }
            }
        });

        Picasso.with(getActivity().getApplicationContext())
                .load(args.getString("caminhoFotoPlanta"))
                .resize(500, 500)
                .into(img);
        tx1.setText(args.getString("nomePlanta"));
        tx2.setText(args.getString("nomeCientificoPlanta"));
        tx3.setText(args.getString("crescimento"));
        tx4.setText(args.getString("plantio"));
        tx5.setText(args.getString("phPlanta"));
        tx6.setText(args.getString("temperaturaPlanta"));
        tx7.setText(args.getString("tamanhoPlanta"));
        tx8.setText(args.getString("posicaoPlanta"));
        tx9.setText(args.getString("emersao"));
        tx10.setText(args.getString("substrato"));
        tx11.setText(args.getString("co2"));
        tx12.setText(args.getString("disponibilidadePlanta"));

        nomeItem = tx1.getText().toString();

        setupToolbar(view);
        return view;
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        final ActionBar bar = activity.getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setShowHideAnimationEnabled(true);
            bar.setHomeAsUpIndicator(R.drawable.ic_navigation_menu);
            bar.setTitle(activity.getString(R.string.detalhes));
        }
    }
}
