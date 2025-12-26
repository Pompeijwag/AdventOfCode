import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static Character[][] grid;
    public static List<Double> ranges = new ArrayList<>();
    public static List<Double> number = new ArrayList<>();
    public static List<Double[]> longrange = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File file1 = new File("..\\lib\\input1.txt");
        File file2 = new File("..\\lib\\input2.txt");
        
        Scanner scan1 = new Scanner(file1);
        Scanner scan2 = new Scanner(file2);
        
        scan1.useDelimiter("\\s*[\\n-]\\s*");
        
        while(scan1.hasNext()){
            ranges.add(Double.parseDouble(scan1.next()));
        }
        while(scan2.hasNext()){
            number.add(Double.parseDouble(scan2.next()));
        }
        System.out.println(ranges);
        System.err.println(" ");
        System.err.println(number);
        part2();
    }

    public static void part1()
    {
        int answer = 0;
        for (double d : number){
            boolean fresh = false;
            for (int i = 0; i < ranges.size(); i+=2) {
                if(d >= ranges.get(i) && d <= ranges.get(i+1)){
                    fresh = true;
                    break;
                }
            }

            if(fresh){answer++;}
        }
        System.err.println("part1: " + answer);
    }

    public static void part2()
    {
        double answer = 0;
        for (int i = 0; i < ranges.size(); i+=2) {
            double low = ranges.get(i);
            double high = ranges.get(i+1);
            fitrange(low, high);
        }
        for (Double[] dou : longrange){
            System.err.println(Arrays.toString(dou));
        }
        for (Double[] dou : longrange){
            answer += (dou[1] - dou[0] + 1);
        }
        System.err.println("part2: " + answer);
    }

    public static void fitrange(double low, double high)
    {
        boolean checkagain = false;
        double checkagainlow = 0, checkagainhigh = 0;
        for (Double[] dou : longrange){
            if((low >= dou[0] && low <= dou[1]) && high > dou[1]){
                low = dou[1] + 1;
            }
            else if((low >= dou[0] && low <= dou[1]) && high <= dou[1]){
                low = 99;
                high = 1;
                break;
            }
            else if((high >= dou[0] && high <= dou[1]) && low < dou[0]){
                high = dou[0] - 1;
            }
            else if(high > dou[1] && low < dou[0]){
                checkagain = true;
                checkagainlow = dou[0] - 1.0;
                checkagainhigh = dou[1] + 1.0;
                break;
            }
        }
        if(checkagain){
            fitrange(low, checkagainlow);
            fitrange(checkagainhigh, high);
        }
        else if(low <= high){
            longrange.add(new Double[] {low, high});
        }
        
    }
}

