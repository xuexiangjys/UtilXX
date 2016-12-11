package com.example.mycustomview.custom;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mycustomview.R;
import com.xuexiang.view.calendar.data.CalendarDate;
import com.xuexiang.view.calendar.fragment.CalendarViewFragment;
import com.xuexiang.view.calendar.fragment.CalendarViewPagerFragment;


public class CalendarActivity extends AppCompatActivity implements
        CalendarViewPagerFragment.OnPageChangeListener,
        CalendarViewFragment.OnDateClickListener,
        CalendarViewFragment.OnDateCancelListener {

    private TextView tv_date;
    private boolean isChoiceModelSingle = false;
    private List<CalendarDate> mListDate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        tv_date = (TextView) findViewById(R.id.tv_date);
        initFragment();
    }

   private void initFragment(){
       FragmentManager fm = getSupportFragmentManager();
       FragmentTransaction tx = fm.beginTransaction();
       Fragment fragment = CalendarViewPagerFragment.newInstance(isChoiceModelSingle);
       tx.replace(R.id.fl_content, fragment);
       tx.commit();
   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_single:
                isChoiceModelSingle = true;
                initFragment();
                break;
            case R.id.menu_multi:
                isChoiceModelSingle = false;
                initFragment();
                break;
            default:
                break;
        }
        return true;
    }
    @Override
    public void onDateClick(CalendarDate calendarDate) {

        int year = calendarDate.getSolar().solarYear;
        int month = calendarDate.getSolar().solarMonth;
        int day = calendarDate.getSolar().solarDay;
        if (isChoiceModelSingle) {
            tv_date.setText(year + "-" + month + "-" + day);
        } else {
            mListDate.add(calendarDate);
            tv_date.setText(listToString(mListDate));
        }

    }

    @Override
    public void onDateCancel(CalendarDate calendarDate) {
        int count = mListDate.size();
        for (int i = 0; i < count; i++) {
            CalendarDate date = mListDate.get(i);
            if (date.getSolar().solarDay == calendarDate.getSolar().solarDay) {
                mListDate.remove(i);
                break;
            }
        }
        tv_date.setText(listToString(mListDate));
    }

    @Override
    public void onPageChange(int year, int month) {
        tv_date.setText(year + "-" + month);
        mListDate.clear();
    }

    private static String listToString(List<CalendarDate> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (CalendarDate date : list) {
            stringBuffer.append(date.getSolar().solarYear + "-" + date.getSolar().solarMonth + "-" + date.getSolar().solarDay).append(" ");
        }
        return stringBuffer.toString();
    }

}
