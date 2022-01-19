package com.tenantmanagement.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import android.widget.Toast;
import java.util.*;
import model.Property;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Property> properties = new ArrayList<Property>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listProperty);

        properties.add(Property.getInstanceOfProperty("cdc", "cdd"));
        properties.add(Property.getInstanceOfProperty("cdc", "cdd"));
        properties.add(Property.getInstanceOfProperty("cdc", "cdd"));

        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, properties);
        listView.setAdapter(adapter);
    }
}
