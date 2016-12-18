package com.example.ygh.ttbd.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.ygh.ttbd.BasePresenter;
import com.example.ygh.ttbd.BaseView;

/**
 * Created by ygh on 2016/12/15.
 */

public interface MainContract
{
    interface View extends BaseView<Presenter>
    {
        void showUpdateDialog();
        void showGuidePages();
        void showStartPage();

        void showProgressDialog();
        void dismissProgressDialog();
        void changeProgress(int pos);
    }
    interface Presenter extends BasePresenter
    {
        void doFirstRun();
        void downloadApp();
        void doShowUpdateDialog(DialogInterface.OnClickListener clickListener);
        ProgressDialog doShowProgressDialog(DialogInterface.OnClickListener cancelListener);
        void cancelDownloadApp();
    }
}
