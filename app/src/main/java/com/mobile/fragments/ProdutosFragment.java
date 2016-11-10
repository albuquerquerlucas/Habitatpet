package com.mobile.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobile.R;
import com.mobile.adapter.ItemProdutoAdapter;
import com.mobile.app.AppConfiguracao;
import com.mobile.converter.JsonProdutoConverter;
import com.mobile.entity.Produto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProdutosFragment extends Fragment {

    private ItemProdutoAdapter produtoAdapter;

    public ProdutosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.produtos_fragment, container, false);

        ListView lista = (ListView) view.findViewById(R.id.listaDeProdutos);
        produtoAdapter = new ItemProdutoAdapter(getActivity().getBaseContext(), new ArrayList<Produto>());
        lista.setAdapter(produtoAdapter);

        setupToolbar(view);

        new RequisicaoTask().execute();
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
            bar.setTitle(activity.getString(R.string.produtos));
        }
    }

    public class RequisicaoTask extends AsyncTask<Void, Void, List<Produto>> {

        @Override
        protected List<Produto> doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try{
                URL url = new URL(AppConfiguracao.URL_PRODUTOS);
                //URL url = new URL("http://10.54.108.19:80/peixesWs/exibirPeixesJson.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String linha;
                StringBuffer buffer = new StringBuffer();

                while ((linha = reader.readLine()) != null){
                    buffer.append(linha);
                    buffer.append("\n");
                }

                return JsonProdutoConverter.converteJson(buffer.toString());

            }catch (Exception e){
                e.printStackTrace();
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Produto> itemProdutos) {

            produtoAdapter.clear();
            produtoAdapter.addAll(itemProdutos);
            produtoAdapter.notifyDataSetChanged();
        }
    }
}