import java.io.File;
import java.util.Scanner;

public class App {
    public static String[] splitting;
    public static String[] values ;
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file);
        double answer = 0;
        while(scan.hasNextLine())
        {
            splitting = scan.nextLine().split(": ");
            values = splitting[1].split(" ");
            double next = operator(1, Double.parseDouble(values[0]), Double.parseDouble(splitting[0]));
            if(next != -1)
            {
                answer = answer + next;
            }
        }
        System.out.println(answer);
    }

    public static double operator(double index, double current, double target)
    {
        if(index >= values.length)
        {
            if(current == target)
            {
                return current;
            }
            else
            {
                return -1;
            }
        }
        else if(current > target)
        {
            return -1;
        }
        else
        {
            double multiply = operator(index + 1, current * Double.parseDouble(values[(int)index]), target);
            double plus = operator(index + 1, current + Double.parseDouble(values[(int)index]), target);
            double length = String.valueOf(Double.parseDouble(values[(int)index])).length() - 2;
            length = Math.pow(10, length);
            double concat = operator(index + 1, current * length + Double.parseDouble(values[(int)index]), target);
            if(multiply != -1)
            {
                return multiply;
            }
            else if(plus != -1)
            {
                return plus;
            }
            else if(concat != -1)
            {
                return concat;
            }
        }
        return -1;
    }
}
