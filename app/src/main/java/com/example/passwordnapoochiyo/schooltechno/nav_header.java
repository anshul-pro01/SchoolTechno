package com.example.passwordnapoochiyo.schooltechno;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class nav_header extends AppCompatActivity implements View.OnClickListener {
    TextView Email,Name;
    String parent_name,parent_emaial;
    SQLiteAdapter adapter;
    ArrayList<EmployeeData> employeeDataArrayList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);
        adapter=new SQLiteAdapter(this);
        employeeDataArrayList=new ArrayList<>();
        Name=(TextView)findViewById(R.id.name);
        Email=(TextView)findViewById(R.id.email);
        employeeDataArrayList=getdata();
        Name.setText(parent_name);
        Email.setText(parent_emaial);
        Name.setOnClickListener(this);

    }

    private ArrayList<EmployeeData> getdata() {
        adapter.openToRead();
        ArrayList<EmployeeData> employeeDataArrayList=adapter.getdata();
        for(int i=0;i<employeeDataArrayList.size();i++)
        {
             parent_name=employeeDataArrayList.get(i).getName();
             parent_emaial=employeeDataArrayList.get(i).getEmail();

        }
        return employeeDataArrayList;
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(this, "hiii", Toast.LENGTH_SHORT).show();
    }
}