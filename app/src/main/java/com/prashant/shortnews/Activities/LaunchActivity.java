package com.prashant.shortnews.Activities;

import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.prashant.shortnews.Fragments.FragmentLaunch;
import com.prashant.shortnews.R;
import com.prashant.shortnews.Utils.CustomMethods;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class LaunchActivity extends AppCompatActivity {

    AdView adView;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    FragmentLaunch fragmentLaunch;

    public CustomMethods customMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        frameLayout = findViewById(R.id.activity_main_frame_layout);

        customMethods = new CustomMethods();

        MobileAds.initialize(this, "ca-app-pub-6896219143730837~2050686684");

        adView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        fragmentLaunch = new FragmentLaunch();
        fragmentTransaction.replace(R.id.activity_main_frame_layout, fragmentLaunch, "fragmentLaunch");
        fragmentTransaction.addToBackStack(fragmentLaunch.getClass().toString());

        fragmentTransaction.commit();





    }



}
