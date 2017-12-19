package com.trisutraayurveda.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.trisutraayurveda.R;
import com.trisutraayurveda.base.BaseActivity;
import com.trisutraayurveda.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener {

    private ActivityRegistrationBinding registrationBinding;

    public static void start(Context context) {
        Intent starter = new Intent(context, RegistrationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_registration);
    }

    @Override
    public void initVariable() {
        registrationBinding = getBinding();
        registrationBinding.setOnClick(this);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                LoadingActivity.start(this, "Please Wait");
                break;
        }
    }
}
