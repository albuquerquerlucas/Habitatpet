package com.mobile.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mobile.R;
import com.mobile.app.AppConfiguracao;
import com.mobile.app.AppController;
import com.mobile.helper.SQLiteHandler;
import com.mobile.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends Activity {

    private static final String TAG = CadastroActivity.class.getSimpleName();
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnSalvar;
    private Button btnCancelar;
    private ProgressDialog pDialog;
    private SessionManager sessao;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = (EditText) findViewById(R.id.edtCNome);
        edtEmail = (EditText) findViewById(R.id.edtCEmail);
        edtSenha = (EditText) findViewById(R.id.edtCSenha);
        btnSalvar = (Button) findViewById(R.id.btnCSalvar);
        btnCancelar = (Button) findViewById(R.id.btnCCancelar);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        sessao = new SessionManager(getApplicationContext());
        db = new SQLiteHandler(getApplicationContext());

        if (sessao.isLoggedIn()) {

            System.out.println("Está Logado? " + sessao.isLoggedIn());
            Intent intent = new Intent(CadastroActivity.this, PrincipalActivity.class);
            startActivity(intent);
            finish();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String senha = edtSenha.getText().toString().trim();

                if (!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
                    cadastrarUsuario(nome, email, senha);
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void cadastrarUsuario(final String nome, final String email, final String senha) {

        String tag_string_req = "req_register";

        pDialog.setMessage("Conectando ao WebService...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfiguracao.URL_CADASTRO, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Cadastro Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {

                        String idUnico = jObj.getString("id_unico");

                        JSONObject usuario = jObj.getJSONObject("usuario");
                        String nome = usuario.getString("nome");
                        String email = usuario.getString("email");
                        String data_de_criacao = usuario.getString("data_de_criacao");

                        db.salvarUsuario(nome, email, idUnico, data_de_criacao);

                        Toast.makeText(getApplicationContext(), "Cadastrado com sucesso. Faça login!", Toast.LENGTH_LONG).show();
                        //sessao.setLogin(false);

                    } else {
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Cadastro Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("nome", nome);
                params.put("email", email);
                params.put("senha", senha);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
