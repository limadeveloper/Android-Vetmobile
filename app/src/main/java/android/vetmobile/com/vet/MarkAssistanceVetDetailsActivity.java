package android.vetmobile.com.vet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MarkAssistanceVetDetailsActivity extends AppCompatActivity {

    private TextView selectedDateTextView;
    private TextView selectedVetNameTextView;
    private TextView selectedVetDescriptionTextView;
    private ListView listViewHours;
    private User selectedUser = null;
    private String selectedDate = null;
    boolean listIsEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_assistance_vet_details);

        selectedDateTextView = findViewById(R.id.mavt_dateselected_text_id);
        selectedVetNameTextView = findViewById(R.id.mavt_vetname_text_id);
        selectedVetDescriptionTextView = findViewById(R.id.mavt_vetinfo_text_id);
        listViewHours = findViewById(R.id.mavt_hours_list_id);

        setOrientation();
        configureExtras();
        updateUI();
        configureListView();
    }

    private void setOrientation() {
        if (Support.isTablet(getWindowManager())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void configureExtras() {
        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();
        if (intent != null && extras != null) {
            int userId = intent.getIntExtra(getResources().getString(R.string.key_user_id), 0);
            selectedUser = User.getUserBy(userId);
            selectedDate = intent.getStringExtra(getResources().getString(R.string.key_selected_date));
        }
    }

    private void updateUI() {

        selectedDateTextView.setText(selectedDate);
        selectedVetNameTextView.setText(selectedUser.getName());

        if (selectedUser != null) {
            VetDetail vetDetail = VetDetail.getVetDetailByUserId(selectedUser.getId());
            if (vetDetail == null) { return; }
            String userDetails = "CRMV: "+ vetDetail.getCrmv() +"\nEspecialidade: "+ vetDetail.getDomainArea() +"\nEndereço: "+ vetDetail.getAddress() +"\nNº: "+ vetDetail.getAddressNumber() +"\nCidade: "+ vetDetail.getCity() +"\nEstado: "+ vetDetail.getUf();
            selectedVetDescriptionTextView.setText(userDetails);
        }
    }

    private void configureListView() {

        List<String> hours = new ArrayList<>();
        List<VetAvailability> availabilities = new ArrayList<>();

        if (selectedDate != null) {
            String dateString = selectedDate.replace("/", "-");
            availabilities = VetAvailability.getResultsBy(selectedUser, dateString);
        }

        if (availabilities.size() > 0) {
            int count = 1;
            for (VetAvailability availability: availabilities) {
                String itemHour = ""+ count +". Das "+ availability.getStartHour() +" às "+ availability.getFinishHour();
                hours.add(itemHour);
                count++;
            }
        }else {
            hours.add("Nenhum horário");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hours);
        listViewHours.setAdapter(adapter);

        listViewHours.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!listIsEmpty) {
                    // Go to next activity

                }
            }
        });
    }

}
