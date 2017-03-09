package com.example.testutil.view;

import java.util.ArrayList;
import java.util.List;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.satellitemenu.SatelliteMenu;
import com.xuexiang.view.satellitemenu.SatelliteMenu.SateliteClickedListener;
import com.xuexiang.view.satellitemenu.SatelliteMenuItem;

public class SatelliteMenuActivity extends BaseActivity {
    
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_satellitemenu);
		initTitleBar(TAG);
        SatelliteMenu menu = (SatelliteMenu) findViewById(R.id.menu);
        
//			  Set from XML, possible to programmatically set        
//	        float distance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, getResources().getDisplayMetrics());
//	        menu.setSatelliteDistance((int) distance);
//	        menu.setExpandDuration(500);
//	        menu.setCloseItemsOnClick(false);
//	        menu.setTotalSpacingDegree(60);
        
        List<SatelliteMenuItem> items = new ArrayList<SatelliteMenuItem>();
        items.add(new SatelliteMenuItem(4, R.drawable.ic_1));
        items.add(new SatelliteMenuItem(4, R.drawable.ic_3));
        items.add(new SatelliteMenuItem(4, R.drawable.ic_4));
        items.add(new SatelliteMenuItem(3, R.drawable.ic_5));
        items.add(new SatelliteMenuItem(2, R.drawable.ic_6));
        items.add(new SatelliteMenuItem(1, R.drawable.ic_2));
//	        items.add(new SatelliteMenuItem(5, R.drawable.sat_item));
        menu.addItems(items);        
        menu.setMainImage(R.drawable.sat_item);
        menu.setOnItemClickedListener(new SateliteClickedListener() {
			
			public void eventOccured(int id) {
				Toast("Clicked on " + id);
			}
		});
	}
}
