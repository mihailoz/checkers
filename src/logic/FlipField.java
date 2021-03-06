package logic;

import commons.Field;

/**
 * Created by mihailozdravkovic on 5/15/18.
 */
public class FlipField {

    public static int[] logicToBoard = {
            46, 47, 48, 49, 50, 41, 42, 43, 44, 45, 36, 37, 38, 39, 40,
            31, 32, 33, 34, 35, 26, 27, 28, 29, 30, 21, 22, 23, 24, 25,
            16, 17, 18, 19, 20, 11, 12, 13, 14, 15,  6,  7,  8,  9, 10,
            1, 2, 3, 4, 5
    };

    public static int[] boardToLogic = {
            0, 45, 46, 47, 48, 49, 40, 41, 42, 43, 44, 35, 36, 37, 38, 39,
            30, 31, 32, 33, 34, 25, 26, 27, 28, 29, 20, 21, 22, 23, 24, 15,
            16, 17, 18, 19, 10, 11, 12, 13, 14, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4
    };

    public static Field[] convertBoardToLogic(Field[] board) {
        Field[] newField = new Field[board.length];

        for(int i = 0; i < board.length; i++) {
            newField[boardToLogic[i]] = board[i];
        }

        return newField;
    }
}
