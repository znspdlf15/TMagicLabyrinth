package com.tWilliam.MagicLabyrinth.Game;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TDPCalculator;

import org.w3c.dom.Text;

public class TLocation extends TMap {
    private ImageView imageView;
    private int imageId;

    public TLocation(View view) {
        super(view);
    }

    public TLocation(ImageView view, int id) {
        super(view);

        this.imageView = view;
        imageView.setBackgroundColor(Color.parseColor("#EEEEEE"));
        int dp_10 = (int)TDPCalculator.DPToPixel(10, view.getContext());
        imageView.setPadding(dp_10, dp_10, dp_10, dp_10);

        imageView.requestLayout();

        imageId = id;
        if ( id != 0 ){
            imageView.setImageResource(id);
        }
    }

    public void hideImage(){
        imageView.setImageResource(0);
    }

    @Override
    public View getView(){
        return this.imageView;
    }

    public boolean isThereImage(){
        return imageId > 0;
    }
}
