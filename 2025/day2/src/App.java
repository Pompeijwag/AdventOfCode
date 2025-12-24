import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static List<String> direction = new ArrayList<>();
    public static List<Double> number = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file);
        scan.useDelimiter("\\s*[,-]\\s*");
        

        while(scan.hasNext()){
            number.add(Double.parseDouble(scan.next()));
        }


        scan.close();
        System.out.println(number);
        // part1();
        part2();
    }

    public static void part1()
    {
        double answer = 0;
        for (int i = 0; i < number.size(); i+=2) {
            double start = number.get(i);
            double end = number.get(i + 1);
            for (double ii = start; ii <= end; ii++) {
                int leng = findlength(ii);
                if(leng % 2 == 0){
                    // System.out.print(Math.floor((ii / Math.pow(10, leng/2 ))));
                    // System.out.print(", ");
                    // System.out.print((ii % Math.pow(10, leng/2 )));
                    // System.out.print(", ");
                    // System.out.println((ii ));

                    if(Math.floor((ii / Math.pow(10, leng/2))) == (ii % Math.pow(10, leng/2 ))){
                        answer+= ii;
                    }
                }

            }
        }
        System.err.println(answer);
    }

    public static void part2()
    {
        double answer = 0;
        for (int i = 0; i < number.size(); i+=2) {
            double start = number.get(i);
            double end = number.get(i + 1);
            for (double ii = start; ii <= end; ii++) {
                int leng = findlength(ii);
                //System.out.println("current number: " + ii);
                for (int iii = 1; iii <= (leng/2); iii++){
                    if(leng % iii == 0){
                        double currentnumber = ii;
                        double comparitor = currentnumber % Math.pow(10, iii);
                        System.out.println("comparitor: " + comparitor);
                        currentnumber = Math.floor(currentnumber / Math.pow(10, iii));
                        while(currentnumber > 0){  
                            double section = currentnumber % Math.pow(10, iii);
                            if(section != comparitor){
                                break;
                            }
                            currentnumber = Math.floor(currentnumber / Math.pow(10, iii));
                        }
                        if(currentnumber <= 0){
                            System.err.println("added: " + ii);
                            answer += ii;
                            break;
                        }
                    }
                }

            }
        }
        System.err.println(answer);
    }

    public static int findlength(double a)
    {   
        int answer = 1;
        double comp = 10;
        while(a > comp)
        {
            answer++;
            comp *= 10;
        }
        return answer;
    }
}
