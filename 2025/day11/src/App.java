import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class App {
    public static double part1answer = 0;
    public static double svrdac = 4.69533827768E11;
    public static double svrfft = 9449.0;
    public static double dacfft = 0.0;
    public static double fftdac = 6730780.0;
    public static double dacout = 7089.0;
    public static double fftout = 6.52557236156E11;
    public static double part2answer = 0;
    public static Hashtable<String, String[]> table = new Hashtable<>();
    public static Hashtable<String, Double> memory = new Hashtable<>();
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNext()){
            String[] str = scan.nextLine().split(" ");
            table.put(str[0].substring(0, str[0].length() - 1), Arrays.copyOfRange(str, 1, str.length));
        }
        // for (String key : table.keySet()) {
        //     System.out.println(key + " -> " + Arrays.toString(table.get(key)));
        // }
        part2();
        
    }

    public static void part1(){
        recur("you", new ArrayList<>(List.of("you")));
        System.err.println(part1answer);
    }

     public static void part2(){
        recur("fft", new ArrayList<>(List.of("fft")));
        System.err.println(part1answer);
        System.err.println(svrfft * fftdac * dacout);
    }

    public static void recur(String at, List<String> visited){
        // System.err.println(at + " -> " + visited);
        if(at.equals("out")) {
            part1answer++;
        }
        else if(memory.containsKey(at)){
            part1answer += memory.get(at);
        }
        else{
            if(table.containsKey(at)){
                double save = part1answer;
                for (String to : table.get(at)){
                    if(!visited.contains(to)){
                        List<String> newvisited = new ArrayList<>(visited);
                        newvisited.add(to);
                        recur(to, newvisited);
                    }
                }
                memory.put(at, part1answer - save);
                System.err.println(at  + " -> "  + (part1answer - save));
            }
        }
    }

    public static void recur2(String at, List<String> visited){
        if(at.equals("out")){
            
            System.err.println(at + "-> " + visited);
            if(visited.contains("dac") && visited.contains("fft")){
                part2answer++;
            }
        }
        else{
            if(table.containsKey(at)){
                for (String to : table.get(at)){
                    if(!visited.contains(to)){
                        List<String> newvisited = new ArrayList<>(visited);
                        newvisited.add(to);
                        recur2(to, newvisited);
                    }
                }
            }
        }
    }
}
