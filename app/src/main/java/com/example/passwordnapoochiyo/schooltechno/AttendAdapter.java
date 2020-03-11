package com.example.passwordnapoochiyo.schooltechno;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;

class AttendAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<String> array_id;
    ArrayList<String> array_date;
    ArrayList<String> array_status;
    int a=0;
LayoutInflater inflater;
    public AttendAdapter( Activity activity,ArrayList<String> array_id, ArrayList<String> array_date, ArrayList<String> array_status) {
        this.array_id = array_id;
        this.array_date = array_date;
        this.array_status = array_status;
        this.activity = activity;
        inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return array_id.size();
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

      AttendAdapter.ViewHolder hold;
        if(view==null) {
            view = inflater.inflate(R.layout.layout_attendance, null);
            hold=new AttendAdapter.ViewHolder(view);
            view.setTag(hold);
        }
      else {
          hold = (AttendAdapter.ViewHolder) view.getTag();
         }

        hold.A_date.setText(array_date.get(i));
      hold.A_status.setText(array_status.get(i));
      hold.A_id.setText(String.valueOf(a=a+1));
      return view;
}

    private class ViewHolder {
        TextView A_id,A_date,A_status;

        public ViewHolder(View view) {
          A_id=(TextView)view.findViewById(R.id.Atte_serial_no);
            A_date=(TextView)view.findViewById(R.id.Atte_date);
            A_status=(TextView)view.findViewById(R.id.Atte_status);
        }
    }
}
