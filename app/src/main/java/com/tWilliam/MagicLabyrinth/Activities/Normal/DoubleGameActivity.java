package com.tWilliam.MagicLabyrinth.Activities.Normal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.tWilliam.MagicLabyrinth.Activities.PopUp.AiLevelSelectActivity;
import com.tWilliam.MagicLabyrinth.Activities.PopUp.GameSettingActivity;
import com.tWilliam.MagicLabyrinth.Game.TStatusBoard;
import com.tWilliam.MagicLabyrinth.Player.TAiPlayer;
import com.tWilliam.MagicLabyrinth.Player.THumanPlayer;
import com.tWilliam.MagicLabyrinth.Player.TPlayer;
import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TDirection;
import com.tWilliam.MagicLabyrinth.TLibrary.TIntentCode;

public class DoubleGameActivity extends GameActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void popSelectActivity(){
        Intent intent = new Intent(this, GameSettingActivity.class);
        startActivityForResult(intent, TIntentCode.DUAL_LEVEL_SELECT_ACTIVITY_CODE);

    }

    @Override
    public void enrollPlayers(){
        TPlayer[] players = {new THumanPlayer(R.mipmap.soccer_player), new THumanPlayer(R.mipmap.magician)};

        mGameBoard.enrollPlayers(players);
        mStatusBoard.enrollPlayers(players);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch ( requestCode ){
            case TIntentCode.DUAL_LEVEL_SELECT_ACTIVITY_CODE:
                if ( resultCode == RESULT_OK ){
                    this.wallNumber = data.getIntExtra("wall_count", 0);
                    this.goalScore = data.getIntExtra("goal_count", 5);

                    activityStart();
                } else if ( resultCode == RESULT_CANCELED ){
                    finish();
                }
                break;
            default:
        }
    }
}
