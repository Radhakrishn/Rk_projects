package utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This is utility class for writing network related utility methods.
 */
public class NetworkUtility
{

    /**
     * Checks whether the network connection is available
     *
     * @param inContext Context
     * @return true if network available or else false
     */
    public static boolean isNetworkAvailable(Context inContext)
    {
        if(inContext != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) inContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        }
        return false;
    }

}
