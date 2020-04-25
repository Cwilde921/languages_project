using System;
// using System.Diagnostics;
// using System.Threading;

namespace assn12
{
    public class Assn3
    {
        //nim win function from cs5050 assn1
    
        public static int MED(int i, int j, String A, String B)
        {
            if(i == -1)
            {
                return j + 1;
            }
            if(j == -1)
            {
                return i + 1;
            }
            int myInt = A[i] == B[j] ? 0 : 1;
            return Math.Min(MED(i-1,j,A,B) + 1, 
                   Math.Min(MED(i,j-1,A,B) + 1, 
                            MED(i-1,j-1,A,B) + myInt));
        }
        
        public static int dpMED(int i, int j, String A, String B)
        {
            int[,] results = new int[i+1, j+1];
            for(int x  = 0; x <= i;x++)
            {
                for(int y = 0; y <= j; y++)
                {
                    if(x == 0)
                    {
                        results[x, y]= y;
                    }
                    else if(y == 0)
                    {
                    results[x, y]=  x;
                    }
                    else
                    {
                    int myInt = A[x] == B[y] ? 0 : 1;
                    results[x, y]=  Math.Min(results[x-1, y]+ 1, 
                                    Math.Min(results[x, y-1] + 1, 
                                            results[x-1, y-1] + myInt));
                    }
                }
            }
            return results[i, j];
        }
    }
}