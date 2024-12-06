import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static int lengthx = 0;
    public static int lengthy = 0;
    public static String[][] matrix;

    public static ArrayList<Integer> position = new ArrayList<>();
    
    public static boolean inbound(int x, int y)
    {
        return(x >= 0 && x < lengthx && y >= 0 && y < lengthy);
    }

    public static void main(String[] args) throws Exception {
         File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file).useDelimiter("");
        Scanner getlength = new Scanner(file).useDelimiter("");
        lengthx = getlength.nextLine().length();
        int answer2 = 0;
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


        int[] facingx = {0, 1, 0, -1};
        int[] facingy = {-1, 0, 1, 0};
        int facing = 0;
        int posy = Integer.MAX_VALUE;
        int posx = Integer.MAX_VALUE;
        int answer = 1;

        for(int i = 0; i < matrix.length; i++)
        {
            for(int ii = 0; ii < matrix[0].length; ii++)
            {
                if(matrix[i][ii].equals("^"))
                {
                    posy = i;
                    posx = ii;
                }
                if(matrix[i][ii].equals("#"))
                {
                    position.add(posy * 100000 + posx);
                }
                
            }
            if(posy != Integer.MAX_VALUE)
            {
                break;
            }
        }

        int guardpos = posy * 100000 + posx;

        while(inbound(posx + facingx[facing], posy + facingy[facing]))
        {

            String ob = matrix[posy + facingy[facing]][posx + facingx[facing]];
            if(ob.equals("#"))
            {
                facing++;
                if(facing > 3)
                {
                    facing = 0;
                }
            }
            else
            {
                String[][] clone = new String[lengthy][lengthx];
                for (int i = 0; i < lengthy; i++) {
                    for(int ii = 0; ii < lengthx; ii++){
                        clone[i][ii] = matrix[i][ii];
                    }
                }
                int cloneposy = posy;
                int cloneposx = posx;
                int clonefacing = facing;
                int doubleturn = 0;
                int turn = 0;
                int objectpos = (posy + facingy[clonefacing])* 100000 + (posx + facingx[clonefacing]);
                if(!(position.contains(objectpos)))
                {
                    clone[cloneposy + facingy[clonefacing]][cloneposx + facingx[clonefacing]] = "#";
                    while(inbound(cloneposx + facingx[clonefacing], cloneposy + facingy[clonefacing]))
                    {
                        String cloneob = clone[cloneposy + facingy[clonefacing]][cloneposx + facingx[clonefacing]];
                        if(cloneob.equals("#"))
                        {
                            turn++;
                            clonefacing++;
                            if(clonefacing > 3)
                            {
                                clonefacing = 0;
                            }
                            if(turn == 2)
                            {
                                if(doubleturn == 1)
                                {
                                    answer2++;
                                    position.add(objectpos);
                                    break;
                                }
                                doubleturn = 1;
                            }
                        }
                        else if(cloneob.equals("."))
                        {
                            turn = 0;
                            clone[cloneposy][cloneposx] = String.valueOf(clonefacing);
                            cloneposy = cloneposy + facingy[clonefacing];
                            cloneposx = cloneposx + facingx[clonefacing];
                        }
                        else
                        {
                            turn = 0;
                            if(cloneob.equals(String.valueOf(clonefacing)))
                            {
                                    answer2++;
                                    position.add(objectpos);
                                break;
                            }
                            clone[cloneposy][cloneposx] = String.valueOf(clonefacing);
                            cloneposy = cloneposy + facingy[clonefacing];
                            cloneposx = cloneposx + facingx[clonefacing];
                        }
                    }
                }
                
                if(ob.equals("."))
                {
                    answer++;
                    matrix[posy][posx] = String.valueOf(facing);
                    posy = posy + facingy[facing];
                    posx = posx + facingx[facing];
                }
                
                else
                {
                    matrix[posy][posx] = String.valueOf(facing);
                    posy = posy + facingy[facing];
                    posx = posx + facingx[facing];
                }
            }
            
        }
        System.out.println("");

        for(int i = 0; i < matrix.length; i++)
        {
            for(int ii = 0; ii < matrix[0].length; ii++)
            {
                System.out.print(matrix[i][ii]);
            }
            System.out.println("");
        }

        System.out.println(answer);
        System.out.println(answer2);
        System.out.println(position.contains(guardpos));
        System.out.println((guardpos));
    }
}
