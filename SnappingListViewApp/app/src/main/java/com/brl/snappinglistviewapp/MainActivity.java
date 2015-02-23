package com.brl.snappinglistviewapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Main Activity to show SnappingListView functionality.
 *
 * @author Lorenzo Barbagli
 * @see <a href="http://www.lorenzobarbagli.net">www.lorenzobarbagli.net</a>
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the SnappingListView
        SnappingListView mSnappingListView = (SnappingListView) findViewById(R.id.snapping_list_view);

        // Here we create a simple Adapter using a List<String>
        List<String> mListViewObjects = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            mListViewObjects.add("Element_" + i);
        }

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, R.layout.list_row, mListViewObjects);

        // You can change snapping speed
        mSnappingListView.setSnappingSpeed(700);

        // Set the adapter
        mSnappingListView.setAdapter(mAdapter);

        //That's all :)
    }
}