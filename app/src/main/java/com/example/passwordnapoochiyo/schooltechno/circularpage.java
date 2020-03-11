package com.example.passwordnapoochiyo.schooltechno;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class circularpage extends AppCompatActivity   {
    CardView cardview;
     TextView textdate,texttitle;
     SQLiteAdapter adapter;
    ProgressDialog pDialog;
     ArrayList<EmployeeData> employeeDataArrayList;
     ArrayList<String> arraycircularId=new ArrayList<>();
    ArrayList<String> arraycirculartitle=new ArrayList<>();
    ArrayList<String> arraycirculardesc=new ArrayList<>();
    ArrayList<String> arraycirculardate=new ArrayList<>();
    ArrayList<String> arraycircularimage=new ArrayList<>();
      circular_adapter circularadapter;
    //ListView listview_circular;
    RecyclerView recyclerView;
     String school_id,url,title,date,image,desc,asd,cir_id;
     RequestQueue mque;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circularpage);
        adapter=new SQLiteAdapter(this);
        ArrayList<EmployeeData> employeeDataArrayList=new ArrayList<>();
        mque= Volley.newRequestQueue(this);

       employeeDataArrayList=getdata();

        makeJsonGETCustomer3();
     //   cardview=(CardView)findViewById(R.id.cardview);
     //   cardview.setOnClickListener(this);


        }

ArrayList<EmployeeData> getdata()
{
    adapter.openToRead();
    ArrayList<EmployeeData> employeeDataArrayList=adapter.getdata();
          for(int i=0;i<employeeDataArrayList.size();i++)
          {
              school_id=employeeDataArrayList.get(i).getSchoolid();
          }

       return employeeDataArrayList;
}



    private void makeJsonGETCustomer3()
    {
        pDialog = new ProgressDialog(circularpage.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        arraycircularId=new ArrayList<>();
        arraycirculartitle=new ArrayList<>();
        arraycirculardesc=new ArrayList<>();
        arraycirculardate=new ArrayList<>();
        arraycircularimage=new ArrayList<>();
        Map<String, String> params=new HashMap<>();
        params.put("fk_school_id",school_id);
        // System.out.print("#####vendor_id=="+vendor_id);
        RequestQueue requestQueue = Volley.newRequestQueue(circularpage.this);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, ProjectConfig.Circular_List, params, this.createRequestSuccessListenerCustomer3(), this.createRequestErrorListenerCustomer3());
        requestQueue.add(jsObjRequest);
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
    private Response.Listener<JSONObject> createRequestSuccessListenerCustomer3() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if (pDialog.isShowing())
                    pDialog.dismiss();
                try
                {
                    JSONArray data1 = response.getJSONArray("bg_state");
                    System.out.print("##VendorLeads=="+data1);
                    for(int i=0;i<data1.length();i++)
                    {
                        JSONObject jobj = data1.getJSONObject(i);
                        String  Id = jobj.getString("c_id");
                        String  Title = jobj.getString("c_title");
                        String  Date = jobj.getString("c_date");
                        String Description= jobj.getString("c_desc");
                         String img=jobj.getString("c_img");

                        arraycircularId.add(Id);
                        arraycirculartitle.add(Title);
                        arraycirculardesc.add(Description);
                        arraycirculardate.add(Date);
                        arraycircularimage.add(img);


                    }
                } catch (Exception e) {
                    System.out.println("Error in http connection " + e.toString());
                }
                Log.i("##", "##" + response.toString());
               // circular_adapter=new circular_adapter(circularpage.this,arraycircularId,arraycirculartitle,arraycirculardesc,arraycirculardate,arraycircularimage);
               // listview_circular.setAdapter(circular_adapter);
                recyclerView=(RecyclerView)findViewById(R.id.recycler_circular);
                RecyclerView.LayoutManager manager=new LinearLayoutManager(circularpage.this);
                recyclerView.setLayoutManager(manager);
                recyclerView.setHasFixedSize(true);
                recyclerView.addItemDecoration(new DividerItemDecoration(circularpage.this,0));
                circularadapter=new circular_adapter(circularpage.this,arraycircularId,arraycirculartitle,arraycirculardesc,arraycirculardate,arraycircularimage);
                  recyclerView.setAdapter(circularadapter);
            }
        };
    };
















  /*  @Override
    public void onClick(View view) {
          view.getId();
          Intent intent=new Intent(this,layout_circular_description.class);
          intent.putExtra("id",);
          startActivity(intent);
    }           */


}
