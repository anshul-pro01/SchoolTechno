package com.example.passwordnapoochiyo.schooltechno;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.ViewHolder>{

    private Context context;
    private List<SubList> slist;

    public ExamListAdapter(Context context, List<SubList> slist) {
        this.context = context;
        this.slist = slist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.layout_examsublist,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SubList subList=slist.get(position);

        holder.date.setText(subList.getExam_date());
        holder.id.setText(subList.getEs_id());
        holder.subject.setText(subList.getExam_subject());
    }

    @Override
    public int getItemCount() {
        return slist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView date,id,subject;
        public ViewHolder(View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            id=itemView.findViewById(R.id.serial);
            subject=itemView.findViewById(R.id.examinfo);

        }
    }
}
