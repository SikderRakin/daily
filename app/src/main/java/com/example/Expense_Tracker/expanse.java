package com.example.Expense_Tracker;


import android.app.DatePickerDialog;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class expanse extends Fragment {


    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private ExpanseAdapter expanseAdapter;
    private List<Expanse_Container> expanse_list = new ArrayList<>();
    private DatabaseHelper helper;
    private Button filter;
    private  View view_global;

    private Calendar calendar = Calendar.getInstance();
    private long selectedDateinMSFrom,selectedDateinMSTo;

    private Spinner spinner;
    private Button dateFrom , dateTo;
    private String[]test_expanse_spinner = {"Food","Mobile","Education","Transport","Other"};

    SimpleDateFormat dateAndtimeSDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    SimpleDateFormat dateSDF = new SimpleDateFormat("dd MMMM yyyy");

    public expanse() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expanse, container, false);
        view_global= view;
        filter = view.findViewById(R.id.filter);
        dateFrom = view.findViewById(R.id.dateFrom);
        dateTo = view.findViewById(R.id.dateTo);
        getActivity().setTitle("Expanse");

        ArrayAdapter<String> expanse_adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,test_expanse_spinner);
        spinner = view.findViewById(R.id.spinner);
        expanse_adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(expanse_adapter);

        show_data();

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
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // filter();
            }
        });





        floatingActionButton=view.findViewById(R.id.addExpanseFAB);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                MainActivity mainAct= (MainActivity) getActivity();
                mainAct.addExpanseFragment();

            }
        });
        return view;

    }



    public void show_data(){

        recyclerView = view_global.findViewById(R.id.expanseRecycler);

        helper = new DatabaseHelper(getActivity());
        Cursor cursor =  helper.showData();


        while(cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            String expanse_name =cursor.getString(cursor.getColumnIndex(helper.COL_EXPANSE_NAME));
            int expanse_amount =cursor.getInt(cursor.getColumnIndex(helper.COL_EXPANSE_AMOUNT));
            int expanse_date = cursor.getInt(cursor.getColumnIndex(helper.COL_EXPANSE_DATE));
            int expanse_time = cursor.getInt(cursor.getColumnIndex(helper.COL_EXPANSE_TIME));

            // add date and time here //


          //  Toast.makeText(getActivity(), ""+expanse_amount, Toast.LENGTH_SHORT).show();
            expanse_list.add(new Expanse_Container(id,expanse_name,expanse_amount,expanse_date,expanse_time));
        }
        expanseAdapter = new ExpanseAdapter(this.getActivity(),expanse_list,helper);        //can have error//

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(expanseAdapter);

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

                Toast.makeText(getActivity(), ""+selectedDateinMSFrom, Toast.LENGTH_SHORT).show();
                selectedDateinMSFrom = date.getTime();
                dateFrom.setText(dateSDF.format(date));
                selectedDateinMSTo = System.currentTimeMillis();






            }

        };

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getActivity(), onDateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();


    }

    public void filter(){
        Toast.makeText(getActivity(), ""+spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        if(spinner.getSelectedItem().toString() == null){


           helper.showDataType(spinner.getSelectedItem().toString());

       }
        Toast.makeText(getActivity(), ""+selectedDateinMSFrom+selectedDateinMSTo, Toast.LENGTH_SHORT).show();
       if(dateFrom.getText().toString() != null && dateTo.getText().toString() !=null){

           Toast.makeText(getActivity(), "adasdasd  "+selectedDateinMSFrom+selectedDateinMSTo, Toast.LENGTH_SHORT).show();

           helper.showDataFromTo(selectedDateinMSFrom,selectedDateinMSTo);

       }
        else{
           //Toast.makeText(getActivity(), "filter error", Toast.LENGTH_SHORT).show();
            //helper.showDataType(spinner.getSelectedItem().toString());
        }


    }




}
