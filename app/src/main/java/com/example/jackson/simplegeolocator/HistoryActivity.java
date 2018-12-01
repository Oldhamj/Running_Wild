package com.example.jackson.simplegeolocator;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;

public class HistoryActivity extends BaseActivity {

    private final LinkedList<String> mRunList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.stuff); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_history, contentFrameLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(3).setChecked(true);

        try {
            JSONObject runs = new JSONObject(readJSONFromAsset());
            //System.out.println(skills.getString("Java"));
            Iterator<String> keys = runs.keys();
            while(keys.hasNext()) {
                mRunList.addLast(keys.next());
            }
            mRecyclerView = findViewById(R.id.recyclerview);
            mAdapter = new ListAdapter(this, mRunList);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                    DividerItemDecoration.VERTICAL));


        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
    }

    public String readJSONFromAsset() {
        String json;
        try {
            File jsonFile = new File("../../assets/runs.json");
            InputStream is = getAssets().open(jsonFile.getName());
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
