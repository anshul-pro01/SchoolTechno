package com.example.passwordnapoochiyo.schooltechno;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import android.content.Intent;
import java.util.ArrayList;

class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.Viewholder>{
    Activity a;


    ArrayList<String> array_homeworkid;
    ArrayList<String> array_homeworktitle;
    ArrayList<String> array_homeworkdate;
    ArrayList<String> array_staffname;
    ArrayList<String> array_homeworkimage;

    public HomeworkAdapter(Activity a,ArrayList<String> array_homeworkid, ArrayList<String> array_homeworktitle,
                           ArrayList<String> array_homeworkdate, ArrayList<String> array_staffname,
                           ArrayList<String> array_homeworkimage) {
        this.a = a;
        this.array_homeworkid = array_homeworkid;
        this.array_homeworktitle = array_homeworktitle;
        this.array_homeworkdate = array_homeworkdate;
        this.array_staffname = array_staffname;
        this.array_homeworkimage = array_homeworkimage;
    }

    public HomeworkAdapter() {

    }

    @NonNull
    @Override
    public HomeworkAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_homework_list,parent,false);
        Viewholder holder=new Viewholder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeworkAdapter.Viewholder holder, int position) {
        holder.title.setText(array_homeworktitle.get(position));
        holder.date.setText(array_homeworkdate.get(position));
        final String HW_image=array_homeworkimage.get(position);
        final String HW_staffname=array_staffname.get(position);
        final String HW_title=array_homeworktitle.get(position);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(a,homework_image.class);
                intent.putExtra("image",HW_image);
                intent.putExtra("staffname",HW_staffname);
                intent.putExtra("title",HW_title);
                   a.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return array_homeworkid.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
TextView title,date;
ImageView image;
        public Viewholder(View itemView) {
            super(itemView);
           title=(TextView)itemView.findViewById(R.id.homework_title);
            date=(TextView)itemView.findViewById(R.id.homework_date);
            image=(ImageView)itemView.findViewById(R.id.homework_imageview);
        }
    }
}
