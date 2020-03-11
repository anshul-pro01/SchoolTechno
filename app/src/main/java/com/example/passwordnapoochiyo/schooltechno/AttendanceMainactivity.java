package com.example.passwordnapoochiyo.schooltechno;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;


public class AttendanceMainactivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

   Spinner monthSpinner;
   ListView listattendance;
   SQLiteAdapter adapter;
   ArrayList<EmployeeData> employeeDataArrayList;
    String student_id,attend_id,attend_date,attend_status;
    AttendAdapter attendAdapter;
    RequestQueue queue;
    ArrayList<String> array_id=new ArrayList<>();
    ArrayList<String> array_date=new ArrayList<>();
    ArrayList<String> array_status=new ArrayList<>();
    ArrayList<String> array_filter_date=new ArrayList<>();
    ArrayList<String> array_filter_status=new ArrayList<>();
    ArrayList<String> array_filter_id=new ArrayList<>();

    ProgressDialog dialog;
String value;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_mainactivity);
        listattendance=(ListView)findViewById(R.id.listview_attendance);
        adapter=new SQLiteAdapter(this);
        ArrayList<EmployeeData> employeeDataArrayList=new ArrayList<>();
        queue= Volley.newRequestQueue(this);
         employeeDataArrayList=getdata();
         attendane_getdata();
        dialog = new ProgressDialog(AttendanceMainactivity.this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();

        //for spinner
        monthSpinner =(Spinner)findViewById(R.id.spinner1);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(monthSpinner);

            // Set popupWindow height to 500px
            popupWindow.setHeight(500);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(AttendanceMainactivity.this,R.array.month,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);
        monthSpinner.setOnItemSelectedListener(this);

    }

    private void attendane_getdata() {

        array_date=new ArrayList<>();
         array_status=new ArrayList<>();
         array_id=new ArrayList<>();
        HashMap<String,String> param=new HashMap<>();
        param.put("fk_stud_id",student_id);
CustomRequest request=new CustomRequest(Request.Method.POST,ProjectConfig.ATTENDANCE,param,this.requestsuccess(),this.requesterror());
queue.add(request);
btn=(Button)findViewById(R.id.attendance_button1);
btn.setOnClickListener(this);

    }

    private Response.ErrorListener requesterror() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(dialog.isShowing())
                    dialog.dismiss();
            }
        };
    }

    private Response.Listener<JSONObject> requestsuccess() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(dialog.isShowing())
                        dialog.dismiss();
                    JSONObject jsonObject=new JSONObject(response.toString());
                    JSONArray jsonArray=jsonObject.getJSONArray("bg_state");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject object=jsonArray.getJSONObject(i);
                        attend_id=object.getString("sattend_id");
                        attend_date=object.getString("sattend_date");
                        attend_status=object.getString("sattend_status");
                        array_id.add(attend_id);
                        array_date.add(attend_date);
                        array_status.add(attend_status);    



                    }
                    attendAdapter=new AttendAdapter(AttendanceMainactivity.this,array_id,array_date,array_status);
                    listattendance.setAdapter(attendAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private ArrayList<EmployeeData> getdata() {
       adapter.openToRead();
       ArrayList<EmployeeData> employeeDataArrayList=adapter.getdata();
       for(int i=0;i<employeeDataArrayList.size();i++)
       {
           student_id=employeeDataArrayList.get(i).stud_id;

       }
     //   Toast.makeText(this, student_id, Toast.LENGTH_SHORT).show();
        return employeeDataArrayList;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        value=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onClick(View view) {
        String spinner_value=value;
        //Toast.makeText(this, spinner_value, Toast.LENGTH_SHORT).show();
        switch(spinner_value)
        {
            case "January":
                   array_filter_status.clear();
                   array_filter_date.clear();
                   array_filter_id.clear();
                for(int i=0;i<array_date.size();i++)
           {
               String str=array_date.get(i);
               String status=array_status.get(i);
               String id=array_id.get(i);
               String[] separated = str.split("-");
               String str1=separated[0];
               String str2=separated[1];
             //  Toast.makeText(this,str2, Toast.LENGTH_SHORT).show();
              if(str2.equals("01"))
              {
                array_filter_status.add(status);
               array_filter_date.add(str);
               array_filter_id.add(id);
                  attendAdapter=new AttendAdapter(AttendanceMainactivity.this,array_filter_id,array_filter_date,array_filter_status);
                 // attendAdapter=new AttendAdapter(AttendanceMainactivity.this,array_filter_id,array_filter_status,array_filter_date);
                  listattendance.setAdapter(attendAdapter);
         // String a=array_filter_date.get(i);
        //  String b=array_filter_status.get(i);
             //   Toast.makeText(this,a+"\t\t"+b, Toast.LENGTH_SHORT).show();

              }


           }

                break;
            case "February":
                array_filter_status.clear();
                array_filter_date.clear();
                array_filter_id.clear();
                for(int a=0;a<array_date.size();a++)
                  {

                      String date=array_date.get(a);
                      String status=array_status.get(a);
                      String id=array_id.get(a);
                     // String date1=array_status.get(c);

                      String[] part=date.split("-");
             String value1=part[0];
              String value2=part[1];
                    //  Toast.makeText(this,value2, Toast.LENGTH_SHORT).show();
                   if(value2.equals("02"))
                      {


                        // String abc=array_filter_date.get(0);
                          Toast.makeText(this,"Fine", Toast.LENGTH_SHORT).show();
                         array_filter_date.add(date);
                         array_filter_status.add(status);
                         array_filter_id.add(id);
                          attendAdapter=new AttendAdapter(AttendanceMainactivity.this,array_filter_id,array_filter_date,array_filter_status);
                          listattendance.setAdapter(attendAdapter);

              //            String a=array_filter_date.get(0);
              //            String b=array_filter_status.get(0);
              //            Toast.makeText(this,a+"\t\t"+b, Toast.LENGTH_SHORT).show();
                        //  array_filter_date.add(date

                          // );
                        //  array_filter_status.add(date1);
                       //   String e=array_filter_date.get(c);
                        //  String f=array_filter_status.get(c);
                        //  Toast.makeText(this,e+"\t\t"+f, Toast.LENGTH_SHORT).show();

                      }

                  }
                  break;
            case "March":
                array_filter_status.clear();
                array_filter_date.clear();
                array_filter_id.clear();
                for(int b=0;b<array_date.size();b++) {

                    String date = array_date.get(b);
                    String status = array_status.get(b);
                    String id = array_id.get(b);

                    String[] part = date.split("-");
                    String value1 = part[0];
                    String value2 = part[1];
                    if (value2.equals("03")) {


                        // String abc=array_filter_date.get(0);
                        Toast.makeText(this, "Fine", Toast.LENGTH_SHORT).show();
                        array_filter_date.add(date);
                        array_filter_status.add(status);
                        array_filter_id.add(id);
                        attendAdapter = new AttendAdapter(AttendanceMainactivity.this, array_filter_id, array_filter_date, array_filter_status);
                        listattendance.setAdapter(attendAdapter);
                    }
                }

                        break;
            case "April":
                array_filter_status.clear();
                array_filter_date.clear();
                array_filter_id.clear();
                for(int c=0;c<array_date.size();c++) {

                    String date = array_date.get(c);
                    String status = array_status.get(c);
                    String id = array_id.get(c);

                    String[] part = date.split("-");
                    String value1 = part[0];
                    String value2 = part[1];
                    if (value2.equals("04")) {


                        // String abc=array_filter_date.get(0);
                        Toast.makeText(this, "Fine", Toast.LENGTH_SHORT).show();
                        array_filter_date.add(date);
                        array_filter_status.add(status);
                        array_filter_id.add(id);
                        attendAdapter = new AttendAdapter(AttendanceMainactivity.this, array_filter_id, array_filter_date, array_filter_status);
                        listattendance.setAdapter(attendAdapter);
                    }
                }

                break;
            case "May":
                array_filter_status.clear();
                array_filter_date.clear();
                array_filter_id.clear();
                for(int d=0;d<array_date.size();d++) {

                    String date = array_date.get(d);
                    String status = array_status.get(d);
                    String id = array_id.get(d);

                    String[] part = date.split("-");
                    String value1 = part[0];
                    String value2 = part[1];
                    if (value2.equals("05")) {


                        // String abc=array_filter_date.get(0);
                        Toast.makeText(this, "Fine", Toast.LENGTH_SHORT).show();
                        array_filter_date.add(date);
                        array_filter_status.add(status);
                        array_filter_id.add(id);
                        attendAdapter = new AttendAdapter(AttendanceMainactivity.this, array_filter_id, array_filter_date, array_filter_status);
                        listattendance.setAdapter(attendAdapter);
                    }
                }

                break;
            case "Jun":
                array_filter_status.clear();
                array_filter_date.clear();
                array_filter_id.clear();
                for(int e=0;e<array_date.size();e++) {

                    String date = array_date.get(e);
                    String status = array_status.get(e);
                    String id = array_id.get(e);

                    String[] part = date.split("-");
                    String value1 = part[0];
                    String value2 = part[1];
                    if (value2.equals("06")) {


                        // String abc=array_filter_date.get(0);
                        Toast.makeText(this, "Fine", Toast.LENGTH_SHORT).show();
                        array_filter_date.add(date);
                        array_filter_status.add(status);
                        array_filter_id.add(id);
                        attendAdapter = new AttendAdapter(AttendanceMainactivity.this, array_filter_id, array_filter_date, array_filter_status);
                        listattendance.setAdapter(attendAdapter);
                    }
                }
                break;
            case "Jully":
                array_filter_status.clear();
                array_filter_date.clear();
                array_filter_id.clear();
                for(int f=0;f<array_date.size();f++) {

                    String date = array_date.get(f);
                    String status = array_status.get(f);
                    String id = array_id.get(f);

                    String[] part = date.split("-");
                    String value1 = part[0];
                    String value2 = part[1];
                    if (value2.equals("07")) {


                        // String abc=array_filter_date.get(0);
                        Toast.makeText(this, "Fine", Toast.LENGTH_SHORT).show();
                        array_filter_date.add(date);
                        array_filter_status.add(status);
                        array_filter_id.add(id);
                        attendAdapter = new AttendAdapter(AttendanceMainactivity.this, array_filter_id, array_filter_date, array_filter_status);
                        listattendance.setAdapter(attendAdapter);

                    }
                }
                break;

                        case "August":
                            array_filter_status.clear();
                            array_filter_date.clear();
                            array_filter_id.clear();
                            for(int f=0;f<array_date.size();f++) {

                                String date = array_date.get(f);
                                String status = array_status.get(f);
                                String id = array_id.get(f);

                                String[] part = date.split("-");
                                String value1 = part[0];
                                String value2 = part[1];
                                if (value2.equals("08")) {


                                    // String abc=array_filter_date.get(0);
                                    Toast.makeText(this, "Fine", Toast.LENGTH_SHORT).show();
                                    array_filter_date.add(date);
                                    array_filter_status.add(status);
                                    array_filter_id.add(id);
                                    attendAdapter = new AttendAdapter(AttendanceMainactivity.this, array_filter_id, array_filter_date, array_filter_status);
                                    listattendance.setAdapter(attendAdapter);

                                }
                            }

                            break;

            case "September":
                array_filter_status.clear();
                array_filter_date.clear();
                array_filter_id.clear();
                for(int f=0;f<array_date.size();f++) {

                    String date = array_date.get(f);
                    String status = array_status.get(f);
                    String id = array_id.get(f);

                    String[] part = date.split("-");
                    String value1 = part[0];
                    String value2 = part[1];
                    if (value2.equals("09")) {


                        // String abc=array_filter_date.get(0);
                        Toast.makeText(this, "Fine", Toast.LENGTH_SHORT).show();
                        array_filter_date.add(date);
                        array_filter_status.add(status);
                        array_filter_id.add(id);
                        attendAdapter = new AttendAdapter(AttendanceMainactivity.this, array_filter_id, array_filter_date, array_filter_status);
                        listattendance.setAdapter(attendAdapter);

                    }
                }
                break;
            case "October":
                array_filter_status.clear();
                array_filter_date.clear();
                array_filter_id.clear();
                for(int f=0;f<array_date.size();f++) {

                    String date = array_date.get(f);
                    String status = array_status.get(f);
                    String id = array_id.get(f);

                    String[] part = date.split("-");
                    String value1 = part[0];
                    String value2 = part[1];
                    if (value2.equals("10")) {


                        // String abc=array_filter_date.get(0);
                        Toast.makeText(this, "Fine", Toast.LENGTH_SHORT).show();
                        array_filter_date.add(date);
                        array_filter_status.add(status);
                        array_filter_id.add(id);
                        attendAdapter = new AttendAdapter(AttendanceMainactivity.this, array_filter_id, array_filter_date, array_filter_status);
                        listattendance.setAdapter(attendAdapter);
                    }
                }
                break;
            case "November":
                array_filter_status.clear();
                array_filter_date.clear();
                array_filter_id.clear();
                for(int f=0;f<array_date.size();f++) {

                    String date = array_date.get(f);
                    String status = array_status.get(f);
                    String id = array_id.get(f);

                    String[] part = date.split("-");
                    String value1 = part[0];
                    String value2 = part[1];
                    if (value2.equals("11")) {


                        // String abc=array_filter_date.get(0);
                        Toast.makeText(this, "Fine", Toast.LENGTH_SHORT).show();
                        array_filter_date.add(date);
                        array_filter_status.add(status);
                        array_filter_id.add(id);
                        attendAdapter = new AttendAdapter(AttendanceMainactivity.this, array_filter_id, array_filter_date, array_filter_status);
                        listattendance.setAdapter(attendAdapter);
                    }
                }
                break;
            case "December":
                array_filter_status.clear();
                array_filter_date.clear();
                array_filter_id.clear();
                for(int f=0;f<array_date.size();f++) {

                    String date = array_date.get(f);
                    String status = array_status.get(f);
                    String id = array_id.get(f);

                    String[] part = date.split("-");
                    String value1 = part[0];
                    String value2 = part[1];
                    if (value2.equals("12")) {


                        // String abc=array_filter_date.get(0);
                        Toast.makeText(this, "Fine", Toast.LENGTH_SHORT).show();
                        array_filter_date.add(date);
                        array_filter_status.add(status);
                        array_filter_id.add(id);
                        attendAdapter = new AttendAdapter(AttendanceMainactivity.this, array_filter_id, array_filter_date, array_filter_status);
                        listattendance.setAdapter(attendAdapter);
                    }
                }
                break;

                }

    }
}
