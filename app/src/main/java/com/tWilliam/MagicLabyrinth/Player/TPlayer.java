package com.tWilliam.MagicLabyrinth.Player;

import com.tWilliam.MagicLabyrinth.Game.TLocation;
import com.tWilliam.MagicLabyrinth.R;

public class TPlayer {
    private int imageId;
    private TLocation location;
    private int x;
    private int y;
    private int org_x;
    private int org_y;

    private int move_count;

    public TPlayer(int imageId) {
        this.setImageId(imageId);

        this.org_x = -1;
        this.org_y = -1;
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
        if ( this.org_x < 0 ) {
            this.org_x = x;
        }
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
        if ( this.org_y < 0 ) {
            this.org_y = y;
        }
    }
    public int getOrgX() { return org_x; }
    public int getOrgY() { return org_y; }
    public int getMove_count() { return move_count; }
    public void setMove_count(int move_count) { this.move_count = move_count; }
}
