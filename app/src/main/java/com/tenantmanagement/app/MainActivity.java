package com.tenantmanagement.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Toast;
import java.util.*;

import model.Person;
import model.Property;
import model.Tenant;
import model.Utilities;

public class MainActivity extends AppCompatActivity {

    ListView listView, listViewTenants, listViewUtilities;
    ArrayList<Property> properties = new ArrayList<Property>();
    ArrayList<Person> tenants = new ArrayList<>();
    ArrayList<Utilities> utilities = new ArrayList<>();
    ArrayAdapter adapter, adapter2, adapter3;
    int globalPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listProperty);
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, properties);
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

    public void setListView(){
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, properties);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tenantListWindow(position);
            }
        });
    }

    public void setListViewUtilities(){
        listViewUtilities = (ListView) findViewById(R.id.listViewUtilities);
        adapter3 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, utilities);
        listViewUtilities.setAdapter(adapter3);
    }


    public void addProperty(View view) {
        String name = getInputOfTextField(R.id.inputPropertyName);
        String location = getInputOfTextField(R.id.inputLocation);
        properties.add(Property.getInstanceOfProperty(name, location));
        Toast.makeText(getApplicationContext(), "Property added.", Toast.LENGTH_LONG).show();
        setListView();
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
        setListView();
    }

    public Property findProperty(String name, String location) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getName().equals(name) && properties.get(i).getLocation().equals(location)) {
                return (properties.get(i));
            }
        }
        return null;
    }

    public void tenantListWindow(int position){
        globalPosition = position;
        setContentView(R.layout.tenant_list);
        tenants = properties.get(position).getPerson();
        listViewTenants = (ListView) findViewById(R.id.tenantListView);
        adapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tenants);
        listViewTenants.setAdapter(adapter2);

        listViewTenants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tenantMonthlyDetail(position);
            }
        });
    }

    public void addTenantButtonClicked(View view){
        setContentView(R.layout.add_tenant);
    }

    public void confirmButtonCLicked(View view){
        String name = getInputOfTextField(R.id.inputTenantName);
        int phone = Integer.parseInt(getInputOfTextField(R.id.inputTenantPhoneNumber));
        String flat = getInputOfTextField(R.id.inputFlatNumber);
        int year = Integer.parseInt(getInputOfTextField(R.id.inputYear));
        String month = getItemSelected(R.id.inputSpinnerMonth);
        double baseRent = Double.parseDouble(getInputOfTextField(R.id.inputBaseRent));
        double advancePayment = Double.parseDouble(getInputOfTextField(R.id.inputAdvancePayment));
        double securityDeposit = Double.parseDouble(getInputOfTextField(R.id.inputSecurityPayment));
        Tenant p = Tenant.getInstanceOfTenant(name, flat, phone, baseRent, advancePayment, securityDeposit, (month + " " + year));
        p.addUtility(utilities);
        properties.get(globalPosition).addPerson(p);
        utilities.clear();
        tenantListWindow(globalPosition);
    }

    public void addUtilityButtonCLicked(View view){
        String utilityType = getInputOfTextField(R.id.inputUtilityType);
        double utilityAmount = Double.parseDouble(getInputOfTextField(R.id.inputUtilityAmount));
        utilities.add(Utilities.getInstanceOfBill(utilityType, utilityAmount));
        setListViewUtilities();
    }





    public void tenantMonthlyDetail(int position){
        setContentView(R.layout.tenant_monthly_details);
    }
}
