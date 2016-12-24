package com.example.ygh.ttbd.activityMain;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.ygh.ttbd.Api;
import com.example.ygh.ttbd.data.ApiReturn;
import com.example.ygh.ttbd.AppConst;
import com.example.ygh.ttbd.data.ViewModel.VersionData;
import com.example.ygh.ttbd.util.ApkUtils;
import com.example.ygh.ttbd.util.NetConnectUtil;
import com.example.ygh.ttbd.util.PreferencesUtils;
import com.example.ygh.ttbd.util.T;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

import static android.content.ContentValues.TAG;
import static android.content.Context.DOWNLOAD_SERVICE;
import static com.example.ygh.ttbd.activityMain.MainUtil.startDownloadApp;

/**
 * Created by ygh on 2016/12/15.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mMainView;
    private final Context mContext;
    private final RxAppCompatActivity mActivity;
    private long mTaskDownload;

    private VersionData mVersionData;

    public MainPresenter(MainContract.View mainView) {
        mMainView = mainView;
        mContext = (Context) mainView;
        mActivity = (RxAppCompatActivity) mainView;
    }

    @Override
    public void doFirstRun() {
        if (NetConnectUtil.isNetConnected(mContext)) {
            doInstallFirst(); // 执行首次安装的任务

            doInstallUpdate(); // 执行安装完更新的任务

            doUpdate(); // 执行更新任务
        }

        doShowGuidePages();
        mMainView.showGuidePages();
    }

    private void doShowGuidePages() {
        if (!isVersionEqual()) {
            putVersionCodeEqual();
            // 显示引导页
            mMainView.showGuidePages();
        }
    }


    @Override
    public void start() {
        doFirstRun();
        //  显示开始页
        mMainView.showStartPage();
    }

    private void doInstallUpdate() {
        if (!isVersionServerEqual()) {
            // 获取App和系统信息
            Map<String, String> map = MainUtil.getInstallData(mContext, false);

            // 告诉服务器客户端已更新
            Api.simpleApi(Api.installUpdate(map),
                    mActivity.<ApiReturn<Object>>bindToLifecycle())
                    .subscribe(
                            new Action1<ApiReturn<Object>>() {
                                @Override
                                public void call(ApiReturn<Object> objectApiReturn) {
                                    // 保存更新后的版本号
                                    putVersionCodeServerEqual();
                                    Log.e(TAG, "Install updated ok");
                                }
                            },
                            new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    Log
                                            .e(TAG,
                                                    "Install update error. " + throwable.getMessage());
                                }
                            });
        }
    }

    private boolean isVersionEqual() {
        // 获取版本信息
        int spVersionCode = PreferencesUtils.getInt(mContext, AppConst.VERSION_CODE);
        int versionCode = ApkUtils.getVersionCode(mContext);

        return spVersionCode == versionCode;
    }

    private void putVersionCodeEqual() {
        int versionCode = ApkUtils.getVersionCode(mContext);
        PreferencesUtils.putInt(mContext, AppConst.VERSION_CODE, versionCode);
    }

    private void putVersionCodeServerEqual() {
        int versionCode = ApkUtils.getVersionCode(mContext);
        PreferencesUtils.putInt(mContext, AppConst.VERSION_CODE_SERVER, versionCode);
    }

    private boolean isVersionServerEqual() {
        // 获取版本信息
        int spVersionCode = PreferencesUtils
                .getInt(mContext, AppConst.VERSION_CODE_SERVER);
        int versionCode = ApkUtils.getVersionCode(mContext);

        return spVersionCode == versionCode;
    }

    private void doInstallFirst() {
        if (!PreferencesUtils.contains(mContext, AppConst.VERSION_CODE_SERVER)) {
            // 获取App和系统信息
            Map<String, String> map = MainUtil.getInstallData(mContext, true);

            // 告诉服务器客户端首次安装
            Api.simpleApi(Api.installFirst(map), mActivity.<ApiReturn<Object>>bindToLifecycle())
                    .subscribe(
                            new Action1<ApiReturn<Object>>() {
                                @Override
                                public void call(ApiReturn<Object> objectApiReturn) {
                                    // 设置版本号
                                    putVersionCodeServerEqual();
                                    Log.e(TAG, "Install first ok");
                                }
                            },
                            new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    Log
                                            .e(TAG,
                                                    "Install first error. " + throwable.getMessage());
                                }
                            });

        }
    }

    @Override
    public void doShowUpdateDialog(DialogInterface.OnClickListener clickListener) {
        MainUtil.ShowUpdateDialog(mContext, mVersionData.getVer_name(), clickListener);
    }

    @Override
    public ProgressDialog doShowProgressDialog(DialogInterface.OnClickListener cancelListener) {
        return MainUtil.ShowProgressDialog(mContext, cancelListener);
    }

    private void doUpdate() {
        // 检查服务器最新版本
        Api.simpleApi(Api.getVersion(), mActivity.<ApiReturn<VersionData>>bindToLifecycle())
                .subscribe(new Action1<ApiReturn<VersionData>>() {
                               @Override
                               public void call(ApiReturn<VersionData> versionDataApiReturn) {
                                   mVersionData = versionDataApiReturn.data;

                                   int versionCode = ApkUtils.getVersionCode(mContext);
                                   int ver_code = versionDataApiReturn.data.getVer_code();
                                   if (ver_code > versionCode) {
                                       mMainView.showUpdateDialog();
                                   }
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                T.showShort(mContext, throwable.getMessage());
                            }
                        });
    }


    // 显示欢迎页
    // 获取首页数据
    // 显示首页
    private String s = "http://www.weather.com.cn/data/sk/101010100.html";

    private int mProgressOldPos;

    @Override
    public void downloadApp() {
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                    Manifest.permission
                            .WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                return;
            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(mActivity,
                        new String[]{Manifest.permission
                                .WRITE_EXTERNAL_STORAGE},
                        AppConst.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                // MY_PERMISSIONS_REQUEST_READ_READ_PHONE_STATE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {

        }

        mTaskDownload = startDownloadApp(mActivity, mVersionData.getUrl(),
                new DownloadReceiver());

        final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int pos = MainUtil.queryDownload(mActivity, mTaskDownload);
                if (pos > mProgressOldPos) {
                    mMainView.changeProgress(pos);
                    mProgressOldPos = pos;
                }
                if (pos == 100) {
                    ses.shutdown();
                    mMainView.dismissProgressDialog();
                }
                Log.e(TAG, "run: queryDownload:" + pos);
            }
        }, 0, 2, TimeUnit.SECONDS);

    }

    @Override
    public void cancelDownloadApp() {
        DownloadManager downloadManager = (DownloadManager) mActivity
                .getSystemService(DOWNLOAD_SERVICE);
        downloadManager.remove(mTaskDownload);
        T.showLong(mContext, "cancelled download");
    }

    class DownloadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1) == mTaskDownload) {
                if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                    DownloadManager downloadManager = (DownloadManager) context
                            .getSystemService(DOWNLOAD_SERVICE);
                    Uri uriForDownloadedFile = downloadManager.getUriForDownloadedFile(mTaskDownload);
                    if (uriForDownloadedFile != null) {
                        Toast.makeText(mContext, "升级文件下载已经完成！", Toast.LENGTH_SHORT)
                                .show();
                        ApkUtils.installAPK(context, uriForDownloadedFile, null);
                    }
                } else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
//                    Toast.makeText(mContext, "别瞎点！！！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
