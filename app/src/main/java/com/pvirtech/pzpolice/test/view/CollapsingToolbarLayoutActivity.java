package com.pvirtech.pzpolice.test.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.ui.base.BaseActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CollapsingToolbarLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);
        initTitleView("aaaaaaaaa");
//        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     /*   if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    /**
     * 共享元素的动画
     * <ImageView
     * …
     * android:transitionName="@string/transition_album_cover" />
     * <p>
     * <p>
     * Intent intent = new Intent();
     * String transitionName = getString(R.string.transition_album_cover);
     * …
     * ActivityOptionsCompat options =
     * ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
     * albumCoverImageView,   // The view which starts the transition
     * transitionName    // The transitionName of the view we’re transitioning to
     * );
     * ActivityCompat.startActivity(activity, intent, options.toBundle());
     */

    private void show(final EditText editText) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.date_time_picker_layout, (ViewGroup) findViewById(R.id.root_view));
        /**
         * 设置日期
         */
        Date date;
        if (TextUtils.isEmpty(editText.getText())) {
            date = new Date(System.currentTimeMillis());
            editText.setText(getDateToYMDHM(date));
        } else {
            String editTextString = editText.getText().toString();
            date = getDateFromYMDHM(editTextString);
        }
        Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        calendar.getTime();
        DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        // 初始化DatePicker组件，初始化时指定监听器
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String datePickerString = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                String[] date = editText.getText().toString().split(" ");
                date[0] = datePickerString;
                editText.setText(date[0] + " " + date[1]);
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
                String timePickerString = hourOfDay + ":" + minute;
                String[] date = editText.getText().toString().split(" ");
                date[1] = timePickerString;
                editText.setText(date[0] + " " + date[1]);
                System.out.println("=======" + timePickerString);
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("这里是Title");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
}
