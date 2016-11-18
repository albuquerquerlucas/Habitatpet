package com.mobile.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mobile.R;

public class FerramentasFragment extends Fragment {

    Button btnVolume;
    Button btnSubstrato;
    Button btnQtdPeixes;
    Button btnQtdCascalho;
    Button btnQtdRacao;

    public FerramentasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ferramentas_fragment, container, false);

        btnVolume = (Button) view.findViewById(R.id.btnCVolume);
        btnSubstrato = (Button) view.findViewById(R.id.btnCSubstrato);
        btnQtdPeixes = (Button) view.findViewById(R.id.btnCPeixes);
        btnQtdCascalho = (Button) view.findViewById(R.id.btnCCascalho);
        btnQtdRacao = (Button) view.findViewById(R.id.btnCRacao);

        acaoBotoes();
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
            bar.setTitle(activity.getString(R.string.ferramentas));
        }
    }

    public void acaoBotoes(){

        btnVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new CalculaVolumeFragment();
                if(fragment != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();
                }
            }
        });

        btnSubstrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Ferramenta Indisponível no momento.", Toast.LENGTH_SHORT).show();
            }
        });

        btnQtdPeixes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Ferramenta Indisponível no momento.", Toast.LENGTH_SHORT).show();
            }
        });

        btnQtdCascalho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new CalculaCascalhoFragment();
                if(fragment != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();
                }
            }
        });

        btnQtdRacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new InfoQtdRacaoFragment();
                if(fragment != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();
                }
            }
        });
    }
}