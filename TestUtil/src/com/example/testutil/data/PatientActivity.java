
package com.example.testutil.data;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testutil.R;
import com.example.testutil.data.adapter.PatientAdapter;
import com.example.testutil.data.entity.Patient;
import com.xuexiang.util.data.db.ahibernate.DatabaseService;

public class PatientActivity extends Activity {
    /** Called when the activity is first created. */
    DatabaseService<Patient> mDatabaseService;
    private PatientAdapter adapter;
    private List<Patient> data=new  ArrayList<Patient>();

    Button mAddButton;
    Button mQueryButton;
    private ListView lvData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        
        mAddButton = (Button) findViewById(R.id.add);
        mQueryButton = (Button) findViewById(R.id.query);
        lvData=(ListView) findViewById(R.id.lvData);
        
        mDatabaseService = new DatabaseService<Patient>(this,Patient.class);
        mAddButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
            	Patient pat = new Patient();
                pat.setName("baocui");
                pat.setAge("23宀�");
                int row=mDatabaseService.add(pat);
                if(row>0){
                	 Toast.makeText(PatientActivity.this, "鎻掑叆鎴愬姛", Toast.LENGTH_SHORT).show();
                }else{
                	 Toast.makeText(PatientActivity.this, "鎻掑叆澶辫触", Toast.LENGTH_SHORT).show();
                }
               
            }
        });

        mQueryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	data=mDatabaseService.getObjectsByWhere(null);
            	adapter=new PatientAdapter(PatientActivity.this, data,mDatabaseService);
            	lvData.setAdapter(adapter);
            }
        });
        
       /* lvData.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Patient p=data.get(position);
				
				Map<String,String> map=new HashMap<String, String>();
				map.put("id", p.getId()+"");
				p.setName(p.getName()+"ttt");
				//mDatabaseService.update(p, map);
				mDatabaseService.delete(map);
				data=mDatabaseService.getObjectsByWhere(null);
				adapter.setData(data);
				
			}
			
		});*/
    }
      
}
