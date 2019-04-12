package com.tWilliam.MagicLabyrinth.TLibrary;

public class TDirection {
    public enum Dir4 {
        DOWN(0),
        UP(1),
        RIGHT(2),
        LEFT(3);
        static final private int[] dx = {0, 0, 1, -1};
        static final private int[] dy = {1, -1, 0, 0};

        private int idx;

        Dir4(int idx){
            this.idx = idx;
        }

        public int DeltaX(){
            return dx[this.idx];
        }
        public int DeltaY(){
            return dy[this.idx];
        }
    }
    public enum Dir8 {
        DOWN(0),
        UP(1),
        RIGHT(2),
        LEFT(3),
        RIGHT_UP(4),
        RIGHT_DOWN(5),
        LEFT_UP(6),
        LEFT_DOWN(7);
        static final private int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
        static final private int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};

        private int idx;

        Dir8(int idx){
            this.idx = idx;
        }

        public int DeltaX(){
            return dx[this.idx];
        }
        public int DeltaY(){
            return dy[this.idx];
        }
    }
}
