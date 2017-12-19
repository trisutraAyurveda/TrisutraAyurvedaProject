package com.trisutraayurveda.base;


import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.trisutraayurveda.R;
import com.trisutraayurveda.utility.AnimatorClass;
import com.trisutraayurveda.utility.Logg;
import com.trisutraayurveda.utility.Util;

public abstract class BaseActivity extends AppCompatActivity implements ActImpMethods {

    public static final String TAG = BaseActivity.class.getSimpleName();
    protected Toolbar toolbar;
    protected ViewDataBinding binding;
    private Dialog dialog = null;

    protected void setView(int layoutResId) {
        binding = DataBindingUtil.setContentView(this, layoutResId);
        try {
            initVariable();
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
            Util.showToast(BaseActivity.this, e.toString());
            Logg.e(TAG, e.toString());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

    }


    protected <T extends ViewDataBinding> T getBinding() {
        return (T) binding;
    }

    protected void setToolbarcolor(int toolbarcolor) {
        toolbar.setBackgroundColor(toolbarcolor);
    }

    protected void setToolbarDrawable(Drawable toolbarDrawable) {
        toolbar.setNavigationIcon(toolbarDrawable);
    }

    /**
     * @param toolbar
     * @param title
     * @param isBackEnabled
     */
    protected void setToolbar(Toolbar toolbar, String title, boolean isBackEnabled) {
        this.toolbar = toolbar;
        super.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitle(title);

        if (isBackEnabled) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    /**
     * @param toolbar
     */
    protected void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        super.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    protected void setToolbarWithText(Toolbar toolbar, String title, String subTitle, boolean isBackEnabled) {
        this.toolbar = toolbar;
        super.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitle(title);
        toolbar.setSubtitle(subTitle);

        if (isBackEnabled) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    public void setToolbarTextColor(int color) {
        toolbar.setTitleTextColor(color);
    }

    public void setToolbarSubTitleTextColor(int color) {
        toolbar.setSubtitleTextColor(color);
    }

    /**
     * @param layout      Layout ID
     * @param fragment    Fragment
     * @param isBaskStack Boolean True or false_img
     * @param isAnimation required animations or not
     * @param TAG         Fragment Tag
     */
    public void changeFragment(int layout, Fragment fragment, boolean isBaskStack, boolean isAnimation, String TAG) {
        //hide keyboard when fragment change
        Util.hideKeyboard(this);
        if (isAnimation) {
            if (!isBaskStack) {
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(layout, fragment, TAG).commit();
            } else {
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(layout, fragment, TAG).addToBackStack(null).commit();
            }
        } else {
            if (!isBaskStack) {
                getSupportFragmentManager().beginTransaction().replace(layout, fragment, TAG).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(layout, fragment, TAG).addToBackStack(null).commit();
            }
        }
    }

    /**
     * use startNewActivity instade of startActivity();
     *
     * @param intent
     */
    public void startNewActivity(Intent intent) {
        //hide keyboard when activity change
        Util.hideKeyboard(BaseActivity.this);
        startActivity(intent);
        AnimatorClass.appearLeftAnimation(BaseActivity.this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*hide keyboard when activity change*/
        Util.hideKeyboard(BaseActivity.this);
        //apply animations
        AnimatorClass.disappearLeftAnimation(BaseActivity.this);
    }

    /**
     * @param
     */
    /*public void displayCustomProgressDialog(boolean isCancelable) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_progress_dialog);
        dialog.setCancelable(isCancelable);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }
    public void closeCustomProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }*/

    @Override
    protected void onStop() {
        //closeCustomProgressDialog();
        super.onStop();
    }

    public void setToolBarTitle(String title) {
        toolbar.setTitle(title);
    }

    public void setToolBarSubTitle(String title) {
        toolbar.setSubtitle(title);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        //Change the color of backstack app bar color
        /*ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(getString(R.string.app_name),null,R.color.white);
        (this).setTaskDescription(taskDescription);*/
    }
}
