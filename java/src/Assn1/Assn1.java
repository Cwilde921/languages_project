import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Assn1{

    public static void main(String[] args)
    {/*
        int i =0;
        long timeElapsed = 0;
        while(i < 79)
        {
            boolean[] Done = new boolean[i + 1];
            boolean[] W = new boolean[i+1];
            long startTime = System.nanoTime();
            newWin(i,Done,W);
            long endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            /*if(currentValue)
                System.out.println("The number is a win and is : " + i);
            else{
                System.out.println("The number isn't a win and is : " + i);
            }
            System.out.println(timeElapsed + " " + i);
            i++;
        }*/
    	game();
    }
	//This is the recursive 
    static boolean win(int N)
    {
        if(N == 0)
            return true;
        if(N == 1)
            return false;
        return !(win(N-1) && win(N-2));
    }

    static boolean newWin(int N,boolean[] Done, boolean[] W)
    {
        if(N == 0)
            return true;
        if(N == 1)
            return false;
        if(Done[N])
            return W[N];
        W[N] = !(newWin(N-1,Done,W) && newWin(N-2,Done,W));
        Done[N] = true;
        return W[N];
    }
    
    static void game()
    {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    	System.out.println("How many stones do you want to start with?");
    	int amount = myObj.nextInt();
    	int removeAmount;
    	boolean[] Done = new boolean[amount + 1];
        boolean[] W = new boolean[amount+1];
    	boolean Turn = true;
    	while(amount > 0)
    	{
    		System.out.println("The amount remaining is " + amount);
    		if(Turn)
    		{
    			System.out.println("How many stones do you like to remove?");
            	removeAmount = myObj.nextInt();
            	amount -= removeAmount;
            	Turn = !Turn;
    		}
    		else
    		{
    			if(newWin(amount - 1, Done, W) != true || amount - 2 == -1) {
    				removeAmount = 1;
    			}
    			else {
    				removeAmount = 2;
    			}
    			System.out.println("The computer removed " + removeAmount);

            	amount -= removeAmount;
            	Turn = !Turn;
    		}
    		
    	}
    	if(Turn)
    	{
    		System.out.println("The human won");
    	}
    	else
    	{
    		System.out.println("The computer won");

    	}
    	myObj.close();
    }
}