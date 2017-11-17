/**
 * Created by limadeveloper on 17/11/17.
 */

package android.vetmobile.com.vet;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class VetAvailability extends RealmObject {

    @PrimaryKey
    private int id;
    private String date;
    private int weekDayNumber;
    private String weekDayName;
    private String startHour;
    private String finishHour;
    private User user;

}
