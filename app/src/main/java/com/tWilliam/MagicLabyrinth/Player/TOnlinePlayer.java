package com.tWilliam.MagicLabyrinth.Player;

public class TOnlinePlayer extends THumanPlayer {

    public TOnlinePlayer(int imageId) {
        super(imageId);
    }

    @Override
    public boolean isLocalPlayer() {
        return false;
    }
}
