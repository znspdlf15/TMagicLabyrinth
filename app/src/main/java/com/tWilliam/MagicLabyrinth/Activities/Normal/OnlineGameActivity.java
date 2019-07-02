package com.tWilliam.MagicLabyrinth.Activities.Normal;

import android.content.Intent;
import android.os.Bundle;

import com.tWilliam.MagicLabyrinth.Activities.PopUp.GameSettingActivity;
import com.tWilliam.MagicLabyrinth.Activities.PopUp.OnlineSearchActivity;
import com.tWilliam.MagicLabyrinth.Player.THumanPlayer;
import com.tWilliam.MagicLabyrinth.Player.TPlayer;
import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TIntentCode;

public class OnlineGameActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.wallNumber = 25;
        this.goalScore = 5;
    }

    @Override
    public void popSelectActivity(){
        Intent intent = new Intent(this, OnlineSearchActivity.class);
        startActivityForResult(intent, TIntentCode.ONLINE_SEARCH_ACTIVITY_CODE);
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
            case TIntentCode.ONLINE_SEARCH_ACTIVITY_CODE:
                if ( resultCode == RESULT_OK ){
                    activityStart();
                } else if ( resultCode == RESULT_CANCELED ){
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
