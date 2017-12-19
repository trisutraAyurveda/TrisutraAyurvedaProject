package com.trisutraayurveda.activity;

import android.os.Bundle;

import com.trisutraayurveda.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setView(R.layout.activity_splash);
        IntroductionActivity.start(this);
    }

    @Override
    public void initVariable() {

    }

    @Override
    public void loadData() {

    }
}
