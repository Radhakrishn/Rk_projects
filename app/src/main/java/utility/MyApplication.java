package utility;

import android.app.Application;

import network.VolleySingleton;

/**
 * Created by user on 7/17/2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleySingleton.init(this);
    }
}
