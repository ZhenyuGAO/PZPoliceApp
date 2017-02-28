package com.pvirtech.pzpolice.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.ui.base.BaseActivity;
import com.pvirtech.pzpolice.ui.contract.LeaveContract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class LeaveActivity extends BaseActivity implements LeaveContract.View {
    private Context mContext;
    private String TAG = "LeaveActivity";
    private TextView tv_week_house_time;
    private TextView tv_center;
    private String beginTime;
    private EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        initTitleView("请假");
        tv_week_house_time = (TextView) findViewById(R.id.tv_house_time);
        ed = (EditText) findViewById(R.id.ed);
        tv_center = (TextView) findViewById(R.id.tv_center);
        tv_week_house_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(ed);
            }
        });
    }

    private void show(final EditText editText) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.date_time_picker_layout, (ViewGroup) findViewById(R.id.root_view));
        /**
         * 设置日期
         */
        Date date;
        if (TextUtils.isEmpty(editText.getText())) {
            date = new Date(System.currentTimeMillis());
        } else {
            String editTextString = editText.getText().toString();
            date = getDateFromYMDHM(editTextString);
        }
        final String[] times = {getDateByFrom(date, "yyyy-MM-dd"), getDateByFrom(date, "HH:mm")};
        Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        calendar.getTime();
        DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        // 初始化DatePicker组件，初始化时指定监听器

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String datePickerString = year + "-" + getWidthZero((monthOfYear + 1)) + "-" + getWidthZero(dayOfMonth);
               /* String[] date = editText.getText().toString().split(" ");
                date[0] = datePickerString;
                editText.setText(date[0] + " " + date[1]);*/
                times[0] = datePickerString;
                System.out.println("=======" + datePickerString);
            }
        });
        /**
         * 设置时间
         */
        TimePicker timePicker = (TimePicker) view.findViewById(R.id.time_picker);
        //设置成24小时，隐藏AM/PM picker
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String timePickerString = getWidthZero(hourOfDay) + ":" + getWidthZero(minute);
               /* String[] date = editText.getText().toString().split(" ");
                date[1] = timePickerString;
                editText.setText(date[0] + " " + date[1]);*/
                times[1] = timePickerString;
                System.out.println("=======" + timePickerString);
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("这里是Title");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String data = times[0] + " " + times[1];
                        editText.setText(data);
                        dialog.dismiss();
                    }
                }
        );
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setView(view);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.show();
    }

    public static Date getDateFromYMDHM(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDateToYMDHM(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    public static String getDateByFrom(Date date, String strFormat) {
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        return format.format(date);
    }

    private String getWidthZero(int data) {
        String strData = "";
        if (data < 9) {
            strData = "0" + data;
        } else {
            strData = String.valueOf(data);
        }
        return strData;
    }

}
