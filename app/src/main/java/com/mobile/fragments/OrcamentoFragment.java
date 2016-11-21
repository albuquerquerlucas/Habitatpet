package com.mobile.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
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
import com.mobile.activity.LoginActivity;
import com.mobile.activity.PrincipalActivity;
import com.mobile.entity.Item;
import com.mobile.entity.Pedido;
import com.mobile.helper.SQLiteHandler;
import com.mobile.helper.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrcamentoFragment extends Fragment {

    private TextView txtPedidos;
    private Button btnEnviarPedido;
    private Button btnCancelarPedido;
    private SQLiteHandler db;
    private SessionManager session;
    private AlertDialog alerta;
    String nome, email;
    String nomeItem = "", qtdItem = "";

    public OrcamentoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orcamento_fragment, container, false);
        //Bundle args = getArguments();

        TextView txtTitleOrc1 = (TextView) view.findViewById(R.id.txtTitleOrc1);
        TextView txtTitleOrc2 = (TextView) view.findViewById(R.id.txtTitleOrc2);
        txtPedidos = (TextView) view.findViewById(R.id.txtPedidos);
        btnEnviarPedido = (Button) view.findViewById(R.id.btnEnviarPedido);
        btnCancelarPedido = (Button) view.findViewById(R.id.btnCancelarPedido);

        db = new SQLiteHandler(getActivity().getApplicationContext());
        session = new SessionManager(getActivity().getApplicationContext());

        /*if (!session.isLoggedIn()) {
            logoutUsuario();
        }*/

        HashMap<String, String> usuarios = db.getUsuarioDetalhes();
        List<Item> itens = db.getAllItens();
        StringBuilder builder = new StringBuilder();
        for (Item i : itens) {
            builder.append(i + "\n");
            Log.i("", "Valor: " + i.toString());
        }

        nome = usuarios.get("nome");
        email = usuarios.get("email");

        if(itens.isEmpty()){
            txtPedidos.setText("Não possui itens no momento.");
        }else{
            txtPedidos.setText(builder.toString());
        }

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
                    body.append("<hr/><p>Cliente: " + nome + "</p>");
                    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("text/html");
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "[Orçamento Habitat Mobile]");
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body.toString()));
                    startActivity(Intent.createChooser(emailIntent, "Enviar email...."));
                    db.deletarProdutos();
                }
            }
        });

        btnCancelarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPedidos.setText("Não possui itens no momento.");
                db.deletarProdutos();
            }
        });
    }


}