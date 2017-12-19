package com.trisutraayurveda.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.trisutraayurveda.R;
import com.trisutraayurveda.adapter.SlidePagerAdapter;
import com.trisutraayurveda.base.BaseActivity;
import com.trisutraayurveda.databinding.ActivityIntroductionBinding;

public class IntroductionActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ActivityIntroductionBinding introductionBinding;


    public static void start(Context context) {
        Intent starter = new Intent(context, IntroductionActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_introduction);
    }

    @Override
    public void initVariable() {
        introductionBinding = getBinding();
        introductionBinding.setOnClick(this);
    }

    @Override
    public void loadData() {
        introductionBinding.viewPager.setAdapter(new SlidePagerAdapter(this));
        introductionBinding.indicator.setViewPager(introductionBinding.viewPager);
        introductionBinding.viewPager.setOffscreenPageLimit(2);
        introductionBinding.viewPager.addOnPageChangeListener(this);
        introductionBinding.btnStart.setTag(0);
    }

    @Override
    public void onBackPressed() {
        if (introductionBinding.viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            introductionBinding.viewPager.setCurrentItem(introductionBinding.viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onClick(View v) {
        int pos = (int) introductionBinding.btnStart.getTag();
        if (pos == 0) {
            introductionBinding.btnStart.setText(getString(R.string.next));
            introductionBinding.viewPager.setCurrentItem(1, true);
        } else if (pos == 1) {
            introductionBinding.btnStart.setText(getString(R.string.next));
            introductionBinding.viewPager.setCurrentItem(2, true);
        } else {
            introductionBinding.btnStart.setText(getString(R.string.start_test));
            RegistrationActivity.start(this);
        }
    }

    @Override
    public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {

        switch (pos) {
            case 0:
                introductionBinding.btnStart.setText(getString(R.string.next));
                break;

            case 1:
                introductionBinding.btnStart.setText(getString(R.string.next));
                break;

            case 2:
                introductionBinding.btnStart.setText(getString(R.string.start_test));
                break;
        }

    }

    @Override
    public void onPageSelected(int position) {
        introductionBinding.btnStart.setTag(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


}
