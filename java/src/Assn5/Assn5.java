import java.lang.reflect.Array;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.lang.Math;
import java.util.Random;
import java.io.IOException;



class Assn5{
    public static void main(String args[])
    {
        try
        {
            File file = new File("output.txt");
            //Create the file
            if (file.createNewFile())
            {
                System.out.println("File is created!");
            } 
            else {
                System.out.println("File already exists.");
            }
            
            //Write Content
            FileWriter writer = new FileWriter(file);
            for(int i = 32; i <=65_536;i *=2) 
            {   
                double hsAverage = 0;
                double polyAverage = 0;
                for(int q = 0; q < 10; q++)
                {    
                    Random rd = new Random(); // creating Random object
                    double[] P = new double[i];
                    double Q[] = new double[i];
                    for (int j = 0; j < P.length; j++) {
                        P[j] = (2* rd.nextDouble()) - 1;
                        Q[j] = (2* rd.nextDouble()) - 1;
                    }
                    //Starts the timing for the ineffecienct algorithm
                    long nano_startTime = System.nanoTime(); 
                    //The effiecient algorithm
                    highschool(i, P, Q);
                    long nano_endTime = System.nanoTime();
                    long hsTime = (nano_endTime - nano_startTime);
                    //System.out.println("The time for " + i + " is: " + hsTime);
                    nano_startTime = System.nanoTime();
                    //The inefficient algorithm
                    polyM(i, P, Q);
                    nano_endTime = System.nanoTime();
                    //System.out.println("The time for " + i + " is: " + (nano_endTime - nano_startTime));
                    hsAverage += hsTime;
                    polyAverage +=nano_endTime - nano_startTime;
                }
                writer.write(i + " ");
                writer.write( hsAverage/10 + " ");
                writer.write( polyAverage/10 + " \n");
                System.out.println("Finished "+i);
            }
            writer.close();
        }
        catch (IOException e)
        {
        }

    }

    //This is the effiecient algorithm
    //This uses dynamic programming
    public static double[] highschool(int n, double[] P, double[] Q) {
        double[] PQ = new double[2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                PQ[i + j] += P[i] * Q[j];
            }
        }
        return PQ;
    }
    //The inefficient program
    public static double[] polyM(int n, double[] P, double[] Q)
    {
        double[] PQ = new double[2*n];
        //Base case
        if(n == 1)
        {
            PQ[0] = P[0]*Q[0];
            return PQ;
        }
        else{
            //Initializes all the arrays for the 4 halfs of the algorithm
            //Creates an array with the first halves of the arrays

           double[] PQll = polyM(n/2, Arrays.copyOfRange(P,0,n/2), Arrays.copyOfRange(Q,0,n/2));
           //Creates and array with the first half of the P array and the 2nd half of the Q array
           double[] PQlh = polyM(n/2, Arrays.copyOfRange(P,0,n/2), Arrays.copyOfRange(Q,n/2,n));
            //Creates and array with the first half of the Q array and the 2nd half of the P array
           double[] PQhl = polyM(n/2, Arrays.copyOfRange(P,n/2,n), Arrays.copyOfRange(Q,0,n/2));
            //Creates an array with the second halves of the arrays
           double[] PQhh = polyM(n/2, Arrays.copyOfRange(P,n/2,n), Arrays.copyOfRange(Q,n/2,n));
           for(int i = 0; i<n;i++)
           {
               //Filling in the arrays
               PQ[i] += PQll[i];
               PQ[i+(n/2)] += PQlh[i];
               PQ[i+(n/2)] += PQhl[i];
               PQ[i+n] += PQhh[i];
           }
        }
        return PQ;
    }
}