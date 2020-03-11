package com.example.passwordnapoochiyo.schooltechno;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class profile extends AppCompatActivity {
  SQLiteAdapter adapter;
  String name,email,address,contact,gender,parentid;
    TextView textViewname,textViewemail,textViewaddress,textViewcontact,textViewgender;

    ActionBar action;

    ArrayList<EmployeeData> employeeDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textViewname=(TextView)findViewById(R.id.textpname);
        textViewemail=(TextView)findViewById(R.id.textpemail);
        textViewaddress=(TextView)findViewById(R.id.textpaddress);
        textViewcontact=(TextView)findViewById(R.id.textpcontact);
        textViewgender=(TextView)findViewById(R.id.textpgender);
       adapter=new SQLiteAdapter(this);
       //for toolbarcolor
       //action=getSupportActionBar();
       //action.setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
       adapter=new SQLiteAdapter(this);
       employeeDataArrayList=new ArrayList<>();
       adapter.openToRead();
       ArrayList<EmployeeData> employeeDataArrayList=getdata();

    }
   ArrayList<EmployeeData> getdata()
   {
       adapter.openToRead();
       ArrayList<EmployeeData> employeeDataArrayList=adapter.getdata();
       for(int i=0;i<employeeDataArrayList.size();i++)
       {
           name=employeeDataArrayList.get(i).getName();
           email=employeeDataArrayList.get(i).getEmail();
           address=employeeDataArrayList.get(i).getAddress();
           contact=employeeDataArrayList.get(i).getContact();
           gender=employeeDataArrayList.get(i).getGender();
           parentid=employeeDataArrayList.get(i).getParentId();
       }
       textViewname.setText(name);
       textViewemail.setText(email);
       textViewaddress.setText(address);
       textViewcontact.setText(contact);
       textViewgender.setText(gender);
       return employeeDataArrayList;
   }


}
