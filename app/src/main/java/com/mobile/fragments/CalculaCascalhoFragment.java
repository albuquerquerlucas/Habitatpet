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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.R;

import java.text.DecimalFormat;

/**
 * Created by Mrluke on 25/10/2016.
 */

public class CalculaCascalhoFragment extends Fragment {

    private EditText edtComprimento;
    private EditText edtLargura;
    private TextView txtResultado;
    private Button btnCalcular, btnLimpar, btnVoltar;

    public CalculaCascalhoFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calcula_cascalho_fragment, container, false);

        edtComprimento = (EditText) view.findViewById(R.id.edtNComprimento);
        edtLargura = (EditText) view.findViewById(R.id.edtNLargura);
        txtResultado = (TextView) view.findViewById(R.id.txtResultCalcVolume);

        btnCalcular = (Button) view.findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String comprimento = edtComprimento.getText().toString();
                String largura = edtLargura.getText().toString();

                if(!comprimento.isEmpty() && !largura.isEmpty()){
                    float result = (Float.parseFloat(comprimento) * Float.parseFloat(largura));
                    DecimalFormat df = new DecimalFormat("0.0");

                    txtResultado.setText(df.format(result / 10) + " kg");
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLimpar = (Button) view.findViewById(R.id.btnLimparCalcular);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtComprimento.setText("");
                edtLargura.setText("");
                txtResultado.setText("");
            }
        });

        btnVoltar = (Button) view.findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().beginTransaction().remove(CalculaCascalhoFragment.this).commit();
                Fragment fragment = null;
                fragment = new FerramentasFragment();
                if(fragment != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();
                }
            }
        });

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

    /*@Override
    public void onDestroyView(){
        getActivity().getFragmentManager().beginTransaction().remove(CalculaCascalhoFragment.this).commit();
    }*/
}
