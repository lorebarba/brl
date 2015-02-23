package com.brl.snappinglistviewapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SnappingListView mSnappingListView = (SnappingListView) findViewById(R.id.snapping_list_view);

        List<String> mListViewObjects = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            mListViewObjects.add("Element_" + i);
        }

        mSnappingListView.setSnappingSpeed(1000);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, R.layout.list_row, mListViewObjects);
        mSnappingListView.setAdapter(mAdapter);
    }
}