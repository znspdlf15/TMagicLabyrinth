package com.tWilliam.MagicLabyrinth;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.tWilliam.MagicLabyrinth.Game.TGameBoard;
import com.tWilliam.MagicLabyrinth.Game.TStatusBoard;
import com.tWilliam.MagicLabyrinth.Player.TPlayer;
import com.tWilliam.MagicLabyrinth.TLibrary.TActivityConstant;
import com.tWilliam.MagicLabyrinth.TLibrary.TIntentCode;

public class GameActivity extends StandardActivity implements View.OnClickListener {

    private RelativeLayout backgroundLayout;
    private TGameBoard mGameBoard;

    private ConstraintLayout statusboardLayout;
    private TStatusBoard mStatusBoard;
    private final int statusBoardId = 100001;
    private int goalScore = 5;
    private int wallNumber = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        backgroundLayout = findViewById(R.id.game_board);

        // game board
        mGameBoard = new TGameBoard(backgroundLayout.getContext(), this.wallNumber, this.goalScore);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        mGameBoard.setLayoutParams(layoutParams);

        mGameBoard.setAllOnClickListener(this);
        backgroundLayout.addView(mGameBoard);

        // status board
        statusboardLayout = findViewById(R.id.game_status_board);
        mStatusBoard = new TStatusBoard(statusboardLayout.getContext(), statusBoardId, mGameBoard);

        statusboardLayout.addView(mStatusBoard);
        ConstraintSet c = new ConstraintSet();
        c.clone(statusboardLayout);

        c.constrainWidth(statusBoardId, ConstraintSet.MATCH_CONSTRAINT);
        c.constrainHeight(statusBoardId, ConstraintSet.MATCH_CONSTRAINT);
        c.connect(statusBoardId, ConstraintSet.LEFT, R.id.game_status_board, ConstraintSet.LEFT, 0);
        c.connect(statusBoardId, ConstraintSet.RIGHT, R.id.game_status_board, ConstraintSet.RIGHT, 0);
        c.connect(statusBoardId, ConstraintSet.TOP, R.id.game_status_board, ConstraintSet.TOP, 0);
        c.connect(statusBoardId, ConstraintSet.BOTTOM, R.id.game_status_board, ConstraintSet.BOTTOM, 0);
        c.applyTo(statusboardLayout);

        mStatusBoard.setAllOnClickListener(this);

        TPlayer[] players = {new TPlayer(R.mipmap.soccer_player), new TPlayer(R.mipmap.magician)};
        mGameBoard.enrollPlayers(players);
        mStatusBoard.enrollPlayers(players);

        mGameBoard.setStatusBoard(mStatusBoard);
    }

    @Override
    public void onClick(View v) {
        TActivityConstant.ActivityReactType type;

        type = mGameBoard.reactOnClick(v);
        mStatusBoard.reactOnClick(v);

        if ( TActivityConstant.ActivityReactType.DESTROY == type ){
            if ( mGameBoard.getWinner() != null ){
                int imageId = mGameBoard.getWinner().getImageId();
                Intent intent = new Intent(this, WinPopUpActivity.class);
                intent.putExtra("imageId", imageId);
                startActivityForResult(intent, TIntentCode.WINNER_ACTIVITY_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == TIntentCode.WINNER_ACTIVITY_CODE ){
            if( resultCode == RESULT_OK ){
                finish();
            }
        }
    }
}
