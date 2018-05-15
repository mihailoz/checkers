package logic;

import commons.Board;
import commons.Field;
import commons.Move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static logic.BoardCalculator.*;

public class TurnController {

    private List< List<Move>> paths;

    private ArrayList<Field> fields;
    private int longestPath;
    private Board board;
    private Field player;

    private int tableEnd;
    private int direction;

    private int startPosition;
    private int distancePassed;
    private Calculator leftCalc;
    private Calculator rightCalc;
    private boolean turnOver;

    //TODO modify this part of the code to fit the right parameters
    public TurnController(String player, Board board){

        this.board = board;
        this.fields = new ArrayList<Field>(Arrays.asList(board.getFields()));
        if(player.equals("PLAYER")){
            tableEnd = 9;
            leftCalc = BoardCalculator.getLeftUp2();
            rightCalc = BoardCalculator.getRightUp2();
            direction = 1;
        }
        else{
            tableEnd = 0;
            direction = -1;
            leftCalc = BoardCalculator.getLeftDown2();
            rightCalc = BoardCalculator.getRightDown2();
        }
    }

    //generates paths for the selected checker
    public void makePathsForField(int i){
        startPosition = i;
        distancePassed = 0;
        longestPath = -1;
        paths = new ArrayList<>();
        Field f = fields.get(i);

        //if the checker is a queen
        //should all be good
        if(f.equals(Field.PLAYER_QUEEN)){
            makePathsForQueen(i, fields,new ArrayList<Move>());
            if(paths.size()==0){
                for(Calculator c : directions()){
                    int next = c.getNext(i);
                    while(next!=-1 && fields.get(next)==Field.EMPTY){
                        ArrayList<Move> simpleList = new ArrayList<>();
                        simpleList.add(new Move(i, next, -1, fields.get(i)));
                        paths.add(simpleList);
                        next = c.getNext(next);
                    }
                }
            }
        }

        //if checker is a regular checker
        if(f.equals(Field.PLAYER_FIGURE)){

            makePathsForPawn(i, fields, new ArrayList<Move>());

            if(paths.size()==0){
                int left = leftCalc.getNext(i);
                if(left != -1 && fields.get(left)==Field.EMPTY){
                    ArrayList<Move> list = new ArrayList<>();
                    list.add(new Move(i, left, -1, fields.get(i)));
                    longestPath = 1;
                    paths.add(list);
                }

                int right = rightCalc.getNext(i);
                if(right!=-1 && fields.get(right)==Field.EMPTY){
                    ArrayList<Move> list = new ArrayList<>();
                    list.add(new Move(i, right, -1, fields.get(i)));
                    paths.add(list);
                    longestPath = 1;
                }
            }
        }

            Iterator it = paths.iterator();
            while(it.hasNext()){
                List<Move> l = (List<Move>) it.next();
                if(l.size()<longestPath)
                    it.remove();
            }

    }




    private void makePathsForPawn(int start, ArrayList<Field> board, ArrayList<Move> list){
        boolean terminalState = true;

        for(Calculator c: directions()){
            int next = c.getNext(start);
            if(next!=-1 && (board.get(next)==Field.OPPONENT_FIGURE || board.get(next)==Field.OPPONENT_QUEEN)){
                int end = c.getNext(next);
                if(end!=-1 && board.get(end)==Field.EMPTY){
                    ArrayList<Field> boardCopy = new ArrayList<>(board);
                    boardCopy.set(next, Field.EMPTY);
                    ArrayList<Move> listCopy = new ArrayList<>(list);
                    listCopy.add(new Move(start, end,next, fields.get(start)));
                    makePathsForPawn(end, boardCopy, listCopy);
                    terminalState = false;
                }
            }
        }

        if(terminalState){
            if(list.size()>=longestPath && list.size()!=0){
                longestPath = list.size();
                paths.add(list);
            }
        }
    }

    //5-(i%10-1)%2 ili tako nesto
    private void makePathsForQueen(int start, ArrayList<Field> board, ArrayList<Move> list){
        boolean terminalState = true;

        for(Calculator c: directions()) {
            int next = c.getNext(start);
            while(next!=-1 && board.get(next)!=Field.OPPONENT_FIGURE && board.get(next)!=Field.OPPONENT_QUEEN){
                next = c.getNext(next);
            }
            if(next!=-1 & c.getNext(next)!=-1){
                ArrayList<Field> boardCopy = new ArrayList<>(board);
                boardCopy.set(next, Field.EMPTY);
                int end = c.getNext(next);
                while(end!=-1 && board.get(end)==Field.EMPTY){
                    ArrayList<Move> listCopy = new ArrayList<>(list);
                    listCopy.add(new Move(start, end,next, fields.get(start)));
                    makePathsForQueen(end,boardCopy, listCopy);
                    terminalState = false;
                    end = c.getNext(end);
                }
            }
        }

        if(terminalState){
            if(list.size()>=longestPath && list.size()!=0){
                longestPath = list.size();
                paths.add(list);
            }
        }
    }



    //TODO a function that returns next possible fields after the piece is moved
    //this is kind of it, but it needs to be better
    public List<Integer> availableFields(){
        ArrayList<Integer> list = new ArrayList<>();
        if(distancePassed<longestPath)
            for(List<Move> l : paths){
                list.add(l.get(distancePassed).getEndPosition());
            }
        return list;
    }

    public void choosePath(int end){
        Iterator it = paths.iterator();
        Move m=null;
        while(it.hasNext()){
            List<Move> l = (List<Move>)it.next();
            if(l.get(distancePassed).getEndPosition() != end){
                it.remove();
            }else{
                m = l.get(distancePassed);
            }
        }if(m!=null) {
            board.setField(Field.EMPTY, m.getStartPosition());
            board.setField(Field.EMPTY, m.getEatenPosition());
            board.setField(m.getFigure(), m.getEndPosition());
            if((m.getEndPosition()-1)/5==tableEnd){
                if(m.getFigure()==Field.PLAYER_FIGURE)
                    board.setField(Field.PLAYER_QUEEN, m.getEndPosition());
            }
        }
        distancePassed++;
        if(distancePassed==longestPath)
            turnOver = true;
    }

    public boolean isTurnOver(){
        return turnOver;
    }



}




//-9 je dole desno
//-11 je dole levo
//9 je gore levo
//11 je gore desno























































