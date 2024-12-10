import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");         //get matrix
        Scanner scan = new Scanner(file).useDelimiter("");
        ArrayList<Integer> disk = new ArrayList<>();
        int count = 0;
        int id = 0;
        while(scan.hasNext())
        {
            int rep = Integer.parseInt(scan.next());
            for(int i = 0; i < rep; i++)
            {
                if(count % 2 == 0)
                {
                    disk.add(id);
                }
                else
                {
                    disk.add(-1);
                }
            }
            if(count % 2 == 0 && rep != 0)
            {
                id++;
            }
            count++;
        }
        System.out.println(disk);

        int[] diskarray = new int[disk.size()];
        count = 0;
        for(int n : disk)
        {
            diskarray[count] = n;
            count++;
        }
        int low = 0;
        int high = diskarray.length - 1;
        while(diskarray[high] == -1)
        {
            high--;
        }
        int localid = diskarray[high];
        high = diskarray.length - 1;
        while(localid > 0)
        {
            while(diskarray[high] != localid)
            {
                high--;
            }
            int seekhigh = 0;
            while(diskarray[high - seekhigh] == localid && localid > 0)
            {
                seekhigh++;
            }
            int seeklow = 0;
            low = 0;
            while(low < high)
            {
                while(diskarray[low] != -1)
                {
                    low++;
                }
                while(diskarray[seeklow + low] == -1 && seeklow + low < high)
                {
                    seeklow++;
                }
                if(seeklow >= seekhigh)
                {
                    for(int r = 0; r < seekhigh; r++)
                    {
                        int temp = diskarray[high];
                        diskarray[high] = diskarray[low];
                        diskarray[low] = temp;
                        high--;
                        low++;
                    }
                    break;
                }
                else
                {
                    low = low + seeklow;
                    seeklow = 0;
                }
            }
            if(low >= high)
            {
                high = high - seekhigh;
            }
            localid--;
        }

        double answer = 0;
        for(int i = 0; i < diskarray.length; i++)
        {
            if(diskarray[i] != -1)
            {
                answer = answer + i * diskarray[i];
            }
        }

        System.out.println(answer);
    }
}
