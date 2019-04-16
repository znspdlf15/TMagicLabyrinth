package com.tWilliam.MagicLabyrinth.Game;

import android.content.Context;
import android.graphics.Color;

public class TWall extends TMap {
    private boolean isWall;

    enum wallType{
        vertical,
        horizontal
    }

    public TWall(Context context) {
        super(context);

        this.setWall(false);
        this.hideWall();
    }

    public void setWallType (wallType type){
//        if ( type == wallType.horizontal ) {
//            this.setPadding(dp_10, dp_3, dp_10, dp_3);
//        } else if ( type == wallType.vertical ){
//            this.setPadding(dp_3, dp_10, dp_3, dp_10);
//        }
    }

    public void hideWall(){
        this.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    public void showWall(){
        if ( isWall )
            this.setBackgroundColor(Color.parseColor("#ff0000"));
    }

    public void setWall(boolean wall){
        isWall = wall;
    }

    public boolean isWall(){
        return isWall;
    }
}
