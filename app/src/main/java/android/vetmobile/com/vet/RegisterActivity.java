package android.vetmobile.com.vet;

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

        dateButton = (Button) findViewById(R.id.birthday_button_id);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        RegisterActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        dateButton.setText(date.toString());
    }
}
