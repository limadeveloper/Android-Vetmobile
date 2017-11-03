package android.vetmobile.com.vet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterMoreInfoVetActivity extends AppCompatActivity {

    private EditText addressEditText;
    private EditText addressNumberEditText;
    private EditText neighborhoodEditText;
    private EditText cityEditText;
    private EditText ufCityEditText;
    private EditText cepCityEditText;
    private EditText crvEditText;
    private EditText domainAreaEditText;
    private Button finishButton;
    private String currentUserLogin;
    private User currentUser = null;
    private int delayToContinue = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_more_info_vet);

        addressEditText = findViewById(R.id.address_field_id);
        addressNumberEditText = findViewById(R.id.addressnumber_field_id);
        neighborhoodEditText = findViewById(R.id.neighborhood_field_id);
        cityEditText = findViewById(R.id.city_field_id);
        ufCityEditText = findViewById(R.id.ufcity_field_id);
        cepCityEditText = findViewById(R.id.cepcity_field_id);
        crvEditText = findViewById(R.id.crv_field_id);
        domainAreaEditText = findViewById(R.id.domainarea_field_id);
        finishButton = findViewById(R.id.finish_button_id);

        addActionForFinishButton();
        setOrientation();
        setCurrentUserLogin();
    }

    private void addActionForFinishButton() {
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Validação dos dados
                if (!validateFields()) {
                    showMessageErrorFields();
                    return;
                }

                // Salva os dados no banco
                boolean saved = saveData();

                if (!saved) {
                    showMessageSaveDataError();
                    return;
                }

                showMessageSaveDataSuccessful();

                // Cria um delay para para prosseguir para a próxima tela
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(RegisterMoreInfoVetActivity.this, HomeVetActivity.class);
                        intent.putExtra(getResources().getString(R.string.key_current_user), currentUserLogin);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }, delayToContinue);
            }
        });
    }

    private boolean isValidAddress() {
        boolean isValid = !addressEditText.getText().toString().isEmpty();
        return isValid;
    }

    private boolean isValidAddressNumber() {
        boolean isValid = !addressNumberEditText.getText().toString().isEmpty();
        return isValid;
    }

    private boolean isValidNeighborhood() {
        boolean isValid = !neighborhoodEditText.getText().toString().isEmpty();
        return isValid;
    }

    private boolean isValidCity() {
        boolean isValid = !cityEditText.getText().toString().isEmpty();
        return isValid;
    }

    private boolean isValidUFCity() {
        boolean isValid = !ufCityEditText.getText().toString().isEmpty();
        return isValid;
    }

    private boolean isValidCepCity() {
        boolean isValid = !cepCityEditText.getText().toString().isEmpty();
        return isValid;
    }

    private boolean isValidCRV() {
        boolean isValid = !crvEditText.getText().toString().isEmpty();
        return isValid;
    }

    private boolean isValidDomainArea() {
        boolean isValid = !domainAreaEditText.getText().toString().isEmpty();
        return isValid;
    }

    private boolean validateFields() {
        boolean isValid = isValidAddress() && isValidAddressNumber() && isValidNeighborhood() && isValidCity() && isValidUFCity() && isValidCepCity() && isValidCRV() && isValidDomainArea();
        return isValid;
    }

    private void showMessageErrorFields() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.errorFields), Toast.LENGTH_SHORT).show();
    }

    private void showMessageSaveDataSuccessful() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.text_save_data_successful), Toast.LENGTH_LONG).show();
    }

    private void showMessageSaveDataError() {
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.text_database_try_saving_object_null), Toast.LENGTH_SHORT).show();
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

    private boolean saveData() {

        VetDetail vetDetail = new VetDetail(
                addressEditText.getText().toString(),
                addressNumberEditText.getText().toString(),
                neighborhoodEditText.getText().toString(),
                cityEditText.getText().toString(),
                ufCityEditText.getText().toString(),
                cepCityEditText.getText().toString(),
                crvEditText.getText().toString(),
                domainAreaEditText.getText().toString(),
                currentUser
        );

        VetDetail.insertOrUpdateData(getApplicationContext(), vetDetail);

        boolean status = VetDetail.getVetDetailBy(vetDetail.getId()) != null;

        return status;
    }

}
