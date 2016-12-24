package com.example.ygh.ttbd.activityDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.ygh.ttbd.R;
import com.example.ygh.ttbd.activityDetail.news.NewsFragment;

public class DetailActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fm.beginTransaction();
        Intent intent = getIntent();
        switch (intent.getStringExtra("title"))
        {
            case "News":
                ft.replace(R.id.container_detail, new NewsFragment());
                break;
            case "Notice":
                break;
            default:
                break;
        }
        ft.commit();
    }

}
