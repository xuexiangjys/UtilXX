package com.xuexiang.view.pickers.picker;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import android.app.Activity;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuexiang.view.pickers.adapter.ArrayWheelAdapter;
import com.xuexiang.view.pickers.common.LineConfig;
import com.xuexiang.view.pickers.listeners.OnItemPickListener;
import com.xuexiang.view.pickers.util.DateUtils;
import com.xuexiang.view.pickers.util.LogUtils;
import com.xuexiang.view.pickers.widget.WheelListView;
import com.xuexiang.view.pickers.widget.WheelView;

/**
 * 日期时间选择器，可同时选中日期及时间，另见{@link DatePicker}和{@link TimePicker}
 * @author matt
 * blog: addapp.cn
 * @since 2015/9/29
 */
public class DateTimePicker extends WheelPicker {
    /**
     * 不显示
     */
    public static final int NONE = -1;
    /**
     * 年月日
     */
    public static final int YEAR_MONTH_DAY = 0;
    /**
     * 年月
     */
    public static final int YEAR_MONTH = 1;
    /**
     * 月日
     */
    public static final int MONTH_DAY = 2;

    /**
     * 24小时
     */
    public static final int HOUR_24 = 3;
    /**
     * 12小时
     */
    public static final int HOUR_12 = 4;

    private ArrayList<String> years = new ArrayList<>();
    private ArrayList<String> months = new ArrayList<>();
    private ArrayList<String> days = new ArrayList<>();
    private ArrayList<String> hours = new ArrayList<>();
    private ArrayList<String> minutes = new ArrayList<>();
    private String yearLabel = "年", monthLabel = "月", dayLabel = "日";
    private String hourLabel = "时", minuteLabel = "分";
    private int selectedYearIndex = 0, selectedMonthIndex = 0, selectedDayIndex = 0,selectedHourIndex = 0,selectedMinuteIndex = 0;
    private String selectedHour = "", selectedMinute = "";
    private OnWheelListener onWheelListener;
    private OnDateTimePickListener onDateTimePickListener;
    private int dateMode = YEAR_MONTH_DAY, timeMode = HOUR_24;
    private int startYear = 2010, startMonth = 1, startDay = 1;
    private int endYear = 2020, endMonth = 12, endDay = 31;
    private int startHour, startMinute = 0;
    private int endHour, endMinute = 59;

