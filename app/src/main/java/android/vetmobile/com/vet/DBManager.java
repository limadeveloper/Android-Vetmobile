/**
 * Created by limadeveloper on 01/11/17.
 */

package android.vetmobile.com.vet;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import io.realm.Realm;

public class DBManager {

    public enum EntitiesEnum {
        CLIENT,
        PET,
        VET
    }

    private static void showMessageErrorObjectNull(Context context) {
        Toast.makeText(context, context.getResources().getString(R.string.text_database_try_saving_object_null), Toast.LENGTH_LONG).show();
    }

    private static void showMessageErrorEntitieNull(Context context) {
        Toast.makeText(context, context.getResources().getString(R.string.text_internal_error), Toast.LENGTH_LONG).show();
    }

    public static void insertOrUpdateData(final DBManager.EntitiesEnum entitieEnum, final Context context, final Client client) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    switch (entitieEnum) {
                        case CLIENT:
                            if (client != null) {
                                realm.insertOrUpdate(client);
                                break;
                            }
                            showMessageErrorObjectNull(context);
                            break;
                        default:
                            showMessageErrorEntitieNull(context);
                            break;
                    }
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public static void showRealmPath(Context context) {
        Realm realm = Realm.getDefaultInstance();
        Toast.makeText(context, "realm path: "+ realm.getPath(), Toast.LENGTH_LONG).show();
        Log.d("", "realm path: "+ realm.getPath());
    }
}