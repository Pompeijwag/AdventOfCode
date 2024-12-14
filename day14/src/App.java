import java.io.File;
import java.util.Scanner;

public class App {
    public static int sizex = 101;
    public static int sizey = 103;
    public static int[][] matrix = new int[sizey][sizex];
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");         //get matrix
        Scanner scan = new Scanner(file).useDelimiter("[^-0-9]+");
        int seconds = 100;
        while(scan.hasNext())
        {
            int positionx = scan.nextInt();
            int positiony = scan.nextInt();
            int vectorx = scan.nextInt();
            int vectory = scan.nextInt();
            System.out.println(vectory);
            positionx = positionx + seconds * vectorx;
            positiony = positiony + seconds * vectory;
            positionx = positionx % sizex;
            if(positionx < 0)
            {
                positionx = positionx * -1;
                positionx = sizex - positionx;
            }
            positiony = positiony % sizey;
            if(positiony < 0)
            {
                positiony = positiony * -1;
                positiony = sizey - positiony;
            }
            matrix[positiony][positionx]++;
        }
        
        for (int i = 0; i < sizey; i++) 
        {
            for (int ii = 0; ii < sizex; ii++) 
            {
                if(matrix[i][ii] == 0)
                {
                    System.out.print(".");
                }
                else 
                {
                    System.out.print(matrix[i][ii]);   
                }
            }
            System.out.println("");
        }
        int first = 0;
        int second = 0;
        int third = 0;
        int fourth = 0;
        for (int i = 0; i < sizey / 2; i++) {
            for (int ii = 0; ii < sizex / 2; ii++) {
                first = first + matrix[i][ii];
            }  
        }
        for (int i = (sizey / 2 )+ 1; i < sizey; i++) {
            for (int ii = 0; ii < sizex / 2; ii++) {
                second = second + matrix[i][ii];
            }  
        }
        for (int i = 0; i < sizey / 2; i++) {
            for (int ii = (sizex / 2) + 1; ii < sizex; ii++) {
                third = third + matrix[i][ii];
            }  
        }
        for (int i = (sizey / 2 )+ 1; i < sizey; i++) {
            for (int ii = (sizex / 2) + 1; ii < sizex; ii++) {
                fourth = fourth + matrix[i][ii];
            }  
        }

        System.out.println(first * second * third * fourth);
    }
}
