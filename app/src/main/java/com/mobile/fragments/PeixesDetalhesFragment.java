package com.mobile.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.R;
import com.mobile.entity.Item;
import com.mobile.entity.Pedido;
import com.mobile.helper.SQLiteHandler;
import com.mobile.helper.SessionManager;
import com.squareup.picasso.Picasso;

/**
 * Created by Mrluke on 27/10/2016.
 */

public class PeixesDetalhesFragment extends Fragment {

    private EditText edtQuantidade;
    private Button btnAddOrcamento;
    private SQLiteHandler db;
    private SessionManager session;
    String nomeItem, qtdItem;

    public PeixesDetalhesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.peixes_detalhes_fragment, container, false);
        Bundle args = getArguments();

        db = new SQLiteHandler(getActivity().getApplicationContext());
        session = new SessionManager(getActivity().getApplicationContext());

        ImageView img = (ImageView) view.findViewById(R.id.iPeixes);
        TextView tx1 = (TextView) view.findViewById(R.id.txtDetNome);
        TextView tx2 = (TextView) view.findViewById(R.id.txtDetCientifico);
        TextView tx3 = (TextView) view.findViewById(R.id.txtDetDescricao);
        TextView tx4 = (TextView) view.findViewById(R.id.txtDetAlimentacao);
        TextView tx5 = (TextView) view.findViewById(R.id.txtDetPh);
        TextView tx6 = (TextView) view.findViewById(R.id.txtDetTemperatura);
        TextView tx7 = (TextView) view.findViewById(R.id.txtDetTamanho);
        TextView tx8 = (TextView) view.findViewById(R.id.txtDetPosicaoNoAquario);
        TextView tx9 = (TextView) view.findViewById(R.id.txtDetTipo);
        TextView tx10 = (TextView) view.findViewById(R.id.txtDetDisponibilidade);



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
                .load(args.getString("caminhoFoto"))
                .resize(500, 500)
                .into(img);
        tx1.setText(args.getString("nomePeixe"));
        tx2.setText(args.getString("nomeCientifico"));
        tx3.setText(args.getString("descricao"));
        tx4.setText(args.getString("alimentacao"));
        tx5.setText(args.getString("ph"));
        tx6.setText(args.getString("temperatura"));
        tx7.setText(args.getString("tamanho"));
        tx8.setText(args.getString("posicao"));
        tx9.setText(args.getString("tipo"));
        tx10.setText(args.getString("disponibilidade"));

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
