package com.example.passwordnapoochiyo.schooltechno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

public class layout_circular_description extends AppCompatActivity {
TextView title;
TextView date;
TextView desc;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_circular_description);

        date=(TextView)findViewById(R.id.date);
        title=(TextView)findViewById(R.id.title);
        desc=(TextView)findViewById(R.id.description);
        img=(ImageView)findViewById(R.id.imageview);
        Intent intent=getIntent();
        String datevalue=intent.getStringExtra("date");
        String titlevalue=intent.getStringExtra("title");
        String descvalue=intent.getStringExtra("desc");
      String imagevalue=intent.getStringExtra("image");
        date.setText(datevalue);
        title.setText(titlevalue);
         desc.setText(descvalue);

       /* Glide.with(layout_circular_description.this).load(imagevalue)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);*/
        Picasso.with(layout_circular_description.this).load(imagevalue).into(img);
    }
}
