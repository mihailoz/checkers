package logic;

public class BoardCalculator {

    public static interface Calculator{
        int getNext(int pos);
    }

    public static Calculator[] directions(){
        Calculator[] c = new Calculator[4];
        c[0] = BoardCalculator.getLeftUp();
        c[1] = BoardCalculator.getRightUp();
        c[2] = BoardCalculator.getLeftDown();
        c[3] = BoardCalculator.getRightDown();
        return c;
    }

    public static Calculator getLeftUp(){
        return (int pos) ->{
            if(pos/5==9 || pos%10==0)
                return -1;
            return pos+4+(pos/5)%2;
        };
    }

    public static Calculator getRightUp(){
        return (int pos) ->{
            if(pos/5==9 || pos%10==9)
                return -1;
            return pos+5+(pos/5)%2;
        };
    }

    public static Calculator getLeftDown(){
        return (int pos) ->
        {
            if(pos/5==0 || pos%10==0)
                return -1;
            return pos -6 + (pos/5)%2;
        };
    }

    public static Calculator getRightDown(){
        return (int pos) -> {
            if(pos/5==0 || pos%10==9)
                return -1;
            return pos -5 + (pos/5)%2;
        };
    }

//    public static int getLeftUp(int pos){
//        if(pos/5==9 || pos%10==0)
//            return -1;
//        return 4+(pos/5)%2;
//    }

//    public static int getRightUp(int pos){
//        if(pos/5==9 || pos%10==9)
//            return -1;
//        return 5+(pos/5)%2;
//    }

//    public static int getLeftDown(int pos){
//        if(pos/5==0 || pos%10==0)
//            return -1;
//        return -6 + (pos/5)%2;
//    }

//    public static int getRightDown(int pos){
//        if(pos/5==0 || pos%10==9)
//            return -1;
//        return -5 + (pos/5)%2;
//    }

    //makes sure that we can jump on the side we chose
    public static boolean checkBoarders(int start, int diff){
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
}
