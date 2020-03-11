package com.example.passwordnapoochiyo.schooltechno;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Map;

public class FriFragment extends Fragment {
    ListView list;
    TextView sub, staff;
    String schoolid, stdname, subname, staffname, divname,time,day;
    SQLiteAdapter sqLiteAdapter;

    ArrayList<EmployeeData> employeeDataArrayList;

    // ProgressDialog dialog;
    RequestQueue queue;

    ArrayList<String> array_subject = new ArrayList<>();
    ArrayList<String> array_staffname = new ArrayList<>();
    ArrayList<String> array_std = new ArrayList<>();
    ArrayList<String> array_div = new ArrayList<>();
    ArrayList<String>  array_time= new ArrayList<>();
    ArrayList<String> array_day = new ArrayList<>();
    public FriFragment() {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        queue = Volley.newRequestQueue(getActivity());
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_fri, container, false);
        sqLiteAdapter=new SQLiteAdapter(getActivity());
        ArrayList<EmployeeData>  employeeDataArrayList=new ArrayList<>();
        list=(ListView)v.findViewById(R.id.listview_timetable1);
        employeeDataArrayList=getdata();
        makedata();
        return v;
    }
    private ArrayList<EmployeeData> getdata() {
        sqLiteAdapter.openToRead();
        ArrayList<EmployeeData> employeeDataArrayList=sqLiteAdapter.getdata();
        for(int i=0;i<employeeDataArrayList.size();i++)
        {
            schoolid=employeeDataArrayList.get(i).getStud_id();

        }
        //Toast.makeText(getActivity(), schoolid, Toast.LENGTH_SHORT).show();
        return employeeDataArrayList;

    }
    private void makedata() {
        array_subject = new ArrayList<>();
        array_staffname = new ArrayList<>();
        array_std = new ArrayList<>();
        array_div = new ArrayList<>();
        array_time= new ArrayList<>();
        array_day= new ArrayList<>();
        //  dialog = new ProgressDialog(getActivity());
        //  dialog.setMessage("Loading..");
        //  dialog.setCancelable(false);
        //  dialog.show();
        Map<String, String> params = new HashMap<>();
        params.put("fk_school_id", schoolid);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, ProjectConfig.TIMETABLE, params, this.requestsuccess(), this.requesterror());
        queue.add(jsObjRequest);


    }
    private Response.ErrorListener requesterror() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //    if (dialog.isShowing())
                //       dialog.dismiss();
            }
        };
    }
    private Response.Listener<JSONObject> requestsuccess() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //  if (dialog.isShowing())
                //      dialog.dismiss();
                try {

                    JSONArray jsonArray = response.getJSONArray("bg_state");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        stdname = object.getString("std_name");
                        subname = object.getString("sub_name");
                        staffname = object.getString("staff_name");
                        divname = object.getString("div_name");
                        time=object.getString("tt_time");
                        day=object.getString("tt_day");
                        if(day.equals("Friday")) {
                            array_subject.add(subname);
                            array_staffname.add(staffname);
                            array_div.add(divname);
                            array_std.add(stdname);
                            array_day.add(day);
                            array_time.add(time);
                            // Toast.makeText(getActivity(), time, Toast.LENGTH_SHORT).show();
                        }

                    }

                    Timetablefragment_Adapter timetable_adapter=new Timetablefragment_Adapter(getActivity(),array_subject,array_staffname,array_time);
                    list.setAdapter(timetable_adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

}
