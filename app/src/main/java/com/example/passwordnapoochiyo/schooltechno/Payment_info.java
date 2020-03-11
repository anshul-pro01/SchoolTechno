package com.example.passwordnapoochiyo.schooltechno;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class Payment_info  extends android.support.v4.app.Fragment {
    int total_amount;
   String studid;
   ListView listView;
 Listadapter adapter;
 RequestQueue requestQueue;
  SQLiteAdapter sqLiteAdapter;
  ArrayList<EmployeeData> employeeDataArrayList;
  ArrayList<String> Arrayfeesdate=new ArrayList<>();
  ArrayList<String> Arrayfees=new ArrayList<>();
    ArrayList<String> Arrayfeesid=new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.payment_info, container, false);
        sqLiteAdapter=new SQLiteAdapter(getActivity());
        listView=(ListView)v.findViewById(R.id.listview_fees);
        ArrayList<EmployeeData> employeeDataArrayList=new ArrayList<>();
        employeeDataArrayList=getdata();
        requestQueue= Volley.newRequestQueue(getActivity());
        HashMap<String,String> params=new HashMap<>();
        params.put("stud_id",studid);
        CustomRequest request=new CustomRequest(Request.Method.POST,ProjectConfig.FEESDETAI,params,this.requestsuccess(),this.requesterror());
        requestQueue.add(request);
        return v;

    }

    private Response.ErrorListener requesterror() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
    }

    private Response.Listener<JSONObject> requestsuccess() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("bg_state");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String feesid=jsonObject.getString("sf_id");
                        String fees=jsonObject.getString("sf_fees");
                        String feesdate=jsonObject.getString("sf_date");
                        int count=i+1;
                        String sr=String.valueOf(count);
                        Arrayfeesdate.add(feesdate);
                        Arrayfees.add(fees);
                        Arrayfeesid.add(sr);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter=new Listadapter(getActivity(),Arrayfeesid,Arrayfeesdate,Arrayfees);
                listView.setAdapter(adapter);
            }
        };
    }

    private ArrayList<EmployeeData> getdata() {
        ArrayList<EmployeeData> employeeDataArrayList=new ArrayList<>();
        sqLiteAdapter.openToRead();
        employeeDataArrayList=sqLiteAdapter.getdata();
        for(int i=0;i<employeeDataArrayList.size();i++)
        {
             studid=employeeDataArrayList.get(i).getStud_id();

        }


        return employeeDataArrayList;
    }



}

