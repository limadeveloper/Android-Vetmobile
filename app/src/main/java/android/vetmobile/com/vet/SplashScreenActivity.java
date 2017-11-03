package android.vetmobile.com.vet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private int time = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Cria um delay para executar o método showLogin
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startInitialActivity();
            }
        }, time);

        // Mostra o path do banco de dados
        if (!Support.isEmulator()) { return; }
        DBManager.showRealmPath(getApplicationContext());
    }

    private void startInitialActivity() {

        User user = User.getLoggedUser();

        if (user != null) {
            User.TypeUserEnum type = User.getTypeUserEnum(getApplicationContext(), user);
            if (type == User.TypeUserEnum.VET) {
                Intent intent = new Intent(SplashScreenActivity.this, HomeVetActivity.class);
                startActivity(intent);
                finish();
            }else if (type == User.TypeUserEnum.CLIENT) {
                Intent intent = new Intent(SplashScreenActivity.this, HomeClientActivity.class);
                startActivity(intent);
                finish();
            }
        }else {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
