package com.example.passwordnapoochiyo.schooltechno;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

public class ResultMain extends AppCompatActivity {

    private String url="http://reliablefastsms.com//technoschool/api/examList.php?fk_school_id=1";

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private RecyclerView rsList;
    private List<Exam> examList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_main);


        rsList=findViewById(R.id.class_list);

        examList=new ArrayList<>();
        adapter=new ResultMainAdapter(getApplicationContext(),examList);

        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration=new DividerItemDecoration(rsList.getContext(),linearLayoutManager.getOrientation());

        rsList.setHasFixedSize(true);
        rsList.setLayoutManager(linearLayoutManager);
        rsList.addItemDecoration(dividerItemDecoration);
        rsList.setAdapter(adapter);

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

                        Exam exam = new Exam();
                        exam.setExam_id(jsonObject.getString("exam_id"));
                        exam.setExam_name(jsonObject.getString("exam_name"));

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
