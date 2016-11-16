package com.example.testutil.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.stepsview.StepsView;


public class StepsViewActivity extends BaseActivity {

    private final String[] views = {"View 1", "View 2", "View 3", "View 4", "View 5", "View 6",
            "View 7", "View 8", "View 9", "View 10", "View 11", "View 12"};

    @Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_stepsview);
		initTitleBar(TAG);
        ListView mListView = (ListView) findViewById(R.id.list);

        MyAdapter adapter = new MyAdapter(this, 0);
        adapter.addAll(views);

        mListView.setAdapter(adapter);
		
	}

    public static class MyAdapter extends ArrayAdapter<String> {

        private final String[] labels = {"Step 1", "Step 2", "Step 3", "Step 4", "Step 5"};

        public MyAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_listview_stepview, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.mLabel.setText(getItem(position));

            holder.mStepsView.setCompletedPosition(position % labels.length)
                    .setLabels(labels)
                    .setBarColorIndicator(
                            getContext().getResources().getColor(R.color.bluegrey800))
                    .setProgressColorIndicator(getContext().getResources().getColor(R.color.orange))
                    .setLabelColorIndicator(getContext().getResources().getColor(R.color.orange))
                    .drawView();

            return convertView;
        }

        static class ViewHolder {

            TextView mLabel;
            StepsView mStepsView;

            public ViewHolder(View view) {
                mLabel = (TextView) view.findViewById(R.id.label);
                mStepsView = (StepsView) view.findViewById(R.id.stepsView);
            }
        }
    }

	

}
