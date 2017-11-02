/**
 * Created by limadeveloper on 01/11/17.
 */

package android.vetmobile.com.vet;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import io.realm.Realm;

public class DBManager {

    public enum EntityEnum {
        USER,
        PET,
        VET_DETAIL
    }

    public static void showMessageErrorObjectNull(Context context) {
        Toast.makeText(context, context.getResources().getString(R.string.text_database_try_saving_object_null), Toast.LENGTH_LONG).show();
    }

    public static void showMessageErrorEntityNull(Context context) {
        Toast.makeText(context, context.getResources().getString(R.string.text_internal_error), Toast.LENGTH_LONG).show();
    }

    public static void showRealmPath(Context context) {
        Realm realm = Realm.getDefaultInstance();
        Toast.makeText(context, "realm path: "+ realm.getPath(), Toast.LENGTH_LONG).show();
        Log.d("", "realm path: "+ realm.getPath());
    }
}