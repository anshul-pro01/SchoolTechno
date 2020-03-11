package com.example.passwordnapoochiyo.schooltechno;

import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class listdisplay extends AppCompatActivity implements View.OnClickListener {
    // TextView textview,textView2,textView3,textView4,textView5,textView6;
    String parentid,password,savedpassword,Newpassword,Confirmpassword;
    ArrayList<EmployeeData> employeeDataArrayList;
    SQLiteAdapter adapter;
    EditText oldpassword,newpassword,confirmpassword;
    Button chandepassword;
     RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdisplay);
      /* textview=(TextView)findViewById(R.id.textname);
        textView2=(TextView)findViewById(R.id.textemail);
        textView3=(TextView)findViewById(R.id.textcontact);
        textView4=(TextView)findViewById(R.id.textgender);

       employeeDataArrayList=new ArrayList<>();

        adapter=new SQLiteAdapter(this);
        adapter.openToRead();
        ArrayList<EmployeeData> employeeDataArrayList=getdata();*/
        employeeDataArrayList=new ArrayList<>();
        adapter=new SQLiteAdapter(this);
        queue=Volley.newRequestQueue(this);
        oldpassword=(EditText)findViewById(R.id.editoldpassword);
        newpassword=(EditText)findViewById(R.id.editnewpassword);
        confirmpassword=(EditText)findViewById(R.id.editconfirmpassword);
        chandepassword=(Button)findViewById(R.id.change);
        chandepassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       savedpassword=oldpassword.getText().toString();
       Newpassword=newpassword.getText().toString();
       Confirmpassword=confirmpassword.getText().toString();
ArrayList<EmployeeData> employeeDataArrayList=getdata();

if(savedpassword.equals(password))
{

    //Toast.makeText(this, "password correct", Toast.LENGTH_SHORT).show();
    if(Newpassword.equals(Confirmpassword))
    {
        HashMap<String,String> params=new HashMap<>();
        params.put("p_id",parentid);
        params.put("p_password",Confirmpassword);
        CustomRequest request=new CustomRequest(Request.Method.POST,ProjectConfig.CHANGEPASSWORD,params,this.requestsuccess(),this.requesterror());
   queue.add(request);
    }
}
else{
    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setMessage("old password incorrect");
    builder.setTitle("Error");
    builder.setCancelable(true);
    builder.show();

}

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
                AlertDialog.Builder builder=new AlertDialog.Builder(listdisplay.this);
                builder.setMessage("password change successfully");
                builder.setCancelable(true);
                builder.show();


            }
        };
    }


    ArrayList<EmployeeData> getdata()
    {
      adapter.openToRead();
       ArrayList<EmployeeData> employeeDataArrayList=adapter.getdata();
       for(int i=0;i<employeeDataArrayList.size();i++)
       {

           parentid=employeeDataArrayList.get(i).getParentId();
           password=employeeDataArrayList.get(i).getP_password();

       }


      /* textview.setText(name);
        textView2.setText(email);
        textView3.setText(contact);
        textView4.setText(gender);*/
        return employeeDataArrayList;

    }


  /*  {

        adapter.openToRead();
        ArrayList<EmployeeData> employeeDataArrayList=new ArrayList<>();
        ArrayList<EmployeeData> employeeDataArrayList1=adapter.getdata();
        System.out.println("array##"+employeeDataArrayList.size());
        for(int i=0;i<employeeDataArrayList.size();i++)
        {
            //String id=employeeDataArrayList.get(0).getId();
            String add=employeeDataArrayList.get(1).getAddress();
            System.out.println("add"+add);
        }
            // textview.setText(id);
        textView2.setText(add);
        return employeeDataArrayList;
    }*/


}
