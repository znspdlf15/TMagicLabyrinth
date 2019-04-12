package com.tWilliam.MagicLabyrinth.TLibrary;

public class TDirection {
    private static final TDirection ourInstance = new TDirection();

    static public TDirection getInstance() {
        return ourInstance;
    }

    static class Dir4 {
        private static final Dir4 ourInstance = new Dir4();

        static public Dir4 getInstance() {
            return ourInstance;
        }

        static final public int[] dx = {0, 0, 1, -1};
        static final public int[] dy = {1, -1, 0, 0};

        enum dir{
            down, up, right, left
        }
    }
    static class Dir8 {
        private static final Dir8 ourInstance = new Dir8();

        static public Dir8 getInstance() {
            return ourInstance;
        }

        static final public int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
        static final public int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};

        enum dir{
            down, up, right, left, right_up, right_down, left_up, left_down
        }
    }


    private TDirection(){}

    static public Dir4 get4dir() {
        return Dir4.getInstance();
    }
    static public Dir8 get8dir() {
        return Dir8.getInstance();
    }
}
