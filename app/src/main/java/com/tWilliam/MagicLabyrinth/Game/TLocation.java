package com.tWilliam.MagicLabyrinth.Game;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.tWilliam.MagicLabyrinth.TLibrary.TDPCalculator;

public class TLocation extends TMap {
    public TLocation(Context context) {
        super(context);
    }

    public TLocation(Context context, int id) {
        super(context);

        int dp_10 = (int)TDPCalculator.DPToPixel(10, this.getContext());
        this.setPadding(dp_10, dp_10, dp_10, dp_10);
        this.setBackgroundColor(Color.parseColor("#eeeeee"));

        this.requestLayout();

        this.setImageResource(id);
    }

    public void hideImage(){
        this.setAlpha((float)0);
    }

    public boolean isThereImage(){
        return this.getId() != 0;
    }
}
