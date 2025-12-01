import java.util.ArrayList;
import java.util.Hashtable;

public class App {
    public static double answer = 0; 
    public static Hashtable<Double, double[]> ht = new Hashtable();
    public static int times = 75;
    public static void main(String[] args) throws Exception {
        // int[] input = {3935565, 31753, 437818, 7697, 5, 38, 0, 123};
        int[] input = {125, 17};
        // int[] input = {0};
        
        for(int i = 100; i < 0; i++)
        {
            int temps = i * 2024;
            int si = 0;
            System.out.print(i);
            while(temps >= 10)
            {
                temps = temps / 10;
                si++;
            }
            if(si % 2 == 0)
            {
                System.out.println(": Even");
            }
            else
            {
                System.out.println(": Odd");
            }
        }
        ArrayList<Double> list = new ArrayList<>(); 
        for(int i = 0; i < input.length; i++)
        {
            list.add((double)input[i]);
        }
        for(double n : list)
        {
            answer = answer + divide(n, times);
        }
        System.out.println(answer);
    }

    public static double divide(double number, int iteration)
    {
        if(iteration <= 0)
        {
            return 1d;
        }
        if(ht.containsKey(number))
        {
            if(ht.get(number)[iteration] != 0)
            {
                return ht.get(number)[iteration];
            }
        }
        Double temp = number;
        int size = 1;
        while(temp >= 10)
        {
            temp = temp / 10;
            size++;
        }
        if(number == 0)
        {
            double ans = divide(1d, iteration - 1);
            addhash(number);
            ht.get(number)[iteration] = ans;
            return ans;
        }
        else if(size % 2 == 0)
        {
            double ans = (double)(divide((double)Math.floor(number / Math.pow(10, (size / 2))), iteration - 1) 
            + divide((double)Math.floor(number % Math.pow(10, (size / 2))), iteration - 1));
            addhash(number);
            ht.get(number)[iteration] = ans;
            return ans;
        }
        else
        {
            double ans = divide(number * 2024, iteration - 1);
            addhash(number);
            ht.get(number)[iteration] = ans;
            return ans;
        }
    }

    public static void addhash(double number)
    {
        if(!ht.containsKey(number))
        {
            double[] array = new double[times + 1];
            for(int i = 0; i < array.length; i++)
            {
                array[i] = 0;
            }
            ht.put(number, array);
        }
    }
}
