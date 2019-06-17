package com.tWilliam.MagicLabyrinth.Activities.Normal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.tWilliam.MagicLabyrinth.Activities.PopUp.WinPopUpActivity;
import com.tWilliam.MagicLabyrinth.Game.TStatusBoard;
import com.tWilliam.MagicLabyrinth.Player.TAiPlayer;
import com.tWilliam.MagicLabyrinth.Player.THumanPlayer;
import com.tWilliam.MagicLabyrinth.Player.TPlayer;
import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TActivityConstant;
import com.tWilliam.MagicLabyrinth.TLibrary.TDirection;
import com.tWilliam.MagicLabyrinth.TLibrary.TIntentCode;

public class SingleGameActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAiPlayer aiPlayer = new TAiPlayer(R.mipmap.magician);
        aiPlayer.setAiLevel(1);
        TPlayer[] players = {new THumanPlayer(R.mipmap.soccer_player), aiPlayer};

        mGameBoard.enrollPlayers(players);
        mStatusBoard.enrollPlayers(players);
        Handler handler = new Handler();

        final TPlayer nowPlayer = mGameBoard.getNowPlayer();

        if ( nowPlayer instanceof TAiPlayer ){
            TStatusBoard.status.setDiceRollable(false);
            handler.postDelayed(new Runnable() {
                public void run() {
                    AiTurn((TAiPlayer)nowPlayer, true);
                }
            }, 2000);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        TPlayer now_player = mGameBoard.getNowPlayer();

        if ( now_player.isLocalPlayer() ) {
            return;
        } else {
            this.AiTurn((TAiPlayer)now_player, false);
        }
    }


    public void rollEnd(final TAiPlayer nowPlayer){
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                if ( nowPlayer.getMoveCount() <= 0 ) return;

                TDirection.Dir4 dir = nowPlayer.getNextDir(mGameBoard.getLocationMap());

                int targetX = nowPlayer.getX() + dir.DeltaX();
                int targetY = nowPlayer.getY() + dir.DeltaY();

                if (mGameBoard.canPlayerGo(nowPlayer.getX(), nowPlayer.getY(), dir)) {
                    mGameBoard.movePlayer(nowPlayer, targetX, targetY);
                    mGameBoard.unhighlightLocations();

                    if (mGameBoard.getLocationMap()[targetY][targetX].isTarget()) {
                        nowPlayer.addScore(1);
                        mGameBoard.getLocationMap()[targetY][targetX].setImageAlpha(50);
                        mGameBoard.setNextTarget();
                    }

                    if ( nowPlayer.getScore() >= goalScore ) {
                        mGameBoard.setWinner(nowPlayer);
                        gameEnd();
                        return;
                    }

                    if (nowPlayer.getMoveCount() == 0)
                        mGameBoard.nextTurn();

                    mGameBoard.highlightNearPlayer(mGameBoard.getNowPlayer(), true);

                    handler.postDelayed(this, 1000);
                } else {
                    int org_x = nowPlayer.getX();
                    int org_y = nowPlayer.getY();
                    mGameBoard.moveFailPlayer(nowPlayer, targetX, targetY);
                    if ( dir == TDirection.Dir4.DOWN || dir == TDirection.Dir4.UP ){
                        nowPlayer.knowHorizontalWall((org_x + targetX)/2, (org_y + targetY)/2);
                        mGameBoard.getHorizontalWalls()[(org_y + targetY)/2][(org_x + targetX)/2].blink();
                    } else {
                        nowPlayer.knowVerticalWall((org_x + targetX)/2, (org_y + targetY)/2);
                        mGameBoard.getVerticalWalls()[(org_y + targetY)/2][(org_x + targetX)/2].blink();
                    }
                }
            }
        }, 1000);
    }

    public interface AiPlayerCallBack{
        public void diceRollEnd();
    }

    public void AiTurn(final TAiPlayer nowPlayer, boolean force){
        mStatusBoard.aiRollDice(new AiPlayerCallBack(){
            @Override
            public void diceRollEnd(){
                rollEnd(nowPlayer);
            }
        }, force);
    }
}
