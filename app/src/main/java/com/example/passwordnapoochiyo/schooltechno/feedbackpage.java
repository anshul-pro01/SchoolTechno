package com.example.passwordnapoochiyo.schooltechno;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Process;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class feedbackpage extends AppCompatActivity implements View.OnClickListener {
String pid,feedback;
ProgressDialog pdialog;
    EditText editTextfeedback,editTextname;
    Button button;
SQLiteAdapter adapter;
RequestQueue queue;
 ArrayList<EmployeeData> employeeDataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackpage);
        editTextfeedback=(EditText)findViewById(R.id.editfeedback);
        button=(Button)findViewById(R.id.submit);
       // editTextname=(EditText)findViewById(R.id.edtname);
        adapter=new SQLiteAdapter(this);
        queue= Volley.newRequestQueue(this);
        ArrayList<EmployeeData> employeeDataArrayList=new ArrayList<>();
        employeeDataArrayList=getdata();
        button.setOnClickListener(this);


    }

    private ArrayList<EmployeeData> getdata() {
        adapter.openToRead();
        ArrayList<EmployeeData> employeeDataArrayList=adapter.getdata();
        for(int i=0;i<employeeDataArrayList.size();i++)
        {
             pid=employeeDataArrayList.get(i).getParentId();
        }

        return employeeDataArrayList;
    }

    @Override
    public void onClick(View view) {
        pdialog=new ProgressDialog(this);
        pdialog.setMessage("Loading..");
        pdialog.setCancelable(false);
        pdialog.show();
        feedback= editTextfeedback.getText().toString();
        if(feedback.equals(""))
        {
            if (pdialog.isShowing())
                pdialog.dismiss();
            AlertDialog.Builder dialog=new AlertDialog.Builder(this,R.style.AlertDialogStyle);
            dialog.setMessage("please fill feedback");
            dialog.setCancelable(true);
            dialog.show();

        }
        else
            {

                HashMap<String,String> params=new HashMap<>();
                params.put("f_feedback",feedback);
                params.put("fk_p_id",pid);
            CustomRequest request=new CustomRequest(Request.Method.POST,ProjectConfig.FEEDBACK,params,this.resultsuccess(),this.resulterror());

      queue.add(request);
            }

    }

    private Response.ErrorListener resulterror() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 if(pdialog.isShowing())
                     pdialog.dismiss();
            }
        };
    }

    private Response.Listener<JSONObject> resultsuccess() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (pdialog.isShowing())
                    pdialog.dismiss();

              AlertDialog.Builder builder=new AlertDialog.Builder(feedbackpage.this,R.style.AlertDialogStyle);
              builder.setMessage("feedback proceed success");
              builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      editTextfeedback.setText("");
                  }
              });
              builder.show();

            }
        };
    }
}
