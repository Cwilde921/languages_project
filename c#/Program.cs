using System;
using System.Diagnostics;
using System.Threading;
// using assn1;

namespace assn12
{
    class Program
    {
        //nim win function from cs5050 assn1
        static bool nim_win(int n)
        {
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
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            var stopwatch = new Stopwatch();

            String a = "abcdefghksdkd";
            String b = "abcdefghijkkd";
            int al = a.Length;
            int bl = b.Length;
            Console.WriteLine("\nFirst string:  {0}\nSecond string: {1}", a, b);

            stopwatch.Start();
            Console.WriteLine("Minimum edit distance: {0}", Assn3.MED(al-1, bl-1, a, b));
            stopwatch.Stop();
            var elapsed_time1 = stopwatch.Elapsed;
            Console.WriteLine("elapsed time: {0}", elapsed_time1);
            stopwatch.Start();
            Console.WriteLine("Minimum edit distance: {0}", Assn3.dpMED(al-1, bl-1, a, b));
            stopwatch.Stop();
            var elapsed_time2 = stopwatch.Elapsed;
            Console.WriteLine("elapsed time dp: {0}", elapsed_time2);
        }
    }
}
