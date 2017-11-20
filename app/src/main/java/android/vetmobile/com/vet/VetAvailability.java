/**
 * Created by limadeveloper on 17/11/17.
 */

package android.vetmobile.com.vet;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
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

    public VetAvailability() {}

    public VetAvailability(String date, int weekDayNumber, String weekDayName, String startHour, String finishHour, User user) {
        this.date = date;
        this.weekDayNumber = weekDayNumber;
        this.weekDayName = weekDayName;
        this.startHour = startHour;
        this.finishHour = finishHour;
        this.user = user;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public int getWeekDayNumber() { return weekDayNumber; }
    public void setWeekDayNumber(int weekDayNumber) { this.weekDayNumber = weekDayNumber; }
    public String getWeekDayName() { return weekDayName; }
    public void setWeekDayName(String weekDayName) { this.weekDayName = weekDayName; }
    public String getStartHour() { return startHour; }
    public void setStartHour(String startHour) { this.startHour = startHour; }
    public String getFinishHour() { return finishHour; }
    public void setFinishHour(String finishHour) { this.finishHour = finishHour; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public static String getKeyID() {
        return "id";
    }

    public static RealmResults<VetAvailability> getResults() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(VetAvailability.class).findAll();
    }

    public static VetAvailability getAvailabilityBy(int id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(VetAvailability.class).equalTo(getKeyID(), id).findFirst();
    }

    public static void insertOrUpdateData(final Context context, final VetAvailability availability) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (availability != null) {
                        Number currentId = realm.where(VetAvailability.class).max(VetAvailability.getKeyID());
                        int nextId = 1;
                        if (currentId != null) {
                            nextId = currentId.intValue() + 1;
                        }
                        availability.setId(nextId);
                        realm.insertOrUpdate(availability);
                        return;
                    }
                    DBManager.showMessageErrorObjectNull(context);
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public static void deleteAllData() {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            final RealmResults<VetAvailability> results = realm.where(VetAvailability.class).findAll();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    results.deleteAllFromRealm();
                }
            });
        }finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

}
