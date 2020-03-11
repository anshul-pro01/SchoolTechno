package com.example.passwordnapoochiyo.schooltechno;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class homeworkpage extends AppCompatActivity {
    SQLiteAdapter adapter;
    private RecyclerView recyclerView;
        HomeworkAdapter homeworkadapter;

   // private List studentDataList =new ArrayList<>();

    ArrayList<EmployeeData> employeeDataArrayList;
    ArrayList<String> array_homeworkid=new ArrayList<>();
    ArrayList<String> array_homeworktitle=new ArrayList<>();
    ArrayList<String> array_homeworkdate=new ArrayList<>();
    ArrayList<String> array_staffname=new ArrayList<>();
    ArrayList<String> array_homeworkimage=new ArrayList<>();
    RequestQueue requestQueue;

String id,name,stud_id;
    String hwa_id,h_content,h_date,staff_name,h_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeworkpage);

        adapter=new SQLiteAdapter(this);
        ArrayList<EmployeeData> employeeDataArrayList=new ArrayList<>();
        employeeDataArrayList=getdata();
        recyclerView =(RecyclerView)findViewById(R.id.rv);
       // studentAdapter=new StudentAdapter(studentDataList);

       // recyclerView.setAdapter(studentAdapter);
       // StudentDataPrepare();
        requestQueue= Volley.newRequestQueue(this);
        new data().execute();

    }

    private ArrayList<EmployeeData> getdata() {

        adapter.openToRead();
        ArrayList<EmployeeData> employeeDataArrayList=adapter.getdata();
        for (int i=0;i<employeeDataArrayList.size();i++)
        {
            stud_id=employeeDataArrayList.get(i).getStud_id();
        }
        return employeeDataArrayList;
    }

    private class data extends AsyncTask<String,String,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            array_homeworkid=new ArrayList<>();
                  array_homeworktitle=new ArrayList<>();
               array_homeworkdate=new ArrayList<>();
              array_staffname=new ArrayList<>();
              array_homeworkimage=new ArrayList<>();
            HashMap<String,String> params=new HashMap<>();
            params.put("fk_stud_id",stud_id);

            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST,ProjectConfig.HOMEWORK,params,this.requesuccess(),this.requeerror());


           /* StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object=new JSONObject(response.toString());
                        JSONArray array=object.getJSONArray("bg_state");
                        for(int i=0;i<array.length();i++)
                        {
                           JSONObject object1=array.getJSONObject(i);
                           id=object1.getString("doc_id");
                           name=object1.getString("doc_name");
                           array_id.add(id);
                           array_name.add(name);
                        }
                        studentAdapter=new StudentAdapter(array_id,array_name);
                        recyclerView.setAdapter(studentAdapter);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });*/
            requestQueue.add(jsObjRequest);
            return null;
        }

        private Response.ErrorListener requeerror() {
            return new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            };
        }

        private Response.Listener<JSONObject> requesuccess() {
            return new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try {
                        JSONObject object=new JSONObject(response.toString());
                        JSONArray jsonArray=object.getJSONArray("bg_state");
                        for(int j=0;j<jsonArray.length();j++)
                        {
                            JSONObject object1=jsonArray.getJSONObject(j);
                            hwa_id=object1.getString("hwa_id");
                            h_content=object1.getString("h_content");
                            h_date=object1.getString("h_date");
                            staff_name=object1.getString("staff_name");
                            h_image=object1.getString("h_image");
                            array_homeworkid.add(hwa_id);
                            array_homeworkdate.add(h_date);
                            array_homeworkimage.add(h_image);
                            array_homeworktitle.add(h_content);
                            array_staffname.add(staff_name);


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RecyclerView.LayoutManager manager=new LinearLayoutManager(homeworkpage.this);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.addItemDecoration(new DividerItemDecoration(homeworkpage.this, LinearLayoutManager.VERTICAL));
                    homeworkadapter=new HomeworkAdapter(homeworkpage.this,array_homeworkid,array_homeworktitle,array_homeworkdate,array_staffname,array_homeworkimage);
                    recyclerView.setAdapter(homeworkadapter);

                }
            };
        }
    }
  /*  private void StudentDataPrepare() {
        studentData data=new studentData("sai","12");
        studentDataList.add(data);
        data=new studentData("sai","25");
        studentDataList.add(data);
        data=new studentData("raghu","20");
        studentDataList.add(data);
        data=new studentData("raj","28");+
        studentDataList.add(data);
        data=new studentData("amar","15");
        studentDataList.add(data);
        data=new studentData("bapu","19");
        studentDataList.add(data);
        data=new studentData("chandra","52");
        studentDataList.add(data);
        data=new studentData("deraj","30");
        studentDataList.add(data);
        data=new studentData("eshanth","28");
        studentDataList.add(data);
        data=new studentData("eshanth","28");
        studentDataList.add(data);
        data=new studentData("eshanth","28");
        studentDataList.add(data);
    }*/

}
