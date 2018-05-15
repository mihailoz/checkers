import commons.Board;
import commons.Field;
import logic.TurnController;

public class LogicTest {
    public static void main(String[] args){




//        TurnController controller = new TurnController("PLAYER", board);
//        controller.makePathsForField(19);
//        for(int i : controller.availableFields()){
//            System.out.println(i);
//        }

        TurnController controller = new TurnController("PLAYER", new Board());
        controller.makePathsForField(19);
        for(int i : controller.availableFields()){
            System.out.println(i);
        }
    }

    static void normalBoard(Field[] board){
        for(int i=0;i<20;i++)
            board[i] = Field.OPPONENT_FIGURE;
        for(int i = 20; i<30;i++)
            board[i] = Field.EMPTY;
        for(int i=30;i<50;i++)
            board[i] = Field.PLAYER_FIGURE;
    }

    static void clearBoard(Field[] board){
        for(int i = 0;i<50;i++)
            board[i] = Field.EMPTY;
    }

    static void queenTest(Field[] board){
        board[16] = Field.PLAYER_QUEEN;

        board[22] = Field.OPPONENT_FIGURE;

        board[32] = Field.OPPONENT_FIGURE;

        board[33] = Field.OPPONENT_FIGURE;
    }

}
