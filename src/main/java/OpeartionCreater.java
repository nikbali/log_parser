import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpeartionCreater {
    private HashMap<String, InputCommand> hashMap = new HashMap<String, InputCommand>();
    private ArrayList<Operation> listOperation  = new ArrayList<Operation>();
    private HashMap<Integer, ArrayList<Operation>> mapPull = new HashMap<Integer, ArrayList<Operation>>();
    private static OpeartionCreater instance = new OpeartionCreater();
    private OpeartionCreater()
    {

    }
    public static  OpeartionCreater getInstance()
    {
        return instance;
    }

    public void addToPull(Command comm)
    {
        Operation op = null;
        if(hashMap.containsKey(comm.getId()))
        {
            op = new Operation(hashMap.get(comm.getId()), (OutputCommand) comm);
           if(mapPull.containsKey(op.getType_num_out()))
           {
               mapPull.get(op.getType_num_out()).add(op);
               hashMap.remove(comm.getId());
           }
           else
           {
               ArrayList<Operation> list = new ArrayList<Operation>();
               list.add(op);
               mapPull.put(op.getType_num_out(), list);
           }
        }
        else
        {
            if(comm.getClass().getName().equals("InputCommand")) hashMap.put(comm.getId(), (InputCommand) comm);
        }

    }

    public HashMap<Integer, ArrayList<Operation>> getPull()
    {

        return mapPull;
    }
    public void clearPull()
    {
        mapPull.clear();
    }
}
