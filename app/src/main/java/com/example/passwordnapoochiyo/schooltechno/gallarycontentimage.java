package com.example.passwordnapoochiyo.schooltechno;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Intent;


import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import static android.icu.text.DateFormat.NONE;

public class gallarycontentimage extends AppCompatActivity {
    ImageView imageView;
    String viewimg;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallarycontentimage);

        imageView = (ImageView) findViewById(R.id.imageView_dispay);

        Intent intent = getIntent();
        String viewimg = intent.getStringExtra("viewimage");
        Picasso.with(gallarycontentimage.this).load(viewimg).into(imageView);
       /* Glide.with(gallarycontentimage.this).load(viewimg).into(imageView);
        TouchImageView img = new TouchImageView(gallarycontentimage.this);
        //img.setImageResource(imagepath);
       Glide.with(gallarycontentimage.this).load(viewimg).into(imageView);
        img.setMaxZoom(4f);
        setContentView(imageView);*/
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }
}


