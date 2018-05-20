import java.text.ParseException;

public class InputCommand extends Command {

    private long total_in_mcs;

    public InputCommand(String str) throws ParseException
    {
        this.time = parseSec(str);
        this.time_mcs = parseMcs(str);
        this.id = regexParseId(str);
        this.numType = regexParseNumType(str);
        total_in_mcs =  getTime().getTime()*1000 + time_mcs;

    }

    public long getTotalInMcs() {
        return total_in_mcs;
    }
}
