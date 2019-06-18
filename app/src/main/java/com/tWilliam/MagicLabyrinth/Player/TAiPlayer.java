package com.tWilliam.MagicLabyrinth.Player;

import com.tWilliam.MagicLabyrinth.Game.TLocation;
import com.tWilliam.MagicLabyrinth.TLibrary.TDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import android.util.Pair;

public class TAiPlayer extends TPlayer {
    int aiLevel;
    private boolean isGoing = false;

    wallType[][] knownVerticalWall;
    wallType[][] knownHorizontalWall;

    enum wallType{
        Unknown, Occupy
    }
    public TAiPlayer(int imageId) {
        super(imageId);

        aiLevel = 1;
        knownHorizontalWall = new wallType[5][6];
        knownVerticalWall = new wallType[6][5];

        for (wallType[] row : knownHorizontalWall) {
            Arrays.fill(row, wallType.Unknown);
        }
        for (wallType[] row : knownVerticalWall) {
            Arrays.fill(row, wallType.Unknown);
        }
    }

    public void setAiLevel(int level){
        this.aiLevel = level;
    }

    @Override
    public boolean isLocalPlayer() {
        return false;
    }

    public void knowVerticalWall(int x, int y){
        knownVerticalWall[y][x] = wallType.Occupy;
    }

    public void knowHorizontalWall(int x, int y){
        knownHorizontalWall[y][x] = wallType.Occupy;
    }

    public void forgetVerticalWall(){
        ArrayList<Pair <Integer, Integer>> q = new ArrayList<>();

        for ( int y = 0; y < knownVerticalWall.length; y++ ){
            for ( int x = 0; x < knownVerticalWall[0].length; x++ ){
                if ( knownVerticalWall[y][x] == wallType.Occupy ){
                    q.add(Pair.create(x, y));
                }
            }
        }

        Random random = new Random();
        int idx = random.nextInt(q.size());


        knownVerticalWall[q.get(idx).second][q.get(idx).first] = wallType.Unknown;
    }

    public void forgetHorizontalWall(){
        ArrayList<Pair <Integer, Integer>> q = new ArrayList<>();

        for ( int y = 0; y < knownHorizontalWall.length; y++ ){
            for ( int x = 0; x < knownHorizontalWall[0].length; x++ ){
                if ( knownHorizontalWall[y][x] == wallType.Occupy ){
                    q.add(Pair.create(x, y));
                }
            }
        }

        Random random = new Random();
        int idx = random.nextInt(q.size());

        knownHorizontalWall[q.get(idx).second][q.get(idx).first] = wallType.Unknown;
    }

    public TDirection.Dir4 getNextDir(TLocation[][] locationMap){
        int targetX = -1;
        int targetY = -1;

        TDirection.Dir4[][] trace = new TDirection.Dir4[locationMap.length][locationMap[0].length];
        for ( int y = 0; y < locationMap.length; y++ ){
            for ( int x = 0; x < locationMap[0].length; x++ ){
                if ( locationMap[y][x].isTarget() ){
                    targetX = x;
                    targetY = y;
                }
            }
        }

        Queue<Pair <Integer, Integer>> q = new LinkedList<>();

        q.offer(Pair.create(this.getX(), this.getY()));

        while ( !q.isEmpty() ){
            int curX = q.peek().first;
            int curY = q.peek().second;
            q.poll();

            List<TDirection.Dir4> dirs = Arrays.asList(TDirection.Dir4.values());
            Collections.shuffle(dirs);
            for ( TDirection.Dir4 dir: dirs){
                int nextX = curX + dir.DeltaX();
                int nextY = curY + dir.DeltaY();

                if ( nextX < 0 || nextX >= locationMap[0].length || nextY < 0 || nextY >= locationMap.length ) continue;

                if ( trace[nextY][nextX] != null ) continue;

                if ( dir == TDirection.Dir4.UP || dir == TDirection.Dir4.DOWN ){
                    if ( this.knownHorizontalWall[(curY + nextY)/2][(curX + nextX)/2] == wallType.Occupy )
                        continue;
                } else {
                    if ( this.knownVerticalWall[(curY + nextY)/2][(curX + nextX)/2] == wallType.Occupy )
                        continue;
                }

                trace[nextY][nextX] = dir.getReverseDir();

                if ( nextX == targetX && nextY == targetY ){
                    int prevX = nextX;
                    int prevY = nextY;
                    if ( trace[prevY][prevX] == null ){
                        return null;
                    }
                    int traceX = prevX + trace[prevY][prevX].DeltaX();
                    int traceY = prevY + trace[prevY][prevX].DeltaY();
                    while ( !(traceX == this.getX() && traceY == this.getY()) ){
                        prevX = traceX;
                        prevY = traceY;
                        traceX = prevX + trace[prevY][prevX].DeltaX();
                        traceY = prevY + trace[prevY][prevX].DeltaY();
                    }

                    return trace[prevY][prevX].getReverseDir();
                }
                q.offer(Pair.create(nextX, nextY));
            }
        }

        return null;
    }
    public void turnInit(){
        super.turnInit();

        float prob = 0;
        switch ( this.aiLevel ){
            case 1:
                prob = 0f;
                break;
            case 2:
                prob = 0.05f;
                break;
            case 3:
                prob = 0.15f;
                break;
            default:

        }
        Random random = new Random();
        while ( random.nextFloat() <= prob ){
            if ( random.nextInt(2) == 1 ){
                forgetHorizontalWall();
            } else {
                forgetVerticalWall();
            }
        }
    }

    public boolean isGoing(){ return isGoing; }
    public void setGoing(boolean going) { isGoing = going; }
}
