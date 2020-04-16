import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Assn3 {
  public static void main(String[] args){
    String filename = "test.txt";
    List<Tuple<String, Integer>> tuples = new ArrayList<Tuple<String, Integer>>();
    try
    {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line;
      while ((line = reader.readLine()) != null)
      {
        String[] arrOfStr = line.split("->", 2);
        String line1 = " " + arrOfStr[0];
        for(String s : arrOfStr[1].split(","))
        {
          String s1 = arrOfStr[0] + "," + s;
          String s2 = " " + s;
          int amount = dpMED(arrOfStr[0].length(), s.length(), line1, s2);
          tuples.add(new Tuple<String, Integer>(s1, amount));
        }
      }
      reader.close();
    } 
    catch (Exception e) {
      System.err.format("Exception occurred trying to read '%s'.", filename);
      e.printStackTrace();
    }

    Comparator<Tuple<String, Integer>> comparator = new Comparator<Tuple<String, Integer>>()
    {

        public int compare(Tuple<String, Integer> tupleA,
                Tuple<String, Integer> tupleB)
        {
            return tupleA.getIndex().compareTo(tupleB.getIndex());
        }

    };

    Collections.sort(tuples, comparator);
    int[] amount = new int[tuples.get(tuples.size()-1).getIndex() + 1];
    for(int i = 0; i < tuples.size();i++)
    {
      amount[tuples.get(i).getIndex()]++;
      /*for (Tuple<String, Integer> tuple : tuples)
      {
          System.out.println(tuple.getName() + ": " + tuple.getIndex());
      }*/
    }
    try{
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


      for(int i = 0; i < amount.length;i++)
      {
        writer.write("Distance of " +i + " has "+ amount[i] + " results\n");
      }
      writer.write("Results with max distance:\n");
      int i = tuples.size()-1;
      int j = amount.length-1;
      while(tuples.get(i).getIndex() == j)
      {
        writer.write("\t" + tuples.get(i).getName());
        i--;
      }
      writer.close();
    }
    catch (IOException e)
    {
    }
  }
  //Recursive call
  public static int MED(int i, int j, String A, String B)
  {
      if(i == 0)
      {
          return j;
      }
      if(j == 0)
      {
          return i;
      }
      int myInt = A.charAt(i) == B.charAt(j) ? 1 : 0;
      return Math.min(MED(i-1,j,A,B) + 1, Math.min(MED(i,j-1,A,B) + 1, MED(i-1,j-1,A,B) + myInt));
  }
  //DP call
  public static int dpMED(int i, int j, String A, String B)
  {
    int results[][] = new int[i+1][j+1];
    for(int x  = 0; x <= i;x++)
    {
      for(int y = 0; y <= j; y++)
      {
        if(x == 0)
        {
            results[x][y]= y;
        }
        else if(y == 0)
        {
          results[x][y]=  x;
        }
        else
        {
          int myInt = A.charAt(x) == B.charAt(y) ? 0 : 1;
          results[x][y]=  Math.min(results[x-1][y]+ 1, Math.min(results[x][y-1] + 1, results[x-1][y-1] + myInt));
        }
      }
    }
    return results[i][j];
  }
}