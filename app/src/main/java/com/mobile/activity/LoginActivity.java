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

import com.android.volley.Request.Method;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    private static final String TAG = CadastroActivity.class.getSimpleName();
    private static ArrayList<Activity> activities=new ArrayList<Activity>();
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnEntrar;
    private Button btnNovoCadastro;
    private ProgressDialog pDialog;
    private SessionManager sessao;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activities.add(this);

        edtEmail = (EditText) findViewById(R.id.edtLEmail);
        edtSenha = (EditText) findViewById(R.id.edtLSenha);
        btnEntrar = (Button) findViewById(R.id.btnLEntrar);
        btnNovoCadastro = (Button) findViewById(R.id.btnLNovoCadastro);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        db = new SQLiteHandler(getApplicationContext());
        sessao = new SessionManager(getApplicationContext());
        ///sessao.setLogin(false);

        if (sessao.isLoggedIn()) {

            Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
            startActivity(intent);
            finish();
        }

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String senha = edtSenha.getText().toString().trim();

                if (!email.isEmpty() && !senha.isEmpty()) {
                    verificaLogin(email, senha);
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private boolean verificaLoginLocalmente(final String email){
        // Trabalhando aqui...
        return false;
    }

    private void verificaLogin(final String email, final String senha) {

        String tag_string_req = "req_login";

        pDialog.setMessage("Verificando login... Aguarde!");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST, AppConfiguracao.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                // Luz no fim do túnel... :)
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {

                        sessao.setLogin(true);

                        String idUnico = jObj.getString("idUnico");

                        JSONObject usuario = jObj.getJSONObject("usuario");
                        String nome = usuario.getString("nome");
                        String email = usuario.getString("email");
                        String data_de_criacao = usuario.getString("data_de_criacao");

                        Log.i("Dados recuperados Ws: ", "" + nome + " " + email);  // Test ok@
                        db.salvarUsuario(nome, email, idUnico, data_de_criacao);

                        Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Login e Senha não correspondem. Tente novamente!", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        activities.remove(this);
    }

    @Override
    public void onBackPressed() {
        //return nothing
        return;
    }
}
