package com.example.passwordnapoochiyo.schooltechno;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class homework_image extends AppCompatActivity implements View.OnClickListener {
    TextView hw_title, teacher;
    ImageView Image;
    CardView image_download;
    String hw_image, dirPath, fileName;
    File file;
    private ScaleGestureDetector mScaleGestureDetector;
    private float ScaleFactor = 1.0f;
    DownloadManager manager;
    private AsyncTask mMyTask;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_image);
        Intent intent = getIntent();
        String ass_name = intent.getStringExtra("title");
        String teacher_name = intent.getStringExtra("staffname");
        String hw_image = intent.getStringExtra("image");
        Image = (ImageView) findViewById(R.id.view_homeworkimage);
        image_download = (CardView) findViewById(R.id.imagedownload);
        hw_title = (TextView) findViewById(R.id.imagetitle);
        teacher = (TextView) findViewById(R.id.teacher_name);

        hw_title.setText(ass_name);
        teacher.setText(teacher_name);
        Picasso.with(homework_image.this).load(hw_image).into(Image);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        image_download.setOnClickListener(this);


    }

    public void imageDownload(homework_image ctx, String url) {
        Picasso.with(homework_image.this)
                .load(hw_image)
                .into(getTarget(Image));
    }

    private Target getTarget(final ImageView url) {
        Target target = new Target(){

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + url);
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                            ostream.flush();
                            ostream.close();
                        } catch (IOException e) {
                            Log.e("IOException", e.getLocalizedMessage());
                        }
                    }
                }).start();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }



    public boolean onTouchEvent (MotionEvent motionEvent){
            mScaleGestureDetector.onTouchEvent(motionEvent);
            return true;
        }


    @Override
    public void onClick(View view) {


        //target to save

    }

    class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            ScaleFactor *= scaleGestureDetector.getScaleFactor();
            ScaleFactor = Math.max(0.1f,
                    Math.min(ScaleFactor, 10.0f));
            Image.setScaleX(ScaleFactor);
            Image.setScaleY(ScaleFactor);
            return true;
        }
    }


}









