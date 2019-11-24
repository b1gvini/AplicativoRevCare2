package br.ufrpe.revcare.filme.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.filme.dominio.Filme;
import br.ufrpe.revcare.filme.negocio.FilmeServices;
import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.infra.gui.Validacao;


public class CadastroFilme extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText vTitulo;
    private EditText vDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro_filme);
        ImageButton btnFinalizarCadastro = findViewById(R.id.imageButtonFinalizar);
        btnFinalizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cadastrar();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Não foi possível cadastro.", Toast.LENGTH_LONG).show();

                }

            }

        });
    }
    private void cadastrar() throws Exception {
        if (validarCampos() && confirmaTitulo()) {
            Filme filme = criarFilme();
            FilmeServices services = new FilmeServices(getBaseContext());
            services.cadastrar(filme);
            Toast.makeText(getApplicationContext(),"Filme cadastrado com sucesso.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(CadastroFilme.this, MainActivity.class));
        }
    }

    private boolean validarCampos() {
        vTitulo = findViewById(R.id.tituloTextField);
        vDescricao = findViewById(R.id.descricaoTextField);
        Validacao valido = new Validacao();
        boolean camposValidos =
                valido.isValido(vTitulo, vDescricao);
        return camposValidos;
    }
    private  boolean confirmaTitulo(){
        Filme result = null;
        FilmeServices services = new FilmeServices(this);
        EditText nTitulo = findViewById(R.id.tituloTextField);
        String titulo = nTitulo.getText().toString().trim();
        result = services.consultarTitulo(titulo);
        if (result != null){
            nTitulo.requestFocus();
            nTitulo.setError("Preencha novamente o campo.");
            Toast.makeText(getApplicationContext(), "Não foi possível realizar o cadastro.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Filme criarFilme() {
        vTitulo = findViewById(R.id.tituloTextField);
        vDescricao = findViewById(R.id.descricaoTextField);

        Filme result = new Filme();
        result.setTitulo(vTitulo.getText().toString().trim());
        result.setDescricao(vDescricao.getText().toString().trim());
        return result;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
