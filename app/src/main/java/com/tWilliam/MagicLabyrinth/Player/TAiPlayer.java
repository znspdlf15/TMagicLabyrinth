package com.tWilliam.MagicLabyrinth.Player;

public class TAiPlayer extends TPlayer {
    public TAiPlayer(int imageId) {
        super(imageId);
    }

    @Override
    public boolean isLocalPlayer() {
        return false;
    }
}
