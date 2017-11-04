package android.vetmobile.com.vet;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.Calendar;
import java.util.Date;

public class MarkAssistanceActivity extends AppCompatActivity {

    private CompactCalendarView calendarView;
    private Button selectedDateButton;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_assistance);

        calendarView = findViewById(R.id.markassistance_compactcalendar_view_id);
        selectedDateButton = findViewById(R.id.markassistance_selecteddate_button_id);
        listView = findViewById(R.id.markassistance_listview_id);

        setOrientation();
        configureCalendarView();
        configureListView();
    }

    private void configureCalendarView() {

        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Log.d("", " selected day: "+ dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                if (isYesterday(firstDayOfNewMonth)) {
                    calendarView.setCurrentDate(new Date());
                }
            }
        });
    }

    private void configureListView() {

    }

    private void setOrientation() {
        if (Support.isTablet(getWindowManager())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private boolean isYesterday(Date date) {
        return new Date().before(date);
    }

}
