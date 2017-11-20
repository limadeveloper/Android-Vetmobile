/**
 * Created by limadeveloper on 18/10/17.
 */

package android.vetmobile.com.vet;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Support {

    // **********************
    // PROPERTIES
    // **********************
    private static double tabletValue = 6.5;

    // **********************
    // METHODS
    // **********************
    public static boolean isTablet(WindowManager manager) {

        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);

        float yInches = metrics.heightPixels / metrics.ydpi;
        float xInches = metrics.widthPixels / metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);

        return diagonalInches >= tabletValue;
    }

    public static boolean isEmulator() {
        if (Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK")) {
            return true;
        }
        return false;
    }

    public static String getPhoneNumber(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        String phoneNumber = manager.getLine1Number();
        return phoneNumber;
    }

    // **********************
    // IMAGES
    // **********************
    public static Bitmap getBitmapFromUri(Uri uri, ContentResolver contentResolver) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    public static Bitmap getScaledBitmap(String picturePath, int width, int height) {

        BitmapFactory.Options sizeOptions = new BitmapFactory.Options();
        sizeOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picturePath, sizeOptions);

        int inSampleSize = calculateInSampleSize(sizeOptions, width, height);

        sizeOptions.inJustDecodeBounds = false;
        sizeOptions.inSampleSize = inSampleSize;

        return BitmapFactory.decodeFile(picturePath, sizeOptions);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    public static byte[] getBytesFromImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageBytes = stream.toByteArray();
        return imageBytes;
    }

    public static Bitmap getImageFromBytes(byte[] bytes) {
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        return bitmap;
    }

    // **********************
    // STORAGE DATA
    // **********************
    public static void storageBoolean(boolean value, String key, Activity activity) {
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getStorageBoolean(String key, Activity activity) {
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        boolean value = preferences.getBoolean(key, false);
        return value;
    }

    public static void storageString(String value, String key, Activity activity) {
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStorageString(String key, Activity activity) {
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        String value = preferences.getString(key, "");
        return value;
    }

    public static void storageFloat(float value, String key, Activity activity) {
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public static float getStorageFloat(String key, Activity activity) {
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        float value = preferences.getFloat(key, 0);
        return value;
    }

    // **********************
    // DATES
    // **********************
    public enum DateOptions {
        START,
        END
    }

    public enum DateWeekDays {
        NONE,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public static String getDateFormat1() {
        return "dd-MM-yyyy";
    }

    public static String getDateFormat2() {
        return "MMM dd, yyyy";
    }

    public static List<Date> getDates(String fromDate, String toDate) {
        ArrayList<Date> dates = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(getDateFormat1());
        try {
            Calendar fromCal = Calendar.getInstance();
            fromCal.setTime(dateFormat.parse(fromDate));
            Calendar toCal = Calendar.getInstance();
            toCal.setTime(dateFormat.parse(toDate));
            while (!fromCal.after(toCal)) {
                dates.add(fromCal.getTime());
                fromCal.add(Calendar.DATE, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }

    public static List<Date> getDates(Date fromDate, Date toDate) {
        SimpleDateFormat format = new SimpleDateFormat(getDateFormat1());
        String initialDate = format.format(fromDate);
        String finalDate = format.format(toDate);
        return getDates(initialDate, finalDate);
    }

    public static List<String> getStringDates(List<Date> fromDates) {
        SimpleDateFormat format = new SimpleDateFormat(getDateFormat1());
        List<String> result = new ArrayList<>();
        for (Date date : fromDates) {
            String stringDate = format.format(date);
            result.add(stringDate);
        }
        return result;
    }

    /**
     * @param option
     * START for start date of month e.g.  Nov 01, 2017
     * END for end date of month e.g.  Nov 30, 2017
     * @return
     */
    public static String getMonthDate(DateOptions option) {
        SimpleDateFormat format = new SimpleDateFormat(getDateFormat1());
        format.setTimeZone(TimeZone.getDefault());
        format.format(Calendar.getInstance().getTime());
        Calendar calendar = Calendar.getInstance();
        int date = calendar.getActualMinimum(Calendar.DATE);
        if (DateOptions.END.equals(option)) {
            date = calendar.getActualMaximum(Calendar.DATE);
        }
        calendar.set(Calendar.DATE, date);
        String result = format.format(calendar.getTime());
        return result;
    }

    public static String getMonthStartDateString() {
        return getMonthDate(DateOptions.START);
    }

    public static String getMonthEndDateString() {
        return getMonthDate(DateOptions.END);
    }

    public static Date getMonthStartDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(getDateFormat1());
        Date result = format.parse(getMonthStartDateString());
        return result;
    }

    public static Date getMonthEndDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(getDateFormat1());
        Date result = format.parse(getMonthEndDateString());
        return result;
    }

    public static DateWeekDays getWeekDayByStringDate(String stringDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(getDateFormat1());
        int dayOfWeek = -1;
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = dateFormat.parse(stringDate);
            calendar.setTime(date);
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getWeekDayEnumByNumber(dayOfWeek);
    }

    public static String getWeekDayNameByNumber(int weekNumber) {
        switch (weekNumber) {
            case 1: return "SUNDAY";
            case 2: return "MONDAY";
            case 3: return "TUESDAY";
            case 4: return "WEDNESDAY";
            case 5: return "THURSDAY";
            case 6: return "FRIDAY";
            case 7: return "SATURDAY";
            default: return "";
        }
    }

    public static DateWeekDays getWeekDayEnumByNumber(int weekNumber) {
        switch (weekNumber) {
            case 1: return DateWeekDays.SUNDAY;
            case 2: return DateWeekDays.MONDAY;
            case 3: return DateWeekDays.TUESDAY;
            case 4: return DateWeekDays.WEDNESDAY;
            case 5: return DateWeekDays.THURSDAY;
            case 6: return DateWeekDays.FRIDAY;
            case 7: return DateWeekDays.SATURDAY;
            default: return DateWeekDays.NONE;
        }
    }

    public static int getWeekDayNumberByEnum(DateWeekDays weekDay) {
        switch (weekDay) {
            case SUNDAY: return 1;
            case MONDAY: return 2;
            case TUESDAY: return 3;
            case WEDNESDAY: return 4;
            case THURSDAY: return 5;
            case FRIDAY: return 6;
            case SATURDAY: return 7;
            default: return -1;
        }
    }
}
