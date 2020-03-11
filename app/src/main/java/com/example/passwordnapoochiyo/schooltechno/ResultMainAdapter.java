package com.example.passwordnapoochiyo.schooltechno;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ResultMainAdapter extends RecyclerView.Adapter<ResultMainAdapter.ViewHolder>{

    private Context context;
    private List<Exam> list;

    public ResultMainAdapter(Context context, List<Exam> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.layout_result_main,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Exam exam=list.get(position);

        holder.textExamClass.setText(exam.getExam_name());

        holder.exam_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(view.getContext(),Result.class);
                intent.putExtra("exam_class",exam.exam_name);
                view.getContext().startActivity(intent);
                intent.putExtra("exam_id",exam.exam_id);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textExamClass;
        public CardView exam_card;

        public ViewHolder(View itemView) {
            super(itemView);
            textExamClass=itemView.findViewById(R.id.exam_class);
            exam_card=itemView.findViewById(R.id.exam);
        }
    }
}
