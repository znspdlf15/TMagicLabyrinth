package com.tWilliam.MagicLabyrinth.Game;

import android.view.View;
import android.widget.TextView;

public class TWall extends TDraw {
    public TWall(View view) {
        super(view);

        ((TextView)view).setText("벽");
    }

    @Override
    public View getView(){
        return null;
    }
}
