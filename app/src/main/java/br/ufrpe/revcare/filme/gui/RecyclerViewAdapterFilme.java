package br.ufrpe.revcare.filme.gui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrpe.revcare.R;


public class RecyclerViewAdapterFilme extends RecyclerView.Adapter<RecyclerViewAdapterFilme.ViewHolder> {
    private ArrayList<String> mTitulo = new ArrayList<>();
    private ArrayList<String> mDescricao = new ArrayList<>();


    public RecyclerViewAdapterFilme(Context context, ArrayList<String> titulo, ArrayList<String> descricao) {
        this.mTitulo = titulo;
        this.mDescricao = descricao;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_filmes, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.Titulo.setText(mTitulo.get(position));
        holder.Descricao.setText(mDescricao.get(position));




    }

    @Override
    public int getItemCount() {
        return mTitulo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Titulo;
        TextView Descricao;
        ImageView imagem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Titulo = itemView.findViewById(R.id.Titulo);
            Descricao = itemView.findViewById(R.id.DescricaoFilme);

        }
    }
}


