package com.example.Expense_Tracker;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

   // private TextView mTextMessage;
    //private Spinner spinner;

   // private ArrayAdapter<String> expanse_adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_dashboard:
                    dashboard dashboard = new dashboard();
                    FragmentManager fmd = getSupportFragmentManager();
                    FragmentTransaction ftd =fmd.beginTransaction();
                    ftd.replace(R.id.framecontainer,dashboard);
                    ftd.addToBackStack("dashboard");
                    ftd.commit();

                   // mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_expanse:
                    expanse expanse = new expanse();
                    FragmentManager fme = getSupportFragmentManager();
                    FragmentTransaction fte =fme.beginTransaction();
                    fte.replace(R.id.framecontainer,expanse);
                    fte.addToBackStack("expanse");
                    fte.commit();
                   // mTextMessage.setText(R.string.title_expanse);
                    return true;
            }
            return false;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> expanse_adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.test_expanse_spinner));

        dashboard dashboard = new dashboard();
        FragmentManager fmd = getSupportFragmentManager();
        FragmentTransaction ftd =fmd.beginTransaction();
        ftd.replace(R.id.framecontainer,dashboard);
        ftd.addToBackStack("dashboard");
        ftd.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addExpanseFragment() {
        AddExpanse addExpanse= new AddExpanse();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.framecontainer,addExpanse);
        ft.commit();

    }
}
