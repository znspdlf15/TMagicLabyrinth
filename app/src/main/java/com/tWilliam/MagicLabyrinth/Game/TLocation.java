package com.tWilliam.MagicLabyrinth.Game;

import android.view.View;
import android.widget.TextView;

public class TLocation extends TDraw {
    public TLocation(View view) {
        super(view);

        ((TextView)view).setText("칸");
    }

    @Override
    public View getView(){
        return null;
    }
}
