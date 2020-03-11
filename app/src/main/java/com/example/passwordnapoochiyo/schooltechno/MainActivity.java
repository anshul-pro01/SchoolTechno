package com.example.passwordnapoochiyo.schooltechno;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    String p_name, p_email, p_addr, p_contact, p_gender, p_id,fk_schoolid,p_password,student_id;
    String link;
    TextView text;
    EditText edtmobile, edtpass;
    Button btnlogin;
    String mobile;
    String pass;
    RequestQueue mque;
    String get1,get2,get3;
ArrayList<EmployeeData> employeeDataArrayList;
     SQLiteAdapter sqLiteAdapter;
    AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          sqLiteAdapter=new SQLiteAdapter(MainActivity.this);

          sqLiteAdapter.openToRead();
            int total=sqLiteAdapter.Get_Total_EmployeeData();

            sqLiteAdapter.close();
            employeeDataArrayList=new ArrayList<>();
       if(total>0)
        {
            Intent i=new Intent(MainActivity.this,schooldashboard.class);
            i.putExtra("id",get3);
            startActivity(i);
            finish();
        }
        mque = Volley.newRequestQueue(this);
    //    text = (TextView) findViewById(R.id.timepass);
        btnlogin = (Button) findViewById(R.id.buttonlogin);
        edtmobile = (EditText) findViewById(R.id.editemail);
        edtpass = (EditText) findViewById(R.id.editpassword);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mobile = edtmobile.getText().toString();
                pass = edtpass.getText().toString();
              link = "http://reliablefastsms.com/technoschool/api/signIn.php";
                  new getdata().execute();

            }
        });
    }


 class getdata extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
        @Override
        protected String doInBackground(String... strings) {

            StringRequest request=new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonObject=new JSONObject(response.toString());
                        String result=jsonObject.getString("result");
                        if(result.equals("Success"))
                        {
                            JSONArray array = jsonObject.getJSONArray("data");
                            System.out.println("############array="+array);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);

                                p_name = object.getString("p_name");
                                p_id = object.getString("p_id");

                                p_addr = object.getString("p_addr");
                                p_email = object.getString("p_email");
                                p_contact = object.getString("p_contact");
                                p_gender = object.getString("p_gender");
                                fk_schoolid=object.getString("fk_school_id");
                                p_password=object.getString("p_password");
                                student_id=object.getString("stud_id");
                            }
                           //text.append(p_name);

                         sqLiteAdapter.openToRead();
                            sqLiteAdapter.deleteEmployeeData();
                            sqLiteAdapter.insertEmployeeData(p_name,p_email,p_addr,p_contact,p_gender,p_id,fk_schoolid,p_password,student_id);
                            alert();
                            ArrayList<EmployeeData> employeeDataArrayList=getdata();


                        }
                        else
                        {
                            final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this,R.style.AlertDialogStyle);

                            dialog.setCancelable(true);
                            dialog.setTitle("Alert..");
                            dialog.setMessage("Login failed");
                            AlertDialog AD=dialog.show();

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,"No data connection", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() {
                   Map<String,String> params=new HashMap<>();
                   params.put("mobile",mobile);
                   params.put("password",pass);


                         return params;
                }
            };

             mque.add(request);
            return null;
        }
      public void alert() {
           final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this,R.style.AlertDialogStyle);

           dialog.setCancelable(false);
           dialog.setTitle("Alert");
            dialog.setMessage("Login success");
           dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   Intent intent = new Intent(MainActivity.this, schooldashboard.class);
                   intent.putExtra("id",get3);
                   startActivity(intent);
                   finish();

               }
           });

           AlertDialog alertDialog=dialog.show();


       }


        }
        ArrayList<EmployeeData> getdata()
        {
          sqLiteAdapter.openToRead();
           ArrayList<EmployeeData> employeeDataArrayList=sqLiteAdapter.getdata();
           for (int i=0;i<employeeDataArrayList.size();i++)
           {
               get1=employeeDataArrayList.get(i).getName();
               get2=employeeDataArrayList.get(i).getContact();
               get3=employeeDataArrayList.get(i).getSchoolid();


           }
           // text.setText(get1);

           //text.append(get2);
           // text.append(get3);
           return employeeDataArrayList;
        }
    }


