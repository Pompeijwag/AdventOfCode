import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");   
        Scanner scanbutton = new Scanner(file).useDelimiter("[^0-9]+");
        double answer = 0;
        while(scanbutton.hasNext())
        {
            double ax = scanbutton.nextDouble();
            double ay = scanbutton.nextDouble();
            double bx = scanbutton.nextDouble();
            double by = scanbutton.nextDouble();
            double goalx = scanbutton.nextDouble();
            double goaly = scanbutton.nextDouble();
            
            // goalx += 10000000000000.0;
            // goaly += 10000000000000.0;

            double b = (goalx * ay - ax * goaly)/(-by * ax + bx * ay);
            double a = (goaly - by * b)/ ay;
            if(a % 1 == 0 && b % 1 == 0)
            {
                answer = answer + b + a * 3;
            }
            
        }
        System.out.println(answer);
    }
}
