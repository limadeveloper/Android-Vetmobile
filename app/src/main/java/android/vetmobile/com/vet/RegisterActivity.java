package android.vetmobile.com.vet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText nameEditText;
    private EditText userEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private RadioButton femaleRadioButton;
    private RadioButton maleRadioButton;
    private Button finishButton;
    private Button dateButton;
    private int datePickerDelay = 3000;
    private String gender = "";
    private String typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.name_edittext_id);
        userEditText = findViewById(R.id.user_edittext_id);
        emailEditText = findViewById(R.id.email_edittext_id);
        phoneEditText = findViewById(R.id.phone_edittext_id);
        passwordEditText = findViewById(R.id.password_edittext_id);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edittext_id);
        femaleRadioButton = findViewById(R.id.female_radiobutton_id);
        maleRadioButton = findViewById(R.id.male_radiobutton_id);
        dateButton = findViewById(R.id.birthday_button_id);
        finishButton = findViewById(R.id.finish_button_id);

        addActionForMaleRadioButton();
        addActionForFemaleRadioButton();
        addActionForDateButton();
        addActionForFinishButton();

        setOrientation();
        setPhoneNumber();
        setTypeUserValue();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;

        try {
            if (isTomorrow(date.toString())) {
                showMessageErrorAboutWrongBirthdayDate();
                createDatePickerWithDelay(datePickerDelay);
            }else {
                dateButton.setText(date.toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            showMessageErrorAboutWrongBirthdayDate();
        }
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
        String datePickerTitle = getResources().getString(R.string.text_select_birthday);

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                RegisterActivity.this,
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

    private void setOrientation() {
        if (Support.isTablet(getWindowManager())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void showMessageErrorAboutWrongBirthdayDate() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.wrongBirthdayDate), Toast.LENGTH_SHORT).show();
    }

    private void showMessageErrorFields() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.errorFields), Toast.LENGTH_SHORT).show();
    }

    private void showMessageErrorNoGenderSelected() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.errorNoGenderSelected), Toast.LENGTH_SHORT).show();
    }

    private boolean isValidGender() {
        boolean isValid = !gender.isEmpty();
        return isValid;
    }

    private boolean isValidUserName() {
        return !nameEditText.getText().toString().isEmpty();
    }

    private boolean isValidUser() {
        return !userEditText.getText().toString().isEmpty();
    }

    private boolean isValidLogin() {
        return true;
    }

    private boolean isValidEmail() {
        return !TextUtils.isEmpty(emailEditText.getText()) && android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText()).matches();
    }

    private boolean isValidPassword() {
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        if (!password.isEmpty() && !confirmPassword.isEmpty() && password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }

    private void setPhoneNumber() {
        boolean isEmulator = Support.isEmulator();
        if (!isEmulator) {
            String number = Support.getPhoneNumber(getApplicationContext()).toString();
            phoneEditText.setText(number);
        }
    }

    private void setTypeUserValue() {
        typeUser = getIntent().getExtras().getString(getResources().getText(R.string.key_typeUser).toString());
    }

    private boolean saveClient() {

        Client client = new Client(
                1,
                nameEditText.getText().toString(),
                userEditText.getText().toString(),
                emailEditText.getText().toString(),
                phoneEditText.getText().toString(),
                passwordEditText.getText().toString(),
                dateButton.getText().toString(),
                gender
        );

        DBManager.insertOrUpdateData(DBManager.EntitiesEnum.CLIENT, getApplicationContext(), client);

        return true;
    }

    private void addActionForMaleRadioButton() {
        maleRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = getResources().getText(R.string.const_male).toString();
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
                gender = getResources().getText(R.string.const_female).toString();
                if (maleRadioButton.isChecked()) {
                    maleRadioButton.setChecked(false);
                }
            }
        });
    }

    private void addActionForDateButton() {
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDatePicker();
            }
        });
    }

    private void addActionForFinishButton() {
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isValidLogin() || !isValidEmail() || !isValidPassword() || !isValidUserName()) {
                    showMessageErrorFields();
                    return;
                }
                if (!isValidGender()) {
                    showMessageErrorNoGenderSelected();
                    return;
                }

                if (typeUser.equals(getResources().getText(R.string.const_typeUserVet).toString())) {
                    Intent intent = new Intent(RegisterActivity.this, RegisterMoreInfoVetActivity.class);
                    startActivity(intent);
                }else if (typeUser.equals(getResources().getText(R.string.const_typeUserClient).toString())) {
                    boolean saved = saveClient();
                    if (saved) {
                        return;
                    }
                    Intent intent = new Intent(RegisterActivity.this, RegisterPetActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
