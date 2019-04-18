package com.tWilliam.MagicLabyrinth.Game;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.ImageView;

import com.tWilliam.MagicLabyrinth.R;

public class TStatusBoard extends ConstraintLayout {
    private TDice mDice;
    private TGameBoard gameBoard;
    private ImageView targetImageView;
    private final int diceId = 1030;
    private final int targetImageViewId = 1031;

    public TStatusBoard(Context context, int statusBoardId, TGameBoard gameBoard) {
        super(context);

        this.setId(statusBoardId);
        this.gameBoard = gameBoard;

        ConstraintSet constraintSet = new ConstraintSet();

        mDice = new TDice(context);
        mDice.setId(diceId);
        mDice.setImageResource(R.mipmap.dice_1_b);
        this.addView(mDice);
        highlightDice(true);

        targetImageView = new ImageView(context);
        targetImageView.setId(targetImageViewId);
        targetImageView.setImageResource(this.gameBoard.getTargetImageId());
        this.addView(targetImageView);

        constraintSet.clone(this);

        constraintSet.constrainWidth(targetImageViewId, 200);
        constraintSet.constrainHeight(targetImageViewId, 200);
        constraintSet.connect(targetImageViewId, ConstraintSet.LEFT, statusBoardId, ConstraintSet.LEFT);
        constraintSet.connect(targetImageViewId, ConstraintSet.RIGHT, statusBoardId, ConstraintSet.RIGHT);
        constraintSet.connect(targetImageViewId, ConstraintSet.TOP, statusBoardId, ConstraintSet.TOP);
        constraintSet.connect(targetImageViewId, ConstraintSet.BOTTOM, diceId, ConstraintSet.TOP);
        constraintSet.setVerticalBias(targetImageViewId, (float)0.1);

        constraintSet.applyTo(this);

        constraintSet.constrainWidth(diceId, 200);
        constraintSet.constrainHeight(diceId, 200);
        constraintSet.connect(diceId, ConstraintSet.LEFT, statusBoardId, ConstraintSet.LEFT);
        constraintSet.connect(diceId, ConstraintSet.RIGHT, statusBoardId, ConstraintSet.RIGHT);
        constraintSet.connect(diceId, ConstraintSet.TOP, targetImageViewId, ConstraintSet.BOTTOM);
        constraintSet.connect(diceId, ConstraintSet.BOTTOM, statusBoardId, ConstraintSet.BOTTOM);
        constraintSet.setVerticalBias(diceId, (float)0.15);

        constraintSet.applyTo(this);

    }

    public void setAllOnClickListener(View.OnClickListener listener){
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
                this.highlightDice(false);
            }
        }
    }
    public void highlightDice(boolean set){
        if ( set )
            this.mDice.setBackgroundResource(R.drawable.alert_corner);
        else
            this.mDice.setBackgroundResource(0);
    }
    public void notifyTurnEnd(){
        this.highlightDice(true);
    }
}
