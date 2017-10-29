package android.vetmobile.com.vet;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterPetActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText nameEditText;
    private EditText kindEditText;
    private EditText breedEditText;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private String gender = "";
    private Button birthdayButton;
    private Button loadFotoButton;
    private Button addAnotherButton;
    private Button finishButton;
    private int datePickerDelay = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        nameEditText = findViewById(R.id.petname_edittext_id);
        kindEditText = findViewById(R.id.petkind_edittext_id);
        breedEditText = findViewById(R.id.petbreed_edittext_id);
        maleRadioButton = findViewById(R.id.male_radiobutton_id);
        femaleRadioButton = findViewById(R.id.female_radiobutton_id);
        birthdayButton = findViewById(R.id.selectdate_button_id);
        loadFotoButton = findViewById(R.id.chooseimage_button_id);
        addAnotherButton = findViewById(R.id.addmore_button_id);
        finishButton = findViewById(R.id.finish_button_id);

        addActionForMaleRadioButton();
        addActionForFemaleRadioButton();
        addActionForBirthdayButton();
        addActionForFinishButton();
        setOrientation();
    }

    private void addActionForMaleRadioButton() {
        maleRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Masculino";
                if (femaleRadioButton.isChecked()) {
                    femaleRadioButton.setChecked(false);
                }
            }
        });
    }

    private void addActionForFemaleRadioButton() {
        femaleRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Feminino";
                if (maleRadioButton.isChecked()) {
                    maleRadioButton.setChecked(false);
                }
            }
        });
    }

    private void addActionForFinishButton() {
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isValidName() || !isValidKind()) {
                    showMessageErrorFields();
                    return;
                }
                if (!isValidGender()) {
                    showMessageErrorNoGenderSelected();
                    return;
                }
            }
        });
    }

    private void addActionForBirthdayButton() {
        birthdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDatePicker();
            }
        });
    }

    private void setOrientation() {
        if (DeviceSettings.isTablet(getWindowManager())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private boolean isValidName() {
        boolean isValid = !nameEditText.getText().toString().isEmpty();
        return isValid;
    }

    private boolean isValidKind() {
        boolean isValid = !kindEditText.getText().toString().isEmpty();
        return isValid;
    }

    private void showMessageErrorNoGenderSelected() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.errorNoGenderSelected), Toast.LENGTH_SHORT).show();
    }

    private boolean isValidGender() {
        boolean isValid = !gender.isEmpty();
        return isValid;
    }

    private void showMessageErrorFields() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.errorFields), Toast.LENGTH_SHORT).show();
    }

    private void showMessageErrorAboutWrongBirthdayDate() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.wrongBirthdayDate), Toast.LENGTH_SHORT).show();
    }

    public void createDatePickerWithDelay(int timer) {
        // Cria um delay para criar o datePicker
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                createDatePicker();
            }
        }, timer);
    }

    public void createDatePicker() {

        Calendar now = Calendar.getInstance();
        String datePickerTitle = getResources().getString(R.string.selectBirthday);

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                RegisterPetActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_1);
        datePickerDialog.setThemeDark(true); //set dark them for dialog?
        datePickerDialog.vibrate(true); //vibrate on choosing date?
        datePickerDialog.dismissOnPause(true); //dismiss dialog when onPause() called?
        datePickerDialog.showYearPickerFirst(true); //choose year first?
        datePickerDialog.setTitle(datePickerTitle); //dialog title
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog"); //show dialog
    }

    private boolean isTomorrow(String selectedDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(selectedDate);
        if (new Date().after(date)) {
            return false;
        }
        return true;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;

        try {
            if (isTomorrow(date.toString())) {
                showMessageErrorAboutWrongBirthdayDate();
                createDatePickerWithDelay(datePickerDelay);
            }else {
                birthdayButton.setText(date.toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            showMessageErrorAboutWrongBirthdayDate();
        }
    }

}
