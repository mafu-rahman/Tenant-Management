package com.tenantmanagement.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;

import model.GlobalVariables;
import model.Tenant;
import model.Utilities;

public class TenantList extends AppCompatActivity implements Serializable {

    ListView listView, listView2;
    ArrayList<Utilities> utilities = new ArrayList<>();
    ArrayAdapter adapter, adapter2;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_list);

        Intent intent = getIntent();
        if(intent.hasExtra("index")){
            index = (Integer) intent.getIntExtra("index", 0);
            setListViewTenants();
        }
    }

    public void setListViewTenants(){
        listView = (ListView) findViewById(R.id.tenantListView);
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, GlobalVariables.properties.get(index).getPerson());
        listView.setAdapter(adapter);

        /* ON click of tenant in the list it opens new activity for displaying on tenant monthly and yearly */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TenantList.this, TenantView.class);
                intent.putExtra("property", index);
                intent.putExtra("tenant", position);
                startActivity(intent);
            }
        });
    }

    public void setListViewUtilities() {
        listView2 = (ListView) findViewById(R.id.listViewUtilities);
        adapter2 = new ArrayAdapter(TenantList.this, android.R.layout.simple_list_item_1, utilities);
        listView2.setAdapter(adapter2);
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

    public void addTenantButtonClicked(View view){
        setContentView(R.layout.activity_add_tenant);
    }

    public void addUtilityButtonCLicked(View view){
        String utilityType = getInputOfTextField(R.id.inputUtilityType);
        double utilityAmount = Double.parseDouble(getInputOfTextField(R.id.inputUtilityAmount));
        utilities.add(Utilities.getInstanceOfBill(utilityType, utilityAmount));
        setListViewUtilities();
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

        Tenant p = Tenant.getInstanceOfTenant(name, flat, phone, baseRent, advancePayment, securityDeposit, (month + ", " + year));
        p.addUtility(utilities);
        GlobalVariables.properties.get(index).addPerson(p);
        utilities.clear();

        setContentView(R.layout.activity_tenant_list);
        setListViewTenants();
    }
}