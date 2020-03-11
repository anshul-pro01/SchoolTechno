package com.example.passwordnapoochiyo.schooltechno;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class gallarycontent extends AppCompatActivity {
         RequestQueue queue;
         ProgressDialog pDialog;
         GridView gridView;
         String Eventid;
         String url="http://reliablefastsms.com/technoschool/api/eventDetails.php";
        Gallarycontent_Adapter adapter;
         ArrayList<String> arrayListfilename=new ArrayList<>();
       ArrayList<String> arrayListimage=new ArrayList<>();
       ArrayList<String> arrayListvideo=new ArrayList<>();
        ArrayList<String> arrayListfiletype=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallarycontent);
          gridView=(GridView)findViewById(R.id.gridcontent);
          queue= Volley.newRequestQueue(this);

        Intent i=getIntent();
       Eventid=i.getStringExtra("eventid");
        Log.d("######",Eventid);
         makegallarycontentdata();
    }

    private void makegallarycontentdata() {
       pDialog=new ProgressDialog(gallarycontent.this);
       pDialog.setMessage("Loding..");
       pDialog.setCancelable(false);
     pDialog.show();
     arrayListfilename=new ArrayList<>();
        arrayListimage=new ArrayList<>();
        arrayListvideo=new ArrayList<>();
        arrayListfiletype=new ArrayList<>();
        HashMap<String,String> params=new HashMap<>();
        Log.d("####",Eventid);
        params.put("fk_event_id",Eventid);
       CustomRequest requestcustom=new CustomRequest(Request.Method.POST,ProjectConfig.Gallarycontent,params, this.createrequestsuccess1(), this.createRequestErrorListenerCustomer3());
       queue.add(requestcustom);
    }



    private Response.ErrorListener createRequestErrorListenerCustomer3() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
            }
        };
    }

    private Response.Listener<JSONObject> createrequestsuccess1() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
                try {
                    JSONArray jsonArray=response.getJSONArray("bg_state");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String evdfilename=jsonObject.getString("evd_filename");
                        String evdfilepath=jsonObject.getString("evd_filepath");
                        String evdvideopath=jsonObject.getString("evd_videopath");
                        String evdfiletype=jsonObject.getString("evd_filetype");
                        arrayListfilename.add(evdfilename);
                        arrayListimage.add(evdfilepath);
                        arrayListvideo.add(evdvideopath);
                        arrayListfiletype.add(evdfiletype);
                        Log.d("evdfilename",evdfilename);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
             adapter=new Gallarycontent_Adapter(gallarycontent.this,arrayListfilename,arrayListimage,arrayListvideo,arrayListfiletype);
               gridView.setAdapter(adapter);
            }
        };
    }


}
