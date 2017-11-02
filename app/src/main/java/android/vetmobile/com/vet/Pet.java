/**
 * Created by limadeveloper on 02/11/17.
 */

package android.vetmobile.com.vet;

import android.content.Context;
import android.graphics.Bitmap;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Pet extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String kind;
    private String breed;
    private String gender;
    private User user;
    private byte[] photoBytes;

    @Ignore
    private Bitmap photo;

    public Pet() {}

    public Pet(String name, String kind, String breed, String gender, User user, byte[] photoBytes) {
        this.name = name;
        this.kind = kind;
        this.breed = breed;
        this.gender = gender;
        this.user = user;
        this.photoBytes = photoBytes;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getKind() {return kind;}
    public void setKind(String kind) {this.kind = kind;}
    public String getBreed() {return breed;}
    public void setBreed(String breed) {this.breed = breed;}
    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public byte[] getPhotoBytes() {return photoBytes;}
    public void setPhotoBytes(byte[] photoBytes) {this.photoBytes = photoBytes;}
    public Bitmap getPhoto() {return Support.getImageFromBytes(this.photoBytes);}

    public static String getKeyID() {
        return "id";
    }

    public static RealmResults<Pet> getPets() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Pet.class).findAll();
    }

    public static Pet getPetBy(int id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Pet.class).equalTo(getKeyID(), id).findFirst();
    }

    public static void insertOrUpdateData(final Context context, final Pet pet) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (pet != null) {
                        Number currentId = realm.where(Pet.class).max(Pet.getKeyID());
                        int nextId = 1;
                        if (currentId != null) {
                            nextId = currentId.intValue() + 1;
                        }
                        pet.setId(nextId);
                        realm.insertOrUpdate(pet);
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
            final RealmResults<Pet> pets = realm.where(Pet.class).findAll();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    pets.deleteAllFromRealm();
                }
            });
        }finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

}
