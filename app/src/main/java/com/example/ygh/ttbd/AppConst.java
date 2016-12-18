package com.example.ygh.ttbd;

import android.os.Build;

/**
 * Created by ygh on 2016/12/5.
 */

public class AppConst
{
    public static final String VERSION_CODE = "versionCode";
    public static final String VERSION_CODE_SERVER = "versionCodeServer";

    public static final String SERVER = Build.HARDWARE.equals("goldfish") ? "http://api.ttbd.com/v1/" : "http://192.168.1.111/ttbd/api/web/v1/";

    public static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    public static final String MIME_TYPE_APK = "application/vnd.android.package-archive";
}
