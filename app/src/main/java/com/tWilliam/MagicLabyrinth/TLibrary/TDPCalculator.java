package com.tWilliam.MagicLabyrinth.TLibrary;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class TDPCalculator {
    static public float PixelToDP(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        return px / (metrics.densityDpi / 160f);
    }
    static public float DPToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        return dp * (metrics.densityDpi / 160f);
    }
}
