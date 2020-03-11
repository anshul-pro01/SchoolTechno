package com.example.passwordnapoochiyo.schooltechno;

import android.app.ProgressDialog;
import android.media.Image;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.net.wifi.WifiConfiguration.Status.strings;
import static android.util.Log.d;

public class documentpage extends AppCompatActivity {
    public ProgressDialog pDialog;
    RecyclerView recyclerView;
    document_Adapter adapter;
    TextView title, name1;
    ImageView image;
    String id, name, docfile;
    ArrayList<String> array_doctId = new ArrayList<>();
    ArrayList<String> array_doctName = new ArrayList<>();
    ArrayList<String> array_file = new ArrayList<>();
    String url = "http://reliablefastsms.com/technoschool/api/documentList.php";

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentpage);
        queue = Volley.newRequestQueue(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 0));
        pDialog = new ProgressDialog(documentpage.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        new get().execute();
      /*  JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = object.getJSONArray("bg_state");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object1 = array.getJSONObject(i);
                        id = object1.getString("doc_id");
                        name=object1.getString("doc_name");
                        Toast.makeText(documentpage.this,id+name+"\n", Toast.LENGTH_SHORT).show();
                    }
                       Log.d("##",id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
         queue.add(request);*/

    }

    private class get extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {


            StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(pDialog.isShowing())
                        pDialog.dismiss();
                    try {

                        JSONObject object=new JSONObject(response.toString());
                        JSONArray array=object.getJSONArray("bg_state");
                        for(int i=0;i<array.length();i++)
                        {
                            JSONObject object1=array.getJSONObject(i);
                            id=object1.getString("doc_id");
                            name=object1.getString("doc_name");
                            docfile=object1.getString("doc_file");
                            array_doctId.add(id);
                            array_doctName.add(name);
                            array_file.add(docfile);
                           // Toast.makeText(documentpage.this,id+"\n"+name+"\n"+docfile, Toast.LENGTH_LONG).show();
                        }

                        adapter=new document_Adapter(array_doctId,array_doctName,array_file);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(pDialog.isShowing())
                        pDialog.dismiss();
                    Log.i("##", "##" + error.toString());

                }
            });
            queue.add(request);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


    }
}
