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
    }
}