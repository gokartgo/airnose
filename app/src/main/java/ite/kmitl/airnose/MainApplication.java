package ite.kmitl.airnose;

import android.app.Application;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by ASUS-PC on 23/12/2560.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        // Initialize thing(s) here
        Contextor.getInstance().init(getApplicationContext());
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