    @IntDef(value = {NONE, YEAR_MONTH_DAY, YEAR_MONTH, MONTH_DAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DateMode {
    }

    @IntDef(value = {NONE, HOUR_24, HOUR_12})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TimeMode {
    }

    /**
     * @see #HOUR_24
     * @see #HOUR_12
     */
    public DateTimePicker(Activity activity, @TimeMode int timeMode) {
        this(activity, YEAR_MONTH_DAY, timeMode);
    }

    public DateTimePicker(Activity activity, @DateMode int dateMode, @TimeMode int timeMode) {
        super(activity);
        if (dateMode == NONE && timeMode == NONE) {
            throw new IllegalArgumentException("The modes are NONE at the same time");
        }
        if (dateMode == YEAR_MONTH_DAY && timeMode != NONE) {
            if (screenWidthPixels < 720) {
                textSize = 14;//年月日时分，比较宽，设置字体小一点才能显示完整
            } else if (screenWidthPixels < 480) {
                textSize = 12;
            }
        }
        this.dateMode = dateMode;
        //根据时间模式初始化小时范围
        if (timeMode == HOUR_12) {
            startHour = 1;
            endHour = 12;
        } else {
            startHour = 0;
            endHour = 23;
        }
        this.timeMode = timeMode;
    }


    /**
     * 设置范围：开始的年月日
     */
    public void setDateRangeStart(int startYear, int startMonth, int startDay) {
        if (dateMode == NONE) {
            throw new IllegalArgumentException("Date mode invalid");
        }
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
    }

    /**
     * 设置范围：结束的年月日
     */
    public void setDateRangeEnd(int endYear, int endMonth, int endDay) {
        if (dateMode == NONE) {
            throw new IllegalArgumentException("Date mode invalid");
        }
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.endDay = endDay;
        initYearData();
    }

    /**
     * 设置范围：开始的年月日
     */
    public void setDateRangeStart(int startYearOrMonth, int startMonthOrDay) {
        if (dateMode == NONE) {
            throw new IllegalArgumentException("Date mode invalid");
        }
        if (dateMode == YEAR_MONTH_DAY) {
            throw new IllegalArgumentException("Not support year/month/day mode");
        }
        if (dateMode == YEAR_MONTH) {
            this.startYear = startYearOrMonth;
            this.startMonth = startMonthOrDay;
        } else if (dateMode == MONTH_DAY) {
            int year = Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR);
            startYear = endYear = year;
            this.startMonth = startYearOrMonth;
            this.startDay = startMonthOrDay;
        }
    }

    /**
     * 设置范围：结束的年月日
     */
    public void setDateRangeEnd(int endYearOrMonth, int endMonthOrDay) {
        if (dateMode == NONE) {
            throw new IllegalArgumentException("Date mode invalid");
        }
        if (dateMode == YEAR_MONTH_DAY) {
            throw new IllegalArgumentException("Not support year/month/day mode");
        }
        if (dateMode == YEAR_MONTH) {
            this.endYear = endYearOrMonth;
            this.endMonth = endMonthOrDay;
        } else if (dateMode == MONTH_DAY) {
            this.endMonth = endYearOrMonth;
            this.endDay = endMonthOrDay;
        }
        initYearData();
    }

    /**
     * 设置范围：开始的时分
     */
    public void setTimeRangeStart(int startHour, int startMinute) {
        if (timeMode == NONE) {
            throw new IllegalArgumentException("Time mode invalid");
        }
        boolean illegal = false;
        if (startHour < 0 || startMinute < 0 || startMinute > 59) {
            illegal = true;
        }
        if (timeMode == HOUR_12 && (startHour == 0 || startHour > 12)) {
            illegal = true;
        }
        if (timeMode == HOUR_24 && startHour >= 24) {
            illegal = true;
        }
        if (illegal) {
            throw new IllegalArgumentException("Time out of range");
        }
        this.startHour = startHour;
        this.startMinute = startMinute;
    }

    /**
     * 设置范围：结束的时分
     */
    public void setTimeRangeEnd(int endHour, int endMinute) {
        if (timeMode == NONE) {
            throw new IllegalArgumentException("Time mode invalid");
        }
        boolean illegal = false;
        if (endHour < 0 || endMinute < 0 || endMinute > 59) {
            illegal = true;
        }
        if (timeMode == HOUR_12 && (endHour == 0 || endHour > 12)) {
            illegal = true;
        }
        if (timeMode == HOUR_24 && endHour >= 24) {
            illegal = true;
        }
        if (illegal) {
            throw new IllegalArgumentException("Time out of range");
        }
        this.endHour = endHour;
        this.endMinute = endMinute;
        initHourData();
    }

    /**
     * 设置年月日时分的显示单位
     */
    public void setLabel(String yearLabel, String monthLabel, String dayLabel, String hourLabel, String minuteLabel) {
        this.yearLabel = yearLabel;
        this.monthLabel = monthLabel;
        this.dayLabel = dayLabel;
        this.hourLabel = hourLabel;
        this.minuteLabel = minuteLabel;
    }

    /**
     * 设置默认选中的年月日时分
     */
    public void setSelectedItem(int year, int month, int day, int hour, int minute) {
        if (dateMode != YEAR_MONTH_DAY) {
            throw new IllegalArgumentException("Date mode invalid");
        }
        LogUtils.verbose(this, "change months and days while set selected");
        changeMonthData(year);
        changeDayData(year, month);
        selectedYearIndex = findItemIndex(years, year);
        selectedMonthIndex = findItemIndex(months, month);
        selectedDayIndex = findItemIndex(days, day);
        if (timeMode != NONE) {
            selectedHour = DateUtils.fillZero(hour);
            selectedMinute = DateUtils.fillZero(minute);
        }
    }

    /**
     * 设置默认选中的年月时分或者月日时分
     */
    public void setSelectedItem(int yearOrMonth, int monthOrDay, int hour, int minute) {
        if (dateMode == YEAR_MONTH_DAY) {
            throw new IllegalArgumentException("Date mode invalid");
        }
        if (dateMode == MONTH_DAY) {
            LogUtils.verbose(this, "change months and days while set selected");
            int year = Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR);
            startYear = endYear = year;
            changeMonthData(year);
            changeDayData(year, yearOrMonth);
            selectedMonthIndex = findItemIndex(months, yearOrMonth);
            selectedDayIndex = findItemIndex(days, monthOrDay);
        } else if (dateMode == YEAR_MONTH) {
            LogUtils.verbose(this, "change months while set selected");
            changeMonthData(yearOrMonth);
            selectedYearIndex = findItemIndex(years, yearOrMonth);
            selectedMonthIndex = findItemIndex(months, monthOrDay);
        }
        if (timeMode != NONE) {
            selectedHour = DateUtils.fillZero(hour);
            selectedMinute = DateUtils.fillZero(minute);
        }
    }

