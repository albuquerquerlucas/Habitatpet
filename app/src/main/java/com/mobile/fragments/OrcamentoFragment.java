package com.mobile.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.R;
import com.mobile.entity.Item;
import com.mobile.entity.Pedido;
import com.mobile.helper.SQLiteHandler;

import java.util.HashMap;

public class OrcamentoFragment extends Fragment {

    private TextView txtPedidos;
    private Button btnEnviarPedido;
    private SQLiteHandler db;
    String nomeItem = "", qtdItem = "";

    public OrcamentoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orcamento_fragment, container, false);
        //Bundle args = getArguments();

        txtPedidos = (TextView) view.findViewById(R.id.txtPedidos);
        btnEnviarPedido = (Button) view.findViewById(R.id.btnEnviarPedido);

        //txtPedidos.setText("1 Betta");
        solicitaOrcamento();

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
            bar.setTitle(activity.getString(R.string.orcamento));
        }
    }

    public void solicitaOrcamento(){

        btnEnviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String conteudoEmail = txtPedidos.getText().toString();

                if(conteudoEmail.equals("Não possui itens no momento.")){
                    Toast.makeText(getActivity().getApplicationContext(), "Você não possui produtos para solicitar orçamento.", Toast.LENGTH_SHORT).show();
                }else{
                    String[] recipients = new String[]{"habitatpet@hotmail.com", "",};
                    StringBuilder body = new StringBuilder();
                    body.append("<p><b>Solicitação de Orçamento:</b><hr/>");
                    body.append("<div><p>" + conteudoEmail + "</p></div>");
                    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("text/html");
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "[Orçamento Habitat Mobile]");
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body.toString()));
                    startActivity(Intent.createChooser(emailIntent, "Enviar email...."));
                }


            }
        });
    }
}