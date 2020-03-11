package com.example.passwordnapoochiyo.schooltechno;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Result extends AppCompatActivity {

    SQLiteAdapter sqLiteAdapter;
    private String url="http://reliablefastsms.com/technoschool/api/examListResultDetails.php?fk_exam_id=1";

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private RecyclerView rList;
    private List<Result_p> result_pList;
    private RecyclerView.Adapter adapter;
    TextView can_name,rollno,resu,overal,perce,remarks;
    String ename,eid,over="",rem="",s;
    int sub=0;
    double sum=0.0;
    double og;
    double p;
    List<String> al=new ArrayList<String>();



    ArrayList<EmployeeData> employeeDataArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        can_name=(TextView) findViewById(R.id.name_can);
        rollno=(TextView) findViewById(R.id.rollno);
        resu=(TextView) findViewById(R.id.pass_fail);
        overal=(TextView) findViewById(R.id.overall_grade1);
        perce=(TextView) findViewById(R.id.value_percent);
        remarks=(TextView) findViewById(R.id.remarks_info);
        sqLiteAdapter=new SQLiteAdapter(this);
        employeeDataArrayList=new ArrayList<>();
        sqLiteAdapter.openToRead();
        ArrayList<EmployeeData> employeeDataArrayList=employeegetdata();

        rList=findViewById(R.id.result_list);

        result_pList=new ArrayList<>();
        adapter=new ResultAdapter(getApplicationContext(),result_pList);

        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration=new DividerItemDecoration(rList.getContext(),linearLayoutManager.getOrientation());

        rList.setHasFixedSize(true);
        rList.setLayoutManager(linearLayoutManager);
        rList.addItemDecoration(dividerItemDecoration);
        rList.setAdapter(adapter);

        getData();




    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray ja = response.getJSONArray("bg_state");
                    for (int i = 0; i < ja.length(); i++) {

                        JSONObject jsonObject = ja.getJSONObject(i);
                        Result_p r = new Result_p();

                        r.setEs_id(jsonObject.getString("es_id"));
                        r.setSub_name(jsonObject.getString("sub_name"));
                        r.setSub_overall(jsonObject.getString("es_result"));

                        s = jsonObject.getString("es_result");
                        al.add(s);

                        result_pList.add(r);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }

                calculate();
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
    ArrayList<EmployeeData> employeegetdata()
    {
        sqLiteAdapter.openToRead();
        ArrayList<EmployeeData> employeeDataArrayList=sqLiteAdapter.getdata();
        for(int i=0;i<employeeDataArrayList.size();i++)
        {
            ename=employeeDataArrayList.get(i).getName();
            eid=employeeDataArrayList.get(i).getParentId();
        }
        can_name.setText(ename);
        rollno.setText(eid);
        return employeeDataArrayList;
    }

    void calculate(){
        for(int i=0;i<al.size();i++) {
            switch (al.get(i)) {
                case "A+":
                    sub = 10;
                    break;
                case "A":
                    sub = 9;
                    break;
                case "B+":
                    sub = 8;
                    break;
                case "B":
                    sub = 7;
                    break;
                case "C":
                    sub = 6;
                    break;
                case "D":
                    sub = 5;
                    break;
                case "E":
                    sub = 4;
                    break;
                case "F":
                    sub = 3;
                    break;
            }
            sum += sub;
        }


        og=Math.floor((sum/2.0)*100)/100;

        if(og>=9.0 && og<=10.0){
            over="A+";
            rem="PASS";
        }
        else if(og>=8.0 && og<9.0){
            over="A";
            rem="PASS";
        }
        else if(og>=7.0 && og<8.0){
            over="B+";
            rem="PASS";
        }
        else if(og>=6.0 && og<7.0){
            over="B";
            rem="PASS";
        }
        else if(og>=5.0 && og<6.0){
            over="C";
            rem="PASS";
        }
        else if(og>=4.0 && og<5.0){
            over="D";
            rem="PASS";
        }
        else if(og>=3.0 && og<4.0){
            over="E";
            rem="FAIL";
        }
        else if(og<3.0){
            over="F";
            rem="FAIL";
        }


        p=Math.floor((og*9.5)*100)/100;
        overal.setText(over);
        perce.setText(Double.toString(p));
        remarks.setText(rem);
        resu.setText(rem);

    }
   /* void calculate(){

        for(int i=0;i<al.size();i++) {
            switch (al.get(i)) {
                case "A+":
                    sub = 10;
                case "A":
                    sub = 9;
                case "B+":
                    sub = 8;
                case "B":
                    sub = 7;
                case "C":
                    sub = 6;
                case "D":
                    sub = 5;
                case "E":
                    sub = 4;
                case "F":
                    sub = 3;
            }
            sum += sub;
        }


        og=sum/5;
        if(og<=10.0 && og>=9.1){
            over="A+";
            rem="PASS";
        }
        if(og<=9.0 && og>=8.1){
            over="A";
            rem="PASS";
        }
        if(og<=8.0 && og>=7.1){
            over="B+";
            rem="PASS";
        }
        if(og<=7.0 && og>=6.1){
            over="B";
            rem="PASS";
        }
        if(og<=6.0 && og>=5.1){
            over="C";
            rem="PASS";
        }
        if(og<=5.0 && og>=4.1){
            over="D";
            rem="PASS";
        }
        if(og<=4.0 && og>=3.1){
            over="E";
            rem="FAIL";
        }
        else{
            over="F";
            rem="FAIL";
        }


        p=(og*9.5);
        pe=p.toString();


    }
*/

}
