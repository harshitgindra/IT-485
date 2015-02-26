package com.example.harshit.chicagotransit;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;

/**
 * Created by harshit on 2/23/2015.
 */
public class DisplayList extends ListActivity {

    String menuitem[] = {"Contacts","RssFeed","RefreshSwipe","Swipes","SettingUpWallpaper" ,"ImageLoaderURL","XmlEg", "Translate", "JsonReader", "Tabs", "HttpEg", "SharedPrefs", "Flipper", "SqlLite", "SqlView", "OurBrowser", "StartingPoint", "Camera", "Data", "TextPlay", "Email"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(DisplayList.this, android.R.layout.simple_expandable_list_item_1, menuitem));
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater disp = getMenuInflater();
        disp.inflate(R.menu.cool, menu);
        return true;
    }


}
