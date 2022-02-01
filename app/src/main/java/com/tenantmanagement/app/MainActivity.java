package com.tenantmanagement.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

import model.GlobalVariables;
import model.Property;

public class MainActivity extends AppCompatActivity {

    ListView listView;  // for listing items in listview
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
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, GlobalVariables.properties);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,TenantList.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
    }

    public void addPropertyButtonCLicked(View view) {
        String name = getInputOfTextField(R.id.inputPropertyName);
        String location = getInputOfTextField(R.id.inputLocation);
        GlobalVariables.properties.add(Property.getInstanceOfProperty(name, location));
        Toast.makeText(getApplicationContext(), "Property added.", Toast.LENGTH_SHORT).show();
        setListViewProperties();
    }

    public void removePropertyButtonClicked(View view) {
        String name = getInputOfTextField(R.id.inputPropertyName);
        String location = getInputOfTextField(R.id.inputLocation);
        Property p = findProperty(name, location);
        if (p != null) {
            GlobalVariables.properties.remove(p);
            Toast.makeText(getApplicationContext(), "Property removed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Property not present.", Toast.LENGTH_SHORT).show();
        }
        setListViewProperties();
    }

    public Property findProperty(String name, String location) {
        for (int i = 0; i < GlobalVariables.properties.size(); i++) {
            if (GlobalVariables.properties.get(i).getName().equals(name) && GlobalVariables.properties.get(i).getLocation().equals(location)) {
                return (GlobalVariables.properties.get(i));
            }
        }
        return null;
    }
}
