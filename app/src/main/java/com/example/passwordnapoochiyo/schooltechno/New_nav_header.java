package com.example.passwordnapoochiyo.schooltechno;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class New_nav_header extends AppCompatActivity{
    NavigationView navigationView;
    TextView Email,Name;
    String parent_name,parent_emaial,school_id;
    SQLiteAdapter adapter;
    ArrayList<EmployeeData> employeeDataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_nav_header);
       // Name=(TextView)findViewById(R.id.new_name);
        Email=(TextView)findViewById(R.id.new_email);

        adapter=new SQLiteAdapter(this);
        ArrayList<EmployeeData> employeeDataArrayList=new ArrayList<>();
        employeeDataArrayList=new ArrayList<>();

       // Name.setText("hari");


        employeeDataArrayList=getdata();


    }
    ArrayList<EmployeeData> getdata()
    {
        adapter.openToRead();
        ArrayList<EmployeeData> employeeDataArrayList=adapter.getdata();
        for(int i=0;i<employeeDataArrayList.size();i++)
        {
            school_id=employeeDataArrayList.get(i).getSchoolid();
          // Log.d("###",school_id);
        }

        return employeeDataArrayList;
    }



}
