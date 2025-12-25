import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static Character[][] grid;
    public static int x = 0;
    public static int y = 0;
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file);
        Scanner getlength = new Scanner(file);
        
        x = 0;
        y = 0;
        while(getlength.hasNext()){
            if(y == 0){
                x = getlength.next().length();
            }
            else{
                getlength.next();
            }
            y++;
        }
        System.err.println("x " + x);
        System.err.println("y " + y);
        grid = new Character[y][x];
        int row = 0;
        while(scan.hasNext()){
            String a = scan.next();
            for (int i = 0; i < a.length(); i++) {
                grid[row][i] = a.charAt(i);
            }
            row++;
        }




        scan.close();
        getlength.close();
        System.out.println(Arrays.deepToString(grid));
        part1();
        part2();
    }

    public static void part1(){
        int[][] positions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int answer = 0;
        for (int i = 0; i < y; i++) {
            for (int ii = 0; ii < x; ii++) {
                int count = 0;
                if(grid[i][ii].equals('@')){
                    for(int iii = 0; iii < 8; iii++){
                        int ycheck = i + positions[iii][0];
                        int xcheck = ii + positions[iii][1];
                        if(valid(ycheck, xcheck)){
                            if(grid[ycheck][xcheck].equals('@')){
                                count++;
                            }
                        }
                    }
                    if (count < 4){
                    answer++;
                    }
                }
                
            }
        }
        System.err.println(answer);
    }

    public static void part2(){
        boolean changed = true;
        Character[][] newgrid = new Character[y][x];
        int answer = 0;
        while(changed){
            changed = false;
            int[][] positions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
            for (int i = 0; i < y; i++) {
                for (int ii = 0; ii < x; ii++) {
                    int count = 0;
                    if(grid[i][ii].equals('@')){
                        for(int iii = 0; iii < 8; iii++){
                            int ycheck = i + positions[iii][0];
                            int xcheck = ii + positions[iii][1];
                            if(valid(ycheck, xcheck)){
                                if(grid[ycheck][xcheck].equals('@')){
                                    count++;
                                }
                            }
                        }
                        if (count < 4){
                            answer++;
                            newgrid[i][ii] = '.';
                            changed = true;
                        }
                        else{
                            newgrid[i][ii] = grid[i][ii];
                        }
                    }
                    else{
                        newgrid[i][ii] = '.';
                    }

                }
            }
            grid = newgrid;
        }
        System.err.println(answer);
    }

    public static boolean  valid(int a, int b){
        return ((a >= 0 && a < y) && (b >= 0 && b < x));
    }
}
