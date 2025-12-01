import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static int lengthx = 0;
    public static int lengthy = 0;
    public static String[][] matrix;
    public static boolean[][] boolmatrix;
    public static ArrayList<Integer> paths = new ArrayList<>();
    public static int[] offsety = {0, 1, 0, -1};
    public static int[] offsetx = {1, 0, -1, 0}; 

    public static int area = 0;
    public static int perimeter = 0;
    public static int answer = 0;
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

        boolmatrix = new boolean[lengthy][lengthx];
        for(int i = 0; i < lengthy; i++)
        {
            for(int ii = 0; ii < lengthx; ii++)
            {
                boolmatrix[i][ii] = false;
            }
        }
        
        for(int i = 0; i < lengthy; i++)
        {
            for(int ii = 0; ii < lengthx; ii++)
            {
                if(!boolmatrix[i][ii])
                {
                    flood(i, ii, matrix[i][ii]);
                    answer = answer + area * perimeter;
                    area = 0;
                    perimeter = 0;
                }
            }
        }
        System.out.println(answer);
    }

    public static void flood(int y, int x, String plant)
    {
        if(!boolmatrix[y][x])
        {
            boolmatrix[y][x] = true;
            perimeter += 4;
            area++;
            int[] sides = {0, 0, 0, 0};
            int side = 0;
            int[] cornercheck = {0, 0, 0, 0};
            int[] diagonaloffsetx = {1, -1, -1, 1};
            int[] diagonaloffsety = {1, 1, -1, -1};
            for(int i = 0; i < 4; i++)
            {
                if(inbound(y + offsety[i], x + offsetx[i]) && matrix[y + offsety[i]][x + offsetx[i]].equals(plant) && boolmatrix[y + offsety[i]][x + offsetx[i]])
                {
                    sides[i] = 1;
                    side++;
                    cornercheck[(i + 8 - 1) % 4]++;
                    cornercheck[(i + 8) % 4]++;
                }
            }
            if(sides[0] == 1 && sides[2] == 1)
            {
                perimeter -= 8;
            }
            else if(sides[1] == 1 && sides[3] == 1)
            {
                perimeter -= 8;
            }
            else if(side == 0)
            {
                perimeter -= 0;
            }
            else if(side == 1)
            {
                perimeter -= 4;
            }
            else if (side == 2)
            {
                perimeter -= 6;
            }
            else
            {
                perimeter -= 8;
            }
            for(int i = 0; i < 4; i++)
            {
                if(inbound(y + diagonaloffsety[i], x + diagonaloffsetx[i]) && matrix[y + diagonaloffsety[i]][x + diagonaloffsetx[i]].equals(plant) && boolmatrix[y + diagonaloffsety[i]][x + diagonaloffsetx[i]])
                {
                    if(cornercheck[i] % 2 == 1)
                    {
                        perimeter = perimeter + 2;
                    }
                }
            }
            for(int i = 0; i < 4; i++)
            {
                if(inbound(y + offsety[i], x + offsetx[i]) && matrix[y + offsety[i]][x + offsetx[i]].equals(plant))
                {
                    flood(y + offsety[i], x + offsetx[i], plant);
                }
            }

        }
    }
}
