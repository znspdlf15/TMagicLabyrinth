package com.tWilliam.MagicLabyrinth.Activities.Normal;

import android.os.Bundle;

import com.tWilliam.MagicLabyrinth.Player.THumanPlayer;
import com.tWilliam.MagicLabyrinth.Player.TPlayer;
import com.tWilliam.MagicLabyrinth.R;

public class OnlineGameActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TPlayer[] players = {new THumanPlayer(R.mipmap.soccer_player), new THumanPlayer(R.mipmap.magician)};

        mGameBoard.enrollPlayers(players);
        mStatusBoard.enrollPlayers(players);

    }

}
