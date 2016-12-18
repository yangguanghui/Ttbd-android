package com.example.ygh.ttbd.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

import com.example.ygh.ttbd.AppConst;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * SystemUtils
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-5-15
 */
public class SystemUtils
{

    /**
     * recommend default thread pool size according to system available processors,
     * {@link #getDefaultThreadPoolSize()}
     **/
    public static final  int DEFAULT_THREAD_POOL_SIZE                     =
            getDefaultThreadPoolSize();
    private static final int MY_PERMISSIONS_REQUEST_READ_READ_PHONE_STATE = 0;

    private SystemUtils()
    {
        throw new AssertionError();
    }

    /**
     * get recommend default thread pool size
     *
     * @return if 2 * availableProcessors + 1 less than 8, return it, else return 8;
     * @see {@link #getDefaultThreadPoolSize(int)} max is 8
     */
    public static int getDefaultThreadPoolSize()
    {
        return getDefaultThreadPoolSize(8);
    }

    /**
     * get recommend default thread pool size
     *
     * @param max
     * @return if 2 * availableProcessors + 1 less than max, return it, else return max;
     */
    public static int getDefaultThreadPoolSize(int max)
    {
        int availableProcessors = 2 * Runtime.getRuntime().availableProcessors() + 1;
        return availableProcessors > max ? max : availableProcessors;
    }

    public static String getDeviceId(Context context)
    {
        if (ContextCompat.checkSelfPermission(context,
                                              Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED)
        {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                                                                    Manifest.permission
                                                                            .READ_PHONE_STATE))
            {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            }
            else
            {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions((Activity) context,
                                                  new String[]{Manifest.permission
                                                                       .READ_PHONE_STATE},
                                                  AppConst.MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
                // MY_PERMISSIONS_REQUEST_READ_READ_PHONE_STATE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{
            TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            return tm.getDeviceId();
        }
        return "";
    }
}
