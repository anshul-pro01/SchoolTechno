package com.example.passwordnapoochiyo.schooltechno;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

class document_Adapter extends RecyclerView.Adapter<document_Adapter.ViewHolder> {
    private documentpage documentpage;
String name,image,pdf_path;
   ArrayList<String> array_doctId=new ArrayList<>();
    ArrayList<String> array_doctName=new ArrayList<>();
    ArrayList<String> array_file=new ArrayList<>();

    public document_Adapter(ArrayList<String> array_doctId, ArrayList<String> array_doctName, ArrayList<String> array_file) {
       // this.documentpage = documentpage;
        this.array_doctId = array_doctId;
        this.array_doctName = array_doctName;
        this.array_file = array_file;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_document_list,parent,false);
       ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.id.setText(array_doctId.get(position));
            holder.title.setText(array_doctName.get(position));







      holder.imageview.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    name=array_doctName.get(position);
                    //  image=array_file.get(position);
                    pdf_path=array_file.get(position);
                    Intent intent = new Intent (view.getContext(), DocumentImage.class);

                    intent.putExtra("doc_name",name);
                    view.getContext().startActivity(intent);
                    intent.putExtra("pdfpath",pdf_path);
                    view.getContext().startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        return array_doctId.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, title;
        ImageView imageview;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.index);
            title = (TextView) itemView.findViewById(R.id.title);
            imageview=(ImageView)itemView.findViewById(R.id.imageview);
         /*   itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });*/
        }
    }
}
