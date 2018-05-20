import java.util.Date;

public class Operation {
    private String id;
    private InputCommand in;
    private OutputCommand out;
    private long delay;
    private Date time_sec;
    private int type_num_out;

    public Operation(InputCommand in, OutputCommand out)
    {
        if(in.getId().equals(out.getId()) && in.getTime().equals(out.getTime()))
        {
            this.in = in;
            this.out = out;
            this.id = in.getId();
            this.delay = out.getTime_mcs() - in.getTime_mcs();
            this.time_sec = in.getTime();
            this.type_num_out = out.getNumType();

        }
        else throw new IllegalArgumentException("Коммандыне относятся к одной операции(Id разные)");
    }

    public String getId() {
        return id;
    }

    public InputCommand getIn() {
        return in;
    }

    public OutputCommand getOut() {
        return out;
    }

    public long getDelay() {
        return delay;
    }

    public Date getTime_sec() {
        return time_sec;
    }

    public int getType_num_out() {
        return type_num_out;
    }
}
