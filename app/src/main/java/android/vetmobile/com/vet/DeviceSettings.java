/**
 * Created by limadeveloper on 18/10/17.
 */

package android.vetmobile.com.vet;

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
}
