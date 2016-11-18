package com.mobile.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mobile.R;
import com.mobile.adapter.ItemPeixeAdapter;
import com.mobile.app.AppConfiguracao;
import com.mobile.converter.JsonConverter;
import com.mobile.entity.Peixe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PeixesFragment extends Fragment {

    private ItemPeixeAdapter peixeAdapter;

    public PeixesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.peixes_fragment, container, false);

        final ListView lista = (ListView) view.findViewById(R.id.listaDePeixes);
        peixeAdapter = new ItemPeixeAdapter(getActivity().getBaseContext(), new ArrayList<Peixe>());
        lista.setAdapter(peixeAdapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Peixe p = peixeAdapter.getItem(position);
                //Toast.makeText(getActivity().getApplicationContext(), "Você Clicou em: " + p.getNome(), Toast.LENGTH_SHORT).show();
                Fragment fragment = null;
                fragment = new PeixesDetalhesFragment();
                Bundle args = new Bundle();
                args.putString("caminhoFoto", p.getCaminhoFoto());
                args.putString("nomePeixe", p.getNome());
                args.putString("nomeCientifico", p.getNomePeixeCientifico());
                args.putString("descricao", p.getDescricaoPeixe());
                args.putString("alimentacao", p.getAlimentacao());
                args.putString("ph", p.getPhPeixe());
                args.putString("temperatura", p.getTemperaturaPeixe());
                args.putString("tamanho", p.getTamanhoPeixe());
                args.putString("posicao", p.getPosicaoNoAquarioPeixe());
                args.putString("tipo", p.getTipoPeixe());
                args.putString("disponibilidade", p.getStatusPeixe());
                fragment.setArguments(args);
                if(fragment != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();
                }
            }
        });
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
            bar.setTitle(activity.getString(R.string.peixes));
        }
    }

    public class RequisicaoTask extends AsyncTask<Void, Void, List<Peixe>> {

        @Override
        protected List<Peixe> doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try{
                URL url = new URL(AppConfiguracao.URL_PEIXES);
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

                return JsonConverter.converteJson(buffer.toString());

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
        protected void onPostExecute(List<Peixe> itemPeixes) {

            peixeAdapter.clear();
            peixeAdapter.addAll(itemPeixes);
            peixeAdapter.notifyDataSetChanged();
        }
    }


}