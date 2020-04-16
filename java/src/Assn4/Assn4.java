import java.io.*;

class Assn4 {
  public static void main(String[] args) throws IOException {
    //The variables for all the files used or could be used
    String human = "human.txt";
    String hVnOutput = "humanVSneaderthal.txt";
    String hVaOutput = "humanVSapp.txt";
    String neander = "neadnerthal.txt";
    String neanderVSapeOutput = "neanderVSapes.txt";
    String ape = "ape.txt";
    //Initializing string variables
    String a = "";
    String b = "";
    //Starting the file write
    FileWriter fw=new FileWriter(hVnOutput);
    //These open the files and save them as strings
    try {
      a = readFromFile(human);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      b = readFromFile(neander);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //Array with all the results
    int maxresults[][] = {{ 5, -1, -2, -1, -3 }, 
                          { -1, 5, -3, -2, -4 }, 
                          { -2, -3, 5, -2, -2 }, 
                          { -1, -2, -2, 5, -1 },
                          { -3, -4, -2, -1, 0 } };
    //Saves results from string comparison in this array
    int[][] dpResults = dpMED(a.length(), b.length(), a, b, maxresults);
    //String array with both strings added
    String[] stringresults = backTracer(a.length(), b.length(), a, b, dpResults, maxresults);
    //Iterates through string and writes results to file
    for (int i = 0; i < stringresults[0].length(); i++) {
      String resultingString = "";
      if (stringresults[0].charAt(i) == stringresults[1].charAt(i)) {
        resultingString = stringresults[0].charAt(i) + " = " + stringresults[1].charAt(i) + "\n";
      } else {
        resultingString = stringresults[0].charAt(i) + " -> " + stringresults[1].charAt(i) + "\n";

      }
      fw.write(resultingString);
    }
    //Prints comparison results number
    fw.write("Results of file is: " +dpResults[a.length()][b.length()]);
    //Closes the file writer
    fw.close();
    System.out.println(dpResults[a.length()][b.length()]);
  }
  //Processs the file and returns as string
  public static String readFromFile(String inputString) throws FileNotFoundException {
    File file = new File(inputString);
    BufferedReader br = new BufferedReader(new FileReader(file));
    String returnString = "";
    String st;
    String newString;
    //Removes unnessecary characters
    try {
      while ((st = br.readLine()) != null){
        newString= st.replace("n","");
        newString=newString.replace("N", "");
        newString=newString.replaceAll("\\s","");
        returnString+=newString;
      }
        //System.out.println(st);}
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
      
      return returnString;
    }
    //DP call
    public static int[][] dpMED(int i, int j, String A, String B,int[][] maxresults)
    {
      //Initializes the array
      int results[][] = new int[i+1][j+1];
      //Fills in the base cases
      for(int x = 1;x <= i;++x)
      {
        results[x][0] = results[x-1][0] + maxresults[charToInt(A.charAt(x-1))][charToInt('X')];
      }
      for(int y = 1; y <= j;++y)
      {
        results[0][y] = results[0][y-1] + maxresults[charToInt(B.charAt(y-1))][charToInt('X')];
      }
      int  replaceY;
      int  replaceX;
      int matchOrMismatch;
      //Iterates through first string
      for(int x  = 1; x <= i;x++)
      {
        //Iterates through second string
        for(int y = 1; y <= j; y++)
        { 
          //This hold the value if y doesn't exist
          replaceY = results[x][y-1] + maxresults[charToInt('X')][charToInt(B.charAt(y-1))];
          //Holds the value if x doesnt exist
          replaceX = results[x-1][y] + maxresults[charToInt(A.charAt(x-1))][charToInt('X')];
          //Holds value if it is a match or mismatch
          matchOrMismatch = results[x-1][y-1] + maxresults[charToInt(A.charAt(x-1))][charToInt(B.charAt(y-1))];
          results[x][y]=  Math.max(replaceX,
                          Math.max(replaceY,
                                    matchOrMismatch)) ;
          //System.out.println("results["+x+"]["+y+"]=" + results[x][y]);
        }
      }
      return results;
    }
    //Converts the characters to be used in the analysis matrix
    public static int charToInt(char inputChar)
    {
      char switchChar = Character.toUpperCase(inputChar);
      switch(switchChar){
        case 'A':
          return 0;
        case 'C':
          return 1;
        case 'G' :
          return 2;
        case 'T' :
          return 3;
        default:
          return 4;
        }
    }
  
    //This is the back tracer
    public static String[] backTracer(int x, int y,String s, String t, int[][] dpResults,int[][] maxresults)
    {
      //Initialize the variable
      String tString = "";
      String sString = "";
      int xInt = x;
      int yInt = y;
      //While loop to iterate though
      while(xInt > 0 || yInt > 0)
      {
        //If the t string is empty, add insert 
        if(yInt == 0)
        {
          sString = s.charAt(xInt) + sString;
          tString = "_" + tString;
          xInt--;
        }
        //If the s string is empty, add insert 
        else if(xInt == 0)
        {
          tString = t.charAt(yInt) + tString;
          sString = "_" + sString;
          yInt--;
        }
        //Sees if it's a match
        else if (dpResults[xInt][yInt] ==  dpResults[xInt-1][yInt-1] + maxresults[charToInt(s.charAt(xInt-1))][charToInt(t.charAt(yInt-1))])
        {
          tString = t.charAt(yInt-1) + tString;
          sString = s.charAt(xInt-1) + sString;
          yInt--;
          xInt--;
        }
        else if (dpResults[xInt][yInt] ==  dpResults[xInt-1][yInt] + maxresults[charToInt(s.charAt(xInt-1))][charToInt(t.charAt(yInt))])
        {
          sString = s.charAt(xInt-1) + sString;
          tString = "_" + tString;
          xInt--;
        }
        else{
          tString = t.charAt(yInt-1) + tString;
          sString = "_" + sString;
          yInt--;
        }
      }
      return new String[] {sString,tString};
    }
  }