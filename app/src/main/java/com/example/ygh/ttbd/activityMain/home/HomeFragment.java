package com.example.ygh.ttbd.activityMain.home;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.ygh.ttbd.ImageHolderView;
import com.example.ygh.ttbd.R;
import com.example.ygh.ttbd.activityMain.home.tab.HomeTabFragment;
import com.example.ygh.ttbd.activityMain.home.tab.HomeTabFragmentPagerAdapter;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends RxFragment implements View.OnClickListener,
                                                        HomeContract.view
{
    protected View                   rootView;
    protected ConvenientBanner       mBannerHome;
    protected SearchView             mSearchViewHome;
    protected TabLayout              mHomeTab;
    protected ViewPager              mHomeViewPager;
    protected NestedScrollView       mHomeTabScrollView;
    private   HomeContract.Presenter mPresenter;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initImageLoader();
        mPresenter = new HomePresenter(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mPresenter.start();
        mBannerHome.startTurning(3000);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mBannerHome.stopTurning();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home1, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.bannerHome)
        {

        }
        else if (view.getId() == R.id.searchViewHome)
        {

        }
    }

    private void initView(View rootView)
    {
        mBannerHome = (ConvenientBanner) rootView.findViewById(R.id.bannerHome);
        mBannerHome.setOnClickListener(HomeFragment.this);
        mSearchViewHome = (SearchView) rootView.findViewById(R.id.searchViewHome);
        mSearchViewHome.setOnClickListener(HomeFragment.this);
        mHomeTab = (TabLayout) rootView.findViewById(R.id.homeTab);
        mHomeTabScrollView = (NestedScrollView) rootView.findViewById(R.id.homeTabScrollView);
        mHomeViewPager = (ViewPager) rootView.findViewById(R.id.homeViewPager);
        mFragments.add(HomeTabFragment.newInstance("News"));
        mFragments.add(HomeTabFragment.newInstance("Notice"));
        mFragments.add(HomeTabFragment.newInstance("Faculty"));
        HomeTabFragmentPagerAdapter homeTabFragmentPagerAdapter = new
                HomeTabFragmentPagerAdapter(
                getChildFragmentManager(),
                mFragments);
        mHomeViewPager.setAdapter(homeTabFragmentPagerAdapter);
        mHomeTab.setTabMode(TabLayout.MODE_FIXED);
        mHomeTab.setupWithViewPager(mHomeViewPager);
        mHomeTab.addOnTabSelectedListener(mOntabSelectedListenner);
    }

    TabLayout.OnTabSelectedListener mOntabSelectedListenner = new TabLayout
            .OnTabSelectedListener()
    {
        @Override
        public void onTabSelected(TabLayout.Tab tab)
        {
            int id = tab.getPosition();
            int y = ((HomeTabFragment) mFragments.get(id)).getCurrentY();
            mHomeTabScrollView.scrollTo(0, y);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab)
        {
            int id = tab.getPosition();
            int y = mHomeTabScrollView.getScrollY();
            ((HomeTabFragment) mFragments.get(id)).setCurrentY(y);
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab)
        {
        }
    };

    //初始化网络图片缓存库
    private void initImageLoader()
    {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_default_adimage)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getContext())
                .defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void showBanner(List<String> bannerUrls)
    {
        mBannerHome.setPages(new CBViewHolderCreator<ImageHolderView>()
        {
            @Override
            public ImageHolderView createHolder()
            {
                return new ImageHolderView();
            }
        }, bannerUrls);
    }

    @Override
    public void reloadPage()
    {

    }

    @Override
    public void showSearchResult(String keyword)
    {

    }

    @Override
    public void showBannerClickPage(Uri uri)
    {

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter)
    {
        this.mPresenter = presenter;
    }
}
