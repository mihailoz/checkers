package logic;

import commons.Field;
import commons.Move;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static logic.BoardCalculator.getLeftUp;

public class TurnController {

    private List< List<Move>> paths;

    private ArrayList<Field> board;
    private int longestPath;

    private int tableEnd;
    private int direction;

    private int startPosition;
    private int distancePassed;

    //TODO modify this part of the code to fit the right parameters
    public TurnController(String player){
        if(player.equals("PLAYER")){
            tableEnd = 9;
            direction = 1;
        }
        else{
            tableEnd = 0;
            direction = -1;
        }
    }

    //generates paths for the selected checker
    public void makePathsForField(int i){
        startPosition = i;
        distancePassed = 0;
        paths = new ArrayList<>();

        //we only look at players checkers
        Field f = board.get(i);
        if(f.equals(Field.PLAYER_FIGURE) || f.equals(Field.PLAYER_QUEEN)){
            tryMove(i, i + 9, new ArrayList<Move>(), new ArrayList<Field>(board));

            tryMove(i, i + 11, new ArrayList<Move>(), new ArrayList<Field>(board));

            tryMove(i, i - 9, new ArrayList<Move>(), new ArrayList<Field>(board));

            tryMove(i, i - 11, new ArrayList<Move>(), new ArrayList<Field>(board));
        }
        //TODO instead of hardcoding 4 and 5, the values should be contained with client/host
        if(paths.size()==0){
            int next = 4*direction;
            if(checkBoarders(i,next) && board.get(i+next).equals(Field.EMPTY)){
                ArrayList<Move> list = new ArrayList<>();
                list.add(new Move(i, i+next, 1, board.get(i)));
                paths.add(list);
            }
            next = 5*direction;
            if(checkBoarders(i,next) && board.get(i+next).equals(Field.EMPTY)){
                ArrayList<Move> list = new ArrayList<>();
                list.add(new Move(i, i+next, 1, board.get(i)));
                paths.add(list);
            }
        }else{
            Iterator it = paths.iterator();
            while(it.hasNext()){
                List<Move> l = (List<Move>) it.next();
                if(l.size()<longestPath)
                    it.remove();
            }
        }
    }

    //5-(i%10-1)%2 ili tako nesto
    private void makePathsForQueen(int start){
        int leftUp = getLeftUp(start);
        if(leftUp!=-1){
            //TODO continue...
        }
    }

    //goes thru the board, finding all the paths
    private void tryMove(int start, int end, ArrayList<Move> list,
                         ArrayList<Field> board){
        boolean terminalState=false;
        if(end<0 || end>49){
            terminalState = true;
        }
        int diff = end - start;
        int middle = start + (diff+1)/2;

        if(!checkBoarders(start, diff)){
            terminalState = true;
        }

        Field endField = board.get(end);
        Field middleField = board.get(middle);

        if(!endField.equals(Field.EMPTY) ||
                !(middleField.equals(Field.OPPONENT_FIGURE) ||
                        (middleField.equals(Field.OPPONENT_QUEEN)))){
            terminalState = true;
        }


        if(!terminalState){

            ArrayList<Field> boardCopy = new ArrayList<>(board);
            boardCopy.set(middle, Field.EMPTY);

            ArrayList<Move> listCopy = new ArrayList<>(list);
            listCopy.add(new Move(start, end, 1, board.get(start)));

            tryMove(end, end + 9, listCopy, boardCopy);

            tryMove(end, end + 11, listCopy, boardCopy);

            tryMove(end, end - 9, listCopy, boardCopy);

            tryMove(end, end - 11, listCopy, boardCopy);
        }else{
            if(list.size() >= longestPath){
                longestPath = list.size();
                paths.add(list);
            }
        }

    }

    //makes sure that we can jump on the side we chose
    private boolean checkBoarders(int start, int diff){
        if(diff == 9){
            if(start%5 == 0 || start/10==4)
                return false;
        }else if(diff == 11){
            if(start%5==4 ||start/10==4)
                return false;
        }else if(diff == -9){
            if(start%5==4 || start/10 == 0)
                return  false;
        }
        else if(diff == -11){
            if(start%5 == 0 || start/10 == 0)
                return false;
        }
        return true;
    }

    //TODO a function that returns next possible fields after the piece is moved
    public List<Integer> availableFields(){
        ArrayList<Integer> list = new ArrayList<>();
        for(List<Move> l : paths){
            list.add(l.get(distancePassed).getEndPosition());
        }
        return list;
    }

    public void choosePath(int end){
        Iterator it = paths.iterator();
        while(it.hasNext()){
            List<Move> l = (List<Move>)it.next();
            if(l.get(distancePassed).getEndPosition() != end){
                it.remove();
            }
        }
        distancePassed++;
    }



}




//-9 je dole desno
//-11 je dole levo
//9 je gore levo
//11 je gore desno























































