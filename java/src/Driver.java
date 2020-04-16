package java.src;

public class Driver{

    private static Boolean nim_win(int n){
        if(n <= 0){
            return true;
        }
        else if(n == 1){
            return false;
        }
        else{
            return ! ( nim_win(n-1) & nim_win(n-2) );
        }
    }

    public static void main(String[] args) {
        long start_time = System.currentTimeMillis();
        Driver.nim_win(5);
        long end_time = System.currentTimeMillis();
        long elapsed_time = end_time - start_time;
        System.out.println("elapsed time: " + elapsed_time);
    }
};