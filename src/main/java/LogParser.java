import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {



    public static void main(String[] args) {

        File file_in = new File("D:\\medium.log");
        OpeartionCreater oper_creater = OpeartionCreater.getInstance();
        HashMap<Integer, ArrayList<Operation>> cur_pull_operation;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file_in));

            String str = "";
            long second = 0;
            long curSecond;
            //нужно организовать посекундный пулл операций

            while ((str = reader.readLine()) != null)
            {
                if(validation(str) == true)
                {
                    Command comm = FactoryCommand.create(str);
                    curSecond = comm.getTime().getTime();
                    if(curSecond > second)
                    {
                        cur_pull_operation = oper_creater.getPull();

                        for (Map.Entry<Integer, ArrayList<Operation>> pair : cur_pull_operation.entrySet())
                        {
                            System.out.println(new Statistic(pair.getValue()));
                        }
                        oper_creater.clearPull();
                        System.out.println("----------------");
                        second = curSecond;
                    }
                    oper_creater.addToPull(comm);
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




