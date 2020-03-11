package com.example.passwordnapoochiyo.schooltechno;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

class circular_adapter extends RecyclerView.Adapter<circular_adapter.ViewHolder>{

 //   private final View.OnClickListener mOnClickListener = new MyOnClickListener();
    ArrayList<String> arraycircularId=new ArrayList<>();
    ArrayList<String>arraycirculartitle=new ArrayList<>();
    ArrayList<String>arraycirculardesc=new ArrayList<>();
    ArrayList<String>arraycirculardate=new ArrayList<>();
ArrayList<String> arraycircularimage=new ArrayList<>();

    Activity activity;
  //  private LayoutInflater layoutInflater=null;
    public circular_adapter(Activity a, ArrayList<String> arraycircularId,
                            ArrayList<String> arraycirculartitle,
                            ArrayList<String> arraycirculardesc,
                            ArrayList<String> arraycirculardate,
                            ArrayList<String> arraycircularimage){
        this.activity=a;
        this.arraycircularId=arraycircularId;
        this.arraycirculartitle=arraycirculartitle;
        this.arraycirculardesc=arraycirculardesc;
        this.arraycirculardate=arraycirculardate;
        this.arraycircularimage=arraycircularimage;


        //layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public circular_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_circular_list,parent,false);
   //    v.setOnClickListener(mOnClickListener);
        ViewHolder viewHolder=new ViewHolder(v);

       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final circular_adapter.ViewHolder holder, final int position) {
        holder.textview_title.setText(arraycirculartitle.get(position));
        holder.textview_date.setText(arraycirculardate.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc=arraycirculardesc.get(position);
                String date=arraycirculardate.get(position);
                String title=arraycirculartitle.get(position);
                String image=arraycircularimage.get(position);
                Intent intent=new Intent(activity,layout_circular_description.class);
                intent.putExtra("desc",desc);
                intent.putExtra("date",date);
                intent.putExtra("title",title);
                intent.putExtra("image",image);

                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arraycircularId.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_title,textview_date;
        public ViewHolder(View itemView) {
            super(itemView);



            textview_title= (TextView) itemView.findViewById(R.id.textview_title);

            textview_date= (TextView) itemView.findViewById(R.id.textview_date);

        }
    }

 /*   private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            int itemPosition = mRecyclerView.getChildLayoutPosition(view);
            String item = mList.get(itemPosition);
            Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();
        }
    }*/








  /*  @Override
    public int getCount() {
        return arraycircularId.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }*/
/*
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final circular_adapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_circular_list, null);
            viewHolder = new circular_adapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (circular_adapter.ViewHolder) convertView.getTag();
        }
        viewHolder.textview_title.setText(arraycirculartitle.get(position));
        viewHolder.textview_date.setText(arraycirculardate.get(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 String desc=arraycirculardesc.get(position);
                 String date=arraycirculardate.get(position);
                    String title=arraycirculartitle.get(position);
                    String image=arraycircularimage.get(position);
                  Intent intent=new Intent(activity,layout_circular_description.class);
                        intent.putExtra("desc",desc);
                        intent.putExtra("date",date);
                    intent.putExtra("title",title);
                    intent.putExtra("image",image);

                       activity.startActivity(intent);
                }
            });

                return convertView;
    }*/




    /*public class ViewHolder {
        TextView textview_title,textview_date;

        public ViewHolder(View view) {
            textview_title= (TextView) view.findViewById(R.id.textview_title);

            textview_date= (TextView) view.findViewById(R.id.textview_date);

        }
    }*/



    //////////////////////////////////////////Image Share/////////////////////////////////////////////////////////////////////////////////////////

   /* public Uri getLocalBitmapUri(ImageView imageView1) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }*/



}
