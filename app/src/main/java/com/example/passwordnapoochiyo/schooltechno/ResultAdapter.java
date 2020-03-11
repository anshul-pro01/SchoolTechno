package com.example.passwordnapoochiyo.schooltechno;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder>{

    private Context context;
    private List<Result_p> list;

    public ResultAdapter(Context context, List<Result_p> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.layout_result,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.ViewHolder holder, int position) {
        final Result_p result_p= list.get(position);

        holder.sub_name.setText(result_p.getSub_name());
        holder.sub_overall.setText(result_p.getSub_overall());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView sub_name,sub_mid,sub_end,sub_overall;

        public ViewHolder(View itemView) {
            super(itemView);

            sub_name=itemView.findViewById(R.id.sub_card_name);
            sub_overall=itemView.findViewById(R.id.sub_card_overallgrade);
        }
    }
}



