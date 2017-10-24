package android.vetmobile.com.vet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private RadioButton vetRadioButton;
    private RadioButton clientRadioButton;
    private int typeUser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setOrientation();

        loginEditText = findViewById(R.id.login_edittext_id);
        passwordEditText = findViewById(R.id.password_edittext_id);
        vetRadioButton = findViewById(R.id.vet_radiobutton_id);
        clientRadioButton= findViewById(R.id.client_radiobutton_id);
        loginButton = findViewById(R.id.login_button_id);
        registerButton = findViewById(R.id.register_button_id);

        vetRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeUser = 1;
                if (clientRadioButton.isChecked()) {
                    clientRadioButton.setChecked(false);
                }
            }
        });

        clientRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeUser = 2;
                if (vetRadioButton.isChecked()) {
                    vetRadioButton.setChecked(false);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidLogin() || !isValidPassword()) {
                    showMessageErrorFields();
                    return;
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (typeUser) {
                    case 0:
                        showMessageToSelectTypeUser();
                        break;
                    case 2:
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
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

    private void showMessageErrorFields() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.errorFields), Toast.LENGTH_SHORT).show();
    }

    private void showMessageToSelectTypeUser() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.noTypeUserSelected), Toast.LENGTH_SHORT).show();
    }

    private boolean isValidLogin() {
        boolean isValid = !loginEditText.getText().toString().isEmpty();
        return isValid;
    }

    private boolean isValidPassword() {
        boolean isValid = !passwordEditText.getText().toString().isEmpty();
        return isValid;
    }
}
