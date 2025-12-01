import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static List<String> direction = new ArrayList<>();
    public static List<Integer> number = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file);
        

        while(scan.hasNext()){
            String line = scan.next();
            direction.add(line.substring(0, 1));
            number.add(Integer.parseInt(line.substring(1)));
        }


        scan.close();
        part1();
        part2();
    }

    public static void part1()
    {
        int dial = 50;
        int answer = 0;
        for (int i = 0; i < direction.size(); i++) {
            if (direction.get(i).equals("R")){
                dial += number.get(i);
                dial = dial % 100;
            }  
            else {
                dial -= number.get(i);
                dial = dial % 100;
                if(dial < 0){
                    dial = 100 + dial;
                }
            }
            if(dial == 0){
                answer++;
            }
            // System.err.println(dial);
        }
        System.err.println(answer);
    }

    public static void part2()
    {
        int dial = 50;
        int answer = 0;
        for (int i = 0; i < direction.size(); i++) {
            int temp = number.get(i);
            answer += temp / 100;
            temp = temp % 100;
            int start = dial;
            if((start == 0) && (temp == 0)){
                answer--;
            }
            if (direction.get(i).equals("R")){
                dial = dial + temp;
            }  
            else {
                dial = dial - temp;
            }
            if(dial == 0){
                answer++;
            }
            else if(dial >= 100){
                answer++;
                dial = dial - 100;
            }
            else if(dial < 0){
                if(start != 0){
                    answer++;
                }
                dial = 100 + dial;
            }
            // System.err.println(dial);
            // System.err.println(answer);
        }
        System.err.println(answer);
    }
}
