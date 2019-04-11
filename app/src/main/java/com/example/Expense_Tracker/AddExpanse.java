package com.example.Expense_Tracker;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class AddExpanse extends Fragment {

    private TextView addDateTV ,addTimeTV;
    private EditText addExpensesAmountET;
    private ImageButton addDateBTN;
    private ImageButton addTimeBTN;
    private Button addExpenseBTN;
    private Calendar calendar = Calendar.getInstance();
    private long selectedDateinMS, selectTimeInMS;
    private  TimeFunctions tf ;
    private String[]test_expanse_spinner = {"Food","Mobile","Education","Transport","Other"};
    private Spinner spinner;

    // private  TimeF

    private DatabaseHelper helper;
    SimpleDateFormat dateAndtimeSDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    SimpleDateFormat dateSDF = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat timeSDF = new SimpleDateFormat("HH:mm:ss aa");

    public AddExpanse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_expanse, container, false);
        getActivity().setTitle("Add Expanse");


        ArrayAdapter<String> expanse_adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,test_expanse_spinner);
        spinner = view.findViewById(R.id.spinner);
        expanse_adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(expanse_adapter);

        addExpensesAmountET = view.findViewById(R.id.addExpensesAmountET);
        addDateBTN = view.findViewById(R.id.addDateBTN);
        addTimeBTN = view.findViewById(R.id.addTimeBTN);
        addExpenseBTN = view.findViewById(R.id.addExpenseBTN);
        addDateTV = view.findViewById(R.id.addDateTV);
        addTimeTV = view.findViewById(R.id.addTImeTV);
        helper = new DatabaseHelper(getActivity());

        addDateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendatepicker();




            }
        });


        addTimeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentimepicker();
            }
        });

        addExpenseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // is(selectedDateinMS, "dd MMMM yyyy ");
                insertdata();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    private void opentimepicker() {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String selecttime = hourOfDay + " : " + minute;
                Time time = new Time(hourOfDay, minute, 0);
                selectTimeInMS = time.getTime();
                addTimeTV.setText(timeSDF.format(time));
                //addTimeBTN.setText(String.valueOf(selectTimeInMS));

            }

        };

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);


        TimePickerDialog timePickerDialog = new TimePickerDialog(this.getActivity(), onTimeSetListener, hour, minute, false);
        timePickerDialog.show();


    }

    private void opendatepicker() {

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

                Toast.makeText(getActivity(), ""+selectedDateinMS, Toast.LENGTH_SHORT).show();
                selectedDateinMS = date.getTime();
                addDateTV.setText(dateSDF.format(date));

            }

        };

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getActivity(), onDateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();


    }




    private void insertdata() {
        //String name = addExpensesAmountET.getText().toString();

        String type = spinner.getSelectedItem().toString();
        String temp = addExpensesAmountET.getText().toString();
        int amount = Integer.valueOf(temp);
        temp = addDateTV.getText().toString();
        long date = selectedDateinMS;
        temp = addTimeTV.getText().toString();
        long time = selectTimeInMS;
        temp = "dummy expanse name";


        long id = helper.insertData(type, amount, date, time);
        Toast.makeText(getActivity(), "name" +type+ " amount " + amount + " date " + date + " time " + time, Toast.LENGTH_SHORT).show();
        //return id;
    }





    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public void is(long datems , String dateform){

        String a = getDate(datems, "hh:mm:ss aa");

        Toast.makeText(getActivity(), ""+a, Toast.LENGTH_SHORT).show();
    }


}
