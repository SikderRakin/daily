package com.example.Expense_Tracker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class TimeFunctions {

    private Calendar calendar = Calendar.getInstance();
    private long selectedDateinMS, selectTimeInMS;
    private  String result;
    SimpleDateFormat dateAndtimeSDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    SimpleDateFormat dateSDF = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat timeSDF = new SimpleDateFormat("HH:mm:ss aa");


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setSelectedDateinMS(long selectedDateinMS) {
        this.selectedDateinMS = selectedDateinMS;
    }

    public void setSelectTimeInMS(long selectTimeInMS) {
        this.selectTimeInMS = selectTimeInMS;
    }

    public void  opendatepicker(final Context context) {

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;

                String selectDate = year + "/" + month + "/" + dayOfMonth + " 00:00:00";
                Date date = null;

                try {
                    date = dateAndtimeSDF.parse(selectDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //Toast.makeText(context, ""+selectedDateinMS, Toast.LENGTH_SHORT).show();
               // selectedDateinMS = date.getTime();
                setSelectedDateinMS(date.getTime());

                setResult(dateSDF.format(date));

            }

        };

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, onDateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();


       //return temp;


    }
    private void opentimepicker(Context context) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String selecttime = hourOfDay + " : " + minute;
                Time time = new Time(hourOfDay, minute, 0);
                selectTimeInMS = time.getTime();
                //addTimeTV.setText(timeSDF.format(time));
                //addTimeBTN.setText(String.valueOf(selectTimeInMS));

            }

        };

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);


        TimePickerDialog timePickerDialog = new TimePickerDialog(context, onTimeSetListener, hour, minute, false);
        timePickerDialog.show();


    }
}

