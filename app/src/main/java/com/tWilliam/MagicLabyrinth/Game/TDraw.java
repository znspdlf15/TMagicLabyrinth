package com.tWilliam.MagicLabyrinth.Game;

import android.view.View;

public abstract class TDraw {
    View view;
    public TDraw(View view){
        this.view = view;
    }

    public abstract View getView();
}
