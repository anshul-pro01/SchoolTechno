package com.example.passwordnapoochiyo.schooltechno;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import android.content.Intent;
class Gallarycontent_Adapter extends BaseAdapter {

    LayoutInflater inflater2=null;
    Activity a;
    ArrayList<String> arrayListfilename=new ArrayList<>();
    ArrayList<String> arrayListimage=new ArrayList<>();;
    ArrayList<String> arrayListvideo=new ArrayList<>();;
    ArrayList<String> arrayListfiletype=new ArrayList<>();;


    public Gallarycontent_Adapter(Activity a, ArrayList<String> arrayListfilename,
                                  ArrayList<String>  arrayListimage,
                                  ArrayList<String> arrayListvideo,
                                  ArrayList<String> arrayListfiletype) {
        this.a = a;
        this.arrayListfilename = arrayListfilename;
        this.arrayListimage = arrayListimage;
        this.arrayListvideo = arrayListvideo;
        this.arrayListfiletype = arrayListfiletype;
        inflater2=(LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayListfilename.size();
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
        final Gallarycontent_Adapter.Viewholder viewholder;
        if (view == null) {
            view = inflater2.inflate(R.layout.layout_gallarycontent, null);
            viewholder = new Gallarycontent_Adapter.Viewholder(view);
            view.setTag(viewholder);
        } else {
            viewholder = (Gallarycontent_Adapter.Viewholder) view.getTag();
        }
     //   viewholder.textview.setText(arrayListfilename.get(i));
        if(arrayListfiletype.get(i).equals("Image"))
        {
            Glide.with(a).load(arrayListimage.get(i))
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(viewholder.image);
            viewholder.video.setVisibility(View.GONE);
            viewholder.image.setVisibility(View.VISIBLE);

        }
        else
        {
            viewholder.video.setVisibility(View.VISIBLE);
            viewholder.image.setVisibility(View.GONE);
        }
      viewholder.video.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Uri uri = Uri.parse("https://www.youtube.com/watch?v=PCwL3-hkKrg");
              viewholder.video.setVideoURI(uri);
              viewholder.video.start();
          }
      });

        viewholder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String img=arrayListimage.get(i);
                Intent intent=new Intent(a,gallarycontentimage.class);
                intent.putExtra("viewimage",img);
                a.startActivity(intent);
            }
        });

        return view;
    }

    public class Viewholder {
        ImageView image;
        TextView textview;
        VideoView video;
        public Viewholder(View view) {
             image=view.findViewById(R.id.gallarycontentimage);
           // textview=view.findViewById(R.id.gallarycontentimagename);
            video=view.findViewById(R.id.video);

        }
    }
}
