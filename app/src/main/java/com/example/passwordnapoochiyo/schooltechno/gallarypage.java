package com.example.passwordnapoochiyo.schooltechno;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.Map;

public class gallarypage extends AppCompatActivity {
    ProgressDialog pDialog;
    SQLiteAdapter adapter;

    GridView gridView;
    RequestQueue mqueue;

    String url = "http://reliablefastsms.com/technoschool/api/eventList.php";
    String schoolid;
    ArrayList<EmployeeData> employeeDataArrayList;
    ArrayList<String> arrayeventid = new ArrayList<>();
    ArrayList<String> arrayeventname = new ArrayList<>();
    ArrayList<String> arrayeventtime = new ArrayList<>();
    ArrayList<String> arrayeventdate = new ArrayList<>();
    ArrayList<String> arrayeventimage = new ArrayList<>();
    GallarypageAdapter gallaryadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallarypage);
        adapter=new SQLiteAdapter(this);
        gridView = (GridView) findViewById(R.id.gallary_gridview);
        mqueue = Volley.newRequestQueue(this);
        ArrayList<EmployeeData> employeeDataArrayList=new ArrayList<>();
        employeeDataArrayList = getdata();
        Makejsongallarydata();

    }


    ArrayList<EmployeeData> getdata() {
        adapter.openToRead();
        ArrayList<EmployeeData> employeeDataArrayList = adapter.getdata();
        for (int i = 0;i < employeeDataArrayList.size();i++) {
            schoolid = employeeDataArrayList.get(i).getSchoolid();
        }
        return employeeDataArrayList;
    }

    private void Makejsongallarydata() {
        pDialog = new ProgressDialog(gallarypage.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        arrayeventid = new ArrayList<>();
        arrayeventname = new ArrayList<>();
        arrayeventtime = new ArrayList<>();
        arrayeventdate = new ArrayList<>();
        arrayeventimage = new ArrayList<>();
        Map<String, String> params = new HashMap<>();
        params.put("fk_school_id", schoolid);
        mqueue = Volley.newRequestQueue(this);
        CustomRequest request = new CustomRequest(Request.Method.POST, ProjectConfig.Gallarypage, params, this.createrequestsuccess(), this.createRequestErrorListenerCustomer3());
        mqueue.add(request);
    }

    private Response.ErrorListener createRequestErrorListenerCustomer3() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pDialog.isShowing())
                    pDialog.dismiss();
                Log.i("##", "##" + error.toString());
            }
        };
    }

    private Response.Listener<JSONObject> createrequestsuccess() {
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
                        String eventid=jsonObject.getString("event_id");
                        String eventname=jsonObject.getString("event_name");
                        String eventdate=jsonObject.getString("event_date");
                        String evaenttime=jsonObject.getString("event_time");
                        String eventimage=jsonObject.getString("event_img");
                         arrayeventid.add(eventid);
                         arrayeventname.add(eventname);
                         arrayeventdate.add(eventdate);
                         arrayeventtime.add(evaenttime);
                         arrayeventimage.add(eventimage);
                         Log.d("###",eventid);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                 gallaryadapter=new GallarypageAdapter(gallarypage.this,arrayeventid,arrayeventname,arrayeventdate,arrayeventtime,arrayeventimage);
                  gridView.setAdapter(gallaryadapter);
            }
        };
    }




}