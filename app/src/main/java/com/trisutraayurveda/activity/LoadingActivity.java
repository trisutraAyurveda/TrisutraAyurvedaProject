package com.trisutraayurveda.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.trisutraayurveda.R;
import com.trisutraayurveda.base.BaseActivity;
import com.trisutraayurveda.databinding.ActivityLoadingBinding;


public class LoadingActivity extends BaseActivity {

    private ActivityLoadingBinding loadingBinding;

    public static void start(Context context, String message) {
        Intent starter = new Intent(context, LoadingActivity.class);
        starter.putExtra("message", message);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_loading);
    }

    @Override
    public void initVariable() {
        loadingBinding = getBinding();
    }

    @Override
    public void loadData() {
        loadingBinding.dotProgress.setVisibility(View.VISIBLE);
    }
}
