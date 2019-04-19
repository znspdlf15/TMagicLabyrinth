package com.tWilliam.MagicLabyrinth.Game;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tWilliam.MagicLabyrinth.Player.TPlayer;
import com.tWilliam.MagicLabyrinth.R;

public class TStatusBoard extends ConstraintLayout {
    private TDice mDice;
    private TGameBoard gameBoard;
    private ImageView targetImageView;
    private ImageView[] playerImageViews;
    private TextView[] scoreTextViews;

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
        notifyTargetChange();
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
    public void notifyTargetChange(){
        targetImageView.setImageResource(this.gameBoard.getTargetImageId());
    }
    public void enrollPlayers(TPlayer[] players){
        playerImageViews = new ImageView[players.length];
        scoreTextViews = new TextView[players.length];

        for ( int i = 0; i < players.length; i++ ){
            playerImageViews[i] = new ImageView(this.getContext());
            scoreTextViews[i] = new TextView(this.getContext());
            playerImageViews[i].setImageResource(players[i].getImageId());
            scoreTextViews[i].setText(players[i].getScore() + "");
            scoreTextViews[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            scoreTextViews[i].setTextSize(20);
        }

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        for ( int i = 0; i < players.length; i++ ){
            int imageId = 11 + i;
            int textId = 21 + i;

            playerImageViews[i].setId(imageId);
            constraintSet.constrainWidth(imageId, 150);
            constraintSet.constrainHeight(imageId, 150);

            scoreTextViews[i].setId(textId);
            constraintSet.constrainWidth(textId, 150);
            constraintSet.constrainHeight(textId, 100);

            this.addView(playerImageViews[i]);
            this.addView(scoreTextViews[i]);

            switch ( i ){
                case 0:
                    constraintSet.connect(imageId, ConstraintSet.LEFT, this.getId(), ConstraintSet.LEFT, 50);
                    constraintSet.connect(imageId, ConstraintSet.TOP, this.getId(), ConstraintSet.TOP, 50);
                    constraintSet.connect(textId, ConstraintSet.TOP, imageId, ConstraintSet.BOTTOM, 50);
                    constraintSet.connect(textId, ConstraintSet.LEFT, this.getId(), ConstraintSet.LEFT, 50);
                    break;
                case 1:
                    constraintSet.connect(imageId, ConstraintSet.RIGHT, this.getId(), ConstraintSet.RIGHT, 50);
                    constraintSet.connect(imageId, ConstraintSet.TOP, this.getId(), ConstraintSet.TOP, 50);
                    constraintSet.connect(textId, ConstraintSet.TOP, imageId, ConstraintSet.BOTTOM, 50);
                    constraintSet.connect(textId, ConstraintSet.RIGHT, this.getId(), ConstraintSet.RIGHT, 50);
                    break;
                case 2:
                    constraintSet.connect(textId, ConstraintSet.LEFT, this.getId(), ConstraintSet.LEFT, 50);
                    constraintSet.connect(imageId, ConstraintSet.BOTTOM, textId, ConstraintSet.TOP, 50);
                    constraintSet.connect(textId, ConstraintSet.BOTTOM, this.getId(), ConstraintSet.BOTTOM, 50);
                    constraintSet.connect(textId, ConstraintSet.LEFT, this.getId(), ConstraintSet.LEFT, 50);
                    break;
                case 3:
                    constraintSet.connect(textId, ConstraintSet.RIGHT, this.getId(), ConstraintSet.RIGHT, 50);
                    constraintSet.connect(imageId, ConstraintSet.BOTTOM, textId, ConstraintSet.TOP, 50);
                    constraintSet.connect(textId, ConstraintSet.BOTTOM, this.getId(), ConstraintSet.BOTTOM, 50);
                    constraintSet.connect(textId, ConstraintSet.RIGHT, this.getId(), ConstraintSet.RIGHT, 50);
                    break;
                default:
                    break;
            }
            constraintSet.applyTo(this);
        }
    }

    public void notifyScoreChange(TPlayer[] players){
        for ( int i = 0; i < players.length; i++ ){
            scoreTextViews[i].setText(players[i].getScore() + "");
        }
    }
}
