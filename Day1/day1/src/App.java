import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;



public class App {
    public static List<Integer> list1 = new ArrayList<>();
    public static List<Integer> list2 = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file);
        

        while(scan.hasNext()){
            list1.add(Integer.parseInt(scan.next()));
            list2.add(Integer.parseInt(scan.next()));
        }

        scan.close();
        part1();
        part2();
    }

    public static void part1()
    {
        int answer = 0;

        Collections.sort(list1);
        Collections.sort(list2);
        for(int i = 0; i < list1.size(); i++)
        {
            int one = list1.get(i);
            int two = list2.get(i);
            answer = answer + Math.abs(one - two);
        }
        System.out.println(answer);
    }

    public static void part2()
    {
        int answer = 0;

        for(int i = 0; i < list1.size(); i++)
        {
            int a = list1.get(i);
            answer =  answer + a * Collections.frequency(list2, a);
        }

        System.out.println(answer);
    }
}