    public void setOnWheelListener(OnWheelListener onWheelListener) {
        this.onWheelListener = onWheelListener;
    }

    public void setOnDateTimePickListener(OnDateTimePickListener listener) {
        this.onDateTimePickListener = listener;
    }

    public String getSelectedYear() {
        if (dateMode == YEAR_MONTH_DAY || dateMode == YEAR_MONTH) {
            if (years.size() <= selectedYearIndex) {
                selectedYearIndex = years.size() - 1;
            }
            return years.get(selectedYearIndex);
        }
        return "";
    }

    public String getSelectedMonth() {
        if (dateMode != NONE) {
            if (months.size() <= selectedMonthIndex) {
                selectedMonthIndex = months.size() - 1;
            }
            return months.get(selectedMonthIndex);
        }
        return "";
    }

    public String getSelectedDay() {
        if (dateMode == YEAR_MONTH_DAY || dateMode == MONTH_DAY) {
            if (days.size() <= selectedDayIndex) {
                selectedDayIndex = days.size() - 1;
                }
            return days.get(selectedDayIndex);
        }
        return "";
    }

    public String getSelectedHour() {
        if (timeMode != NONE) {
            return selectedHour;
        }
        return "";
    }

    public String getSelectedMinute() {
        if (timeMode != NONE) {
            return selectedMinute;
        }
        return "";
    }

