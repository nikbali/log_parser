import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {



    public static void main(String[] args) {
        System.out.println("Enter full path to LOG - file:");
        BufferedReader read_console = new BufferedReader(new InputStreamReader(System.in));

        try {
            File file_in = new File(read_console.readLine());
            read_console.close();
            OpeartionCreater oper_creater = OpeartionCreater.getInstance();
            HashMap<Integer, ArrayList<Operation>> cur_pull_operation;

            try {
                BufferedReader reader = new BufferedReader(new FileReader(file_in));
                BufferedWriter writerToFile = new BufferedWriter(new FileWriter("output.csv"));
                String str = "";
                long second = 0;
                long curSecond;
                writerToFile.write("Time,Type,Count,Average,Median,Percentile90,Percentile99,Maximum \r\n");
                writerToFile.flush();
                while ((str = reader.readLine()) != null) {
                    if (validation(str) == true) {
                        Command comm = FactoryCommand.create(str);
                        curSecond = comm.getTime().getTime();
                        if (curSecond > second) {
                            cur_pull_operation = oper_creater.getPull();

                            for (Map.Entry<Integer, ArrayList<Operation>> pair : cur_pull_operation.entrySet()) {
                                writerToFile.write(new Statistic(pair.getValue()).toString());
                                writerToFile.flush();
                            }
                            oper_creater.clearPull();
                            second = curSecond;
                        }
                        oper_creater.addToPull(comm);
                    }
                }
                reader.close();
                writerToFile.close();

            } catch (IOException exception) {
                System.out.println("File not found!!!");
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }catch (IOException ex)
        {
            System.out.println("Input error in console!!!!");
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




