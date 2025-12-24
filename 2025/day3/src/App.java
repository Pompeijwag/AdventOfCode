import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static List<String> cells = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file);
        

        while(scan.hasNext()){
            cells.add(scan.nextLine());
        }


        scan.close();
        System.out.println(cells);
        part1();
        part2();
    }

    public static void part1(){
        int answer = 0;
        for (String cell : cells) {
            int biggest = 0;
            int biggestindex = 0;
            for (int i = 0; i < cell.length() - 1; i++) {
                if (Character.getNumericValue(cell.charAt(i)) > biggest){
                    biggest = Character.getNumericValue(cell.charAt(i));
                    biggestindex = i;
                }
            }
            int biggestsecond = 0;
            for (int i = biggestindex + 1; i < cell.length(); i++) {
                if (Character.getNumericValue(cell.charAt(i)) > biggestsecond){
                    biggestsecond = Character.getNumericValue(cell.charAt(i));
                }
            } 
            answer += (biggest * 10 + biggestsecond);
        }
        System.err.println(answer);
    }

    public static void part2(){
        double answer = 0;
        for (String cell : cells) {
            int biggestindex = 0;
            int startingindex = 0;
            double turnedon = 0;
            for (int ii = 11; ii >= 0; ii--) {
                double biggest = 0;
                for (int i = startingindex; i < cell.length() - ii; i++) {
                if (Character.getNumericValue(cell.charAt(i)) > biggest){
                    biggest = Character.getNumericValue(cell.charAt(i));
                    biggestindex = i;
                    }
                } 
                startingindex = biggestindex + 1;
                turnedon += biggest * Math.pow(10, ii);
            }
            answer += turnedon;
            System.err.println(turnedon);
        }
        System.err.println(answer);
    }
}
