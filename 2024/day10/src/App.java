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
        for(int i = 0; i < lengthy; i++)
        {
            for(int ii = 0; ii < lengthx; ii++)
            {
                System.out.print(matrix[i][ii]);
            }
            System.out.println("");
        }

        int answer = 0;
    
        for(int i = 0; i < lengthy; i++)
        {
            for(int ii = 0; ii < lengthx; ii++)
            {
                if(matrix[i][ii].equals("0"))
                {
                    for(int iii = 0; iii < 4; iii++)
                    {
                        if(inbound(i + offsety[iii], ii + offsetx[iii]))
                        {
                            flood(i + offsety[iii], ii + offsetx[iii], 1);
                        }
                    }
                    answer = answer + paths.size();
                    paths.clear();
                }

            }
        }
        System.out.println(answer);
    }

    public static void flood(int y, int x, int height)
    {
        if(Integer.parseInt(matrix[y][x]) == height)
        {
            if(height == 9)
            {
                paths.add(1);

                // if(!paths.contains(y * 1000 + x)) //part 1
                // {
                //     paths.add(y * 1000 + x);
                // }
            }
            else
            {
                for(int iii = 0; iii < 4; iii++)
                {
                    if(inbound(y + offsety[iii], x + offsetx[iii]))
                    {
                        flood(y + offsety[iii], x + offsetx[iii], height + 1);
                    }
                }
            }

        }
    }
}
