package br.ufrpe.revcare.filme.negocio;

import android.content.Context;

import br.ufrpe.revcare.filme.dominio.Filme;
import br.ufrpe.revcare.filme.persistencia.FilmeDAO;

public class FilmeServices {
    private FilmeDAO dao;

    public FilmeServices(Context context) {
        dao = new FilmeDAO(context);
    }

    public long cadastrar(Filme filme) throws Exception {
        Filme filmeBD = dao.consultarTitulo(filme.getTitulo());
        if (filmeBD != null) {
            throw new Exception("Filme já cadastrado");
        }
        return dao.cadastrar(filme);
    }
    public Filme consultarTitulo(String titulo){
        return dao.consultarTitulo(titulo);
    }
}
