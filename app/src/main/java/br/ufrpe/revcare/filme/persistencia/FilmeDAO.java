package br.ufrpe.revcare.filme.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.revcare.filme.dominio.Filme;
import br.ufrpe.revcare.infra.persistencia.DBHelper;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_TITULO;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_FILME;

public class FilmeDAO {
    private static DBHelper dbHelper;

    public FilmeDAO(Context context) {

        dbHelper = new DBHelper(context);
    }

    public long cadastrar(Filme filme){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_TITULO, filme.getTitulo());
        values.put(DBHelper.COL_DESCRICAO, filme.getDescricao());
        long id = db.insert(TABELA_FILME, null, values);
        db.close();
        return id;

    }
    private Filme criarFilme(Cursor cursor) {
        Filme result = new Filme();
        result.setId(cursor.getInt(0));
        result.setTitulo(cursor.getString(1));
        result.setDescricao(cursor.getString(2));

        return result;
    }

    public Filme consultarTitulo(String titulo) {
        Filme result = null;
        String query =
                " SELECT * " +
                        " FROM " + TABELA_FILME +
                        " WHERE " + COL_TITULO + " = ? ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{titulo});
        if (cursor.moveToFirst()) {
            result = criarFilme(cursor);
        }
        cursor.close();
        db.close();

        return result;
    }

    public List<Filme> getAllProfissional() {
        List<Filme> filmeArrayList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = " SELECT * " +
                " FROM " + TABELA_FILME;
        String[] args = {};
        Cursor cursor = db.rawQuery(query, args);
        Filme filme = null;
        if (cursor.moveToFirst()) {
            do {
                filme = criarFilme(cursor);
                filmeArrayList.add(filme);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
            return filmeArrayList;
        }
        cursor.close();
        db.close();
        return filmeArrayList;
    }
}
