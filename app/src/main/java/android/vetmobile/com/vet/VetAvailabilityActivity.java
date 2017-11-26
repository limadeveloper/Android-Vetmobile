package android.vetmobile.com.vet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    private boolean serviceAvailableDuringAllDay = true;
    private List<String> listOfDates = new ArrayList<>();
    private int delayToContinue = 2000;
    private String defaultFirstHour = "09h00m";
    private String defaultLastHour = "06h00m";

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
        setListOfDates();
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
                serviceAvailableDuringAllDay = true;
                showToastMessage(getResources().getString(R.string.text_done_to_save_data));
            }
        });
    }

    private void addActionToTodayButton2() {
        todayButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceAvailableDuringAllDay = false;
                showToastMessage(getResources().getString(R.string.text_done_to_save_data));
            }
        });
    }

    private void addActionToSaveButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hasAnyHour()) {
                    if (listOfDates.size() > 0) {

                        List<String> filterDates = new ArrayList<>();
                        List<Boolean> saveStatus = new ArrayList<>();
                        SimpleDateFormat dateFormat = new SimpleDateFormat(Support.getDateFormat1());
                        Date today = new Date();
                        String todayString = dateFormat.format(today);

                        // Filtra somente as datas válidas para adicionar no array
                        for (String stringDate: listOfDates) {
                            try {
                                Date date = dateFormat.parse(stringDate);
                                if (!date.before(new Date())) {
                                    filterDates.add(stringDate);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        listOfDates = filterDates;

                        // Adicionando a data atual na lista caso necessário
                        if (serviceAvailableDuringAllDay) {
                            if (!listOfDates.contains(todayString)) {
                                listOfDates.add(0, todayString);
                            }
                        }else {
                            VetAvailability.deleteData(todayString);
                        }

                        Log.d("", "filter dates: "+ listOfDates);

                        // Salva os dados no banco
                        for (String date: listOfDates) {

                            Support.DateWeekDays weekDay = Support.getWeekDayByStringDate(date);
                            int weekDayNumber = Support.getWeekDayNumberByEnum(weekDay);
                            boolean isToday = date.equals(todayString);

                            switch (weekDay) {
                                case MONDAY:
                                    String monFirstHour = mondayFirstHourEditText.getText().toString();
                                    String monLastHour = mondayLastHourEditText.getText().toString();
                                    if ((monFirstHour.isEmpty() || monLastHour.isEmpty()) && !isToday) {
                                        break;
                                    }else if (isToday && (monFirstHour.isEmpty() || monLastHour.isEmpty())) {
                                        monFirstHour = defaultFirstHour;
                                        monLastHour = defaultLastHour;
                                    }
                                    boolean monStatus = saveAvailability(date, weekDayNumber, monFirstHour, monLastHour);
                                    saveStatus.add(monStatus);
                                    break;
                                case TUESDAY:
                                    String tueFirstHour = tuesdayFirstHourEditText.getText().toString();
                                    String tueLastHour = tuesdayLastHourEditText.getText().toString();
                                    if ((tueFirstHour.isEmpty() || tueLastHour.isEmpty()) && !isToday) {
                                        break;
                                    }else if (isToday && (tueFirstHour.isEmpty() || tueLastHour.isEmpty())) {
                                        tueFirstHour = defaultFirstHour;
                                        tueLastHour = defaultLastHour;
                                    }
                                    boolean tueStatus = saveAvailability(date, weekDayNumber, tueFirstHour, tueLastHour);
                                    saveStatus.add(tueStatus);
                                    break;
                                case WEDNESDAY:
                                    String wedFirstHour = wednesdayFirstHourEditText.getText().toString();
                                    String wedLastHour = wednesdayLastHourEditText.getText().toString();
                                    if ((wedFirstHour.isEmpty() || wedLastHour.isEmpty()) && !isToday) {
                                        break;
                                    }else if (isToday && (wedFirstHour.isEmpty() || wedLastHour.isEmpty())) {
                                        wedFirstHour = defaultFirstHour;
                                        wedLastHour = defaultLastHour;
                                    }
                                    boolean wedStatus = saveAvailability(date, weekDayNumber, wedFirstHour, wedLastHour);
                                    saveStatus.add(wedStatus);
                                    break;
                                case THURSDAY:
                                    String thuFirstHour = thursdayFirstHourEditText.getText().toString();
                                    String thuLastHour = thursdayLastHourEditText.getText().toString();
                                    if ((thuFirstHour.isEmpty() || thuLastHour.isEmpty()) && !isToday) {
                                        break;
                                    }else if (isToday && (thuFirstHour.isEmpty() || thuLastHour.isEmpty())) {
                                        thuFirstHour = defaultFirstHour;
                                        thuLastHour = defaultLastHour;
                                    }
                                    boolean thuStatus = saveAvailability(date, weekDayNumber, thuFirstHour, thuLastHour);
                                    saveStatus.add(thuStatus);
                                    break;
                                case FRIDAY:
                                    String friFirstHour = fridayFirstHourEditText.getText().toString();
                                    String friLastHour = fridayLastHourEditText.getText().toString();
                                    if ((friFirstHour.isEmpty() || friLastHour.isEmpty()) && !isToday) {
                                        break;
                                    }else if (isToday && (friFirstHour.isEmpty() || friLastHour.isEmpty())) {
                                        friFirstHour = defaultFirstHour;
                                        friLastHour = defaultLastHour;
                                    }
                                    boolean friStatus = saveAvailability(date, weekDayNumber, friFirstHour, friLastHour);
                                    saveStatus.add(friStatus);
                                    break;
                                case SATURDAY:
                                    String satFirstHour = saturdayFirstHourEditText.getText().toString();
                                    String satLastHour = saturdayLastHourEditText.getText().toString();
                                    if ((satFirstHour.isEmpty() || satLastHour.isEmpty()) && !isToday) {
                                        break;
                                    }else if (isToday && (satFirstHour.isEmpty() || satLastHour.isEmpty())) {
                                        satFirstHour = defaultFirstHour;
                                        satLastHour = defaultLastHour;
                                    }
                                    boolean satStatus = saveAvailability(date, weekDayNumber, satFirstHour, satLastHour);
                                    saveStatus.add(satStatus);
                                    break;
                                case SUNDAY:
                                    String sunFirstHour = sundayFirstHourEditText.getText().toString();
                                    String sunLastHour = sundayLastHourEditText.getText().toString();
                                    if ((sunFirstHour.isEmpty() || sunLastHour.isEmpty()) && !isToday) {
                                        break;
                                    }else if (isToday && (sunFirstHour.isEmpty() || sunLastHour.isEmpty())) {
                                        sunFirstHour = defaultFirstHour;
                                        sunLastHour = defaultLastHour;
                                    }
                                    boolean sunStatus = saveAvailability(date, weekDayNumber, sunFirstHour, sunLastHour);
                                    saveStatus.add(sunStatus);
                                    break;
                                default: break;
                            }
                        }

                        // Verifica se ocorreu algum erro ao tentar salvar os dados
                        boolean hasError = false;
                        for (boolean saved: saveStatus) {
                            if (!saved) {
                                hasError = true;
                                break;
                            }
                        }

                        if (hasError) {
                            showToastMessage("Ocorreu um erro ao tentar salvar algum dos horários.");
                        }else {
                            showToastMessage("Os dados foram salvos com sucesso.");
                            // Cria um delay para para prosseguir para a Home
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(VetAvailabilityActivity.this, HomeVetActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                            }, delayToContinue);
                        }
                    }else {
                        showToastMessage(getResources().getString(R.string.text_internal_error_02));
                    }
                }else {
                    showToastMessage(getResources().getString(R.string.text_no_hour_to_save));
                }
            }
        });
    }

    private void showToastMessage(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
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

        VetAvailability availabilityMonday = VetAvailability.getAvailabilityByWeekDayNumber(Support.getWeekDayNumberByEnum(Support.DateWeekDays.MONDAY));
        VetAvailability availabilityTuesday = VetAvailability.getAvailabilityByWeekDayNumber(Support.getWeekDayNumberByEnum(Support.DateWeekDays.TUESDAY));
        VetAvailability availabilityWednesday = VetAvailability.getAvailabilityByWeekDayNumber(Support.getWeekDayNumberByEnum(Support.DateWeekDays.WEDNESDAY));
        VetAvailability availabilityThursday = VetAvailability.getAvailabilityByWeekDayNumber(Support.getWeekDayNumberByEnum(Support.DateWeekDays.THURSDAY));
        VetAvailability availabilityFriday= VetAvailability.getAvailabilityByWeekDayNumber(Support.getWeekDayNumberByEnum(Support.DateWeekDays.FRIDAY));
        VetAvailability availabilitySaturday = VetAvailability.getAvailabilityByWeekDayNumber(Support.getWeekDayNumberByEnum(Support.DateWeekDays.SATURDAY));
        VetAvailability availabilitySunday= VetAvailability.getAvailabilityByWeekDayNumber(Support.getWeekDayNumberByEnum(Support.DateWeekDays.SUNDAY));

        if (availabilityMonday != null) {
            mondayFirstHourEditText.setText(availabilityMonday.getStartHour());
            mondayLastHourEditText.setText(availabilityMonday.getFinishHour());
        }
        if (availabilityTuesday != null) {
            tuesdayFirstHourEditText.setText(availabilityTuesday.getStartHour());
            tuesdayLastHourEditText.setText(availabilityTuesday.getFinishHour());
        }
        if (availabilityWednesday != null) {
            wednesdayFirstHourEditText.setText(availabilityWednesday.getStartHour());
            wednesdayLastHourEditText.setText(availabilityWednesday.getFinishHour());
        }
        if (availabilityThursday != null) {
            thursdayFirstHourEditText.setText(availabilityThursday.getStartHour());
            thursdayLastHourEditText.setText(availabilityThursday.getFinishHour());
        }
        if (availabilityFriday != null) {
            fridayFirstHourEditText.setText(availabilityFriday.getStartHour());
            fridayLastHourEditText.setText(availabilityFriday.getFinishHour());
        }
        if (availabilitySaturday != null) {
            saturdayFirstHourEditText.setText(availabilitySaturday.getStartHour());
            saturdayLastHourEditText.setText(availabilitySaturday.getFinishHour());
        }
        if (availabilitySunday != null) {
            sundayFirstHourEditText.setText(availabilitySunday.getStartHour());
            sundayLastHourEditText.setText(availabilitySunday.getFinishHour());
        }

        printListOfDates();
    }

    private void setListOfDates() {
        try {
            Date fromDate = Support.getMonthStartDate();
            Date toDate = Support.getMonthEndDate();
            List<Date> dates = Support.getDates(fromDate, toDate);
            listOfDates = Support.getStringDates(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void printListOfDates() {
        Log.d("", "list of dates: "+ listOfDates);
    }

    private boolean hasAnyHour() {
        return (!mondayFirstHourEditText.getText().toString().isEmpty() && !mondayLastHourEditText.getText().toString().isEmpty()) || (!tuesdayFirstHourEditText.getText().toString().isEmpty() && !tuesdayLastHourEditText.getText().toString().isEmpty()) || (!wednesdayFirstHourEditText.getText().toString().isEmpty() && !wednesdayLastHourEditText.getText().toString().isEmpty()) || (!thursdayFirstHourEditText.getText().toString().isEmpty() && !thursdayLastHourEditText.getText().toString().isEmpty()) || (!fridayFirstHourEditText.getText().toString().isEmpty() && !fridayLastHourEditText.getText().toString().isEmpty()) || (!saturdayFirstHourEditText.getText().toString().isEmpty() && !saturdayLastHourEditText.getText().toString().isEmpty()) || (!sundayFirstHourEditText.getText().toString().isEmpty() && !sundayLastHourEditText.getText().toString().isEmpty());
    }

    private boolean saveAvailability(String date, int weekDayNumber, String startHour, String finishHour) {

        String weekDayName = Support.getWeekDayNameByNumber(weekDayNumber);
        User user = User.getLoggedUser();

        VetAvailability availability = new VetAvailability(
                date,
                weekDayNumber,
                weekDayName,
                startHour,
                finishHour,
                user
        );

        VetAvailability.insertOrUpdateData(getApplicationContext(), availability);

        boolean status = VetAvailability.getAvailabilityById(availability.getId()) != null;

        return status;
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
