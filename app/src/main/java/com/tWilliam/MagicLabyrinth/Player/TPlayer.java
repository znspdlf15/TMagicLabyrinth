package com.tWilliam.MagicLabyrinth.Player;

import com.tWilliam.MagicLabyrinth.Game.TLocation;
import com.tWilliam.MagicLabyrinth.R;

public class TPlayer {
    private int imageId;
    private TLocation location;
    private int x;
    private int y;

    public TPlayer(int imageId) {
        this.setImageId(imageId);
    }
    public void setImageId(int id){
        imageId = id;
    }
    public int getImageId(){
        return imageId;
    }

    public TLocation getLocation() {
        return location;
    }
    public void setLocation(TLocation location) {
        this.location = location;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}
