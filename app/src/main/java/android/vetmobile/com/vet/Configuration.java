/**
 * Created by limadeveloper on 31/10/17.
 *
 * reference: https://www.youtube.com/watch?v=c9hy74_UAwk&index=2&list=PL2QkKoTK1V5GlubxDKp2TvnRQhjtoPhfh
 * reference: https://realm.io/docs/java/latest/#initializing-realm
 */

package android.vetmobile.com.vet;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Configuration extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("vetmobile.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
