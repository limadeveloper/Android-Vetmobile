/**
 * Created by limadeveloper on 02/11/17.
 */

package android.vetmobile.com.vet;

import android.content.Context;
import android.content.Intent;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String login;
    private String email;
    private String phone;
    private String password;
    private String birthday;
    private String gender;
    private String typeUser;
    private boolean isLogged;

    public User() {}

    public User(String name, String login, String email, String phone, String password, String birthday, String gender, String typeUser, boolean isLogged) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.typeUser = typeUser;
        this.isLogged = isLogged;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getTypeUser() { return typeUser; }
    public void setTypeUser(String typeUser) { this.typeUser = typeUser; }
    public boolean getIsLogged() { return isLogged; }
    public void setIsLogged(boolean isLogged) { this.isLogged = isLogged; }

    public static String getKeyID() {
        return "id";
    }

    public static String getKeyLogin() {
        return "login";
    }

    public static String getKeyIsLogged() {
        return "isLogged";
    }

    public static RealmResults<User> getUsers() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(User.class).findAll();
    }

    public static User getLoggedUser() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(User.class).equalTo(getKeyIsLogged(), true).findFirst();
    }

    public static User getUserBy(int id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(User.class).equalTo(getKeyID(), id).findFirst();
    }

    public static User getUserBy(String login) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(User.class).equalTo(getKeyLogin(), login).findFirst();
    }

    public static boolean checkingLoginAvailable(String login) {
        Realm realm = Realm.getDefaultInstance();
        boolean isAvailable = realm.where(User.class).contains(getKeyLogin(), login).findAll().isEmpty();
        return isAvailable;
    }

    public static void insertOrUpdateData(final Context context, final User user) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (user != null) {
                        Number currentId = realm.where(User.class).max(User.getKeyID());
                        int nextId = 1;
                        if (currentId != null) {
                            nextId = currentId.intValue() + 1;
                        }
                        user.setId(nextId);
                        realm.insertOrUpdate(user);
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

    public static void updateStatusLogged(final boolean value, final String login) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    User user = realm.where(User.class).equalTo(getKeyLogin(), login).findFirst();
                    user.setIsLogged(value);
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
            final RealmResults<User> users = realm.where(User.class).findAll();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    users.deleteAllFromRealm();
                }
            });
        }finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public static void logout(User user, Context context) {
        updateStatusLogged(false, user.login);
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public enum TypeUserEnum {
        VET,
        CLIENT
    }

    public static TypeUserEnum getTypeUserEnum(Context context, User user) {
        if (user.typeUser.equals(context.getResources().getString(R.string.text_const_type_user_vet))) {
            return TypeUserEnum.VET;
        }
        return TypeUserEnum.CLIENT;
    }
}