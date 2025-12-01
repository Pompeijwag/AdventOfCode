import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class App {
    public static Hashtable<Integer, ArrayList<Integer>> secondtofirst = new Hashtable<>();
    public static Hashtable<Integer, ArrayList<Integer>> firsttosecond = new Hashtable<>();
    public static File file = new File("..\\lib\\input.txt");
    public static File input = new File("..\\lib\\input1.txt");
    public static int question1 = 0;
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(file);
        
        while(scan.hasNextLine())
        {
            String[] dupel = scan.nextLine().split("\\|");
            int zero = Integer.parseInt(dupel[0]);
            int one =  Integer.parseInt(dupel[1]);
            if(!secondtofirst.containsKey(one))
            {
                secondtofirst.put(one, new ArrayList<>());
            }
            secondtofirst.get(one).add(zero);
            if(!firsttosecond.containsKey(zero))
            {
                firsttosecond.put(zero, new ArrayList<>());
            }
            firsttosecond.get(zero).add(one);
        }

        scan.close();
        question1();
        question2();
    }

    public static void question1() throws FileNotFoundException
    {   
        int answer = 0;
        Scanner scan1 = new Scanner(input);
        while(scan1.hasNextLine())
        {
            String[] array = scan1.nextLine().split(",");
            int[] arrayint = new int[array.length];
            for(int i = 0; i < array.length; i++)
            {
                arrayint[i] = Integer.parseInt(array[i]);
            }
            ArrayList<Integer> invalid = new ArrayList<>();
            for(int i = 0; i < arrayint.length; i++)
            {
                int current = arrayint[i];
                if(invalid.contains(current))
                {
                    break;
                }
                else if(i == arrayint.length - 1)
                {
                    answer = answer + arrayint[arrayint.length / 2];
                }
                else
                {
                    if(secondtofirst.containsKey(current))
                    {
                        ArrayList<Integer> dependencies = secondtofirst.get(current);
                        for(int d : dependencies)
                        {
                            invalid.add(d);
                        }
                    }
                }
            }
        }
        question1 = answer;
        System.out.println(answer);
        scan1.close();
    }

    public static void question2() throws FileNotFoundException
    {   
        int answer = 0;
        Scanner scan1 = new Scanner(input);
        while(scan1.hasNextLine())
        {
            String[] array = scan1.nextLine().split(",");
            int[] arrayint = new int[array.length];
            for(int i = 0; i < array.length; i++)
            {
                arrayint[i] = Integer.parseInt(array[i]);
            }
            for(int i = 0; i < arrayint.length; i++)
            {
                if(firsttosecond.containsKey(arrayint[i]))
                {
                    int before = Integer.MAX_VALUE - 1;
                    for(int ii = i - 1; ii >= 0; ii--)
                    {
                        if(firsttosecond.get(arrayint[i]).contains(arrayint[ii]))
                        {
                            before = ii;
                        }
                    }
                    for(int ii = i - 1; ii >= before; ii--)
                    {
                        int temp = arrayint[ii];
                        arrayint[ii] = arrayint[ii + 1];
                        arrayint[ii + 1] = temp;
                    }
                }

            }
            answer = answer + arrayint[arrayint.length / 2];
        }
        scan1.close();
        System.out.println(answer - question1);
    }
}
