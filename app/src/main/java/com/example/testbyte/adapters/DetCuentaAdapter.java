package com.example.testbyte.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testbyte.R;
import com.example.testbyte.models.Statement;

import java.util.ArrayList;
import java.util.List;

public class DetCuentaAdapter extends RecyclerView.Adapter<DetCuentaViewHolder> {

    private List<Statement> lstStatements;
    private Context context;

    public DetCuentaAdapter(List<Statement> lst, Context context){
        this.lstStatements = lst;
        this.context = context;
    }

    @NonNull
    @Override
    public DetCuentaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.det_cuenta_view, parent,false);
        return new DetCuentaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetCuentaViewHolder holder, int position) {
        holder.bind(lstStatements.get(position), context);
    }

    @Override
    public int getItemCount() {
        return lstStatements.size();
    }
}
