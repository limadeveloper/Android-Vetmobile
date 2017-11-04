package android.vetmobile.com.vet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
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
    private Button finishButton;
    private int datePickerDelay = 3000;
    private int delayToContinue = 1000;
    private boolean hasImage = false;
    private int RESULT_LOAD_IMAGE = 1;
    private Bitmap selectedPetImage = null;
    private String currentUserLogin;
    private User currentUser = null;

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
        finishButton = findViewById(R.id.finish_button_id);

        addActionForMaleRadioButton();
        addActionForFemaleRadioButton();
        addActionForBirthdayButton();
        addActionForLoadPhotoButton();
        addActionForFinishButton();

        setOrientation();
        setCurrentUserLogin();
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

    private void addActionForFinishButton() {
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Validação dos dados
                if (!isValidName() || !isValidKind()) {
                    showMessageErrorFields();
                    return;
                }

                if (!isValidGender()) {
                    showMessageErrorNoGenderSelected();
                    return;
                }

                if (!hasImage && !Support.isEmulator()) {
                    showMessageErrorImageNotSelected();
                    return;
                }

                if (currentUser == null) {
                    DBManager.showMessageErrorEntityNull(getApplicationContext());
                    return;
                }

                // Salva os dados no banco
                boolean saved = savePet();

                if (!saved) {
                    showMessageSavePetError();
                    return;
                }

                showMessageSavePetSuccessful();

                // Cria um delay para para prosseguir para a próxima tela
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(RegisterPetActivity.this, HomeClientActivity.class);
                        intent.putExtra(getResources().getString(R.string.key_current_user), currentUserLogin);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }, delayToContinue);
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

    private void addActionForLoadPhotoButton() {
        loadFotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
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

    private void setCurrentUserLogin() {
        currentUserLogin = getIntent().getExtras().getString(getResources().getString(R.string.key_current_user));
        currentUser = User.getUserBy(currentUserLogin);
    }

    private boolean isValidName() {
        boolean isValid = !nameEditText.getText().toString().isEmpty();
        return isValid;
    }

    private boolean isValidKind() {
        boolean isValid = !kindEditText.getText().toString().isEmpty();
        return isValid;
    }

    private boolean isValidGender() {
        boolean isValid = !gender.isEmpty();
        return isValid;
    }

    private void showMessageErrorNoGenderSelected() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.errorNoGenderSelected), Toast.LENGTH_SHORT).show();
    }

    private void showMessageErrorFields() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.errorFields), Toast.LENGTH_SHORT).show();
    }

    private void showMessageErrorImageNotSelected() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.text_select_pet_photo), Toast.LENGTH_SHORT).show();
    }

    private void showMessageErrorAboutWrongBirthdayDate() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.wrongBirthdayDate), Toast.LENGTH_SHORT).show();
    }

    private void showMessageSavePetSuccessful() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.text_save_pet_successful), Toast.LENGTH_LONG).show();
    }

    private void showMessageSavePetError() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.text_database_try_saving_object_null), Toast.LENGTH_SHORT).show();
    }

    private boolean savePet() {

        Pet pet = new Pet(
                nameEditText.getText().toString(),
                kindEditText.getText().toString(),
                breedEditText.getText().toString(),
                gender,
                currentUser
        );

        if (selectedPetImage != null) {
            pet.setPhotoBytes(Support.getBytesFromImage(selectedPetImage));
        }

        Pet.insertOrUpdateData(getApplicationContext(), pet);

        boolean status = Pet.getPetBy(pet.getId()) != null;

        return status;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bitmap = Support.getScaledBitmap(picturePath, 800, 800);

            selectedPetImage = bitmap;
            hasImage = selectedPetImage != null;
        }
    }

}
