import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");   
        Scanner scanbutton = new Scanner(file).useDelimiter("[^0-9]+");
        int answer = 0;
        while(scanbutton.hasNext())
        {
            int ax = scanbutton.nextInt();
            int ay = scanbutton.nextInt();
            int bx = scanbutton.nextInt();
            int by = scanbutton.nextInt();
            int goalx = scanbutton.nextInt();
            int goaly = scanbutton.nextInt();
            System.out.println(ax);
            System.out.println(ay);
            System.out.println(bx);
            System.out.println(by);
            System.out.println(goalx);
            System.out.println(goaly);

            int bpresses = Math.min(goaly / by, goalx / bx);
            int apresses = 0;
            int currentx = bpresses * bx + apresses * ax;
            int currenty = bpresses * by + apresses * ay;
            apresses = Math.min(goaly - currenty  / ay, goalx / bx);
            if(currentx == goalx && currenty == goaly)
            {
                answer = answer + bpresses + apresses * 3;
            }
        }
    }
}
