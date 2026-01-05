import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static List<int[]> areas = new ArrayList<>();
    public static List<int[]> boxes = new ArrayList<>();
    public static int[] boxsizes = new int[] {5, 7, 7, 7, 7, 6};
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input2.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNext()){
            String[] str = scan.nextLine().split(" ");
            String[] copy = Arrays.copyOfRange(str, 1, str.length);
            boxes.add(Arrays.stream(copy).mapToInt(Integer::parseInt).toArray());
            String[] dimen = str[0].substring(0, str[0].length() - 1).split("x");
            areas.add(new int[]{Integer.parseInt(dimen[0]), Integer.parseInt(dimen[1])});
        }
        for(int[] a : areas){
            System.err.println(Arrays.toString(a));
        }
        part1();
    }

    public static void part1(){
        int areatoobig = 0;
        int areatoosmall = 0;
        int difficult = 0;
        for (int ii = 0; ii < areas.size(); ii++){
            int[] a = areas.get(ii);
            int boxcount = (a[0] / 3) * (a[1] / 3);
            int totalboxes = 0;
            int area = a[0] * a[1];
            int totalarea = 0;
            for (int i = 0; i < boxsizes.length; i++) {
                totalarea += boxsizes[i] * boxes.get(ii)[i];
                totalboxes += boxes.get(ii)[i];
            }
            if(boxcount > totalboxes){
                areatoobig++;
            }
            else if(area < totalarea){
                areatoosmall++;
            }
            else{
                difficult++;
            }
        }
        System.err.println(areatoobig);
        System.err.println(areatoosmall);
        System.err.println(difficult);
    }
}
