package com.tWilliam.MagicLabyrinth.Game;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TDPCalculator;

public class TWall extends TMap {
    private ImageView imageView;
    private boolean isWall;

    enum wallType{
        vertical,
        horizontal
    }
    public TWall(View view) {
        super(view);

    }

    public TWall(ImageView view) {
        super(view);

        this.imageView = view;
        this.setWall(false);
        this.hideWall();
    }

    public void setWallType (wallType type){
        int dp_10 = (int)TDPCalculator.DPToPixel(10, view.getContext());
        int dp_3 = (int)TDPCalculator.DPToPixel(3, view.getContext());
//        if ( type == wallType.horizontal ) {
//            this.imageView.setPadding(dp_10, dp_3, dp_10, dp_3);
//        } else if ( type == wallType.vertical ){
//            this.imageView.setPadding(dp_3, dp_10, dp_3, dp_10);
//        }
    }

    public void hideWall(){
        this.imageView.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    public void showWall(){
        if ( isWall )
            this.imageView.setBackgroundColor(Color.parseColor("#ff0000"));
    }

    public void setWall(boolean wall){
        isWall = wall;
    }


    @Override
    public View getView(){
        return this.view;
    }

    public boolean isWall(){
        return isWall;
    }
}
