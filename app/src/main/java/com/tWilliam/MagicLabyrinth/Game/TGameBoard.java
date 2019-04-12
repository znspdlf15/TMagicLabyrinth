package com.tWilliam.MagicLabyrinth.Game;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TConstant;
import com.tWilliam.MagicLabyrinth.TLibrary.TDPCalculator;

public class TGameBoard extends TDraw {
    private TLocation[][] locationMap;
    private TWall[][] verticalWalls;
    private TWall[][] horizontalWalls;
    private RelativeLayout gameBoard;
    private View[][] gameMap;

    private int[] dx = {0, 0, -1, 1};
    private int[] dy = {-1, 1, 0, 0};

    private int[][] located = {
            {0, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 0, 0}
    };

    public TGameBoard(View view){
        super(view);

        gameBoard = new RelativeLayout(view.getContext());
        gameBoard.setBackgroundColor(Color.parseColor("#ffff00"));

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
    }

    public void enrollPlayers(){

    }

    @Override
    public View getView(){
        return this.gameBoard;
    }
}
