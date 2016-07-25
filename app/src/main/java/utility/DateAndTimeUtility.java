package utility;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Utility class for all date and time related utility tasks.
 */
public class DateAndTimeUtility {


    /**
     * Server time to be replaced for calculating current time
     * @return current calendar
     */
    public static Calendar getCurrentTime() {
        return Calendar.getInstance();
    }

    public static String getFormattedDate(String milliSeconds) {
        String formattedDate = "";
        if (TextUtils.isEmpty(milliSeconds)) {
            return formattedDate;
        }
        Calendar calendar = Calendar.getInstance();
        long number = Long.parseLong(milliSeconds);
        calendar.setTimeInMillis(number);
        SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");//new SimpleDateFormat("dd MMM yy");
        formattedDate = format.format(calendar.getTime());
        return formattedDate;
    }




    public static String getTodaysDate() {
        String today = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        return today;
    }





}
