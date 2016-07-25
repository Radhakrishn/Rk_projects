package utility;

import android.app.Application;
import android.support.v7.appcompat.BuildConfig;

import network.VolleySingleton;

/**
 * Created by user on 7/17/2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.setDebugMode(BuildConfig.DEBUG);
        VolleySingleton.init(this);
    }
}
