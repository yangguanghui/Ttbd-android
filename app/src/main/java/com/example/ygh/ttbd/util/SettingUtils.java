package com.example.ygh.ttbd.util;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by ygh on 2016/12/5.
 */

public class SettingUtils
{
    /**
     * 打开系统网络设置界面
     *
     * @param context
     * @return
     */
    public static boolean openSttingForWlan(Context context) {
        Intent intent = null;
        // 判断手机系统的版本 即API大于10 就是3.0或以上版本
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        context.startActivity(intent);


        return true;
    }

    /**
     * 打开系统GPS设置界面
     *
     * @param context
     * @return
     */
    public static boolean openSttingForGPS(Context context) {
        boolean flagSetting = false;
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);


        } catch (ActivityNotFoundException ex) {


            intent.setAction(Settings.ACTION_SETTINGS);
            try {
                context.startActivity(intent);
                flagSetting = true;
            } catch (Exception e) {
                flagSetting = false;
            }
        }
        return flagSetting;
    }

    /**
     * 尝试帮用户直接开启GPS
     *
     * @param context
     */
    @SuppressWarnings("deprecation")
    public static void openGPS(Context context) {
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        context.sendBroadcast(intent);
        String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps")) { // if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            context.sendBroadcast(poke);
        }


    }
}
