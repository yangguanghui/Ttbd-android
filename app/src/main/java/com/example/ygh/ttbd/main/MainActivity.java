package com.example.ygh.ttbd.main;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ygh.ttbd.AppConst;
import com.example.ygh.ttbd.R;
import com.example.ygh.ttbd.databinding.ActivityMainBinding;
import com.example.ygh.ttbd.util.T;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public class MainActivity extends RxAppCompatActivity implements View.OnClickListener,
                                                                 MainContract.View
{
    public static final String TAG = MainActivity.class.getSimpleName();

    protected TextView mHello;

    private MainContract.Presenter mPresenter;

    private ActivityMainBinding mBinding;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);

        mPresenter = new MainPresenter(this);
        setPresenter(mPresenter);
        mPresenter.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            // permission was granted, yay! Do the
            // contacts-related task you need to do.
            Log.e(TAG, "onRequestPermissionsResult: OK");
            T.showShort(this, "permission accept");
            switch (requestCode)
            {
                case AppConst.MY_PERMISSIONS_REQUEST_READ_PHONE_STATE:
                    break;
                case AppConst.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                {
                    mPresenter.downloadApp();
                    break;
                }
                // other 'case' lines to check for other
                // permissions this app might request
            }
        }
        else
        {
            // permission denied, boo! Disable the
            // functionality that depends on this permission.
            Log.e(TAG, "onRequestPermissionsResult: CANCEL");
            T.showShort(this, "permission deny");
        }
    }

    @Override
    public void showStartPage()
    {
    }

    @Override
    public void showProgressDialog()
    {
        mProgressDialog = mPresenter.doShowProgressDialog(new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                mPresenter.cancelDownloadApp();
                dialog.dismiss();
            }
        });
    }

    @Override
    public void dismissProgressDialog()
    {
        mProgressDialog.dismiss();
    }

    @Override
    public void changeProgress(int pos)
    {
        mProgressDialog.setProgress(pos);
    }

    @Override
    public void showUpdateDialog()
    {
        mPresenter.doShowUpdateDialog(new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                showProgressDialog();
                mPresenter.downloadApp();
            }
        });

    }

    @Override
    public void showGuidePages()
    {

    }

    // 显示欢迎页
    // 获取首页数据
    // 显示首页
    String s = "http://www.weather.com.cn/data/sk/101010100.html";

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.hello)
        {
            T.showShort(MainActivity.this, "clicked me");
        }
        //        else if (view.getId() == R.id.tvVerCode)
        //        {
        //
        //        }
        //        else if (view.getId() == R.id.tvVerName)
        //        {
        //
        //        }
        //        else if (view.getId() == R.id.tvUrl)
        //        {
        //
        //        }
        //        else if (view.getId() == R.id.tvId)
        //        {
        //
        //        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter)
    {
        mPresenter = presenter;
    }
}
