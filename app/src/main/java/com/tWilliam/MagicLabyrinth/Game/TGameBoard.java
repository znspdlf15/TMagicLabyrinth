package com.tWilliam.MagicLabyrinth.Game;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.tWilliam.MagicLabyrinth.Player.TPlayer;
import com.tWilliam.MagicLabyrinth.TLibrary.TConstant;
import com.tWilliam.MagicLabyrinth.TLibrary.TDPCalculator;
import com.tWilliam.MagicLabyrinth.TLibrary.TDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TGameBoard extends RelativeLayout {
    private TLocation[][] locationMap;
    private TWall[][] verticalWalls;
    private TWall[][] horizontalWalls;
    private TStatusBoard statusBoard;

    private int locationWidth = 65;
    private int locationHeight = 65;
    private int wallWidth = 3;
    private int wallHeight = 65;

    private ArrayList<TPlayer> playerLocations = new ArrayList<>();
    private TPlayer[] players;
    private int turnIdx;

    private ArrayList<TLocation> resetList = new ArrayList<>();

    public enum Status{
        WAIT,
        GOING
    }
    private Status nowStatus = Status.WAIT;

    public TGameBoard(Context context, int wallNumber){
        super(context);

        int dp_10 = (int)TDPCalculator.DPToPixel(10, this.getContext());
        this.setPadding(dp_10, dp_10, dp_10, dp_10);

        locationMap = new TLocation[6][6];
        verticalWalls = new TWall[6][5];
        horizontalWalls = new TWall[5][6];

        int imageIdx = 0;
        for ( int y = 0; y < locationMap.length; y++ ){
            for ( int x = 0; x < locationMap[0].length; x++ ){
                // location
                RelativeLayout.LayoutParams locationParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                if ( imageIdx < TConstant.IMAGE_FILE_NAME.length && TConstant.MAP_PLACE[y][x] == 9)
                    locationMap[y][x] = new TLocation(this.getContext(), TConstant.IMAGE_FILE_NAME[imageIdx++]);
                else
                    locationMap[y][x] = new TLocation(this.getContext(), 0);

                if ( y == 0 ){
                    if ( x != 0 ){
                        locationParams.addRule(RelativeLayout.RIGHT_OF, verticalWalls[y][x-1].getId());
                        locationParams.addRule(RelativeLayout.ALIGN_TOP, verticalWalls[y][x-1].getId());
                    }
                } else {
                    locationParams.addRule(RelativeLayout.BELOW, horizontalWalls[y-1][x].getId());
                    locationParams.addRule(RelativeLayout.ALIGN_LEFT, horizontalWalls[y-1][x].getId());
                }

                int dp_location_size = (int)TDPCalculator.DPToPixel(locationWidth, this.getContext());

                locationParams.width = dp_location_size;
                locationParams.height = dp_location_size;

                locationMap[y][x].setId(10000 + 100 * y + x);
                locationMap[y][x].setLayoutParams(locationParams);
                this.addView(locationMap[y][x]);

                // vertical wall
                if ( x < verticalWalls[0].length && y < verticalWalls.length ) {
                    RelativeLayout.LayoutParams verticalParams = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                    verticalWalls[y][x] = new TWall(this.getContext());

                    verticalParams.addRule(RelativeLayout.RIGHT_OF, locationMap[y][x].getId());
                    verticalParams.addRule(RelativeLayout.ALIGN_TOP, locationMap[y][x].getId());

                    int dp_vertical_width = (int) TDPCalculator.DPToPixel(wallWidth, this.getContext());
                    int dp_vertical_height = (int) TDPCalculator.DPToPixel(wallHeight, this.getContext());

                    verticalParams.width = dp_vertical_width;
                    verticalParams.height = dp_vertical_height;

                    verticalWalls[y][x].setId(20000 + 100 * y + x);
                    verticalWalls[y][x].setLayoutParams(verticalParams);
                    this.addView(verticalWalls[y][x]);
                }

                // horizontal wall
                if ( x < horizontalWalls[0].length && y < horizontalWalls.length ) {
                    RelativeLayout.LayoutParams horizontalParams = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                    horizontalWalls[y][x] = new TWall(this.getContext());

                    horizontalParams.addRule(RelativeLayout.BELOW, locationMap[y][x].getId());
                    horizontalParams.addRule(RelativeLayout.ALIGN_LEFT, locationMap[y][x].getId());

                    int dp_horizontal_height = (int) TDPCalculator.DPToPixel(wallWidth, this.getContext());
                    int dp_horizontal_width = (int) TDPCalculator.DPToPixel(wallHeight, this.getContext());

                    horizontalParams.width = dp_horizontal_width;
                    horizontalParams.height = dp_horizontal_height;

                    horizontalWalls[y][x].setId(30000 + 100 * y + x);
                    horizontalWalls[y][x].setLayoutParams(horizontalParams);
                    this.addView(horizontalWalls[y][x]);
                }
            }
        }

        initWalls(wallNumber);
    }

    public void enrollPlayers(TPlayer[] players){
        this.players = players;

        for ( int i = 0; i < players.length; i++ ) {
            int x = -1;
            int y = -1;

            for ( int yy = 0; yy < TConstant.MAP_PLACE.length; yy++ ){
                for ( int xx = 0; xx < TConstant.MAP_PLACE[0].length; xx++ ){
                    if ( TConstant.MAP_PLACE[yy][xx] == i+1 ){
                        x = xx;
                        y = yy;
                    }
                }
            }
            if ( x == -1 )
                continue;

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            int dp_50 = (int)TDPCalculator.DPToPixel(locationWidth, this.getContext());
            params.width = dp_50;
            params.height = dp_50;

            if ( y == 0 ) {
                params.addRule(RelativeLayout.ABOVE, horizontalWalls[0][x].getId());
                params.addRule(RelativeLayout.ALIGN_LEFT, horizontalWalls[0][x].getId());
            } else {
                params.addRule(RelativeLayout.BELOW, horizontalWalls[y-1][x].getId());
                params.addRule(RelativeLayout.ALIGN_LEFT, horizontalWalls[y-1][x].getId());
            }

            TLocationPlayer player = new TLocationPlayer(this.getContext(), players[i].getImageId());
            player.setLayoutParams(params);
            players[i].setLocation(player);
            players[i].setX(x);
            players[i].setY(y);
            player.setLayoutParams(params);
            playerLocations.add(players[i]);

            this.addView(player);
        }
    }

    public void initWalls(int wallNumber){
        if ( wallNumber < 0 )
            return;

        List<TWall> random_list = new ArrayList<>();

        for ( TWall[] row: verticalWalls){
            for ( TWall item: row){
                random_list.add(item);
            }
        }
        for ( TWall[] row: horizontalWalls){
            for ( TWall item: row){
                random_list.add(item);
            }
        }
        Collections.shuffle(random_list);
        setWalls(random_list, wallNumber, 0);

    }

    public boolean setWalls(List<TWall> random_list, int count, int index) {
        if ( count == 0 )
            return true;

        while ( index < random_list.size() ){
            random_list.get(index).setWall(true);
            if ( !checkPerfectMap() ){
                random_list.get(index).setWall(false);
                index++;
                continue;
            }
            if ( setWalls(random_list, count-1, index + 1) ){
                return true;
            }
            index++;
            random_list.get(index).setWall(false);
        }

        return false;
    }

    public void showAllWalls(){
        for ( TWall[] row : verticalWalls ){
            for ( TWall item : row ){
                if ( item.isWall() )
                    item.showWall();
            }
        }
        for ( TWall[] row : horizontalWalls ){
            for ( TWall item : row ){
                if ( item.isWall() )
                    item.showWall();
            }
        }
    }

    public boolean checkPerfectMap(){
        class pair {
            int x;
            int y;
            public pair(int x, int y){
                this.x = x;
                this.y = y;
            }
        }
        LinkedList<pair> q = new LinkedList<>();

        int[][] checking_map = new int[locationMap.length][locationMap[0].length];
        q.add(new pair(0, 0));
        checking_map[0][0] = 1;

        while ( !q.isEmpty() ){
            pair nowPair = q.poll();
            int nowX = nowPair.x;
            int nowY = nowPair.y;

            for ( TDirection.Dir4 dir: TDirection.Dir4.values() ){
                int targetX = nowX + dir.DeltaX();
                int targetY = nowY + dir.DeltaY();

                if ( !canPlayerGo(nowX, nowY, dir) )
                    continue;
                if ( checking_map[targetY][targetX] == 1 )
                    continue;

                checking_map[targetY][targetX] = 1;
                q.add(new pair(targetX, targetY));
            }
        }

        for ( int[] row : checking_map ){
            for ( int item : row ){
                if ( item == 0 ){
                    return false;
                }
            }
        }

        return true;
    }

    public void setAllOnClickListner(View.OnClickListener listener){
        for ( int y = 0; y < locationMap.length; y++ ){
            for ( int x = 0; x < locationMap[y].length; x++ ){
                if (locationMap[y][x] == null)
                    continue;
                locationMap[y][x].setOnClickListener(listener);
            }
        }
        for ( TPlayer player: playerLocations ){
            player.getLocation().setOnClickListener(listener);
        }
    }

    public boolean canPlayerGo(int orgX, int orgY, TDirection.Dir4 direction){
        int wallX = orgX;
        int wallY = orgY;

        if ( orgX < 0 || orgY < 0 || orgX >= locationMap[0].length || orgY >= locationMap.length )
            return false;

        if ( direction == TDirection.Dir4.UP ){
            wallY -= 1;
        }
        if ( direction == TDirection.Dir4.LEFT ){
            wallX -= 1;
        }

        if ( direction == TDirection.Dir4.UP || direction == TDirection.Dir4.DOWN ) {
            if ( wallY < 0 || wallY >= horizontalWalls.length )
                return false;

            return !horizontalWalls[wallY][wallX].isWall();
        } else {
            if ( wallX < 0 || wallX >= verticalWalls[0].length )
                return false;

            return !verticalWalls[wallY][wallX].isWall();
        }
    }

    public void reactOnClick(View v){
        // if status is wait, no react
        if ( nowStatus == Status.WAIT )
            return;

        int clickedX = -1;
        int clickedY = -1;

        // if click object is near players.
        for ( int y = 0; y < locationMap.length; y++ ){
            for ( int x = 0; x < locationMap[0].length; x++ ){
                if ( locationMap[y][x] == v ){
                    clickedX = x;
                    clickedY = y;
                }
            }
        }

        // if click object is player.
        for ( TPlayer player: playerLocations ){
            if ( v == player.getLocation() ) {
                clickedX = player.getX();
                clickedY = player.getY();
            }
        }

        int playerX = players[turnIdx].getX();
        int playerY = players[turnIdx].getY();

        for ( TDirection.Dir4 dir : TDirection.Dir4.values() ){
            int targetX = playerX + dir.DeltaX();
            int targetY = playerY + dir.DeltaY();

            if ( !(targetX == clickedX && targetY == clickedY) )
                continue;

            if ( canPlayerGo(playerX, playerY, dir) ){
                movePlayer(players[turnIdx], targetX, targetY);
                // for debug
                this.unhighlightLocations();
                this.highlightNearPlayer(players[turnIdx], true);
            } else {
                this.moveFailPlayer(players[turnIdx], targetX, targetY);
                if ( dir == TDirection.Dir4.DOWN || dir == TDirection.Dir4.UP ){
                    this.horizontalWalls[(playerY + targetY)/2][(playerX + targetX)/2].blink();
                } else {
                    this.verticalWalls[(playerY + targetY)/2][(playerX + targetX)/2].blink();
                }
            }
        }
    }

    public void unhighlightLocations(){
        for ( TLocation item: resetList ){
            item.setHighlight(TLocation.highlightType.normal);
        }
        resetList.clear();
    }

    public void movePlayer(TPlayer movingPlayer, int targetX, int targetY){
        movingPlayer.setX(targetX);
        movingPlayer.setY(targetY);
        movingPlayer.getLocation().setX(locationMap[targetY][targetX].getX());
        movingPlayer.getLocation().setY(locationMap[targetY][targetX].getY());
    }

    public void moveFailPlayer(TPlayer movingPlayer, int targetX, int targetY){

        int orgX = movingPlayer.getOrgX();
        int orgY = movingPlayer.getOrgY();
        movingPlayer.setX(orgX);
        movingPlayer.setY(orgY);
        movingPlayer.getLocation().setX(locationMap[orgY][orgX].getX());
        movingPlayer.getLocation().setY(locationMap[orgY][orgX].getY());

        this.unhighlightLocations();
    }

    public void highlightNearPlayer(TPlayer player, boolean highlightOn){
        int playerX = player.getX();
        int playerY = player.getY();

        for ( TDirection.Dir4 dir : TDirection.Dir4.values() ){
            int targetX = playerX + dir.DeltaX();
            int targetY = playerY + dir.DeltaY();
            if ( targetX < 0 || targetX >= locationMap[0].length || targetY < 0 || targetY >= locationMap.length )
                continue;

            if ( highlightOn ) {
                locationMap[targetY][targetX].setHighlight(TLocation.highlightType.movable);
                resetList.add(locationMap[targetY][targetX]);
            }
        }
    }

    public void notifyRoll(int rollNumber){
        this.nowStatus = Status.GOING;


    }

    public Status getStatus(){
        return this.nowStatus;
    }


    public void setStatusBoard(TStatusBoard statusBoard) {
        this.statusBoard = statusBoard;
    }
}
