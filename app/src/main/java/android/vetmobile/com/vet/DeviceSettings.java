/**
 * Created by limadeveloper on 18/10/17.
 */

package android.vetmobile.com.vet;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DeviceSettings {

    private static double tabletValue = 6.5;

    public static boolean isTablet(WindowManager manager) {

        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);

        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);

        return diagonalInches >= tabletValue;
    }

    public static String getPhoneNumber(Context context) {
        TelephonyManager manager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = manager.getLine1Number();
        return phoneNumber;
    }

    public static boolean isEmulator() {
        if (Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK")) {
            return true;
        }
        return false;
    }
}
