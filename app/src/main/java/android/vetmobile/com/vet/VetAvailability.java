/**
 * Created by limadeveloper on 17/11/17.
 */

package android.vetmobile.com.vet;

import android.content.Context;
import android.util.Log;

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

    public static String getKeyId() {
        return "id";
    }

    public static String getKeyWeekDayNumber() {
        return "weekDayNumber";
    }

    public static String getKeyDate() {
        return "date";
    }

    public static String getKeyUserId() {
        return "user.id";
    }

    public static RealmResults<VetAvailability> getResultsBy(User currentUser, String date) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<VetAvailability> results = realm.where(VetAvailability.class).equalTo(getKeyUserId(), currentUser.getId()).findAll();
        if (date != null) {
            results = realm.where(VetAvailability.class).equalTo(getKeyUserId(), currentUser.getId()).equalTo(getKeyDate(), date).findAll();
        }
        return results;
    }

    public static VetAvailability getAvailabilityById(int id, User currentUser) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(VetAvailability.class).equalTo(getKeyId(), id).equalTo(getKeyUserId(), currentUser.getId()).findFirst();
    }

    public static VetAvailability getAvailabilityByWeekDayNumber(int weekDayNumber, int userId) {
        Realm realm = Realm.getDefaultInstance();
        VetAvailability result = realm.where(VetAvailability.class).equalTo(getKeyUserId(), userId).equalTo(getKeyWeekDayNumber(), weekDayNumber).findFirst();
        Log.d("availability", " VetAvailability: "+ result);
        return result;
    }

    public static void insertOrUpdateData(final Context context, final VetAvailability availability) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (availability != null) {
                        Number currentId = realm.where(VetAvailability.class).max(VetAvailability.getKeyId());
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

    public static void deleteData(String date) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            User currentUser = User.getLoggedUser();
            RealmResults<VetAvailability> results = realm.where(VetAvailability.class).equalTo(getKeyUserId(), currentUser.getId()).findAll();
            if (date != null) {
                results = realm.where(VetAvailability.class).equalTo(getKeyUserId(), currentUser.getId()).equalTo(getKeyDate(), date).findAll();
            }
            final RealmResults<VetAvailability> finalResults = results;
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    finalResults.deleteAllFromRealm();
                }
            });
        }finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

}
