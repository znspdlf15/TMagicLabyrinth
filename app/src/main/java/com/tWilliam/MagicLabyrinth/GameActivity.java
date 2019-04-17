package com.tWilliam.MagicLabyrinth;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.View.OnTouchListener;

import com.tWilliam.MagicLabyrinth.Game.TDice;
import com.tWilliam.MagicLabyrinth.Game.TGameBoard;
import com.tWilliam.MagicLabyrinth.Game.TStatusBoard;
import com.tWilliam.MagicLabyrinth.Player.TPlayer;

public class GameActivity extends StandardActivity implements View.OnClickListener {
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView mImageView;

    private int _xDelta;
    private int _yDelta;

    private RelativeLayout backgroundLayout;
    private TGameBoard mGameBoard;
    private ConstraintLayout statusboardLayout;
    private TStatusBoard mStatusBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        backgroundLayout = findViewById(R.id.game_board);

        // game board
        mGameBoard = new TGameBoard(backgroundLayout.getContext(), 25);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        mGameBoard.setLayoutParams(layoutParams);

        TPlayer[] players = {new TPlayer(R.mipmap.soccer_player), new TPlayer(R.mipmap.magician)};
        mGameBoard.enrollPlayers(players);

        mGameBoard.setAllOnClickListner(this);
        backgroundLayout.addView(mGameBoard);

        // status board
        statusboardLayout = findViewById(R.id.game_status_board);

        mStatusBoard = new TStatusBoard(statusboardLayout.getContext());
        ConstraintSet constraintSet = new ConstraintSet();

//        constraintSet.connect(R.id.game_status_board, ConstraintSet.)
        ConstraintLayout.LayoutParams statusParams = new ConstraintLayout.LayoutParams(0, 0);
        statusParams.bottomToBottom = R.id.game_status_board;
        statusParams.leftToLeft = R.id.game_status_board;
        statusParams.rightToRight = R.id.game_status_board;
        statusParams.topToTop = R.id.game_status_board;
        mStatusBoard.setLayoutParams(statusParams);

        mStatusBoard.setAllOnClickListner(this);
        statusboardLayout.addView(mStatusBoard);
    }

    @Override
    public void onClick(View v) {
        mGameBoard.reactOnClick(v);
        mStatusBoard.reactOnClick(v);
    }
}
