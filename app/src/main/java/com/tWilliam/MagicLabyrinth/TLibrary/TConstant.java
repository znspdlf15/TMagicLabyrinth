package com.tWilliam.MagicLabyrinth.TLibrary;

import com.tWilliam.MagicLabyrinth.R;

public class TConstant {
    static final public int[] IMAGE_FILE_NAME = {
            R.mipmap.bookmark_star,
            R.mipmap.cannabis_leaf,
            R.mipmap.cloud_dark_shape,
            R.mipmap.cold,
            R.mipmap.crown,
            R.mipmap.diamond,
            R.mipmap.eye,
            R.mipmap.fire,
            R.mipmap.infinite_mathematical_symbol,
            R.mipmap.light_bulb,
            R.mipmap.lightning_electric_energy,
            R.mipmap.locked_padlock,
            R.mipmap.water_drop,
            R.mipmap.wall_clock,
            R.mipmap.wait,
            R.mipmap.valentines_heart,
            R.mipmap.tree_silhouette,
            R.mipmap.raw_fish,
            R.mipmap.open_book_top_view,
            R.mipmap.icon,
            R.mipmap.key_tool,
            R.mipmap.sun,
            R.mipmap.butterfly_wings_couple,
            R.mipmap.waning_moon
    };
    static final public int[] IMAGE_FOR_PLAYER_FILE_NAME = {
            R.mipmap.soccer_player,
            R.mipmap.magician
    };

    /*
    1, 2, 3, 4: player number.
    9 : item
     */
    public static final int[][] MAP_PLACE = {
            {1, 0, 9, 9, 0, 3},
            {0, 9, 9, 9, 9, 0},
            {9, 9, 9, 9, 9, 9},
            {9, 9, 9, 9, 9, 9},
            {0, 9, 9, 9, 9, 0},
            {4, 0, 9, 9, 0, 2}
    };
    static final public int[] IMAGE_FOR_DICE_FILE_NAME = {
            R.mipmap.Dice_0,
            R.mipmap.Dice_1_b,
            R.mipmap.Dice_2_b,
            R.mipmap.Dice_2a_b,
            R.mipmap.Dice_3_b,
            R.mipmap.Dice_3a_b
    };

    static final public int EASY_WALL_COUNT = 19;
    static final public int MIDEUM_WALL_COUNT = 22;
    static final public int HARD_WALL_COUNT = 25;

//    static final public int

}
