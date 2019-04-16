package com.tWilliam.MagicLabyrinth;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.View.OnTouchListener;

import com.tWilliam.MagicLabyrinth.Game.TGameBoard;
import com.tWilliam.MagicLabyrinth.Player.TPlayer;

public class GameActivity extends StandardActivity implements View.OnClickListener {
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView mImageView;

    private int _xDelta;
    private int _yDelta;

    private RelativeLayout background;
    private TGameBoard mGameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        background = findViewById(R.id.game_background);

        mGameBoard = new TGameBoard(background, 25);
        View vGameBoard = mGameBoard.getView();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        vGameBoard.setLayoutParams(layoutParams);

        TPlayer[] players = {new TPlayer(R.mipmap.soccer_player), new TPlayer(R.mipmap.magician)};

        mGameBoard.enrollPlayers(players);
        mGameBoard.setAllOnClickListner(this);
        background.addView(vGameBoard);
    }

    @Override
    public void onClick(View v) {
        mGameBoard.reactOnClick(v);
    }
}
