package android.vetmobile.com.vet;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class HomeVetActivity extends AppCompatActivity {

    private LinearLayout listItem1;
    private LinearLayout listItem2;
    private LinearLayout listItem3;
    private LinearLayout listItem4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_vet);

        listItem1 = findViewById(R.id.item1_list_id);
        listItem2 = findViewById(R.id.item2_list_id);
        listItem3 = findViewById(R.id.item3_list_id);
        listItem4 = findViewById(R.id.item4_list_id);

        addActionForListItem1();
        addActionForListItem2();
        addActionForListItem3();
        addActionForListItem4();
        setOrientation();
    }

    private void setOrientation() {
        if (Support.isTablet(getWindowManager())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void addActionForListItem1() {
        listItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something here
            }
        });
    }

    private void addActionForListItem2() {
        listItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something here
            }
        });
    }

    private void addActionForListItem3() {
        listItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something here
            }
        });
    }

    private void addActionForListItem4() {
        listItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something here
            }
        });
    }

}
