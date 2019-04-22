package com.tWilliam.MagicLabyrinth.Player;

public class THumanPlayer extends TPlayer {
    public THumanPlayer(int imageId) {
        super(imageId);
    }

    @Override
    public boolean isLocalPlayer() {
        return true;
    }
}
