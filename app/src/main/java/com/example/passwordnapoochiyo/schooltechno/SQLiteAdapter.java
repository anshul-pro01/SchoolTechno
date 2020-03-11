package com.example.passwordnapoochiyo.schooltechno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by NILESH on 07-12-2016.
 */
public class SQLiteAdapter
{
    public static final String MYDATABASE_NAME = "Employee DATA";
   // public static final String MYDATABASE_TABLE = "MY_TABLE";
    public static final int MYDATABASE_VERSION = 1;

    public static final String KEY_CONTENT = "Content";
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;
EmployeeData data;
    private Context context;






 public SQLiteAdapter(Context c) {
        context = c;
    }

    public SQLiteAdapter() {

    }

    public SQLiteAdapter openToRead() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
                MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }

    public SQLiteAdapter openToWrite() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
                MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        sqLiteHelper.close();
    }


  public long insertEmployeeData(String Name,String Email,String Address,String Contact,String Gender,String ParentId,String fk_schoolid,String password,String studid) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", Name);
        contentValues.put("Email", Email);
        contentValues.put("Address", Address);
        contentValues.put("Contact", Contact);
        contentValues.put("Gender", Gender);
        contentValues.put("ParentId",ParentId);
      contentValues.put("FK_SchoolId",fk_schoolid);
       contentValues.put("p_password",password);
        contentValues.put("Student_id",studid);
        return sqLiteDatabase.insert("EmployeeData", null, contentValues);
    }

 public ArrayList<EmployeeData> getdata() {
      ArrayList<EmployeeData> employeeDataArrayList = new ArrayList<EmployeeData>();

       Cursor res=sqLiteDatabase.rawQuery("select * from EmployeeData", null);
       if (res.moveToFirst()) {
           do {
               EmployeeData employeeData = new EmployeeData(res.getString(0),
                       res.getString(1),
                       res.getString(2),
                       res.getString(3),
                       res.getString(4),
                       res.getString(5),
                       res.getString(6),
                       res.getString(7),
                       res.getString(8),
                        res.getString(9));
               employeeDataArrayList.add(employeeData);

           } while (res.moveToNext());
       }
       return employeeDataArrayList;

   }

    //db.execSQL("create table NotificationTable(id Integer PRIMARY KEY AUTOINCREMENT,
    //Notice text,date text);");


 /*public ArrayList<EmployeeData> retrieveAllEmployeeData() {

            EmployeeDatalist=new ArrayList<>();
       // EmployeeDatalist.clear();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from EmployeeData;", null);
        try {
            if (cursor.moveToFirst()) {
                do {
                  data = new EmployeeData();



                    data.setId(cursor.getString(0));
                    data.setName(cursor.getString(1));
                    data.setEmail(cursor.getString(2));
                    data.setAddress(cursor.getString(3));
                    data.setContact(cursor.getString(4));
                    data.setGender(cursor.getString(5));
                    data.setParentId(cursor.getString(6));

                    EmployeeDatalist.add(data);
                    //  consumer_metadata_details.add(field_type1);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            //  db.close();
            return     EmployeeDatalist;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_data", "" + e);
        }finally {
            cursor.close();
        }

        return EmployeeDatalist;

    }*/






   /* public ArrayList<EmployeeData> retrieveAllData(String id) {


        EmployeeDatalist.clear();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from EmployeeData where leader_id1= "+ id +";", null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    EmployeeData data = new EmployeeData();
                    data.setEMPID(cursor.getString(0));
                    data.setNAME(cursor.getString(1));
                    data.setCOMPANYNAME(cursor.getString(2));
                    data.setLOCATION(cursor.getString(3));
                    data.setSTATE(cursor.getString(4));
                    data.setDISTRICT(cursor.getString(5));
                    data.setPANCARDNO(cursor.getString(6));
                    data.setEMAIL(cursor.getString(7));
                    data.setMOBILE(cursor.getString(8));
                    data.setDOB(cursor.getString(9));
                    EmployeeDatalist.add(data);
                    //  consumer_metadata_details.add(field_type1);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            //  db.close();
            return     EmployeeDatalist;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_data", "" + e);
        }finally {
            cursor.close();
        }

        return EmployeeDatalist;
    }
*/

    public int Get_Total_EmployeeData() {


        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from EmployeeData;", null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int Get_Total_Sum() {


        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from sum;", null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }


    public int deleteEmployeeData() {

        int k = sqLiteDatabase.delete("EmployeeData", null, null);
        return k;
    }



    public void Update_User(String Designation1)
    {

        sqLiteDatabase.execSQL("update EmployeeData set Designation='" + Designation1+ "'");
        //sqLiteDatabase.execSQL("update OfferTable set flag='" + flag + "'");

    }



    public class SQLiteHelper extends SQLiteOpenHelper {

         /* Constructor called its super class
         */
        public SQLiteHelper(Context context, String name,
                            CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            //store user data
            db.execSQL("create table sum(Sum text);");

            db.execSQL("create table EmployeeData(Id text,Name text,Email text,Address text,Contact text,Gender text,ParentId text,FK_SchoolId text,p_password text,Student_id text);");
            Log.d("Log", "Database Created");
            System.out.println("DataBase");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
              //  db.execSQL("alter table EmployeeData add column FK_SchoolId text ");
               // db.execSQL("alter table EmployeeData add column p_password text ");
               // db.execSQL("alter table EmployeeData add column Student_id text");

        }
        @Override
        public synchronized void close() {
            if(sqLiteDatabase != null){
                sqLiteDatabase.close();
                super.close();
            }
        }
    }
}