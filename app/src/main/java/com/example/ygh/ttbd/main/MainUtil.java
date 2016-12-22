package com.example.ygh.ttbd.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.example.ygh.ttbd.AppConst;
import com.example.ygh.ttbd.BuildConfig;
import com.example.ygh.ttbd.data.post.Install;
import com.example.ygh.ttbd.util.ApkUtils;
import com.example.ygh.ttbd.util.MapUtils;
import com.example.ygh.ttbd.util.SystemUtils;

import java.util.Map;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by ygh on 2016/12/18.
 */

class MainUtil
{
    static ProgressDialog ShowProgressDialog(Context context,
                                             ProgressDialog.OnClickListener cancelListener)
    {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIcon(android.R.drawable.ic_menu_rotate);
        progressDialog.setTitle("下载中...");
        progressDialog.setMessage("");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置进度条对话框//样式（水平，旋转）
        progressDialog.setMax(100);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", cancelListener);
        progressDialog.show();
        return progressDialog;
    }

    static AlertDialog ShowUpdateDialog(Context context, String verName,
                                        DialogInterface.OnClickListener clickListener)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("发现新版本");
        builder.setMessage("请升级APP至版本" + verName);
        builder.setCancelable(true);

        builder.setPositiveButton("确定", clickListener);
        builder.setNegativeButton("下次再说", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return alertDialog;
    }

    static Map<String, String> getInstallData(Context context, boolean isFirstInstall)
    {
        int ver_code = ApkUtils.getVersionCode(context);
        String ver_name = ApkUtils.getVersionName(context);
        Install data = new Install();
        data.device_id = SystemUtils.getDeviceId(context);
        data.is_first = isFirstInstall ? 1 : 0;
        data.os_type = 0;
        data.product = Build.PRODUCT;
        data.sdk_ver = Build.VERSION.SDK_INT;
        data.ver_code = ver_code;
        data.ver_name = ver_name;
        return MapUtils.classToMap(data);
    }

    static long startDownloadApp(Activity activity, String url,
                                 MainPresenter.DownloadReceiver receiver)
    {
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        filter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        activity.registerReceiver(receiver, filter);

        DownloadManager downloadManager = (DownloadManager) activity.getSystemService(
                DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(activity.getApplicationInfo().name);
        request.setDescription("APP下载中...");
        request.setMimeType(AppConst.MIME_TYPE_APK);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                                                  BuildConfig.APPLICATION_ID + ".apk");
        return downloadManager.enqueue(request);
    }

    static int queryDownload(Activity activity, long id)
    {
        DownloadManager downloadManager = (DownloadManager) activity
                .getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(id);
        Cursor cursor = downloadManager.query(query);
        cursor.moveToLast();
        //        cursor.moveToFirst();
        //        cursor.moveToNext();
        int total_size = cursor
                .getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
        int so_far_size = cursor.getInt(cursor.getColumnIndex(
                DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
        Log.e(activity.toString(), "queryDownload: " + total_size + " | " + so_far_size);
        int pos = (int)(100 * ((float)so_far_size / (float) total_size));
        if (cursor.getInt(
                cursor.getColumnIndex(
                        DownloadManager.COLUMN_STATUS)) == DownloadManager
                .STATUS_SUCCESSFUL)
        {
            pos = 100;
        }
        cursor.close();
        return pos;
    }

}
