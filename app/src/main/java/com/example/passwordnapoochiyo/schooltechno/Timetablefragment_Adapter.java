package com.example.passwordnapoochiyo.schooltechno;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

class Timetablefragment_Adapter extends BaseAdapter{
    Activity a;
    ArrayList<String> array_subject;
    ArrayList<String> array_staffname;
    ArrayList<String> array_time;
LayoutInflater inflater;
    public Timetablefragment_Adapter(Activity activity, ArrayList<String> array_subject, ArrayList<String> array_staffname, ArrayList<String> array_time) {
        this.a =activity;
        this.array_subject = array_subject;
        this.array_staffname = array_staffname;
        this.array_time = array_time;
        inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    @Override
    public int getCount() {
        return array_subject.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Timetablefragment_Adapter.ViewHolder hold;
        if(view==null) {
            view = inflater.inflate(R.layout.layout_timetable_list, null);
            hold=new Timetablefragment_Adapter.ViewHolder(view);
            view.setTag(hold);
        }
        else {
            hold = (Timetablefragment_Adapter.ViewHolder) view.getTag();
        }

        hold.sub.setText(array_subject.get(i));
        hold.time.setText(array_time.get(i));
        hold.staff.setText(array_staffname.get(i));
        return view;
    }


    private class ViewHolder {
        TextView sub,time,staff;
        public ViewHolder(View view) {
            sub=(TextView)view.findViewById(R.id.sub_name);
            time=(TextView)view.findViewById(R.id.sub_time);
            staff=(TextView)view.findViewById(R.id.teacher_name);
        }
    }
}
