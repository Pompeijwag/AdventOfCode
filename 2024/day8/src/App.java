import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class App {
    public static int lengthx = 0;
    public static int lengthy = 0;
    public static String[][] matrix;

    public static ArrayList<Integer> position = new ArrayList<>();
    public static ArrayList<String> feq = new ArrayList<>();

    public static boolean inbound(int x, int y)
    {
        return(x >= 0 && x < lengthx && y >= 0 && y < lengthy);
    }

    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");         //get matrix
        Scanner scan = new Scanner(file).useDelimiter("");
        Scanner getlength = new Scanner(file).useDelimiter("");
        lengthx = getlength.nextLine().length();
        lengthy = 1;
        getlength.reset();
        getlength.useDelimiter("");
        while(getlength.hasNextLine())
        {
            getlength.nextLine();
            lengthy++;
        }
        getlength.reset();
        getlength.useDelimiter("");
        matrix = new String[lengthy][lengthx];
        int a = 0;
        while(scan.hasNextLine())
        {
            
            matrix[a] = scan.nextLine().split("");
            a++;
        }
        scan.close();
        getlength.close();

        for(int i = 0; i < lengthy; i++)            //get all the types of antennas
        {
            for(int ii = 0; ii < lengthx; ii++)
            {
                if(!matrix[i][ii].equals("."))
                {
                    if(!feq.contains(matrix[i][ii]))
                    {
                        feq.add(matrix[i][ii]);
                    }
                }
            }
        }

        String[][] clone = new String[lengthy][lengthx];    //make a clone for clarity
        for(int i = 0; i < lengthy; i++)
        {
            for(int ii = 0; ii < lengthx; ii++)
            {
                System.out.print(matrix[i][ii]);
                System.out.print(" ");
                clone[i][ii] = matrix[i][ii];
            }
            System.out.println(" ");
        }

        for(String n : feq)         //check for each type of antenna
        {
            ArrayList<int[]> antenna = new ArrayList<>();
            for(int i = 0; i < lengthy; i++)
            {
                for(int ii = 0; ii < lengthx; ii++)
                {
                    if(matrix[i][ii].equals(n))
                    {
                        int[] pos = {i, ii};
                        antenna.add(pos);
                        System.out.print("(");
                        System.out.print(i);
                        System.out.print(",");
                        System.out.print(ii);
                        System.out.print(")");
                    }
                }
            }

            for(int[] g : antenna)
            {
                for(int[] h : antenna)
                {
                    if(g != h)
                    {
                        int vectorx = h[1] - g[1];
                        int vectory = h[0] - g[0];
                        int checkingpositionx = h[1];
                        int checkingpositiony = h[0];
                        int repeat = 0;
                        while(inbound(checkingpositionx, checkingpositiony)) 
                        {
                            if(!position.contains(checkingpositiony * 100000 + checkingpositionx))
                            {
                                clone[checkingpositiony][checkingpositionx] = "#";
                                position.add(checkingpositiony * 100000 + checkingpositionx);
                            }
                            repeat++;
                            checkingpositiony = h[0] + repeat * vectory;
                            checkingpositionx = h[1] + repeat * vectorx;
                        }
                        checkingpositiony = g[0];
                        checkingpositionx = g[1];
                        repeat = 0;
                        while(inbound(checkingpositionx, checkingpositiony)) 
                        {
                            if(!position.contains(checkingpositiony * 100000 + checkingpositionx))
                            {
                                clone[checkingpositiony][checkingpositionx] = "#";
                                position.add(checkingpositiony * 100000 + checkingpositionx);
                            }
                            repeat++;
                            
                            checkingpositiony = g[0] - repeat * vectory;
                            checkingpositionx = g[1] - repeat * vectorx;
                        }
                    }
                }
            }
            System.out.print(position.size());
            System.out.println(" ");
        }

        for(int i = 0; i < lengthy; i++)
        {
            for(int ii = 0; ii < lengthx; ii++)
            {
                System.out.print(clone[i][ii]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
        System.out.println(position.size());
    }
}
