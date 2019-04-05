package com.example.swiperefresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.swiperefresh.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout pullToRefresh;
    ArrayList menu;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);

        addContent();

        adapter = new ArrayAdapter(this, R.layout.listview, menu);

        ListView listView = (ListView) findViewById(R.id.mobile_lis);

        listView.setAdapter(adapter);

        //setting an setOnRefreshListener on the SwipeDownLayout
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            int Refreshcounter = 1; //Counting how many times user have refreshed the layout

            @Override
            public void onRefresh() {
                //Here you can update your data from internet or from local SQLite data
                if (Refreshcounter == 1) {
                    menu.add("Blackberry");
                }
                if (Refreshcounter == 2) {
                    menu.add("WebOS");
                }
                if (Refreshcounter == 3) {
                    menu.add("Ubuntu");
                }
                if (Refreshcounter == 4) {
                    menu.add("Windows7");
                }
                if (Refreshcounter == 5) {
                    menu.add("Max OS X");
                }
                if (Refreshcounter > 5) {
                    Toast.makeText(MainActivity.this, "No more data to load!!",
                            Toast.LENGTH_SHORT).show();
                }
                Refreshcounter = Refreshcounter + 1;

                adapter.notifyDataSetChanged();

                pullToRefresh.setRefreshing(false);
            }
        });
    }

    //Function to add Item in the list
    public void addContent() {
        menu = new ArrayList();
        menu.add("Android");
        menu.add("IPhone");
        menu.add("WindowsMobile");
    }
}