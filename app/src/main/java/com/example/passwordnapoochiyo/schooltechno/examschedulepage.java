package com.example.passwordnapoochiyo.schooltechno;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class examschedulepage extends AppCompatActivity {

    private String url="http://reliablefastsms.com//technoschool/api/examList.php?fk_school_id=1";

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private RecyclerView eList;
    private List<Exam> examList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examschedulepage);

        eList=findViewById(R.id.examschedule_list);

        examList=new ArrayList<>();
        adapter=new ExamAdapter(getApplicationContext(),examList);

        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration=new DividerItemDecoration(eList.getContext(),linearLayoutManager.getOrientation());



        eList.setHasFixedSize(true);
        eList.setLayoutManager(linearLayoutManager);
        eList.addItemDecoration(dividerItemDecoration);
        eList.setAdapter(adapter);

        getData();

        /*card1=(CardView) findViewById(R.id.exam1);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(examschedulepage.this,examlist.class);
                startActivity(intent);
            }
        });*/
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

                        Exam exam = new Exam();
                        exam.setExam_id(jsonObject.getString("exam_id"));
                        exam.setExam_name(jsonObject.getString("exam_name"));
                        /*exam.setStart_date(jsonObject.getString("start_date"));
                        exam.setEnd_date(jsonObject.getString("end_date"));
                        exam.setExam_date_1(jsonObject.getString("exam_date_1"));
                        exam.setExam_day_1(jsonObject.getString("exam_day_1"));
                        exam.setExam_subject_1(jsonObject.getString("exam_subject_1"));
                        exam.setExam_date_2(jsonObject.getString("exam_date_2"));
                        exam.setExam_day_2(jsonObject.getString("exam_day_2"));
                        exam.setExam_subject_2(jsonObject.getString("exam_subject_2"));
                        exam.setExam_date_3(jsonObject.getString("exam_date_3"));
                        exam.setExam_day_3(jsonObject.getString("exam_day_3"));
                        exam.setExam_subject_3(jsonObject.getString("exam_subject_3"));
                        exam.setExam_date_4(jsonObject.getString("exam_date_4"));
                        exam.setExam_day_4(jsonObject.getString("exam_day_4"));
                        exam.setExam_subject_4(jsonObject.getString("exam_subject_4"));
                        exam.setExam_date_5(jsonObject.getString("exam_date_5"));
                        exam.setExam_day_5(jsonObject.getString("exam_day_5"));
                        exam.setExam_subject_5(jsonObject.getString("exam_subject_5"));
                        exam.setTimings(jsonObject.getString("timings"));*/

                        examList.add(exam);
                    }
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