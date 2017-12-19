package com.trisutraayurveda.utility;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.trisutraayurveda.base.BaseActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.support.graphics.drawable.VectorDrawableCompat.create;

/**
 * Created by dhaval on 19/4/16.
 */
public class Util {

    /**
     * @param mContext
     * @param layout
     * @param fragment
     * @param isBackstage
     * @param isAnimation
     * @param TAG
     */
    public static void changeFragment(Context mContext, int layout, Fragment fragment, boolean isBackstage, boolean isAnimation, String TAG) {
        ((BaseActivity) mContext).changeFragment(layout, fragment, isBackstage, isAnimation, TAG);
    }

    public static void startNewActivity(Context context, Intent intent) {
        ((BaseActivity) context).startNewActivity(intent);
    }

    public static void showSnackbar(View view, String text) {
        if (text.trim().isEmpty() || view == null) return;

        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    /**
     * @param context
     * @param resourceId
     */
    public static Drawable getDrawable(Context context, int resourceId) {
        Drawable drawable = null;
        try {
            drawable = create(context.getResources(), resourceId, context.getTheme());
        } catch (Exception e) {
            drawable = ContextCompat.getDrawable(context, resourceId);
        }
        return drawable;
    }

    /**
     * @param context
     * @param colorCode
     * @return
     */
    public static int getColor(Context context, int colorCode) {
        return ContextCompat.getColor(context, colorCode);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        return isConnected;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(Activity activity) {

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    public static void drawableRight(TextView textView, int resourceId) {
        Drawable drawable = create(textView.getResources(), resourceId, textView.getContext().getTheme());
        Drawable[] drawables = textView.getCompoundDrawables();
        textView.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    public static void setVectorImage(ImageView imageView, int resourceId) {
        Drawable drawable = create(imageView.getResources(), resourceId, imageView.getContext().getTheme());
        imageView.setImageDrawable(drawable);
    }


    public static void drawableLeft(TextView textView, int resourceId) {

        Drawable drawable = create(textView.getResources(), resourceId, textView.getContext().getTheme());
        Drawable[] drawables = textView.getCompoundDrawables();
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable, drawables[1], drawables[2], drawables[3]);
    }

    public static boolean isGPSEnable(Context context) {
        boolean status;

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // getting network status
        status = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (status) return true;

        // getting GPS status
        status = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return status;
    }

    public static int getAppVersionCode(Context context) {
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
        return pInfo.versionCode;

    }

    public static String getVersionName(Context context) {
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pInfo.versionName;
    }


    public static void getHashKey(Context mContext) {
        PackageInfo info;
        try {
            info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    /**
     * @param tag
     * @return
     */
    public Fragment findFragmentByTag(FragmentManager fragmentManager, String tag) {
        return fragmentManager.findFragmentByTag(tag);
    }
}