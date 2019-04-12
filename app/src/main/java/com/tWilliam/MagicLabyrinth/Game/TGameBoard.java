package com.tWilliam.MagicLabyrinth.Game;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tWilliam.MagicLabyrinth.Player.TPlayer;
import com.tWilliam.MagicLabyrinth.TLibrary.TConstant;
import com.tWilliam.MagicLabyrinth.TLibrary.TDPCalculator;
import com.tWilliam.MagicLabyrinth.TLibrary.TDirection;

import java.util.LinkedList;

public class TGameBoard extends TDraw {
    private TLocation[][] locationMap;
    private TWall[][] verticalWalls;
    private TWall[][] horizontalWalls;
    private RelativeLayout gameBoard;
    private View[][] gameMap;

    private int[][] located = {
            {0, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 0, 0}
    };

    private int[][] playersCoordinate = {
            {0, 0},
            {5, 5},
            {5, 0},
            {0, 5}
    };

    public TGameBoard(View view, int wallNumber){
        super(view);

        gameBoard = new RelativeLayout(view.getContext());
        gameBoard.setBackgroundColor(Color.parseColor("#eeeeee"));

        int dp_10 = (int)TDPCalculator.DPToPixel(10, view.getContext());
        gameBoard.setPadding(dp_10, dp_10, dp_10, dp_10);

        locationMap = new TLocation[6][6];
        verticalWalls = new TWall[6][5];
        horizontalWalls = new TWall[5][6];

        gameMap = new View[locationMap.length + horizontalWalls.length][locationMap[0].length + verticalWalls[0].length];

        int imageIdx = 0;
        for ( int y = 0; y < locationMap.length + horizontalWalls.length; y++ ){
            for ( int x = 0; x < locationMap[0].length + verticalWalls[0].length; x++ ){
                if ( y % 2 == 1 && x % 2 == 1 )
                    continue;
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                TMap mapItem = null;
                if ( y % 2 == 0 && x % 2 == 0 ){
                    gameMap[y][x] = new ImageView(gameBoard.getContext());
                    if ( imageIdx < TConstant.imageFileNames.length && located[y/2][x/2] == 1) {
                        locationMap[y/2][x/2] = new TLocation((ImageView) gameMap[y][x], TConstant.imageFileNames[imageIdx++]);
                    } else {
                        locationMap[y/2][x/2] = new TLocation((ImageView) gameMap[y][x], 0);
                    }
                    int dp_50 = (int)TDPCalculator.DPToPixel(50, view.getContext());
                    params.width = dp_50;
                    params.height = dp_50;
                    mapItem = locationMap[y/2][x/2];
                } else {
                    if ( y % 2 == 0 && x % 2 == 1 ){
                        if ( x / 2 >= verticalWalls[0].length )
                            continue;
                        gameMap[y][x] = new ImageView(gameBoard.getContext());
                        verticalWalls[y/2][x/2] = new TWall((ImageView)gameMap[y][x]);
                        verticalWalls[y/2][x/2].setWallType(TWall.wallType.vertical);
                        params.width = (int)TDPCalculator.DPToPixel(3, view.getContext());
                        params.height = (int)TDPCalculator.DPToPixel(50, view.getContext());
                        mapItem = verticalWalls[y/2][x/2];
                    } else if (y % 2 == 1 && x % 2 == 0) {
                        if ( y / 2 >= horizontalWalls.length  )
                            continue;
                        gameMap[y][x] = new ImageView(gameBoard.getContext());
                        horizontalWalls[y/2][x/2] = new TWall((ImageView)gameMap[y][x]);
                        horizontalWalls[y/2][x/2].setWallType(TWall.wallType.horizontal);
                        params.width = (int)TDPCalculator.DPToPixel(50, view.getContext());
                        params.height = (int)TDPCalculator.DPToPixel(3, view.getContext());
                        mapItem = horizontalWalls[y/2][x/2];
                    }
                }

                if ( mapItem == null )
                    continue;

                gameMap[y][x].setId(10000 + y * 100 + x);
                if ( y != 0 ) {
                    if ( gameMap[y-1][x] == null ){
                        params.addRule(RelativeLayout.RIGHT_OF, gameMap[y][x-1].getId());
                        params.addRule(RelativeLayout.ALIGN_TOP, gameMap[y][x-1].getId());
                    } else {
                        params.addRule(RelativeLayout.BELOW, gameMap[y-1][x].getId());
                        params.addRule(RelativeLayout.ALIGN_LEFT, gameMap[y-1][x].getId());
                    }
                } else if ( x != 0 ){
                    params.addRule(RelativeLayout.RIGHT_OF, gameMap[y][x-1].getId());
                    params.addRule(RelativeLayout.ALIGN_TOP, gameMap[y][x-1].getId());
                }
                gameMap[y][x].setLayoutParams(params);

                if ( mapItem.getView() == null )
                    continue;

                gameBoard.addView(gameMap[y][x]);
            }
        }

        initWalls(wallNumber);
    }

    public void enrollPlayers(TPlayer[] players){
        for ( int i = 0; i < players.length; i++ ) {
            int x = playersCoordinate[i][0];
            int y = playersCoordinate[i][1];

            locationMap[y][x].setImageId(players[i].getImageId());
        }
    }

    public void initWalls(int wallNumber){
        if ( wallNumber < 0 )
            return;


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

    @Override
    public View getView(){
        return this.gameBoard;
    }

    public void setAllOnClickListner(View.OnClickListener listener){
        for ( int y = 0; y < locationMap.length; y++ ){
            for ( int x = 0; x < locationMap[y].length; x++ ){
                if (locationMap[y][x] == null)
                    continue;
                locationMap[y][x].setOnClickListener(listener);
            }
        }
    }

    public boolean canPlayerGo(int orgX, int orgY, TDirection.Dir4 direction){
        int wallX = orgX;
        int wallY = orgY;

        if ( orgX < 0 || orgY < 0 || orgX >= gameMap[0].length || orgY >= gameMap.length )
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
        v.setAlpha((float)0.3);
    }
}
