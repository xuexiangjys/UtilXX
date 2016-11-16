package com.example.testutil.resource;

import java.io.File;
import java.util.Collection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.pluginmgr.PluginManager;
import androidx.pluginmgr.environment.PlugInfo;

import com.example.testutil.R;
import com.xuexiang.util.file.LocalFileUtil;
public class PlugLoadActivity extends Activity {
	private ListView plugListView;
	//
	private PluginManager plugMgr;
	private EditText pluginDirTxt;

	private static final String sdcard = LocalFileUtil.APK_PATH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plugload);

		pluginDirTxt = (EditText) findViewById(R.id.pluginDirTxt);
		Button selectpath = (Button) findViewById(R.id.selectpath);
		Button pluginLoader = (Button) findViewById(R.id.pluginLoader);
		plugListView = (ListView) findViewById(R.id.pluglist);

		plugMgr = PluginManager.getSingleton();

		String pluginSrcDir = sdcard;
		pluginDirTxt.setText(pluginSrcDir);

		selectpath.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
			    intent.setType("*/*"); 
			    intent.addCategory(Intent.CATEGORY_OPENABLE);
			    try {
			        startActivityForResult( Intent.createChooser(intent, "选择路径"), 1);
			    } catch (android.content.ActivityNotFoundException ex) {
			       
			    }
			}
		});
		
		plugListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				plugItemClick(position);
			}
		});

		final Context context = this;
		pluginLoader.setOnClickListener(new View.OnClickListener() {
			private volatile boolean plugLoading = false;

			@Override
			public void onClick(View v) {
				final String dirText = pluginDirTxt.getText().toString().trim();
				if (TextUtils.isEmpty(dirText)) {
					Toast.makeText(context, getString(R.string.pl_dir),
							Toast.LENGTH_LONG).show();
					return;
				}
				if (plugLoading) {
					Toast.makeText(context, getString(R.string.loading),
							Toast.LENGTH_LONG).show();
					return;
				}
				String strDialogTitle = getString(R.string.dialod_loading_title);
				String strDialogBody = getString(R.string.dialod_loading_body);
				final ProgressDialog dialogLoading = ProgressDialog.show(
						context, strDialogTitle, strDialogBody, true);
				new Thread(new Runnable() {
					@Override
					public void run() {
						plugLoading = true;
						try {
							Collection<PlugInfo> plugs = plugMgr
									.loadPlugin(new File(dirText));
                            PluginManager.getSingleton().dump();
                            setPlugins(plugs);
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							dialogLoading.dismiss();
						}
						plugLoading = false;
					}
				}).start();
			}
		});
	}

	 @Override  
	  	protected void onActivityResult(int requestCode, int resultCode, Intent data)  {   		   
		  		switch(requestCode){
		  		case 1:
	  				if(data!=null){
	  					pluginDirTxt.setText(getAbsolutePath(data.getData()));
	  				}
	  				break;
		  		}	  			
	  	} 
	    
	 // 取到绝对路径
		protected String getAbsolutePath(Uri uri) {
			// can post image
			String[] proj = { MediaStore.Images.Media.DATA };
			Cursor cursor = managedQuery(uri, proj, // Which columns to return
					null, // WHERE clause; which rows to return (all rows)
					null, // WHERE clause selection arguments (none)
					null); // Order-by clause (ascending by name)

			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		}
		
	private void plugItemClick(int position) {
		PlugInfo plug = (PlugInfo) plugListView.getItemAtPosition(position);
		plugMgr.startMainActivity(this, plug);
	}

	private void setPlugins(final Collection<PlugInfo> plugs) {
		if (plugs == null || plugs.isEmpty()) {
			return;
		}
		final ListAdapter adapter = new PlugListViewAdapter(this, plugs);
		runOnUiThread(new Runnable() {
			public void run() {
				plugListView.setAdapter(adapter);
			}
		});
	}
}
