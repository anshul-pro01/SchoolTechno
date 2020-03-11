package com.example.passwordnapoochiyo.schooltechno;

import android.graphics.drawable.ColorDrawable;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.ArrayList;

public class schooldashboard extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
 DrawerLayout drawerLayout;
    SQLiteAdapter sqLiteAdapter;
 ActionBarDrawerToggle toggle;
 String str,fk_schoolid,user_name,user_email,school_id;
 TextView Name,Email;
 ArrayList<EmployeeData> employeeDataArrayList;

   CardView card_doc,card_cir,card_examsched,card_homework,card_gallary,card_feedback,card_fees,card_Attendance,card_subtimetable,card_diary,card_result;
Toolbar toolbar;

NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schooldashboard);

        //sqlite adapter class object
        sqLiteAdapter=new SQLiteAdapter(this);

        //for set nav header name,email
        ArrayList<EmployeeData> employeeDataArrayList=new ArrayList<>();
        employeeDataArrayList=getdata();

        //cardview cast
        card_doc=(CardView)findViewById(R.id.doct_id);
        card_cir=(CardView)findViewById(R.id.circ_id);
        card_examsched=(CardView)findViewById(R.id.exam_id);
        card_homework=(CardView)findViewById(R.id.homework_id);
        card_gallary=(CardView)findViewById(R.id.gallary);
        card_feedback=(CardView)findViewById(R.id.feedback_id);
        card_fees=(CardView)findViewById(R.id.schoolfees_id);
        card_Attendance=(CardView)findViewById(R.id.attendance_id);
        card_subtimetable=(CardView)findViewById(R.id.timetable_id);
        card_diary=(CardView)findViewById(R.id.diary_id);
        card_result=(CardView)findViewById(R.id.result_id);


        //card event
        card_doc.setOnClickListener(this);
        card_cir.setOnClickListener(this);
        card_examsched.setOnClickListener(this);
        card_homework.setOnClickListener(this);
        card_gallary.setOnClickListener(this);
        card_feedback.setOnClickListener(this);
        card_fees.setOnClickListener(this);
        card_Attendance.setOnClickListener(this);
        card_subtimetable.setOnClickListener(this);
        card_diary.setOnClickListener(this);
        card_result.setOnClickListener(this);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.inflateMenu(R.menu.menu);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawerid);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//navigation event
        navigationView=findViewById(R.id.new_navigation);
        navigationView.setNavigationItemSelectedListener(this);

      //for set nav heder name,email
          View header=navigationView.getHeaderView(0);
        Name = (TextView)header.findViewById(R.id.new_name);
        Email = (TextView)header.findViewById(R.id.new_email);
        Name.setText(user_name);
        Email.setText(user_email);

    }

    private ArrayList<EmployeeData> getdata() {
        sqLiteAdapter.openToRead();
        ArrayList<EmployeeData> employeeDataArrayList=sqLiteAdapter.getdata();
        for (int i=0;i<employeeDataArrayList.size();i++)
        {
            user_name=employeeDataArrayList.get(i).getName();
            user_email=employeeDataArrayList.get(i).getEmail();

            }
        return employeeDataArrayList;
    }

    //for cardview click listener
   @Override
    public void onClick(View view) {
       card_doc=(CardView)findViewById(R.id.doct_id);
       card_cir=(CardView)findViewById(R.id.circ_id);
       card_examsched=(CardView)findViewById(R.id.exam_id);
       card_homework=(CardView)findViewById(R.id.homework_id);
       card_gallary=(CardView)findViewById(R.id.gallary);
       card_feedback=(CardView)findViewById(R.id.feedback_id);
       card_fees=(CardView)findViewById(R.id.schoolfees_id);
       card_Attendance=(CardView)findViewById(R.id.attendance_id);
       card_subtimetable=(CardView)findViewById(R.id.timetable_id);
       card_diary=(CardView)findViewById(R.id.diary_id);
       card_result=(CardView)findViewById(R.id.result_id);
       int id=view.getId();
        switch(id)
        {
            case R.id.doct_id:
                Intent intent1=new Intent(this,documentpage.class);
                startActivity(intent1);
                break;
            case R.id.circ_id:
              Intent intent2=new Intent(this,circularpage.class);
              startActivity(intent2);
                break;
            case R.id.exam_id:
                Intent intent3=new Intent(this,examschedulepage.class);
                startActivity(intent3);
                break;
            case R.id.homework_id:
                    Intent intent4=new Intent(this,homeworkpage.class);
                    startActivity(intent4);
                    break;
            case R.id.gallary:
                Intent intent5=new Intent(this,gallarypage.class);
                startActivity(intent5);
                break;
            case R.id.feedback_id:
                Intent intent6=new Intent(this,feedbackpage.class);
                startActivity(intent6);
                break;
            case R.id.schoolfees_id:
                Intent intent7=new Intent(this,feesMainActivity.class);
                startActivity(intent7);
                break;
            case R.id.attendance_id:
                Intent intent8=new Intent(this,AttendanceMainactivity.class);
                startActivity(intent8);
                break;
            case R.id.timetable_id:
                Intent intent9=new Intent(this,timetable_mainactivity.class);
                startActivity(intent9);
                break;

            case R.id.diary_id:
                Intent intent10=new Intent(this,diary.class);
                startActivity(intent10);
                break;

            case R.id.result_id:
                Intent intent11=new Intent(this,ResultMain.class);
                startActivity(intent11);
                break;


        }

      }





    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch( item.getItemId())
        {
            case R.id.item2 :
                      Intent in=new Intent(this,listdisplay.class);
                      startActivity(in);
                   break;
            case R.id.item3 :
                sqLiteAdapter.openToRead();
                sqLiteAdapter.deleteEmployeeData();
                sqLiteAdapter.close();
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
          case R.id.batman:
              Intent i=new Intent(this,profile.class);
              startActivity(i);
            break;
            case R.id.setting:
                Intent intent=new Intent(this,setting_activity.class);
                startActivity(intent);
                break;
        }

        return true;
    }
}
