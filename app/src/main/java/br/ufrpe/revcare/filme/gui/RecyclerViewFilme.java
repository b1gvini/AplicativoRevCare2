package br.ufrpe.revcare.filme.gui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.filme.dominio.Filme;
import br.ufrpe.revcare.filme.persistencia.FilmeDAO;

public class RecyclerViewFilme extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayList<String> mTitulo = new ArrayList<>();
    private ArrayList<String> mDescricao = new ArrayList<>();
    private List<Filme> filmes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_filmes);
        getSupportActionBar().hide();
        initProfissionais();
        FilmeDAO dao = new FilmeDAO(getApplicationContext());
        zerarArrays();
        filmes = dao.getAllFilmes();
        adicionaNoArray(dao, filmes);
        initRecyclerView();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();


    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void initProfissionais() {
      FilmeDAO dao = new FilmeDAO(getApplicationContext());
      Toast.makeText(getApplicationContext(), "Você não tem recomendações ainda.", Toast.LENGTH_SHORT).show();
      filmes = dao.getAllFilmes();
      adicionaNoArray(dao, filmes);
      initRecyclerView();

    }

    private void adicionaNoArray(FilmeDAO dao, List<Filme> filmes) {
        for (int i = 0; i < filmes.size(); i++) {
            mTitulo.add(filmes.get(i).getTitulo());
            mDescricao.add(this.filmes.get(i).getDescricao());


        }
    }
    private void zerarArrays(){
        mTitulo = new ArrayList<>();
        mDescricao = new ArrayList<>();


    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.filmesRecycler);
        RecyclerViewAdapterFilme adapter = new RecyclerViewAdapterFilme(this, mTitulo, mDescricao);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}
