package com.tWilliam.MagicLabyrinth.Player;

import com.tWilliam.MagicLabyrinth.R;

public class TPlayer {
    private int imageId;
    public TPlayer(int imageId) {
        this.setImageId(imageId);
    }
    public void setImageId(int id){
        imageId = id;
    }
    public int getImageId(){
        return imageId;
    }
}
