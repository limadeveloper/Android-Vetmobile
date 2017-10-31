/**
 * Created by limadeveloper on 31/10/17.
 */

package android.vetmobile.com.vet;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String birthday;
    private String gender;

    public User() {}

    public User(int id, String name, String email, String phone, String password, String birthday, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
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

}
