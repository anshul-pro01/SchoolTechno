package com.example.passwordnapoochiyo.schooltechno;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import android.content.Intent;
import java.util.ArrayList;

class GallarypageAdapter extends BaseAdapter {
    ArrayList<String> arrayeventid=new ArrayList<>();
    ArrayList<String> arrayeventname=new ArrayList<>();
    ArrayList<String> arrayeventdate=new ArrayList<>();
    ArrayList<String> arrayeventtime=new ArrayList<>();
    ArrayList<String> arrayeventimage=new ArrayList<>();
    Activity a;
    LayoutInflater inflater=null;
    public GallarypageAdapter(Activity activity, ArrayList<String> arrayeventid,
                              ArrayList<String> arrayeventname, ArrayList<String> arrayeventdate,
                              ArrayList<String> arrayeventtime, ArrayList<String> arrayeventimage) {
       this.a=activity;
       this.arrayeventid=arrayeventid;
       this.arrayeventname=arrayeventname;
       this.arrayeventdate=arrayeventdate;
       this.arrayeventtime=arrayeventtime;
       this.arrayeventimage=arrayeventimage;
       inflater=(LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return arrayeventid.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final GallarypageAdapter.Viewholder viewholder;
        if (view == null) {
            view = inflater.inflate(R.layout.layout_gallary, null);
            viewholder = new GallarypageAdapter.Viewholder(view);
            view.setTag(viewholder);
        } else {
            viewholder = (GallarypageAdapter.Viewholder) view.getTag();
        }
        viewholder.textview_name.setText(arrayeventname.get(i));


       /* Picasso.with(a)
                .load(arrayeventimage.get(i))
                .fit() // will explain later
                .into((viewholder.image.) view);*/


        Glide.with(a).load(arrayeventimage.get(i))
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewholder.image);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String eventId=arrayeventid.get(i);
               Intent intent5=new Intent(a,gallarycontent.class);
               intent5.putExtra("eventid",eventId);
               a.startActivity(intent5);
               }
        });


        return view;
    }


    public class Viewholder {
        TextView textview_name;
        ImageView image;

        public Viewholder(View view) {
            textview_name= (TextView) view.findViewById(R.id.imagetitle);
            image=(ImageView)view.findViewById(R.id.imageView);
        }
    }
}
