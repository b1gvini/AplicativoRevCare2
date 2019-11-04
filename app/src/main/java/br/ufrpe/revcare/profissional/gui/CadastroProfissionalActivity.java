package br.ufrpe.revcare.profissional.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.infra.gui.Validacao;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.negocio.ProfissionalServices;

public class CadastroProfissionalActivity extends AppCompatActivity {
    private EditText nNome;
    private EditText nDataNascimento;
    private EditText nEndereco;
    private EditText nCpf;
    private EditText nEmail;
    private EditText nTelefone;
    private EditText nCertificado;
    private EditText nSenha;
    private EditText nConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro_profissional1);
        Button btnFinalizarCadastro = findViewById(R.id.botaoFinalizarCadastro);
        btnFinalizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cadastrar();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }
    private void cadastrar() throws Exception {
        if (validarCampos()) {
            Profissional profissional = criarProfissional();
            ProfissionalServices services = new ProfissionalServices(getBaseContext());
            services.cadastrar(profissional);
            Toast.makeText(getApplicationContext(),"Profissional cadastrado com sucesso.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(CadastroProfissionalActivity.this, MainActivity.class));
        }
    }

    private boolean validarCampos() {
        nNome = findViewById(R.id.nomeTextField);
        nDataNascimento = findViewById(R.id.dataNascimentoTextField);
        nCpf = findViewById(R.id.cpfTextField);
        nEndereco = findViewById(R.id.enderecoTextField);
        nTelefone = findViewById(R.id.telefoneTextField);
        nEmail = findViewById(R.id.emailTextField);
        nSenha = findViewById(R.id.caixaTxtSenhaLogin);
        nConfirmarSenha = findViewById(R.id.caixaConfirmaSenha);
        Validacao valido = new Validacao();
        boolean camposValidos =
                valido.isValido(nNome, nDataNascimento, nCpf, nEndereco, nTelefone, nEmail, nSenha, nConfirmarSenha);
        return camposValidos && confirmarSenha();
    }

    private boolean confirmarSenha() {
        boolean result = true;
        nSenha = findViewById(R.id.caixaTxtSenhaLogin);
        nConfirmarSenha = findViewById(R.id.caixaConfirmaSenha);
        if (!nSenha.getText().toString().equals(nConfirmarSenha.getText().toString())) {
            result = false;
            Toast.makeText(getApplicationContext(),"Senhas diferentes", Toast.LENGTH_LONG).show();
        }
        return result;
    }

    private Profissional criarProfissional() {
        nNome = findViewById(R.id.nomeTextField);
        nDataNascimento = findViewById(R.id.dataNascimentoTextField);
        nCpf = findViewById(R.id.cpfTextField);
        nEndereco = findViewById(R.id.enderecoTextField);
        nTelefone = findViewById(R.id.telefoneTextField);
        nEmail = findViewById(R.id.emailTextField);
        nCertificado = findViewById(R.id.certificadoTextField);
        nSenha = findViewById(R.id.caixaTxtSenhaLogin);
        nConfirmarSenha = findViewById(R.id.caixaConfirmaSenha);;

        Profissional result = new Profissional();
        result.setNome(nNome.getText().toString().trim());
        result.setCpf(nCpf.getText().toString().trim());
        result.setEndereco(nEndereco.getText().toString().trim());
        result.setTelefone(nTelefone.getText().toString().trim());
        result.setEmail(nEmail.getText().toString().trim());
        result.setCertificado(nCertificado.getText().toString().trim());
        result.setDataNascimento(nDataNascimento.getText().toString().trim());
        result.setSenha(nSenha.getText().toString().trim());
        return result;
    }
}