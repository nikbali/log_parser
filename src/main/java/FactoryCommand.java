import java.text.ParseException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


    public class FactoryCommand
    {

        public static Command create(String str)
        {
            String st = regexParseIOType(str);
            try
            {
                if(st.equals("input")){

                    return new InputCommand(str);
                }
                else if(st.equals("output")) {

                    return new OutputCommand(str);
                }
                else return null;
            }
            catch (ParseException ex)
            {
                System.err.println("Ошибка при парсинге строки из файла" + ex.getMessage());
                return null;
            }
        }
        private static String regexParseIOType(String str)
        {
            String io_type = "";
            try {
                Pattern patternId = Pattern.compile("input|output");
                Matcher match = patternId.matcher(str);
                match.find();
                io_type = match.group();
            }
            catch (Exception ex)
            {
                System.out.println("Error parse Regex for IO Type");
            }
            finally
            {
                return io_type;
            }
        }
    }



