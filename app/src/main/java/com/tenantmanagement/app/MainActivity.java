package com.tenantmanagement.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

import java.io.Serializable;
import java.util.*;
import model.Person;
import model.Property;
import model.Tenant;
import model.Utilities;

public class MainActivity extends AppCompatActivity {

    ListView listView, listViewTenants, listViewUtilities;  // for listing items in listview
    ArrayList<Property> properties = new ArrayList<Property>(); // this arraylist contains all the properties that the user creates

    ArrayList<Utilities> utilities = new ArrayList<>();
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListViewProperties();
    }

    /* this accessor retrieves input entered on the text view  */
    private String getInputOfTextField(int id) {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    /* this method updates the contents of property list */
    public void setListViewProperties(){
        listView = (ListView) findViewById(R.id.listProperty);
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, properties);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,TenantList.class);

                startActivity(intent);
            }
        });
    }

    public void addPropertyButtonCLicked(View view) {
        String name = getInputOfTextField(R.id.inputPropertyName);
        String location = getInputOfTextField(R.id.inputLocation);
        properties.add(Property.getInstanceOfProperty(name, location));
        Toast.makeText(getApplicationContext(), "Property added.", Toast.LENGTH_SHORT).show();
        setListViewProperties();
    }

    public void removePropertyButtonClicked(View view) {
        String name = getInputOfTextField(R.id.inputPropertyName);
        String location = getInputOfTextField(R.id.inputLocation);
        Property p = findProperty(name, location);
        if (p != null) {
            properties.remove(p);
            Toast.makeText(getApplicationContext(), "Property removed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Property not present.", Toast.LENGTH_SHORT).show();
        }
        setListViewProperties();
    }

    public Property findProperty(String name, String location) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getName().equals(name) && properties.get(i).getLocation().equals(location)) {
                return (properties.get(i));
            }
        }
        return null;
    }
}
