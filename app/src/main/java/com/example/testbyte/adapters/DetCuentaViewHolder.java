package com.example.testbyte.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testbyte.R;
import com.example.testbyte.models.Statement;

public class DetCuentaViewHolder extends RecyclerView.ViewHolder {

    private TextView lblDesc;
    private TextView lblDate;
    private TextView lblAmount;

    public DetCuentaViewHolder(@NonNull View itemView) {
        super(itemView);
        lblDesc = itemView.findViewById(R.id.lblDesc);
        lblDate = itemView.findViewById(R.id.lblDate);
        lblAmount = itemView.findViewById(R.id.lblAmount);
    }

    public void bind(Statement st, Context context){
        lblDesc.setText(st.getDescription());
        lblDate.setText(st.getDate());

        String monto = "";
        if (st.getType().equals("DEBIT")){
            monto = "- ";
            lblAmount.setTextColor(context.getResources().getColor(R.color.debit));
        } else {
            monto = "+ ";
            lblAmount.setTextColor(context.getResources().getColor(R.color.credit));
        }
        monto = monto +st.getCurrency()+" "+st.getAmount();

        lblAmount.setText(monto);
    }
}
