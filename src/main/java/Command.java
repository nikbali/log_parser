import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Абстрактный класс чтобы не создавать объекты Command,
 * а создавать уже конкретно InputCommand и OutputCommand
 */
public  abstract class Command {
    protected long time_mcs;  //кол-во микросекунд
    protected Date time;  //текущее время с точность до секунды
    protected String id;
    protected int numType;

    /**
     *Метод из входной строки лога извлекает микросекунды, которое указано первым аргументом
     * Пример: 22:59:15.999994 из данной строки вернет 999994
     * @author Nikita Balily
     * @param str Входная строка лога
     * @return кол-во микросекунд
     * */
    protected long parseMcs(String str) throws ParseException
    {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSSSSS;");
        Date moment = dateFormat.parse(str.split(",")[0]);
        return moment.getTime() - this.getTime().getTime();
    }

    /**
     *Метод из входной строки лога извлекает время, которое указано первым аргументом
     * @author Nikita Balily
     * @param str Входная строка лога
     * @return объект класа Date
     * */
    protected Date parseSec(String str) throws ParseException
    {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.");
        Date moment = dateFormat.parse(str.split(",")[0]);
        return moment;
    }

    /**
     * Используется для извлечения из строки лога Id операции
     * @author Nikita Balily
     * @param str Входная строка лога
     * @return id опрации
     */
    protected String regexParseId(String str)
    {
        String id = "";
        try {
            Pattern patternId = Pattern.compile(" id.*?,");
            Matcher match = patternId.matcher(str);
            match.find();
            id = match.group().substring(4, match.group().length() - 1);
        }
        catch (Exception ex)
        {
            System.out.println("Error parse Regex for Id");
        }
        finally
        {
            return id;
        }
    }

    /**
     * Метод извлекает из входной строки номер типа операции
     * @author Nikita Balily
     * @param str Входная строка лога
     * @return Number Type опрации Пример: 101, 10, 130
     */
    protected int regexParseNumType(String str)
    {
        int type = 0;
        try {
            Pattern patternId = Pattern.compile(" type.*?,");
            Matcher match = patternId.matcher(str);
            match.find();
            type = Integer.parseInt(match.group().substring(6, match.group().length() - 1));
        }
        catch (Exception ex)
        {
            System.out.println("Error parse Regex for Number Type");
        }
        finally
        {
            return type;
        }
    }


    /**
     *
     * Getters for all fields
     */
    public Date getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public int getNumType() {
        return numType;
    }

    public long getTime_mcs() {

        return time_mcs;
    }
}
