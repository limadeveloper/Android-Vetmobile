package android.vetmobile.com.vet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MarkAssistanceActivity extends AppCompatActivity {

    private TextView calendarTitleTextView;
    private CompactCalendarView calendarView;
    private Button selectedDateButton;
    private ListView listView;
    boolean listIsEmpty = true;
    String selectedDate = "";

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
                    selectedDate = "";
                    return;
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                calendar.setTime(dateClicked);
                String stringDate = ""+ calendar.get(Calendar.DAY_OF_MONTH) +" "+ monthFormat.format(calendar.getTime()) +" "+ calendar.get(Calendar.YEAR);

                selectedDateButton.setText(stringDate);
                selectedDate = dateFormat.format(dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                updateUI(firstDayOfNewMonth);
            }
        });
    }

    private void configureListView() {

        final List<User> users = User.getUsersBy(getResources().getString(R.string.text_const_type_user_vet));
        List<String> names = new ArrayList<>();

        if (users != null && !users.isEmpty()) {
            listIsEmpty = false;
            for (User user: users) {
                String item = ""+ user.getId() + ". "+ user.getName();
                names.add(item);
            }
        }else {
            listIsEmpty = true;
            names.add("Nenhum veterinário cadastrado");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectedDate.isEmpty()) {
                    showMessageErrorNoSelectedDate();
                    return;
                }
                if (!listIsEmpty) {
                    Intent intent = new Intent(MarkAssistanceActivity.this, MarkAssistanceVetDetailsActivity.class);
                    User user = users.get(position);
                    intent.putExtra(getResources().getString(R.string.key_user_id), user.getId());
                    intent.putExtra(getResources().getString(R.string.key_selected_date), selectedDate);
                    startActivity(intent);
                }
            }
        });
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
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_select_future_date), Toast.LENGTH_SHORT).show();
    }

    private void showMessageErrorNoSelectedDate() {
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_no_date_selected), Toast.LENGTH_LONG).show();
    }

}
