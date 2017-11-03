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
    private String typeUser = "";
    private User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEditText = findViewById(R.id.login_edittext_id);
        passwordEditText = findViewById(R.id.password_edittext_id);
        vetRadioButton = findViewById(R.id.vet_radiobutton_id);
        clientRadioButton= findViewById(R.id.client_radiobutton_id);
        loginButton = findViewById(R.id.login_button_id);
        registerButton = findViewById(R.id.register_button_id);

        typeUser = getResources().getText(R.string.const_typeUserNone).toString();

        addActionForVetRadioButton();
        addActionForClientRadioButton();
        addActionForLoginButton();
        addActionForRegisterButton();

        setOrientation();
    }

    private void addActionForVetRadioButton() {
        vetRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeUser = getResources().getText(R.string.const_typeUserVet).toString();
                if (clientRadioButton.isChecked()) {
                    clientRadioButton.setChecked(false);
                }
            }
        });
    }

    private void addActionForClientRadioButton() {
        clientRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeUser = getResources().getText(R.string.const_typeUserClient).toString();
                if (vetRadioButton.isChecked()) {
                    vetRadioButton.setChecked(false);
                }
            }
        });
    }

    private void addActionForLoginButton() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isValidLogin()) {
                    showMessageWrongUserLogin();
                    return;
                }

                if (!isValidPassword()) {
                    showMessageWrongUserPassword();
                    return;
                }

                if (currentUser == null) {
                    DBManager.showMessageErrorEntityNull(getApplicationContext());
                    return;
                }

                User.TypeUserEnum type = User.getTypeUserEnum(getApplicationContext(), currentUser);

                if (type == User.TypeUserEnum.VET) {
                    Intent intent = new Intent(LoginActivity.this, HomeVetActivity.class);
                    intent.putExtra(getResources().getString(R.string.key_current_user), currentUser.getLogin());
                    startActivity(intent);
                    finish();
                }else if (type == User.TypeUserEnum.CLIENT) {
                    Intent intent = new Intent(LoginActivity.this, HomeClientActivity.class);
                    intent.putExtra(getResources().getString(R.string.key_current_user), currentUser.getLogin());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void addActionForRegisterButton() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeUser.equals(getResources().getText(R.string.const_typeUserNone).toString())) {
                    showMessageToSelectTypeUser();
                }else if (typeUser.equals(getResources().getText(R.string.const_typeUserVet).toString()) || typeUser.equals(getResources().getText(R.string.const_typeUserClient).toString())) {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    intent.putExtra(getResources().getText(R.string.key_typeUser).toString(), typeUser);
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

    private void showMessageWrongUserLogin() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.text_wrong_user_login), Toast.LENGTH_LONG).show();
    }

    private void showMessageWrongUserPassword() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.text_wrong_user_password), Toast.LENGTH_LONG).show();
    }

    private void showMessageToSelectTypeUser() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.noTypeUserSelected), Toast.LENGTH_SHORT).show();
    }

    private boolean isValidLogin() {
        String login = loginEditText.getText().toString();
        User user = User.getUserBy(login);
        currentUser = user;
        if (user == null) {return false;}
        boolean value = !login.isEmpty() && user.getLogin().equals(login);
        return value;
    }

    private boolean isValidPassword() {
        String password = passwordEditText.getText().toString();
        String login = loginEditText.getText().toString();
        User user = User.getUserBy(login);
        if (user == null) {return false;}
        boolean value = !login.isEmpty() && !password.isEmpty() && user.getPassword().equals(password);
        return value;
    }

}
