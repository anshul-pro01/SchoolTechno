package com.example.passwordnapoochiyo.schooltechno;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class examlist extends AppCompatActivity {
    private String url="http://reliablefastsms.com/technoschool/api/examListDetails.php?fk_exam_id=1";
    TextView edate1,edate2,edate3,edate4,edate5,eday1,eday2,eday3,eday4,eday5,esub1,esub2,esub3,esub4,esub5;
    TextView cls,tim;
    String e_name,e_id,e_timings,e_date,e_day,e_subject,c,d;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<SubList> subList;
    private RecyclerView exList;
    private RecyclerView.Adapter adapter;
    int k,l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examlist);

        exList=findViewById(R.id.examsub_list);
        subList=new ArrayList<>();
        adapter=new ExamListAdapter(getApplicationContext(),subList);

        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration=new DividerItemDecoration(exList.getContext(),linearLayoutManager.getOrientation());

        cls=(TextView) findViewById(R.id.heading_exam_class);
        tim=(TextView) findViewById(R.id.exam_timings_string);



        exList.setHasFixedSize(true);
        exList.setLayoutManager(linearLayoutManager);
        exList.addItemDecoration(dividerItemDecoration);
        exList.setAdapter(adapter);

        getData();


        RequestQueue requestQueue;

        Intent in = getIntent();
        c=in.getStringExtra("exam_class");
        cls.setText(c);
        d=in.getStringExtra("exam_id");

       /* exList.setHasFixedSize(true);
        exList.setLayoutManager(linearLayoutManager);
        exList.addItemDecoration(dividerItemDecoration);
        exList.setAdapter(adapter);*/





       /* edate1=(TextView)findViewById(R.id.date1);
        edate2=(TextView)findViewById(R.id.date2);
        edate3=(TextView)findViewById(R.id.date3);
        edate4=(TextView)findViewById(R.id.date4);
        edate5=(TextView)findViewById(R.id.date5);
        eday1=(TextView)findViewById(R.id.day1);
        eday2=(TextView)findViewById(R.id.day2);
        eday3=(TextView)findViewById(R.id.day3);
        eday4=(TextView)findViewById(R.id.day4);
        eday5=(TextView)findViewById(R.id.day5);
        esub1=(TextView)findViewById(R.id.exam1info);
        esub2=(TextView)findViewById(R.id.exam2info);
        esub3=(TextView)findViewById(R.id.exam3info);
        esub4=(TextView)findViewById(R.id.exam4info);
        esub5=(TextView)findViewById(R.id.exam5info);*/

        /*String dt1 = in.getStringExtra("exam_date_1");
        edate1.setText(dt1);
        String da1=in.getStringExtra("exam_day_1");
        eday1.setText(da1);
        String s1=in.getStringExtra("exam_subject_1");
        esub1.setText(s1);
        String dt2 = in.getStringExtra("exam_date_2");
        edate2.setText(dt2);
        String da2=in.getStringExtra("exam_day_2");
        eday2.setText(da2);
        String s2=in.getStringExtra("exam_subject_2");
        esub2.setText(s2);
        String dt3 = in.getStringExtra("exam_date_3");
        edate3.setText(dt3);
        String da3=in.getStringExtra("exam_day_3");
        eday3.setText(da3);
        String s3=in.getStringExtra("exam_subject_3");
        esub3.setText(s3);
        String dt4 = in.getStringExtra("exam_date_4");
        edate4.setText(dt4);
        String da4=in.getStringExtra("exam_day_4");
        eday4.setText(da4);
        String s4=in.getStringExtra("exam_subject_4");
        esub4.setText(s4);
        String dt5 = in.getStringExtra("exam_date_5");
        edate5.setText(dt5);
        String da5=in.getStringExtra("exam_day_5");
        eday5.setText(da5);
        String s5=in.getStringExtra("exam_subject_5");
        esub5.setText(s5);
        String t=in.getStringExtra("timings");
        tim.setText(t);*/

    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray sub = response.getJSONArray("bg_state");
                        for(int i=0;i< sub.length();i++){
                            JSONObject js=sub.getJSONObject(i);

                            SubList ss=new SubList();
                            ss.setEs_id(js.getString("es_id"));
                            ss.setExam_date(js.getString("es_date"));
                            ss.setExam_timing(js.getString("es_time"));
                            ss.setExam_subject(js.getString("sub_name"));

                            e_timings=js.getString("es_time");
                            subList.add(ss);
                    }
                    tim.setText(e_timings);

                }catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }

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

}
