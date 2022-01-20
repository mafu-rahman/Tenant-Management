package com.tenantmanagement.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import model.GlobalVariables;
import model.Tenant;

public class TenantView extends AppCompatActivity {

    Tenant tenant;
    int indexProperty, indexTenant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_view);

        indexProperty = (Integer) getIntent().getIntExtra("property", 0);
        indexTenant = (Integer) getIntent().getIntExtra("tenant", 0);
        tenant = (Tenant) GlobalVariables.properties.get(indexProperty).getPerson().get(indexTenant);
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




}