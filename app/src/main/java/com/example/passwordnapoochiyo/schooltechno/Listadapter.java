package com.example.passwordnapoochiyo.schooltechno;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


class Listadapter extends BaseAdapter{
    ArrayList<String> arrayfeesdate;
    ArrayList<String> arrayfees ;
    ArrayList<String> arrayfeesid;
    Activity a;
    LayoutInflater inflater=null;
    public Listadapter(Activity activity, ArrayList<String> arrayfeesid, ArrayList<String> arrayfeesdate, ArrayList<String> arrayfees) {

        this.arrayfeesid = arrayfeesid;
        this.arrayfeesdate = arrayfeesdate;
        this.arrayfees = arrayfees;
     
        this.a=activity;
        inflater=(LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayfeesid.size();
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
        final Listadapter.Viewholder hold;
        if (view == null) {
            view = inflater.inflate(R.layout.layout_listview_fees, null);
            hold = new Listadapter.Viewholder(view);
            view.setTag(hold);
        } else {
            hold = (Listadapter.Viewholder) view.getTag();
        }
       hold.feesid.setText(arrayfeesid.get(i));
        hold.feesdate.setText(arrayfeesdate.get(i));
        hold.feesvalue.setText(arrayfees.get(i));
        return view;
    }

    public class Viewholder {
        TextView feesid, feesvalue, feesdate;

        public Viewholder(View view) {
feesid=(TextView)view.findViewById(R.id.serial_no);
feesdate=(TextView)view.findViewById(R.id.fee_date);
feesvalue=(TextView)view.findViewById(R.id.pay_amount);
        }
    }
    }
