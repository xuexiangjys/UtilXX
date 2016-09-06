package com.example.testutil.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testutil.view.circularfloatingactionmenu.MenuInScrollViewActivity;
import com.example.testutil.view.circularfloatingactionmenu.MenuWithCustomActionButtonActivity;
import com.example.testutil.view.circularfloatingactionmenu.MenuWithCustomAnimationActivity;
import com.example.testutil.view.circularfloatingactionmenu.MenuWithFABActivity;
import com.example.testutil.view.circularfloatingactionmenu.SystemOverlayMenuActivity;

public class CircularFloatingActionMenuActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        
        String[] items = { "Menu with FloatingActionButton",
                "Menu attached to custom button",
                "Menu with custom animation",
                "Menu in ScrollView",
                "Menu as system overlay"
             };
		ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
	    getListView().setAdapter(simpleAdapter);
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
    	switch (position) {
        case 0:
            startActivity(new Intent(this, MenuWithFABActivity.class));
            break;
        case 1:
            startActivity(new Intent(this, MenuWithCustomActionButtonActivity.class));
            break;
        case 2:
            startActivity(new Intent(this, MenuWithCustomAnimationActivity.class));
            break;
        case 3:
            startActivity(new Intent(this, MenuInScrollViewActivity.class));
            break;
        case 4:
            startActivity(new Intent(this, SystemOverlayMenuActivity.class));
            break;
		}		
	}

}
