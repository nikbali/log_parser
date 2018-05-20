import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputCommand extends Command {


    public InputCommand(String str) throws ParseException
    {

        this.time_mcs = parseMcs(str);
        this.time = parseSec(str);
        this.id = regexParseId(str);
        this.numType = regexParseNumType(str);

    }

}
