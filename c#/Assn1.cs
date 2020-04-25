using System;
// using System.Diagnostics;
// using System.Threading;

namespace assn12
{
    public class Assn1
    {
        //nim win function from cs5050 assn1
        public static bool nim_win(int n)
        {
            Console.WriteLine("in assn1 nim");
            if(n <= 0)
            {
                return true;
            }
            else if(n == 1)
            {
                return false;
            }
            else
            {
                return ! ( nim_win(n-1) && nim_win(n-2) );
            }
        }
        // static void Main(string[] args)
        // {
        //     Console.WriteLine("Hello World from assn1");
        //     var stopwatch = new Stopwatch();
        //     stopwatch.Start();
        //     nim_win(5);
        //     stopwatch.Stop();
        //     var elapsed_time = stopwatch.Elapsed;
        //     Console.WriteLine("elapsed time: {0}", elapsed_time);
        // }
    }
}