package com.example.Expense_Tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "expanse.db";
    public static final String TABLE_NAME = "expanse";
    public static final int VERSION = 1;
    public static final String COL_ID = "ID";
    public static final String COL_EXPANSE_NAME = "EXPANSE_NAME";
    public static final String COL_EXPANSE_AMOUNT = "EXPANSE_AMOUNT";
    public static final String COL_EXPANSE_DATE = "EXPANSE_DATE";
    public static final String COL_EXPANSE_TIME = "EXPANSE_TIME";


    public static  final String CREATE_TABLE = "create table " +TABLE_NAME+"("+COL_ID+" Integer primary key,"+COL_EXPANSE_NAME+" Text,"+COL_EXPANSE_AMOUNT+" Integer,"+COL_EXPANSE_DATE+" Integer, "+COL_EXPANSE_TIME+" Integer )";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public  long insertData(String name , int amount , long date , long  time ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EXPANSE_NAME,name);
        contentValues.put(COL_EXPANSE_AMOUNT,amount);
        contentValues.put(COL_EXPANSE_DATE,date);
        contentValues.put(COL_EXPANSE_TIME,time);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME, null,contentValues);
        sqLiteDatabase.close();
        return id;

    }

    public Cursor showData(){
        String showdata = " select *from "+TABLE_NAME;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(showdata,null);
        return cursor;
    }
    public Cursor showDataFromTo(long from , long to){
        String showdata = " select * from " +TABLE_NAME+ " where " +COL_EXPANSE_DATE+ " > " +from+ " and " +COL_EXPANSE_DATE+ " < " +to+ " ; ";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(showdata,null);
        return cursor;
    }

    public Cursor showDataType(String type){
       // String showdata = " select * from " +TABLE_NAME+ " where " +COL_EXPANSE_NAME+ " = '" +type+ "'  ; ";
       // String showdata = " select * from " +TABLE_NAME+ " WHERE " +COL_EXPANSE_NAME+ " = 'Breakfast'  ; ";
        String showdata = " select * from  expanse  WHERE EXPANSE_NAME = 'Breakfast'  ; ";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(showdata,null);
        return cursor;
    }

    // SELECT SUM(column_name)
    //FROM table_name
    //WHERE condition



    public Cursor showDash(){
        String showdata = " select *from "+TABLE_NAME;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(showdata,null);
        return cursor;
    }

    public void deleteData(int id){
        getWritableDatabase().delete(TABLE_NAME," ID=?",new String[]{String.valueOf(id)});
    }


}
