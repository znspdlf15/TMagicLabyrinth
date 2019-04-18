package com.tWilliam.MagicLabyrinth.Game;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TConstant;

import java.util.Random;

public class TStatusBoard extends ConstraintLayout {
    private TDice mDice;
    private TGameBoard gameBoard;
    private final int diceId = 1030;

    public TStatusBoard(Context context, int statusBoardId) {
        super(context);

        this.setId(statusBoardId);
        this.setBackgroundResource(R.drawable.target_location);

        mDice = new TDice(context);
        mDice.setId(diceId);
        mDice.setImageResource(R.mipmap.dice_1_b);
        this.addView(mDice);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);

        constraintSet.constrainWidth(diceId, 200);
        constraintSet.constrainHeight(diceId, 200);

        constraintSet.connect(diceId, ConstraintSet.LEFT, statusBoardId, ConstraintSet.LEFT);
        constraintSet.connect(diceId, ConstraintSet.RIGHT, statusBoardId, ConstraintSet.RIGHT);
        constraintSet.connect(diceId, ConstraintSet.TOP, statusBoardId, ConstraintSet.TOP);
        constraintSet.connect(diceId, ConstraintSet.BOTTOM, statusBoardId, ConstraintSet.BOTTOM);
        constraintSet.setVerticalBias(diceId, (float)0.2);
        constraintSet.applyTo(this);

    }

    public void setAllOnClickListner(View.OnClickListener listener){
        for ( int i = 0; i < this.getChildCount(); i++ ){
            this.getChildAt(i).setOnClickListener(listener);
        }
    }
    public void reactOnClick(View v){
        if ( v == mDice ){
            if ( gameBoard.getStatus() == TGameBoard.Status.GOING )
                return;

            int retRoll = mDice.roll();
            if ( retRoll != -1 ){
                gameBoard.notifyRoll(retRoll);
            }
        }
    }

    public void setGameBoard(TGameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
}
