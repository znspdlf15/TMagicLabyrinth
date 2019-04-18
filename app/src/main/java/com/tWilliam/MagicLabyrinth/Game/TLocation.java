package com.tWilliam.MagicLabyrinth.Game;

import android.content.Context;
import android.graphics.Color;

import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TDPCalculator;

public class TLocation extends TMap {
    private boolean isItem;
    private int imageId;

    enum highlightType {
        normal,
        movable,
        target
    }
    highlightType type = highlightType.normal;
    public TLocation(Context context) {
        super(context);
    }

    public TLocation(Context context, int id) {
        super(context);

        int dp_10 = (int)TDPCalculator.DPToPixel(10, this.getContext());
        this.setPadding(dp_10, dp_10, dp_10, dp_10);

        this.requestLayout();
        this.imageId = id;
        this.setImageResource(this.imageId);
        this.setBackgroundResource(R.drawable.original_location);
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

    public void setItem(boolean b){
        this.isItem = b;
    }

    public boolean isTarget() {
        return this.type == highlightType.target;
    }

    public void setHighlight(highlightType type){
        this.type = type;
        if ( type == highlightType.normal ){
            this.setBackgroundResource(R.drawable.original_location);
        } else if ( type == highlightType.movable ){
            this.setBackgroundResource(R.drawable.movable_location);
        } else if ( type == highlightType.target ){
            this.setBackgroundResource(R.drawable.target_location);
        }
    }

    public int getImageId(){
        return this.imageId;
    }
}
