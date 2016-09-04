package com.example.testutil;

import java.util.Arrays;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testutil.adapter.MultiAdapterActivity;
import com.example.testutil.adapter.SimpleAdapterActivity;

public class AdapterListviewActivity extends ListActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		getListView().setAdapter(
				new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, Arrays.asList(
								"Simple Adapter Text", "MultiItemStyleText")));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		Intent intent = null;
		switch (position)
		{
		case 0:
			intent = new Intent(this, SimpleAdapterActivity.class);
			break;
		case 1:
			intent = new Intent(this, MultiAdapterActivity.class);
			break;
		}
		if (intent != null)
			startActivity(intent);
	}
}
