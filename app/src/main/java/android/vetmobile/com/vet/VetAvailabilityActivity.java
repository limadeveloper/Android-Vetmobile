package android.vetmobile.com.vet;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class VetAvailabilityActivity extends AppCompatActivity {

    private TextView monthTextView;
    private EditText mondayFirstHourEditText;
    private EditText mondayLastHourEditText;
    private EditText tuesdayFirstHourEditText;
    private EditText tuesdayLastHourEditText;
    private EditText wednessFirstHourEditText;
    private EditText wednessLastHourEditText;
    private EditText thursdayFirstHourEditText;
    private EditText thursdayLastHourEditText;
    private EditText fridayFirstHourEditText;
    private EditText fridayLastHourEditText;
    private EditText saturdayFirstHourEditText;
    private EditText saturdayLastHourEditText;
    private EditText sundayFirstHourEditText;
    private EditText sundayLastHourEditText;
    private Button todayButton1;
    private Button todayButton2;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_availability);

        monthTextView = findViewById(R.id.va_month_text_id);
        mondayFirstHourEditText = findViewById(R.id.va_monday_first_hour_edit_text_id);
        mondayLastHourEditText = findViewById(R.id.va_monday_last_hour_edit_text_id);
        tuesdayFirstHourEditText = findViewById(R.id.va_tuesday_first_hour_edit_text_id);
        tuesdayLastHourEditText = findViewById(R.id.va_tuesday_last_hour_edit_text_id);
        wednessFirstHourEditText = findViewById(R.id.va_wednesday_first_hour_edit_text_id);
        wednessLastHourEditText = findViewById(R.id.va_wednesday_last_hour_edit_text_id);
        thursdayFirstHourEditText = findViewById(R.id.va_thursday_first_hour_edit_text_id);
        thursdayLastHourEditText = findViewById(R.id.va_thursday_last_hour_edit_text_id);
        fridayFirstHourEditText = findViewById(R.id.va_friday_first_hour_edit_text_id);
        fridayLastHourEditText = findViewById(R.id.va_friday_last_hour_edit_text_id);
        saturdayFirstHourEditText = findViewById(R.id.va_saturday_first_hour_edit_text_id);
        saturdayLastHourEditText = findViewById(R.id.va_saturday_last_hour_edit_text_id);
        sundayFirstHourEditText = findViewById(R.id.va_sunday_first_hour_edit_text_id);
        sundayLastHourEditText = findViewById(R.id.va_sunday_last_hour_edit_text_id);
        todayButton1 = findViewById(R.id.va_today_button_1_id);
        todayButton2 = findViewById(R.id.va_today_button_2_id);
        saveButton = findViewById(R.id.va_save_button_id);

        setOrientation();
        updateUI();
        addActionToTodayButton1();
        addActionToTodayButton2();
        addActionToSaveButton();
    }

    private void addActionToTodayButton1() {

    }

    private void addActionToTodayButton2() {

    }

    private void addActionToSaveButton() {

    }

    private void setOrientation() {
        if (Support.isTablet(getWindowManager())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void updateUI() {
        SimpleDateFormat format = new SimpleDateFormat("MMM");
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(new Date());
        String textDate = "Mês: "+ format.format(calendar.getTime()) +", "+ calendar.get(Calendar.YEAR);
        monthTextView.setText(textDate);
    }

}
