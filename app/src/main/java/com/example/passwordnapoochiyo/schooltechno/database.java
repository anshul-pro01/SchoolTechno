package com.example.passwordnapoochiyo.schooltechno;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

public class database extends SQLiteOpenHelper {
 public static final String database="parentdata.db";
 public static final String table="parentinfo";
 public static final String col_1="Id";
 public static final String col_2="Name";
 public static final String col_3="Email_Id";
 public static final String col_4="Address";
 public static final String col_5="Contact";
 public static final String col_6="Gender";
 public static final String col_7="ParentId";

    public database(Context context) {
        super(context, database, null, 2);
    }


    @Override
 public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL("create table'+table+'(Id Text primary key autoincrement,Name text,Email_Id Text," +
               "Address Text,Contact Text,Gender Text,ParentId Text)");
       System.out.println("datatable create");
 }

 @Override
 public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
     sqLiteDatabase.execSQL("drop table if exists "+table);
     onCreate(sqLiteDatabase);
 }
 long insert(String name, String email, String address, String contact, String gender, String parentid)
 {


     SQLiteDatabase database=this.getWritableDatabase();
      ContentValues values=new ContentValues();
      values.put("Name",name);
     values.put("Email_Id",email);
     values.put("Address",address);
     values.put("Contact",contact);
     values.put("Gender",gender);
     values.put("parentid",parentid);

     return database.insert(table, null, values);


 }
 public int get_current_empoyee()
 {
     SQLiteDatabase database=this.getWritableDatabase();
     Cursor cursor=database.rawQuery("select * from'+table'",null);
     int count=cursor.getCount();
     return count;
 }
 public int delete_data()
 {
     SQLiteDatabase database=this.getWritableDatabase();
     int k=database.delete(table,null,null);
     return k;
 }


}