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
import com.squareup.picasso.Picasso;

/**
 * Created by Mrluke on 27/10/2016.
 */

public class ProdutosDetalhesFragment extends Fragment {

    private EditText edtQuantidade;
    private Button btnAddOrcamento;

    public ProdutosDetalhesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.produtos_detalhes_fragment, container, false);
        Bundle args = getArguments();

        ImageView img = (ImageView) view.findViewById(R.id.iProdutos);
        TextView txtNomeProduto = (TextView) view.findViewById(R.id.txtDetProdNome);
        TextView txtReferenciaProduto = (TextView) view.findViewById(R.id.txtDetProdReferencia);
        TextView txtDescricaoProduto = (TextView) view.findViewById(R.id.txtDetProdDescricao);
        TextView txtMarcaProduto = (TextView) view.findViewById(R.id.txtDetProdMarca);
        TextView txtModeloProduto = (TextView) view.findViewById(R.id.txtDetProdModelo);
        TextView txtStatusProduto = (TextView) view.findViewById(R.id.txtDetProdDisponibilidade);

        edtQuantidade = (EditText) view.findViewById(R.id.edtQtdPeixes);
        btnAddOrcamento = (Button) view.findViewById(R.id.btnAddQtdPeixes);
        btnAddOrcamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qtd = edtQuantidade.getText().toString();

                if(!qtd.isEmpty()){
                    edtQuantidade.setText("");
                    Toast.makeText(getActivity().getApplicationContext(), "Adicionado à lista de Orçamento.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Informe a quantidade.", Toast.LENGTH_LONG).show();
                }
            }
        });

        Picasso.with(getActivity().getApplicationContext())
                .load(args.getString("caminhoFotoProduto"))
                .resize(500, 500)
                .into(img);
        txtNomeProduto.setText(args.getString("nomeProduto"));
        txtReferenciaProduto.setText(args.getString("referencia"));
        txtDescricaoProduto.setText(args.getString("descricaoProduto"));
        txtMarcaProduto.setText(args.getString("marca"));
        txtModeloProduto.setText(args.getString("modelo"));
        txtStatusProduto.setText(args.getString("statusProduto"));

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
