import java.io.File;
import java.util.Scanner;

public class App {
    public static int lengthx;
    public static int lengthy;
    public static String[][] matrix;

    public static boolean inbound(int x, int y)
    {
        return(x >= 0 && x < lengthx && y >= 0 && y < lengthy);
    }

    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
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

        question1();
        question2();
    }

    public static void question1()
    {
        
        int answer = 0;
        int[] xoffset = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] yoffset = {0, 1, 1, 1, 0, -1, -1, -1};
        String[] match = {"M", "A", "S"};
        for(int y = 0; y < matrix.length; y++)
        {
            for(int x = 0; x < matrix[0].length; x ++)
            {
                if(matrix[y][x].equals("X"))
                {
                    for(int i = 0; i < 8; i++)
                    {
                        int xdepth = x;
                        int ydepth = y;
                        for(int ii = 0; ii < 3; ii++)
                        {
                            xdepth += xoffset[i];
                            ydepth += yoffset[i];
                            if(inbound(xdepth, ydepth))
                            {
                                if(!matrix[ydepth][xdepth].equals(match[ii]))
                                {
                                    break;
                                }
                                if(ii == 2)
                                {
                                    answer++;
                                }
                            }
                            else
                            {
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static void question2()
    {
        int answer = 0;
        int[] xoffset = {-1, 1, 1, -1, -1, 1, 1, -1};
        int[] yoffset = {-1, 1, 1, -1, 1, -1, -1, 1};
        String[] match = {"M","S"};
        for(int y = 0; y < matrix.length; y++)
        {
            for(int x = 0; x < matrix[0].length; x ++)
            {
                if(matrix[y][x].equals("A"))
                {
                    int diagonal = 0;
                    for(int i = 0; i < 4; i++)
                    {
                        int xdepth = x;
                        int ydepth = y;
                        
                        for(int ii = 0; ii < 2; ii++)
                        {
                            if(inbound(xdepth + xoffset[i * 2 + ii], ydepth + yoffset[i * 2 + ii]))
                            {
                                if(!matrix[ydepth + yoffset[i * 2 + ii]][xdepth + xoffset[i * 2 + ii]].equals(match[ii]))
                                {
                                    break;
                                }
                                else if(ii == 1)
                                {
                                    
                                    diagonal++;
                                }
                            }
                            else
                            {
                                break;
                            }
                        }
                        if(diagonal >= 2)
                        {
                            answer++;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
