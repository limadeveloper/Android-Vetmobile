package android.vetmobile.com.vet;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class VetAvailabilityActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView monthTextView;
    private EditText mondayFirstHourEditText;
    private EditText mondayLastHourEditText;
    private EditText tuesdayFirstHourEditText;
    private EditText tuesdayLastHourEditText;
    private EditText wednesdayFirstHourEditText;
    private EditText wednesdayLastHourEditText;
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
    private TimePickerDialog timePickerDialog;
    private TAGWeekHour TAG = TAGWeekHour.EMPTY;

    private enum TAGWeekHour {
        EMPTY,
        MONDAY_FIRST_HOUR,
        MONDAY_LAST_HOUR,
        TUESDAY_FIRST_HOUR,
        TUESDAY_LAST_HOUR,
        WEDNESDAY_FIRST_HOUR,
        WEDNESDAY_LAST_HOUR,
        THURSDAY_FIRST_HOUR,
        THURSDAY_LAST_HOUR,
        FRIDAY_FIRST_HOUR,
        FRIDAY_LAST_HOUR,
        SATURDAY_FIRST_HOUR,
        SATURDAY_LAST_HOUR,
        SUNDAY_FIRST_HOUR,
        SUNDAY_LAST_HOUR,
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_availability);

        monthTextView = findViewById(R.id.va_month_text_id);
        mondayFirstHourEditText = findViewById(R.id.va_monday_first_hour_edit_text_id);
        mondayLastHourEditText = findViewById(R.id.va_monday_last_hour_edit_text_id);
        tuesdayFirstHourEditText = findViewById(R.id.va_tuesday_first_hour_edit_text_id);
        tuesdayLastHourEditText = findViewById(R.id.va_tuesday_last_hour_edit_text_id);
        wednesdayFirstHourEditText = findViewById(R.id.va_wednesday_first_hour_edit_text_id);
        wednesdayLastHourEditText = findViewById(R.id.va_wednesday_last_hour_edit_text_id);
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

        addActionToMondayFirstHour();
        addActionToMondayLastHour();
        addActionToTuesdayFirstHour();
        addActionToTuesdayLastHour();
        addActionToWednesdayFirstHour();
        addActionToWednesdayLastHour();
        addActionToThursdayFirstHour();
        addActionToThursdayLastHour();
        addActionToFridayFirstHour();
        addActionToFridayLastHour();
        addActionToSaturdayFirstHour();
        addActionToSaturdayLastHour();
        addActionToSundayFirstHour();
        addActionToSundayLastHour();

        addActionToTodayButton1();
        addActionToTodayButton2();
        addActionToSaveButton();
    }

    private void addActionToMondayFirstHour() {
        mondayFirstHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.MONDAY_FIRST_HOUR;
                createTimePicker("Segunda - Hora Inicial");
            }
        });
    }

    private void addActionToMondayLastHour() {
        mondayLastHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.MONDAY_LAST_HOUR;
                createTimePicker("Segunda - Hora Final");
            }
        });
    }

    private void addActionToTuesdayFirstHour() {
        tuesdayFirstHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.TUESDAY_FIRST_HOUR;
                createTimePicker("Terça - Hora Inicial");
            }
        });
    }

    private void addActionToTuesdayLastHour() {
        tuesdayLastHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.TUESDAY_LAST_HOUR;
                createTimePicker("Terça - Hora Final");
            }
        });
    }

    private void addActionToWednesdayFirstHour() {
        wednesdayFirstHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.WEDNESDAY_FIRST_HOUR;
                createTimePicker("Quarta - Hora Inicial");
            }
        });
    }

    private void addActionToWednesdayLastHour() {
        wednesdayLastHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.WEDNESDAY_LAST_HOUR;
                createTimePicker("Quarta - Hora Final");
            }
        });
    }

    private void addActionToThursdayFirstHour() {
        thursdayFirstHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.THURSDAY_FIRST_HOUR;
                createTimePicker("Quinta - Hora Inicial");
            }
        });
    }

    private void addActionToThursdayLastHour() {
        thursdayLastHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.THURSDAY_LAST_HOUR;
                createTimePicker("Quinta - Hora Final");
            }
        });
    }

    private void addActionToFridayFirstHour() {
        fridayFirstHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.FRIDAY_FIRST_HOUR;
                createTimePicker("Sexta - Hora Inicial");
            }
        });
    }

    private void addActionToFridayLastHour() {
        fridayLastHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.FRIDAY_LAST_HOUR;
                createTimePicker("Sexta - Hora Final");
            }
        });
    }

    private void addActionToSaturdayFirstHour() {
        saturdayFirstHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.SATURDAY_FIRST_HOUR;
                createTimePicker("Sábado - Hora Inicial");
            }
        });
    }

    private void addActionToSaturdayLastHour() {
        saturdayLastHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.SATURDAY_LAST_HOUR;
                createTimePicker("Sábado - Hora Final");
            }
        });
    }

    private void addActionToSundayFirstHour() {
        sundayFirstHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.SUNDAY_FIRST_HOUR;
                createTimePicker("Domingo - Hora Inicial");
            }
        });
    }

    private void addActionToSundayLastHour() {
        sundayLastHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = TAGWeekHour.SUNDAY_LAST_HOUR;
                createTimePicker("Domingo - Hora Final");
            }
        });
    }

    private void addActionToTodayButton1() {
        todayButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void addActionToTodayButton2() {
        todayButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void addActionToSaveButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    private void updateUI() {
        SimpleDateFormat format = new SimpleDateFormat("MMM");
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(new Date());
        String textDate = "Mês: "+ format.format(calendar.getTime()) +", "+ calendar.get(Calendar.YEAR);
        monthTextView.setText(textDate);
    }

    public void createTimePicker(String title) {

        Calendar now = Calendar.getInstance();

        if (timePickerDialog == null) {
            timePickerDialog = TimePickerDialog.newInstance(
                    VetAvailabilityActivity.this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true
            );
        }else {
            timePickerDialog.initialize(
                    VetAvailabilityActivity.this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    now.get(Calendar.SECOND),
                    true
            );
        }

        timePickerDialog.setThemeDark(true);
        timePickerDialog.vibrate(true);
        timePickerDialog.dismissOnPause(true);
        timePickerDialog.enableSeconds(false);
        timePickerDialog.setVersion(TimePickerDialog.Version.VERSION_1);
        timePickerDialog.setTitle(title);
        timePickerDialog.show(getFragmentManager(), "Timepickerdialog");
    }

    @Override
    protected void onResume() {
        super.onResume();
        TimePickerDialog dialog = (TimePickerDialog) getFragmentManager().findFragmentByTag("Timepickerdialog");
        if (dialog != null) dialog.setOnTimeSetListener(this);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String time = ""+ hourString +"h"+ minuteString +"m";
        String selectedHour = "Hora selecionada: "+ time;

        Toast.makeText(getApplicationContext(), selectedHour, Toast.LENGTH_SHORT).show();

        switch (TAG) {
            case MONDAY_FIRST_HOUR: mondayFirstHourEditText.setText(time); break;
            case MONDAY_LAST_HOUR: mondayLastHourEditText.setText(time); break;
            case TUESDAY_FIRST_HOUR: tuesdayFirstHourEditText.setText(time); break;
            case TUESDAY_LAST_HOUR: tuesdayLastHourEditText.setText(time); break;
            case WEDNESDAY_FIRST_HOUR: wednesdayFirstHourEditText.setText(time); break;
            case WEDNESDAY_LAST_HOUR: wednesdayLastHourEditText.setText(time); break;
            case THURSDAY_FIRST_HOUR: thursdayFirstHourEditText.setText(time); break;
            case THURSDAY_LAST_HOUR: thursdayLastHourEditText.setText(time); break;
            case FRIDAY_FIRST_HOUR: fridayFirstHourEditText.setText(time); break;
            case FRIDAY_LAST_HOUR: fridayLastHourEditText.setText(time); break;
            case SATURDAY_FIRST_HOUR: saturdayFirstHourEditText.setText(time); break;
            case SATURDAY_LAST_HOUR: saturdayLastHourEditText.setText(time); break;
            case SUNDAY_FIRST_HOUR: sundayFirstHourEditText.setText(time); break;
            case SUNDAY_LAST_HOUR: sundayLastHourEditText.setText(time); break;
            default: break;
        }
    }

}
