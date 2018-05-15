package logic;

public class BoardCalculator {
    public static int getLeftUp(int pos){
        if(pos/5==9 || pos%10==0)
            return -1;
        return 4+(pos/5)%2;
    }

    public static int getRightUp(int pos){
        if(pos/5==9 || pos%10==9)
            return -1;
        return 5+(pos/5)%2;
    }

    public static int getLeftDown(int pos){
        if(pos/5==0 || pos%10==0)
            return -1;
        return -6 + (pos/5)%2;
    }

    public static int getRightDown(int pos){
        if(pos/5==0 || pos%10==9)
            return -1;
        return -5 + (pos/5)%2;
    }
}
