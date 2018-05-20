import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutputCommand extends Command {

    private long total_out_mcs ;
    public OutputCommand(String str) throws ParseException
    {
        this.time = parseSec(str);
        this.id = regexParseId(str);
        this.numType = regexParseNumType(str);
        this.time_mcs = parseMcs(str);
       total_out_mcs = getTime().getTime()*1000 + time_mcs;

    }

    public long getTotalOutMcs() {
        return total_out_mcs;
    }
}
