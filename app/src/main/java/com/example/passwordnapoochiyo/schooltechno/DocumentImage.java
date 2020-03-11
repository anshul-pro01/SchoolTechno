package com.example.passwordnapoochiyo.schooltechno;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class DocumentImage extends AppCompatActivity {
    TextView title;
    CardView img;

    PDFView pdf;
 DownloadManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_image);
        RequestQueue requestQueue;
        // pDialog = new ProgressDialog(this);

        title = (TextView) findViewById(R.id.title);
        pdf = (PDFView) findViewById(R.id.pdfview);
        img = (CardView) findViewById(R.id.downlodaimage);
        Intent in = getIntent();
        String name = in.getStringExtra("doc_name");

        title.setText(name);
        final String path = in.getStringExtra("pdfpath");
        //    Toast.makeText(this,path, Toast.LENGTH_SHORT).show();


        new readpdf().execute(path);

       img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 manager=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                Uri uri=Uri.parse(path);

                DownloadManager.Request request=new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference=manager.enqueue(request);




            }

        });

    }


    class readpdf extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream input = null;

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (connection.getResponseCode() == 200) {
                    input = new BufferedInputStream(connection.getInputStream());
                }
            } catch (IOException e) {
                return null;
            }

            return input;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdf.fromStream(inputStream).load();
        }

    }

}








