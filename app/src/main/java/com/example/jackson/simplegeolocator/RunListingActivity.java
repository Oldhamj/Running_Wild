package com.example.jackson.simplegeolocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class RunListingActivity extends AppCompatActivity {
    String runName;
    JSONObject run;
    String description;
    String duration;
    String distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_listing);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        runName = intent.getExtras().getString("RunName");
        try {
            JSONObject runs = new JSONObject(readJSONFromAsset());
            run = new JSONObject(runs.getString(runName));
            description = run.getString("Description");
            duration = run.getString("Duration");
            distance = run.getString("Distance");
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        TextView runNameField = findViewById(R.id.runName);
        runNameField.setText(runName);
        TextView runDescriptionField = findViewById(R.id.runDescription);
        runDescriptionField.setText(description);
        TextView runDurationField = findViewById(R.id.runDuration);
        runDurationField.setText(duration);
        TextView runDistanceField = findViewById(R.id.runDistance);
        runDistanceField.setText(distance);
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
