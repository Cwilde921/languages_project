import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.io.IOException;



class Assn6{
    public static void main(String args[])
    {   double[] P = new double[] {2,2,0,0};
        double Q[] = new double[] {2,2,0,0};
        double[] result = threeSubProblem(2,P,Q);
        for(int i = 0; i < result.length;i++) System.out.print(result[i] + " ");
        /*
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
                double totalthreeSubProblem = 0;
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
                    threeSubProblem(i,P,Q);
                    long nano_endTime = System.nanoTime();
                    long threeSubProblem = (nano_endTime - nano_startTime);
                    nano_startTime = System.nanoTime(); 
                    //The effiecient algorithm
                    highschool(i, P, Q);
                    nano_endTime = System.nanoTime();
                    long hsTime = (nano_endTime - nano_startTime);
                    nano_startTime = System.nanoTime();
                    //The inefficient algorithm
                    polyM(i, P, Q);
                    nano_endTime = System.nanoTime();
                    totalthreeSubProblem += threeSubProblem;
                    hsAverage += hsTime;
                    polyAverage +=nano_endTime - nano_startTime;
                }
                writer.write(i + " ");
                writer.write( hsAverage/10 + " ");
                writer.write( polyAverage/10 + " \n");
                writer.write( totalthreeSubProblem/10 + "\n");
                System.out.println("Finished "+i);
            }
            writer.close();
        }
        catch (IOException e)
        {
        }*/

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
    public static double[] threeSubProblem(int n, double[] P, double[] Q)
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
            double[] pArray = new double[n/2];
            double[] qArray = new double[n/2];
            for(int i = 0;i < n / 2; i++)
            {
                pArray[i] = P[i] + P[(n/2)+i];
                qArray[i] = Q[i] + Q[(n/2)+i];
            }

           double[] PQll = threeSubProblem(n/2, Arrays.copyOfRange(P,0,n/2), Arrays.copyOfRange(Q,0,n/2));
           //Creates and array with the first half of the P array and the 2nd half of the Q array
            double[] PQhh = threeSubProblem(n/2, Arrays.copyOfRange(P,n/2,n), Arrays.copyOfRange(Q,n/2,n));
            double[] PQllhh = threeSubProblem(n/2, Arrays.copyOfRange(pArray,0,n/2), Arrays.copyOfRange(qArray,0,n/2));

           for(int i = 0; i<n;i++)
           {
               //Filling in the arrays
               PQ[i] += PQll[i];
               PQ[i+(n/2)] += PQllhh[i] - PQhh[i]-PQll[i];
               PQ[i+n] += PQhh[i];
           }
        }
        return PQ;
    }

    
    //The new program
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

    public static double[] fft(int n, double[] P, double[] V)
    {
        if(n == 1)
        {
            return P;
        }
        double[] eve =arrayCopier(0, n, 2, P);
        double[] odd =arrayCopier(1, n, 2, P);
        double[] v2 = new double[n];
        for(int i = 0; i < n / 2;i++)
        {
            v2[i] = eve[i] * odd[i];
        }
        double[] eveS = fft(n/2, eve, v2);
        double[] oddS = fft(n/2, odd, v2);
        
    }

    public static double[] arrayCopier(int start, int end, int multiplier, double[] inputArry)
    {
        double[]  returnArray = new double[inputArry.length/multiplier];
        for(int i = start; start < end; start+=multiplier)
        {
            returnArray[i/multiplier] = inputArry[i];
        }
        return returnArray;
    }
}