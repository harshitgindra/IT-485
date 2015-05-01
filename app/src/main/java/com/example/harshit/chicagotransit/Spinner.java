package com.example.harshit.chicagotransit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * Created by harshit on 3/2/2015.
 */
public class Spinner extends MainActivity{

    android.widget.Spinner spinner;
    String [] a = {"a", "b", "c"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);
        initialize();
    }

    private void initialize() {
        spinner = (android.widget.Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, a);
        spinner.setAdapter(adapter);
        spinner.setOnItemClickListener(new MySpinnerSelection());
    }


    private class MySpinnerSelection implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
Toast.makeText(getApplicationContext(), "The selection is "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG ).show();
        }
    }
}
