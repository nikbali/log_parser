import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {



    public static void main(String[] args) {
        HashMap<String, Integer> cnt_command = new HashMap<String, Integer>();
        ArrayList<Command> ar = new ArrayList<Command>();
        File file_in = new File("D:\\medium.log");
        //FactoryOperations fo = new FactoryOperations();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_in));
            String str = "";
            while ((str = reader.readLine()) != null)
            {
                Command comm = FactoryCommand.create(str);
                ar.add(comm);
                if(ar.size()>1)
                {
                    try
                    {
                        Operation op = new Operation((InputCommand) ar.get(0), (OutputCommand) ar.get(1));
                        ar.remove(1);
                        ar.remove(0);
                        System.out.println("Id" + op.getId() + " Задержка: " + op.getDelay());

                    }
                    catch (IllegalArgumentException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                }

                /*
                if(cnt_command.containsKey(comm.getId()))
                {
                    int cnt =  cnt_command.get(comm.getId());
                    cnt_command.put(comm.getId(),cnt++);
                }
                else  cnt_command.put(comm.getId(),1);
                */

            }
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }

    }

    /**
     * Метод проверяет встречается ли в строке слова P2_COD, SQLProxy
     * @param str Входная строка
     * @return true или false
     */
    public static boolean validation(String str)
    {
        
    }


}


