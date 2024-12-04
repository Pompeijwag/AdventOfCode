import java.io.File;
import java.util.Scanner;

public class App {

    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //this is even more disgusting
     
    public static String[] format = {"m", "u", "l", "("}; 
    public static String[] donot = {"d", "o", "n", "'", "t", "(", ")"};
    public static String[] doing = {"d", "o", "(", ")"};
    public static int answer = 0;
    public static int formatting = 0;
    public static int first = 0;
    public static int second = 0;
    public static String next = "";
    public static int command = 0;
    public static boolean active = true;

    
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file).useDelimiter("");
        
        while(scan.hasNext())
        {
            if(next.equals(""))
            {
                next = scan.next();
            }
            if(!active)
            {
                if(next.equals(doing[command]))
                {
                    command++;
                    if(command > 3)
                    {
                        active = true;
                        command = 0;
                    }
                    next = "";
                }
                else
                {
                    command = 0;
                    next = "";
                }
            }
            else
            {
                if(formatting < 4)
                {
                    first = 0;
                    second = 0;
                    if(next.equals(format[formatting]))
                    {
                        formatting++;
                        command = 0;
                        next = "";
                    }
                    else
                    {
                        if(next.equals(donot[command]))
                        {
                            command++;
                            if(command > 6)
                            {
                                active = false;
                                command = 0;
                            }
                        }
                        if(formatting == 0)
                        {
                            next = "";
                        }
                        formatting = 0;
                    }
                }
                else 
                {
                    
                    if(tryParseInt(next))
                    {
                        first = first * 10 + Integer.parseInt(next);
                        if(first > 999)
                        {
                            formatting = 0;
                        }
                        else
                        {
                            next = "";
                        }
                    }
                    else
                    {
                        String comp = ",";
                        if(formatting == 5)
                        {
                            comp = ")";
                        }
                        if(first > 0 && next.equals(comp))
                        {
                            if(second == 0)
                            {
                                second = first;
                                first = 0;
                                formatting = 5;
                            }
                            else
                            {
                                answer = answer + first * second;
                                formatting = 0;
                            }
                            next = "";
                        }
                        else
                        {
                            formatting = 0;
                        }
                    }
                    
                }
            }
            
            
        }
        scan.close();
        System.out.println(answer);
    }
}
