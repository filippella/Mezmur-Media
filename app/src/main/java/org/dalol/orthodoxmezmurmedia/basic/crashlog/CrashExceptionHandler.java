package org.dalol.orthodoxmezmurmedia.basic.crashlog;

import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/27/2016
 */
public class CrashExceptionHandler implements UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler defaultExceptionHandler;
    SharedPreferences preferences;

    public CrashExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler, Context context) {
        //preferences = context.getSharedPreferences(PrefsContract.SHARED_PREFS_NAME, 0);
        defaultExceptionHandler = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable throwable) {
        //preferences.edit().putBoolean(PrefsContract.PREF_APP_HAS_CRASHED, true).commit();
        defaultExceptionHandler.uncaughtException(thread, throwable);
    }
}