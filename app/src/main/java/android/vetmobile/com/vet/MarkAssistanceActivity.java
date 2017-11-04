package android.vetmobile.com.vet;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MarkAssistanceActivity extends AppCompatActivity {

    private TextView calendarTitleTextView;
    private CompactCalendarView calendarView;
    private Button selectedDateButton;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_assistance);

        calendarTitleTextView = findViewById(R.id.markassistance_calendartitle_text_id);
        calendarView = findViewById(R.id.markassistance_compactcalendar_view_id);
        selectedDateButton = findViewById(R.id.markassistance_selecteddate_button_id);
        listView = findViewById(R.id.markassistance_listview_id);

        updateUI(new Date());
        setOrientation();
        configureCalendarView();
        configureListView();
    }

    private void updateUI(Date date) {

        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(date);
        String dateText = ""+ monthFormat.format(calendar.getTime()) +" - "+ calendar.get(Calendar.YEAR);

        calendarTitleTextView.setText(dateText);
    }

    private void configureCalendarView() {

        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        calendarView.setShouldDrawDaysHeader(true);

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                if (isYesterday(dateClicked)) {
                    showMessageErrorSelectInvalidDate();
                    selectedDateButton.setText("");
                    return;
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String stringDate = dateFormat.format(dateClicked);

                selectedDateButton.setText(stringDate);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                updateUI(firstDayOfNewMonth);
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
        return new Date().after(date);
    }

    private void showMessageErrorSelectInvalidDate() {
        Toast.makeText(getApplicationContext(), "Data inválida. Selecione uma data futura.", Toast.LENGTH_SHORT).show();
    }

}
