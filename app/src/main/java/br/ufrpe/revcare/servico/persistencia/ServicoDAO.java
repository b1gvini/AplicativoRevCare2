package br.ufrpe.revcare.servico.persistencia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.revcare.infra.persistencia.DBHelper;
import br.ufrpe.revcare.infra.persistencia.GenericDAO;
import br.ufrpe.revcare.servico.dominio.Servico;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_SERVICO;

<<<<<<< HEAD
public class ServicoDAO {

    private SQLiteDatabase db;

    private DBHelper dbHelper;

    public ServicoDAO(Context context) {
        dbHelper = new DBHelper(context);
   }

    public long cadastrarServico(Servico servico){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
=======
public class ServicoDAO extends GenericDAO{

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public ServicoDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);

    }

    public long cadastrarServico(Servico servico) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

>>>>>>> e0f0aad5cd9c65d184eb69f2781cba3af12087a1
        values.put(DBHelper.COL_NOME_SERVICO, servico.getNome());
        values.put(DBHelper.COL_HORARIO_INICIAL, servico.getHorarioInicial());
        values.put(DBHelper.COL_HORARIO_FINAL, servico.getHorarioFinal());
        values.put(DBHelper.COL_DATA, servico.getData());
<<<<<<< HEAD
        //values.put(DBHelper.COL_SOLICITANTE, servico.getSolicitante());
        //values.put(DBHelper.COL_PROFISSIONAL, servico.getProfissional());
=======
        values.put(DBHelper.COL_SOLICITANTE, servico.getSolicitante());
        values.put(DBHelper.COL_PROFISSIONAL, servico.getProfissional());
>>>>>>> e0f0aad5cd9c65d184eb69f2781cba3af12087a1

        long id = db.insert(TABELA_SERVICO, null, values);
        db.close();
        return id;

    }

<<<<<<< HEAD
    public void excluirServico(Servico servico){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABELA_SERVICO, DBHelper.COL_ID_SERVICO + " = ?",
                new String[]{String.valueOf(servico.getId())});

        db.close();
    }
=======




>>>>>>> e0f0aad5cd9c65d184eb69f2781cba3af12087a1

}

