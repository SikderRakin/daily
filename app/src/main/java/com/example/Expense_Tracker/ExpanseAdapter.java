package com.example.Expense_Tracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ExpanseAdapter extends RecyclerView.Adapter<ExpanseAdapter.ViewHolder> {

    Context context;
    List<Expanse_Container> expanses;
    DatabaseHelper helper;

    public ExpanseAdapter(Context context, List<Expanse_Container> expanses,DatabaseHelper helper) {
        this.context = context;
        this.expanses = expanses;
        this.helper = helper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expanse_list_layout,viewGroup, false);


        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final Expanse_Container expanse = expanses.get(i);
        viewHolder.expenseTypeTV.setText(expanse.getExpanse_name());
        viewHolder.expenseAmountTV.setText(String.valueOf(expanse.getExpanse_amount()));
        viewHolder.expenseDateTV.setText(String.valueOf(expanse.getExpanse_date()));
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                helper.deleteData(expanse.getID());
                expanses.remove(i);
                notifyDataSetChanged();
                return false;
            }
        });
        //viewHolder.expenseT.setText(String.valueOf(expanse.getExpanse_time()));
       // viewHolder.expenseDateTV.setText((String.valueOf()));
    }

    @Override
    public int getItemCount() {
        return expanses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView expenseTypeTV,expenseDateTV,expenseAmountTV;
        private ImageView expanseImageIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            expenseTypeTV = itemView.findViewById(R.id.expenseTypeTV);
            expenseDateTV = itemView.findViewById(R.id.expenseDateTV);
            expenseAmountTV = itemView.findViewById(R.id.expenseAmountTV);
            expanseImageIV = itemView.findViewById(R.id.expanseImageIV);


        }
    }
}
