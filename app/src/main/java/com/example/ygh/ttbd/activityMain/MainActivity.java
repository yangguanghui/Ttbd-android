package com.example.ygh.ttbd.activityMain;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.ygh.ttbd.AppConst;
import com.example.ygh.ttbd.R;
import com.example.ygh.ttbd.databinding.ActivityMainBinding;
import com.example.ygh.ttbd.activityMain.home.HomeFragment;
import com.example.ygh.ttbd.util.T;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends RxAppCompatActivity implements View.OnClickListener,
                                                                 MainContract.View,
                                                                 BottomNavigationBar
                                                                         .OnTabSelectedListener
{
    public static final String TAG = MainActivity.class.getSimpleName();

    protected TextView mHello;

    @BindView(R.id.container_main)
    FrameLayout         mContainerMain;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private MainContract.Presenter mPresenter;

    private ActivityMainBinding mBinding;

    private ProgressDialog mProgressDialog;

    private HomeFragment mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        super.setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);
        setPresenter(mPresenter);
        mPresenter.start();

        ButterKnife.bind(this);

        initView();

    }

    private void initBottomNavigationBar()
    {

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(android.R.drawable.sym_def_app_icon, "HOME"))
                .addItem(new BottomNavigationItem(android.R.drawable.sym_def_app_icon, "HOME"))
                .addItem(new BottomNavigationItem(android.R.drawable.sym_def_app_icon, "HOME"))
                .addItem(new BottomNavigationItem(android.R.drawable.sym_def_app_icon, "home"))
                .setFirstSelectedPosition(0).setTabSelectedListener(this)
                .initialise();
        this.onTabSelected(0);
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
//        startActivity(new Intent(this, GuideActivity.class));
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter)
    {
        mPresenter = presenter;
    }

    private void initView()
    {
        initBottomNavigationBar();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v)
    {

    }

    /**
     * Called when a tab enters the selected state.
     *
     * @param position The position of the tab that was selected
     */
    @Override
    public void onTabSelected(int position)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (position)
        {
            case 0:
                if (null == mHomeFragment)
                {
                    mHomeFragment = new HomeFragment();
                }
                fragmentTransaction.replace(R.id.container_main, mHomeFragment);
                break;
            case 1:
                if (null == mHomeFragment)
                {
                    mHomeFragment = new HomeFragment();
                }
                fragmentTransaction.replace(R.id.container_main, mHomeFragment);
                break;
            case 2:
                if (null == mHomeFragment)
                {
                    mHomeFragment = new HomeFragment();
                }
                fragmentTransaction.replace(R.id.container_main, mHomeFragment);
                break;
            case 3:
                if (null == mHomeFragment)
                {
                    mHomeFragment = new HomeFragment();
                }
                fragmentTransaction.replace(R.id.container_main, mHomeFragment);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    /**
     * Called when a tab exits the selected state.
     *
     * @param position The position of the tab that was unselected
     */
    @Override
    public void onTabUnselected(int position)
    {

    }

    /**
     * Called when a tab that is already selected is chosen again by the user. Some applications
     * may use this action to return to the top level of a category.
     *
     * @param position The position of the tab that was reselected.
     */
    @Override
    public void onTabReselected(int position)
    {

    }
}
