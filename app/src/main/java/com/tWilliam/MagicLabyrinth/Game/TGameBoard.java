package com.tWilliam.MagicLabyrinth.Game;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TGameBoard extends TDraw {
    private TLocation[][] locationMap;
    private TWall[][] verticalWalls;
    private TWall[][] horizontalWalls;
    private TableLayout gameBoard;

    public TGameBoard(View view){
        super(view);

        Log.v(TGameBoard.class.getSimpleName(), "GameBoard is loaded.");

        gameBoard = new TableLayout(view.getContext());
        gameBoard.setBackgroundColor(Color.parseColor("#ffff00"));
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        gameBoard.setLayoutParams(layoutParams);

        locationMap = new TLocation[6][6];
        verticalWalls = new TWall[6][5];
        horizontalWalls = new TWall[5][6];

        for ( int y = 0; y < locationMap.length + Math.max(verticalWalls.length, horizontalWalls.length); y++ ) {
            TableRow tr = new TableRow(gameBoard.getContext());
            TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1);
            tr.setLayoutParams(tableRowParams);

            for ( int x = 0; x < locationMap[0].length + Math.max(verticalWalls[0].length, horizontalWalls[0].length); x++ ) {
                TextView cell = new TextView(gameBoard.getContext());

                tr.setLayoutParams(tableRowParams);

                if ( y % 2 == 0 && x % 2 == 0 ){
                    locationMap[y / 2][x / 2] = new TLocation(cell);
                } else {
                    if ( y % 2 == 0 && x % 2 == 1 ){
                        if ( x / 2 >= verticalWalls[0].length )
                            continue;
                        verticalWalls[y / 2][x / 2] = new TWall(cell);
                        cell.animate().translationY(20).withLayer();

                    } else if (y % 2 == 1 && x % 2 == 0) {
                        if ( y / 2 >= horizontalWalls.length  )
                            continue;
                        horizontalWalls[y / 2][x / 2] = new TWall(cell);
                    }
                }

                tr.addView(cell);
                cell.bringToFront();
            }

            gameBoard.addView(tr);
        }
    }

    @Override
    public View getView(){
        return this.gameBoard;
    }
}
