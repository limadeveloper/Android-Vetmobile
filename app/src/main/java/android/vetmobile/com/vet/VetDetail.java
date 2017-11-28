/**
 * Created by limadeveloper on 02/11/17.
 */

package android.vetmobile.com.vet;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

public class VetDetail extends RealmObject {

    @PrimaryKey
    private int id;
    private String address;
    private String addressNumber;
    private String neighborhood;
    private String city;
    private String uf;
    private String cep;
    private String crmv;
    private String domainArea;
    private User user;

    public VetDetail() {}

    public VetDetail(String address, String addressNumber, String neighborhood, String city, String uf, String cep, String crmv, String domainArea, User user) {
        this.address = address;
        this.addressNumber = addressNumber;
        this.neighborhood = neighborhood;
        this.city = city;
        this.uf = uf;
        this.cep = cep;
        this.crmv = crmv;
        this.domainArea = domainArea;
        this.user = user;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    public String getAddressNumber() {return addressNumber;}
    public void setAddressNumber(String addressNumber) {this.addressNumber = addressNumber;}
    public String getNeighborhood() {return neighborhood;}
    public void setNeighborhood(String neighborhood) {this.neighborhood = neighborhood;}
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}
    public String getUf() {return uf;}
    public void setUf(String uf) {this.uf = uf;}
    public String getCep() {return cep;}
    public void setCep(String cep) {this.cep = cep;}
    public String getCrmv() {return crmv;}
    public void setCrmv(String crmv) {this.crmv = crmv;}
    public String getDomainArea() {return domainArea;}
    public void setDomainArea(String domainArea) {this.domainArea = domainArea;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    public static String getKeyID() {
        return "id";
    }

    public static String getKeyUserID() { return "user.id"; }

    public static RealmResults<VetDetail> getVetDetails() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(VetDetail.class).findAll();
    }

    public static VetDetail getVetDetailBy(int id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(VetDetail.class).equalTo(getKeyID(), id).findFirst();
    }

    public static void insertOrUpdateData(final Context context, final VetDetail vetDetail) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (vetDetail != null) {
                        Number currentId = realm.where(VetDetail.class).max(VetDetail.getKeyID());
                        int nextId = 1;
                        if (currentId != null) {
                            nextId = currentId.intValue() + 1;
                        }
                        vetDetail.setId(nextId);
                        realm.insertOrUpdate(vetDetail);
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
            final RealmResults<VetDetail> details = realm.where(VetDetail.class).findAll();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    details.deleteAllFromRealm();
                }
            });
        }finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public static VetDetail getVetDetailByUserId(int userId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(VetDetail.class).equalTo(getKeyUserID(), userId).findFirst();
    }

}
