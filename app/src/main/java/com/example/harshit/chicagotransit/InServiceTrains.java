package com.example.harshit.chicagotransit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by harshit on 2/21/2015.
 */
public class InServiceTrains extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent about = new Intent("com.example.harshit.chicagotransit.MENU");
        startActivity(about);
    }
}