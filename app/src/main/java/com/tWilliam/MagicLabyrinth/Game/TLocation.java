package com.tWilliam.MagicLabyrinth.Game;

import android.content.Context;
import android.graphics.Color;

import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TDPCalculator;

public class TLocation extends TMap {
    private boolean isItem;

    enum highlightType {
        normal,
        movable
    }
    public TLocation(Context context) {
        super(context);
    }

    public TLocation(Context context, int id) {
        super(context);

        int dp_10 = (int)TDPCalculator.DPToPixel(10, this.getContext());
        this.setPadding(dp_10, dp_10, dp_10, dp_10);

        this.requestLayout();
        this.setImageResource(id);
        this.setBackgroundResource(R.drawable.original_location);

        if ( id != 0 )
            this.isItem = true;
        else
            this.isItem = false;

    }

    public void hideImage(){
        this.setAlpha((float)0);
    }

    public boolean isThereImage(){
        return this.getId() != 0;
    }

    public boolean isItem(){
        return this.isItem;
    }

    public void setHighlight(highlightType type){
        if ( type == highlightType.normal ){
            this.setBackgroundResource(R.drawable.original_location);
        } else if ( type == highlightType.movable ){
            this.setBackgroundResource(R.drawable.movable_location);
        }
    }

    public void onTarget(boolean on){

    }
}
