package br.ufrpe.revcare.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.profissional.gui.PerfilProfissional;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    private ArrayList<String> mNome = new ArrayList<>();
    private ArrayList<String> mEndereco = new ArrayList<>();
    private Context mContext;
    private ArrayList<Long> mIds = new ArrayList<>();

    public RecyclerViewAdapter(Context context, ArrayList<String> nome, ArrayList<String> descricao, ArrayList<Long> ids ) {
        this.mNome = nome;
        this.mEndereco = descricao;
        this.mContext = context;
        this.mIds = ids;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.nome.setText(mNome.get(position));
        holder.descricao.setText(mEndereco.get(position));
        holder.btnVerMais.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PerfilProfissional.class);
                intent.putExtra("nome", mNome.get(position));
                intent.putExtra("endereco", mEndereco.get(position));
                intent.putExtra("id", mIds.get(position));
                mContext.startActivity(intent);
            }

        });


    }

    @Override
    public int getItemCount() {
        return mNome.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nome;
        TextView descricao;
        RelativeLayout parentLayout;
        Button btnVerMais;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome);
            descricao = itemView.findViewById(R.id.localizacao);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            btnVerMais = itemView.findViewById(R.id.btnVerMais);
        }
    }
}

