package android.vetmobile.com.vet;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dateButton = findViewById(R.id.birthday_button_id);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDatePicker();
            }
        });

        setOrientation();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        dateButton.setText(date.toString());
    }

    public void createDatePicker() {

        Calendar now = Calendar.getInstance();
        String datePickerTitle = getResources().getString(R.string.selectBirthday);

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

    private void setOrientation() {
        if (DeviceSettings.isTablet(getWindowManager())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
