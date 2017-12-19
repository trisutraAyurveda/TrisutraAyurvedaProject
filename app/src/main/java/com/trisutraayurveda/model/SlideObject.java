package com.trisutraayurveda.model;

import com.trisutraayurveda.R;

/**
 * Created by Dipen Jansari on 29/11/17.
 * Copyright (c) 2017 Zebpay
 */

public enum SlideObject {
    VATA(R.layout.slide_vata),
    PITTA(R.layout.slide_pitta),
    KAPHA(R.layout.slide_kapha);

    private int mLayoutResId;

    SlideObject(int layoutResId) {
        mLayoutResId = layoutResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}