    @NonNull
    @Override
    protected View makeCenterView() {
        // 如果未设置默认项，则需要在此初始化数据
        if ((dateMode == YEAR_MONTH_DAY || dateMode == YEAR_MONTH) && years.size() == 0) {
            LogUtils.verbose(this, "init years before make view");
            initYearData();
        }
        if (dateMode != NONE && months.size() == 0) {
            LogUtils.verbose(this, "init months before make view");
            int selectedYear = DateUtils.trimZero(getSelectedYear());
            changeMonthData(selectedYear);
        }
        if ((dateMode == YEAR_MONTH_DAY || dateMode == MONTH_DAY) && days.size() == 0) {
            LogUtils.verbose(this, "init days before make view");
            int selectedYear;
            if (dateMode == YEAR_MONTH_DAY) {
                selectedYear = DateUtils.trimZero(getSelectedYear());
            } else {
                selectedYear = Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR);
            }
            int selectedMonth = DateUtils.trimZero(getSelectedMonth());
            changeDayData(selectedYear, selectedMonth);
        }
        if (timeMode != NONE && hours.size() == 0) {
            LogUtils.verbose(this, "init hours before make view");
            initHourData();
        }
        if (timeMode != NONE && minutes.size() == 0) {
            LogUtils.verbose(this, "init minutes before make view");
            changeMinuteData(DateUtils.trimZero(selectedHour));
        }

        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        layout.setWeightSum(5);
        LinearLayout.LayoutParams wheelViewParams;
        LinearLayout.LayoutParams labelViewParams;
        if(weightEnable){
            wheelViewParams = new LinearLayout.LayoutParams(0,WRAP_CONTENT);
            wheelViewParams.weight = 1.0f;
//            labelViewParams= new LinearLayout.LayoutParams(0,WRAP_CONTENT);
//            labelViewParams.weight = 0.5f;
        }else{
            wheelViewParams = new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        }
        labelViewParams = new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
//        wheelViewParams.gravity = Gravity.CENTER;
        if(wheelModeEnable){
            final WheelView yearView = new WheelView(activity);
            final WheelView monthView = new WheelView(activity);
            final WheelView dayView = new WheelView(activity);
            final WheelView hourView = new WheelView(activity);
            final WheelView minuteView = new WheelView(activity);
            if (dateMode == YEAR_MONTH_DAY || dateMode == YEAR_MONTH) {
                yearView.setCanLoop(canLoop);
                yearView.setTextSize(textSize);//must be called before setDateList
                yearView.setSelectedTextColor(textColorFocus);
                yearView.setUnSelectedTextColor(textColorNormal);
                yearView.setAdapter(new ArrayWheelAdapter<>(years));
                yearView.setCurrentItem(selectedYearIndex);
                yearView.setDividerType(LineConfig.DividerType.FILL);
                yearView.setLayoutParams(wheelViewParams);
                yearView.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        selectedYearIndex = index;
                        if (onWheelListener != null) {
                            onWheelListener.onYearWheeled(selectedYearIndex, item);
                        }
//                        if (!isUserScroll) {
//                            return;
//                        }
                        LogUtils.verbose(this, "change months after year wheeled");
                        selectedMonthIndex = 0;//重置月份索引
                        selectedDayIndex = 0;//重置日子索引
                        //需要根据年份及月份动态计算天数
                        int selectedYear = DateUtils.trimZero(item);
                        changeMonthData(selectedYear);
                        monthView.setAdapter(new ArrayWheelAdapter<>(months));
                        monthView.setCurrentItem(selectedMonthIndex);
                        changeDayData(selectedYear, DateUtils.trimZero(months.get(selectedMonthIndex)));
                        dayView.setAdapter(new ArrayWheelAdapter<>(days));
                        dayView.setCurrentItem(selectedDayIndex);
                    }
                });
                layout.addView(yearView);
                if (!TextUtils.isEmpty(yearLabel)){
                    TextView labelView = new TextView(activity);
                    labelView.setLayoutParams(labelViewParams);
                    labelView.setTextColor(textColorFocus);
                    labelView.setTextSize(textSize);
                    labelView.setText(yearLabel);
                    layout.addView(labelView);
                }
            }
            if (dateMode != NONE) {
                monthView.setCanLoop(canLoop);
                monthView.setTextSize(textSize);//must be called before setDateList
                monthView.setSelectedTextColor(textColorFocus);
                monthView.setUnSelectedTextColor(textColorNormal);
                monthView.setAdapter(new ArrayWheelAdapter<>(months));
                monthView.setCurrentItem(selectedMonthIndex);
                monthView.setDividerType(LineConfig.DividerType.FILL);
                monthView.setLayoutParams(wheelViewParams);
                monthView.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        selectedMonthIndex = index;
                        if (onWheelListener != null) {
                            onWheelListener.onMonthWheeled(selectedMonthIndex, item);
                        }
//                        if (!isUserScroll) {
//                            return;
//                        }
                        if (dateMode == YEAR_MONTH_DAY || dateMode == MONTH_DAY) {
                            LogUtils.verbose(this, "change days after month wheeled");
                            selectedDayIndex = 0;//重置日子索引
                            int selectedYear;
                            if (dateMode == YEAR_MONTH_DAY) {
                                selectedYear = DateUtils.trimZero(getSelectedYear());
                            } else {
                                selectedYear = Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR);
                            }
                            changeDayData(selectedYear, DateUtils.trimZero(item));
                            dayView.setAdapter(new ArrayWheelAdapter<>(days));
                            dayView.setCurrentItem(selectedDayIndex);
                        }
                    }
                });
                layout.addView(monthView);
                if (!TextUtils.isEmpty(monthLabel)){
                    TextView labelView = new TextView(activity);
                    labelView.setLayoutParams(labelViewParams);
                    labelView.setTextColor(textColorFocus);
                    labelView.setTextSize(textSize);
                    labelView.setText(monthLabel);
                    layout.addView(labelView);
                }
            }
            if (dateMode == YEAR_MONTH_DAY || dateMode == MONTH_DAY) {
                dayView.setCanLoop(canLoop);
                dayView.setTextSize(textSize);//must be called before setDateList
                dayView.setSelectedTextColor(textColorFocus);
                dayView.setUnSelectedTextColor(textColorNormal);
                dayView.setAdapter(new ArrayWheelAdapter<>(days));
                dayView.setCurrentItem(selectedDayIndex);
                dayView.setDividerType(LineConfig.DividerType.FILL);
                dayView.setLayoutParams(wheelViewParams);
                dayView.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        selectedDayIndex = index;
                        if (onWheelListener != null) {
                            onWheelListener.onDayWheeled(index, item);
                        }
                    }
                });
                layout.addView(dayView);
                if (!TextUtils.isEmpty(dayLabel)){
                    TextView labelView = new TextView(activity);
                    labelView.setLayoutParams(labelViewParams);
                    labelView.setTextColor(textColorFocus);
                    labelView.setTextSize(textSize);
                    labelView.setText(dayLabel);
                    layout.addView(labelView);
                }
            }
            if (timeMode != NONE) {
//                layout.setWeightSum(5);
                //小时
                hourView.setCanLoop(canLoop);
                hourView.setTextSize(textSize);//must be called before setDateList
                hourView.setSelectedTextColor(textColorFocus);
                hourView.setUnSelectedTextColor(textColorNormal);
                hourView.setDividerType(LineConfig.DividerType.FILL);
                hourView.setAdapter(new ArrayWheelAdapter<>(hours));
                hourView.setCurrentItem(selectedHourIndex);
                hourView.setLayoutParams(wheelViewParams);
                hourView.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        selectedHourIndex = index;
                        selectedMinuteIndex = 0;
                        if (onWheelListener != null) {
                            onWheelListener.onDayWheeled(index, item);
                        }
                        changeMinuteData(DateUtils.trimZero(item));
                        minuteView.setAdapter(new ArrayWheelAdapter<>(minutes));
                        minuteView.setCurrentItem(selectedMinuteIndex);
                    }
                });
                layout.addView(hourView);
                if (!TextUtils.isEmpty(hourLabel)){
                    TextView labelView = new TextView(activity);
                    labelView.setLayoutParams(labelViewParams);
                    labelView.setTextColor(textColorFocus);
                    labelView.setTextSize(textSize);
                    labelView.setText(hourLabel);
                    layout.addView(labelView);
                }
                //分钟
                minuteView.setCanLoop(canLoop);
                minuteView.setTextSize(textSize);//must be called before setDateList
                minuteView.setSelectedTextColor(textColorFocus);
                minuteView.setUnSelectedTextColor(textColorNormal);
                minuteView.setAdapter(new ArrayWheelAdapter<>(minutes));
                minuteView.setCurrentItem(selectedMinuteIndex);
                minuteView.setDividerType(LineConfig.DividerType.FILL);
                minuteView.setLayoutParams(wheelViewParams);
                layout.addView(minuteView);
                minuteView.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        selectedMinuteIndex = index;
                        if (onWheelListener != null) {
                            onWheelListener.onMinuteWheeled(index, item);
                        }
                    }
                });
                if (!TextUtils.isEmpty(minuteLabel)){
                    TextView labelView = new TextView(activity);
                    labelView.setLayoutParams(labelViewParams);
                    labelView.setTextColor(textColorFocus);
                    labelView.setTextSize(textSize);
                    labelView.setText(minuteLabel);
                    layout.addView(labelView);
                }
            }
        }else{
            final WheelListView yearView = new WheelListView(activity);
            final WheelListView monthView = new WheelListView(activity);
            final WheelListView dayView = new WheelListView(activity);
            final WheelListView hourView = new WheelListView(activity);
            final WheelListView minuteView = new WheelListView(activity);

            if (dateMode == YEAR_MONTH_DAY || dateMode == YEAR_MONTH) {
                yearView.setLayoutParams(wheelViewParams);
                yearView.setTextSize(textSize);
                yearView.setSelectedTextColor(textColorFocus);
                yearView.setUnSelectedTextColor(textColorNormal);
                yearView.setLineConfig(lineConfig);
                yearView.setOffset(offset);
                yearView.setCanLoop(canLoop);
                yearView.setItems(years, selectedYearIndex);
                yearView.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
                    @Override
                    public void onItemSelected(boolean isUserScroll, int index, String item) {
                        selectedYearIndex = index;
                        if (onWheelListener != null) {
                            onWheelListener.onYearWheeled(selectedYearIndex, item);
                        }
                        if (!isUserScroll) {
                            return;
                        }
                        LogUtils.verbose(this, "change months after year wheeled");
                        selectedMonthIndex = 0;//重置月份索引
                        selectedDayIndex = 0;//重置日子索引
                        //需要根据年份及月份动态计算天数
                        int selectedYear = DateUtils.trimZero(item);
                        changeMonthData(selectedYear);
                        monthView.setItems(months, selectedMonthIndex);
                        changeDayData(selectedYear, DateUtils.trimZero(months.get(selectedMonthIndex)));
                        dayView.setItems(days, selectedDayIndex);
                    }
                });
                layout.addView(yearView);
                if (!TextUtils.isEmpty(yearLabel)) {
                    TextView labelView = new TextView(activity);
                    labelView.setLayoutParams(labelViewParams);
                    labelView.setTextSize(textSize);
                    labelView.setTextColor(textColorFocus);
                    labelView.setText(yearLabel);
                    layout.addView(labelView);
                }
            }

            if (dateMode != NONE) {
                monthView.setLayoutParams(wheelViewParams);
                monthView.setTextSize(textSize);
                monthView.setSelectedTextColor(textColorFocus);
                monthView.setUnSelectedTextColor(textColorNormal);
                monthView.setLineConfig(lineConfig);
                monthView.setOffset(offset);
                monthView.setCanLoop(canLoop);
                monthView.setItems(months, selectedMonthIndex);
                monthView.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
                    @Override
                    public void onItemSelected(boolean isUserScroll, int index, String item) {
                        selectedMonthIndex = index;
                        if (onWheelListener != null) {
                            onWheelListener.onMonthWheeled(selectedMonthIndex, item);
                        }
                        if (!isUserScroll) {
                            return;
                        }
                        if (dateMode == YEAR_MONTH_DAY || dateMode == MONTH_DAY) {
                            LogUtils.verbose(this, "change days after month wheeled");
                            selectedDayIndex = 0;//重置日子索引
                            int selectedYear;
                            if (dateMode == YEAR_MONTH_DAY) {
                                selectedYear = DateUtils.trimZero(getSelectedYear());
                            } else {
                                selectedYear = Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR);
                            }
                            changeDayData(selectedYear, DateUtils.trimZero(item));
                            dayView.setItems(days, selectedDayIndex);
                        }
                    }
                });
                layout.addView(monthView);
                if (!TextUtils.isEmpty(monthLabel)) {
                    TextView labelView = new TextView(activity);
                    labelView.setLayoutParams(labelViewParams);
                    labelView.setTextSize(textSize);
                    labelView.setTextColor(textColorFocus);
                    labelView.setText(monthLabel);
                    layout.addView(labelView);
                }
            }

            if (dateMode == YEAR_MONTH_DAY || dateMode == MONTH_DAY) {
                dayView.setLayoutParams(wheelViewParams);
                dayView.setTextSize(textSize);
                dayView.setSelectedTextColor(textColorFocus);
                dayView.setUnSelectedTextColor(textColorNormal);
                dayView.setLineConfig(lineConfig);
                dayView.setOffset(offset);
                dayView.setCanLoop(canLoop);
                dayView.setItems(days, selectedDayIndex);
                dayView.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
                    @Override
                    public void onItemSelected(boolean isUserScroll, int index, String item) {
                        selectedDayIndex = index;
                        if (onWheelListener != null) {
                            onWheelListener.onDayWheeled(selectedDayIndex, item);
                        }
                    }
                });
                layout.addView(dayView);
                if (!TextUtils.isEmpty(dayLabel)) {
                    TextView labelView = new TextView(activity);
                    labelView.setLayoutParams(labelViewParams);
                    labelView.setTextSize(textSize);
                    labelView.setTextColor(textColorFocus);
                    labelView.setText(dayLabel);
                    layout.addView(labelView);
                }
            }

            if (timeMode != NONE) {
                hourView.setLayoutParams(wheelViewParams);
                hourView.setTextSize(textSize);
                hourView.setSelectedTextColor(textColorFocus);
                hourView.setUnSelectedTextColor(textColorNormal);
                hourView.setLineConfig(lineConfig);
                hourView.setCanLoop(canLoop);
                hourView.setItems(hours, selectedHour);
                hourView.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
                    @Override
                    public void onItemSelected(boolean isUserScroll, int index, String item) {
                        selectedHourIndex = index;
                        selectedMinuteIndex = 0;
                        if (onWheelListener != null) {
                            onWheelListener.onHourWheeled(index, item);
                        }
                        if (!isUserScroll) {
                            return;
                        }
                        LogUtils.verbose(this, "change minutes after hour wheeled");
                        changeMinuteData(DateUtils.trimZero(item));
                        minuteView.setItems(minutes, selectedMinuteIndex);
                    }
                });
                layout.addView(hourView);
                if (!TextUtils.isEmpty(hourLabel)) {
                    TextView labelView = new TextView(activity);
                    labelView.setLayoutParams(labelViewParams);
                    labelView.setTextSize(textSize);
                    labelView.setTextColor(textColorFocus);
                    labelView.setText(hourLabel);
                    layout.addView(labelView);
                }

                minuteView.setLayoutParams(wheelViewParams);
                minuteView.setTextSize(textSize);
                minuteView.setSelectedTextColor(textColorFocus);
                minuteView.setUnSelectedTextColor(textColorNormal);
                minuteView.setLineConfig(lineConfig);
                minuteView.setOffset(offset);
                minuteView.setCanLoop(canLoop);
                minuteView.setItems(minutes, selectedMinute);
                minuteView.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
                    @Override
                    public void onItemSelected(boolean isUserScroll, int index, String item) {
                        selectedMinuteIndex = index;
                        if (onWheelListener != null) {
                            onWheelListener.onMinuteWheeled(index, item);
                        }
                    }
                });
                layout.addView(minuteView);
                if (!TextUtils.isEmpty(minuteLabel)) {
                    TextView labelView = new TextView(activity);
                    labelView.setLayoutParams(labelViewParams);
                    labelView.setTextSize(textSize);
                    labelView.setTextColor(textColorFocus);
                    labelView.setText(minuteLabel);
                    layout.addView(labelView);
                }
            }
        }


        return layout;
    }

    @Override
    protected void onSubmit() {
        if (onDateTimePickListener == null) {
            return;
        }
        String year = getSelectedYear();
        String month = getSelectedMonth();
        String day = getSelectedDay();
        String hour = getSelectedHour();
        String minute = getSelectedMinute();
        switch (dateMode) {
            case YEAR_MONTH_DAY:
                ((OnYearMonthDayTimePickListener) onDateTimePickListener).onDateTimePicked(year, month, day, hour, minute);
                break;
            case YEAR_MONTH:
                ((OnYearMonthTimePickListener) onDateTimePickListener).onDateTimePicked(year, month, hour, minute);
                break;
            case MONTH_DAY:
                ((OnMonthDayTimePickListener) onDateTimePickListener).onDateTimePicked(month, day, hour, minute);
                break;
            case NONE:
                ((OnTimePickListener) onDateTimePickListener).onDateTimePicked(hour, minute);
                break;
        }
    }

    private int findItemIndex(ArrayList<String> items, int item) {
        //折半查找有序元素的索引
        int index = Collections.binarySearch(items, item, new Comparator<Object>() {
            @Override
            public int compare(Object lhs, Object rhs) {
                String lhsStr = lhs.toString();
                String rhsStr = rhs.toString();
                lhsStr = lhsStr.startsWith("0") ? lhsStr.substring(1) : lhsStr;
                rhsStr = rhsStr.startsWith("0") ? rhsStr.substring(1) : rhsStr;
                return Integer.parseInt(lhsStr) - Integer.parseInt(rhsStr);
            }
        });
        if (index < 0) {
            throw new IllegalArgumentException("Item[" + item + "] out of range");
        }
        return index;
    }

    private void initYearData() {
        years.clear();
        if (startYear == endYear) {
            years.add(String.valueOf(startYear));
        } else if (startYear < endYear) {
            //年份正序
            for (int i = startYear; i <= endYear; i++) {
                years.add(String.valueOf(i));
            }
        } else {
            //年份逆序
            for (int i = startYear; i >= endYear; i--) {
                years.add(String.valueOf(i));
            }
        }
    }

    private void changeMonthData(int selectedYear) {
        months.clear();
        if (startMonth < 1 || endMonth < 1 || startMonth > 12 || endMonth > 12) {
            throw new IllegalArgumentException("Month out of range [1-12]");
        }
        if (startYear == endYear) {
            if (startMonth > endMonth) {
                for (int i = endMonth; i >= startMonth; i--) {
                    months.add(DateUtils.fillZero(i));
                }
            } else {
                for (int i = startMonth; i <= endMonth; i++) {
                    months.add(DateUtils.fillZero(i));
                }
            }
        } else if (selectedYear == startYear) {
            for (int i = startMonth; i <= 12; i++) {
                months.add(DateUtils.fillZero(i));
            }
        } else if (selectedYear == endYear) {
            for (int i = 1; i <= endMonth; i++) {
                months.add(DateUtils.fillZero(i));
            }
        } else {
            for (int i = 1; i <= 12; i++) {
                months.add(DateUtils.fillZero(i));
            }
        }
    }

    private void changeDayData(int selectedYear, int selectedMonth) {
        int maxDays = DateUtils.calculateDaysInMonth(selectedYear, selectedMonth);
        days.clear();
        if (selectedYear == startYear && selectedMonth == startMonth
                && selectedYear == endYear && selectedMonth == endMonth) {
            //开始年月及结束年月相同情况
            for (int i = startDay; i <= endDay; i++) {
                days.add(DateUtils.fillZero(i));
            }
        } else if (selectedYear == startYear && selectedMonth == startMonth) {
            //开始年月相同情况
            for (int i = startDay; i <= maxDays; i++) {
                days.add(DateUtils.fillZero(i));
            }
        } else if (selectedYear == endYear && selectedMonth == endMonth) {
            //结束年月相同情况
            for (int i = 1; i <= endDay; i++) {
                days.add(DateUtils.fillZero(i));
            }
        } else {
            for (int i = 1; i <= maxDays; i++) {
                days.add(DateUtils.fillZero(i));
            }
        }
    }

    private void initHourData() {
        for (int i = startHour; i <= endHour; i++) {
            String hour = DateUtils.fillZero(i);
            hours.add(hour);
        }
        if (hours.indexOf(selectedHour) == -1) {
            //当前设置的小时不在指定范围，则默认选中范围开始的小时
            selectedHour = hours.get(0);
        }
    }

    private void changeMinuteData(int selectedHour) {
        if (startHour == endHour) {
            if (startMinute > endMinute) {
                int temp = startMinute;
                startMinute = endMinute;
                endMinute = temp;
            }
            for (int i = startMinute; i <= endMinute; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
        } else if (selectedHour == startHour) {
            for (int i = startMinute; i <= 59; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
        } else if (selectedHour == endHour) {
            for (int i = 0; i <= endMinute; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
        } else {
            for (int i = 0; i <= 59; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
        }
        if (minutes.indexOf(selectedMinute) == -1) {
            //当前设置的分钟不在指定范围，则默认选中范围开始的分钟
            selectedMinute = minutes.get(0);
        }
    }

    public interface OnWheelListener {

        void onYearWheeled(int index, String year);

        void onMonthWheeled(int index, String month);

        void onDayWheeled(int index, String day);

        void onHourWheeled(int index, String hour);

        void onMinuteWheeled(int index, String minute);

    }

    protected interface OnDateTimePickListener {

    }

    public interface OnYearMonthDayTimePickListener extends OnDateTimePickListener {

        void onDateTimePicked(String year, String month, String day, String hour, String minute);

    }

    public interface OnYearMonthTimePickListener extends OnDateTimePickListener {

        void onDateTimePicked(String year, String month, String hour, String minute);

    }


    public interface OnMonthDayTimePickListener extends OnDateTimePickListener {

        void onDateTimePicked(String month, String day, String hour, String minute);
    }


    public interface OnTimePickListener extends OnDateTimePickListener {

        void onDateTimePicked(String hour, String minute);
    }

}
