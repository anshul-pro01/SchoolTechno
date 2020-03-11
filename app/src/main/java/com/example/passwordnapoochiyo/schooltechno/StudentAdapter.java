package com.example.passwordnapoochiyo.schooltechno;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView
        .Adapter<StudentAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<studentData> mDataset;
    ArrayList<String> id=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();

    public static class DataObjectHolder extends RecyclerView.ViewHolder
    {
        TextView label;
        TextView dateTime;

        public DataObjectHolder(View itemView) {
            super(itemView);
          //  label = (TextView) itemView.findViewById(R.id.texttitle);
          // dateTime = (TextView) itemView.findViewById(R.id.textdate);
            Log.i(LOG_TAG, "Adding Listener");

        }


    }


  //  public StudentAdapter(List myDataset) {
   //     mDataset = (ArrayList<studentData>) myDataset;
   // }
  public StudentAdapter(ArrayList<String> array_id,ArrayList<String> array_name) {
      this.id=array_id;
      this.name=array_name;
      }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_homework_list, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
       // holder.label.setText(mDataset.get(position).getmText1());
       // holder.dateTime.setText(mDataset.get(position).getmText2());
        holder.label.setText(id.get(position));
        holder.dateTime.setText(name.get(position));


    }




    @Override
    public int getItemCount() {
        return id.size();
    }


}
