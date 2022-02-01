package com.tenantmanagement.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import model.GlobalVariables;
import model.Tenant;
import model.Time;

public class TenantView extends AppCompatActivity {

    Tenant tenant;
    int indexProperty, indexTenant;

    ListView listView;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_view);

        indexProperty = (Integer) getIntent().getIntExtra("property", 0);
        indexTenant = (Integer) getIntent().getIntExtra("tenant", 0);
        tenant = (Tenant) GlobalVariables.properties.get(indexProperty).getPerson().get(indexTenant);

        setListViewUtilities();
    }

    public void setListViewUtilities() {
        listView = (ListView) findViewById(R.id.listViewUtilitiesTenantInfo);
        adapter = new ArrayAdapter(TenantView.this, android.R.layout.simple_list_item_1, tenant.getUtilities());
        listView.setAdapter(adapter);
    }

    /* this mutator changes contents of text field */
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

    public void onButtonViewInfoClicked(View view){
        String month = getItemSelected(R.id.inputSpinnerMonthView);
        int year = Integer.parseInt(getInputOfTextField(R.id.inputYearView));
        Time time = Time.getInstanceOfTime(month,year);

        setContentsOfTextView(R.id.outputName, tenant.getName());
        setContentsOfTextView(R.id.outputContact, tenant.getPhoneNo()+"");
        setContentsOfTextView(R.id.outputFlat, tenant.getFlatNo());
        setContentsOfTextView(R.id.outputDateJoined, tenant.getDateJoined());
        setContentsOfTextView(R.id.outputBaseRent, tenant.getBaseRent()+"");
        setContentsOfTextView(R.id.outputTotalUtilities, tenant.getTotalUtilities()+"");
        setContentsOfTextView(R.id.outputTotalRent, tenant.getTotalRent()+"");

        if (tenant.getAmountPaidMonth(time) != -1.0)
            setContentsOfTextView(R.id.outputAmountPaid, tenant.getAmountPaidMonth(time)+"");
        else
            setContentsOfTextView(R.id.outputAmountPaid, "Not Paid");

        setContentsOfTextView(R.id.outputRemainingAmount, tenant.getRemainingDues(time)+"");
    }

    public void onButtonPayRentClicked(View view){
        String month = getItemSelected(R.id.inputSpinnerMonthView);
        int year = Integer.parseInt(getInputOfTextField(R.id.inputYearView));
        Time time = Time.getInstanceOfTime(month,year);

        double amount = Double.parseDouble(getInputOfTextField(R.id.inputPayRent));
        tenant.payRent(amount, time);
        onButtonViewInfoClicked(view.findViewById(R.id.buttonViewInfo));
    }


}