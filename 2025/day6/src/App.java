import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
    public static List<List<Double>> numbers = new ArrayList<>();
    public static List<List<Double>> numbers2 = new ArrayList<>();
    public static String[] grid;
    public static List<String> operation = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        
        File file1 = new File("..\\lib\\input1.txt");
        File file2 = new File("..\\lib\\input2.txt");
        
        Scanner scan1 = new Scanner(file1);
        Scanner scan1part2Scanner = new Scanner(file1);
        Scanner getlength = new Scanner(file1);
        Scanner scan2 = new Scanner(file2);

        while(scan2.hasNext()){
            operation.add(scan2.next());
        }

        int length = operation.size();
        for (int i = 0; i < length; i++) {
            numbers.add(new ArrayList<>());
            numbers2.add(new ArrayList<>());
        }
        int index = 0;
        while(scan1.hasNext()){
            numbers.get(index).add(Double.parseDouble(scan1.next()));
            index++;
            if(index >= length){index = 0;}
        }
        index = 0;
        grid = new String[numbers.get(0).size()];
        while(scan1part2Scanner.hasNext()){
            grid[index] = scan1part2Scanner.nextLine();
            index++;
        }

        int column = 0;
        int row = 0;
        for (int i = grid[0].length() - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder();
            for (int ii = 0; ii < numbers.get(0).size(); ii++) {
                if(!(grid[ii].charAt(i) == ' ')){
                    sb.append(grid[ii].charAt(i));
                }
            }
            if(sb.length() > 0){
                numbers2.get(row).add(Double.parseDouble(sb.toString()));
            }
            else{
                row++;
            }
        }

        System.err.println(operation);
        System.err.println(numbers);
        System.err.println(Arrays.toString(grid));
        System.err.println(numbers2);

        // part1();
        part2();
    }

    public static void part1(){
        int index = 0;
        double answer = 0;
        double tempanswer = 0;
        for(String str : operation){
            if(str.equals("+")){    
                for(Double d : numbers.get(index)){
                    tempanswer += d;
                }
            }
            else{
                tempanswer = 1;
                for(Double d : numbers.get(index)){
                    tempanswer *= d;
                }
            }
            answer += tempanswer;
            tempanswer = 0;
            index++;
        }
        System.err.println(answer);
    }

    public static void part2(){
        int index = 0;
        double answer = 0;
        double tempanswer = 0;
        Collections.reverse(operation);
        for(String str : operation){
            if(str.equals("+")){    
                for(Double d : numbers2.get(index)){
                    tempanswer += d;
                }
            }
            else{
                tempanswer = 1;
                for(Double d : numbers2.get(index)){
                    tempanswer *= d;
                }
            }
            answer += tempanswer;
            tempanswer = 0;
            index++;
        }
        System.err.println(answer);
    }

}
