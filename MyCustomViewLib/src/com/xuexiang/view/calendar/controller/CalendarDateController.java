package com.xuexiang.view.calendar.controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.xuexiang.view.calendar.data.CalendarDate;
import com.xuexiang.view.calendar.data.Lunar;
import com.xuexiang.view.calendar.data.Solar;
import com.xuexiang.view.calendar.utils.CalendarUtils;
import com.xuexiang.view.calendar.utils.LunarSolarConverter;


/**
 * Created by joybar on 2/24/16.
 */
public class CalendarDateController {

    public static List<CalendarDate> getCalendarDate(int year, int month) {
        List<CalendarDate> mListDate = new ArrayList<>();
        List<CalendarUtils.CalendarSimpleDate> list = null;
        try {
            list = CalendarUtils.getEverydayOfMonth(year, month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int count = list.size();

        for (int i = 0; i < count; i++) {
            Solar solar = new  Solar();
            solar.solarYear = list.get(i).getYear();
            solar.solarMonth = list.get(i).getMonth();
            solar.solarDay = list.get(i).getDay();
            Lunar lunar = LunarSolarConverter.SolarToLunar(solar);
            mListDate.add(new CalendarDate( month == list.get(i).getMonth(), false,solar,lunar));
        }

        return mListDate;
    }


}
