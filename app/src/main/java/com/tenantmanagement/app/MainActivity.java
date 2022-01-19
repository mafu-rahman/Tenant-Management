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
    /*
    Floating button animation
     */
//    Animation rotateOpen =  AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
//    Animation rotateClose =  AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
//    Animation fromBottom =  AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
//    Animation toBottom =  AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);
//    boolean clicked = false;
//    Button editProperty = findViewById(R.id.floatingActionButtonEditProperty);
//    Button addProperty = findViewById(R.id.floatingActionButtonAddProperty);
//    Button removeProperty = findViewById(R.id.floatingActionButtonRemoveProperty);





    ListView listView;
    ArrayList<Property> properties = new ArrayList<Property>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        editProperty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onAddButtonClicked();
//            }
//        });
//
//       addProperty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Add Property", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        removeProperty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Remove Property", Toast.LENGTH_SHORT).show();
//            }
//        });

        listView = (ListView) findViewById(R.id.listProperty);

        properties.add(Property.getInstanceOfProperty("cdc", "cdd"));
        properties.add(Property.getInstanceOfProperty("cdc", "cdd"));
        properties.add(Property.getInstanceOfProperty("cdc", "cdd"));

        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, properties);
        listView.setAdapter(adapter);
    }

//    private void onAddButtonClicked(){
//        setVisibility(clicked);
//        setAnimation(clicked);
//        clicked = !clicked;
//    }
//
//    private void setVisibility(boolean b){
//        if(!clicked){
//            addProperty.setVisibility(View.VISIBLE);
//            removeProperty.setVisibility(View.VISIBLE);
//        }
//        else{
//            addProperty.setVisibility(View.INVISIBLE);
//            removeProperty.setVisibility(View.INVISIBLE);
//        }
//    }
//
//    private void setAnimation(boolean b){
//        if(!clicked){
//            addProperty.startAnimation(fromBottom);
//            removeProperty.startAnimation(fromBottom);
//            editProperty.startAnimation(rotateOpen);
//        }
//        else{
//            addProperty.startAnimation(toBottom);
//            removeProperty.startAnimation(toBottom);
//            editProperty.startAnimation(rotateClose);
//        }
    }
