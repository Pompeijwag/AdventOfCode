import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
     public static int lengthx = 0;
    public static int lengthy = 0;
    public static String[][] matrix;
    public static ArrayList<Integer> paths = new ArrayList<>();
    public static int[] offsety = {0, 1, 0, -1};
    public static int[] offsetx = {1, 0, -1, 0}; 
    public static int currentx = 0;
    public static int currenty = 0;
    public static boolean inbound(int x, int y)
    {
        return(x >= 0 && x < lengthx && y >= 0 && y < lengthy);
    }
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");  //get matrix
        File file1 = new File("..\\lib\\input1.txt");     
        Scanner scan1 = new Scanner(file1).useDelimiter(""); 
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
        for(int i = 0; i < lengthy; i++)
        {
            for(int ii = 0; ii < lengthx; ii++)
            {
                System.out.print(matrix[i][ii]);
                if(matrix[i][ii].equals("@"))
                {
                    currenty = i;
                    currentx = ii;
                }
            }
            System.out.println("");
        }
        System.out.println(currenty);
        System.out.println(currentx);
        
        while(scan1.hasNext())
        {
            String ins = scan1.next();
            switch(ins)
            {
                case ">":
                    walk(0);
                    break;
                case "v":
                    walk(1);
                    break;
                case "<":
                    walk(2);
                    break;
                case "^":
                    walk(3);
                    break;

            }  
            for(int i = 0; i < lengthy; i++)
            {
                for(int ii = 0; ii < lengthx; ii++)
                {
                    System.out.print(matrix[i][ii]);
                }
                System.out.println("");
            }
            System.out.print(currenty);
            System.out.print(", ");
            System.out.print(currentx);
            System.out.println("");
            
        }

        for(int i = 0; i < lengthy; i++)
        {
            for(int ii = 0; ii < lengthx; ii++)
            {
                System.out.print(matrix[i][ii]);
            }
            System.out.println("");
        }
    }

    public static void walk(int direction)
    {
        if(matrix[currenty + offsety[direction]][currentx + offsetx[direction]].equals("."))
        {
            matrix[currenty + offsety[direction]][currentx + offsetx[direction]] = matrix[currenty][currentx];
            matrix[currenty][currentx] = ".";
            currenty = currenty + offsety[direction];
            currentx = currentx + offsetx[direction];
        }
        else if(matrix[currenty + offsety[direction]][currentx + offsetx[direction]].equals("O"))
        {
            int iteration = 2;
            while(true)
            {
                if(matrix[currenty + (iteration * offsety[direction])][currentx + (iteration * offsetx[direction])].equals("."))
                {
                    matrix[currenty + offsety[direction]][currentx + offsetx[direction]] = matrix[currenty][currentx];
                    matrix[currenty][currentx] = ".";
                    matrix[currenty + (iteration * offsety[direction])][currentx + (iteration * offsetx[direction])] = "O";
                    currenty = currenty + offsety[direction];
                    currentx = currentx + offsetx[direction];    
                }
                else if(matrix[currenty + (iteration * offsety[direction])][currentx + (iteration * offsetx[direction])].equals("#"))
                {
                    break;
                }
                iteration++;
            }
        }
    }
}
