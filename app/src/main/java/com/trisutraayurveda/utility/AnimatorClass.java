package com.trisutraayurveda.utility;

import android.app.Activity;

import com.trisutraayurveda.R;


public class AnimatorClass {

    public static void appearLeftAnimation(Activity a) {
        a.overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);
    }

    public static void disappearLeftAnimation(Activity a) {
        a.overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }





}