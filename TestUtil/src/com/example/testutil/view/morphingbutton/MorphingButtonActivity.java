package com.example.testutil.view.morphingbutton;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testutil.R;

public class MorphingButtonActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] items = getResources().getStringArray(R.array.sample_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                MorphingButtonIconActivity.startThisActivity(this);
                break;
            case 1:
                MorphingButtonTextActivity.startThisActivity(this);
                break;
            case 2:
                ProgressButtonDeterminateActivity.startThisActivity(this);
                break;
            case 3:
                ProgressButtonIndeterminateActivity.startThisActivity(this);
                break;
        }
    }
}
