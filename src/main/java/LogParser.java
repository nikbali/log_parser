import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {



    public static void main(String[] args) {
        HashMap<String, InputCommand> hashMap = new HashMap<String, InputCommand>();
        HashSet hashSet = new HashSet();
        ArrayList<Command> ar = new ArrayList<Command>();
        File file_in = new File("D:\\medium.log");


        //FactoryOperations fo = new FactoryOperations();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_in));
            StringBuilder builder = new StringBuilder();
            String str = "";
            while ((str = reader.readLine()) != null)
            {
                if(validation(str) == true)
                {
                    Command comm = FactoryCommand.create(str);
                    Operation op = null;
                   if(hashMap.containsKey(comm.getId()))
                   {

                       op = new Operation(hashMap.get(comm.getId()), (OutputCommand) comm);
                       System.out.println("Id: " + op.getId() + " Задержка: " + op.getDelay());
                       hashMap.remove(comm.getId());
                   }
                   else
                   {
                       if(comm.getClass().getName().equals("InputCommand")) hashMap.put(comm.getId(), (InputCommand) comm);
                   }

                }
                }
            }

        catch (Exception exception)
        {
            exception.printStackTrace();
        }

    }

    /**
     * Метод проверяет встречается ли в строке слова P2_COD, SQLProxy
     * @param str Входная строка
     * @return true - слова не встречаются или false - встречаются
     */
    public static boolean validation(String str)
    {
         try {
             Pattern patternId = Pattern.compile("P2_COD|SQLProxy");
             Matcher match = patternId.matcher(str);
             match.find();
             if (match.group() != "") return false;
             else return true;
         }
         catch (Exception ex)
         {
             return true;
         }

    }




}




