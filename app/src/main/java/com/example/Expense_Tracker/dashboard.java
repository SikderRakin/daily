package com.example.Expense_Tracker;


import android.app.DatePickerDialog;
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
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class dashboard extends Fragment {
    private Calendar calendar = Calendar.getInstance();
    private long selectedDateinMS;

    private Spinner spinner;
    private Button dateFrom , dateTo;
    private  DatabaseHelper helper;
    private String[]test_expanse_spinner = {"Food","Mobile","Education","Transport","Other"};

    SimpleDateFormat dateAndtimeSDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    SimpleDateFormat dateSDF = new SimpleDateFormat("dd MMMM yyyy");


    public dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Dashboard");

        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);
        //ArrayAdapter<String> expanse_adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.test_expanse_spinner));



        ArrayAdapter<String> expanse_adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,test_expanse_spinner);
        spinner = view.findViewById(R.id.spinner);


        dateFrom = view.findViewById(R.id.dateFrom);
        dateTo =  view.findViewById(R.id.dateTo);

        //expanse_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expanse_adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(expanse_adapter);

        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_dashboard, container, false);



        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendatepicker();
            }
        });

        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendatepicker();
            }
        });

        return  view;
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
                dateFrom.setText(dateSDF.format(date));

            }

        };

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getActivity(), onDateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();


    }


}
