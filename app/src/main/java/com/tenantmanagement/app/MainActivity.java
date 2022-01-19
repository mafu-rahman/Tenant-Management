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

    }

    /* this mutator sets the output label */
    private void setContentsOfTextView(int id, String newContents) {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    /* this accessor retrieves input entered on the text view  */
    private String getInputOfTextField(int id) {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    /* this accessor retrieves input chosen from some spinner (drop-down menu) */
    private String getItemSelected(int id) {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }


    public void addProperty(View view) {
        String name = getInputOfTextField(R.id.inputPropertyName);
        String location = getInputOfTextField(R.id.inputLocation);
        properties.add(Property.getInstanceOfProperty(name, location));
        Toast.makeText(getApplicationContext(), "Property added.", Toast.LENGTH_LONG).show();
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, properties);
        listView.setAdapter(adapter);
    }

    public void removeProperty(View view) {
        String name = getInputOfTextField(R.id.inputPropertyName);
        String location = getInputOfTextField(R.id.inputLocation);
        Property p = findProperty(name, location);
        if (p != null) {
            properties.remove(p);
            Toast.makeText(getApplicationContext(), "Property removed.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Property not present.", Toast.LENGTH_LONG).show();
        }
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, properties);
        listView.setAdapter(adapter);
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
