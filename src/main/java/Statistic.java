import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Statistic {

    private double[] data;
    private Date time;
    private int type;
    private int count_operation;

    private double avr;
    private double median;
    private double maximum;
    private double percentile90;
    private double percentile99;

    public Statistic (ArrayList<Operation> oper)
    {
        this.data = new double[oper.size()];
        for (int i = 0; i < oper.size(); i++)
        {
            data[i] = oper.get(i).getDelay();
        }
        this.time = oper.get(0).getTime_sec();
        this.count_operation = oper.size();
        this.type = oper.get(0).getType_num_out();
        this.avr = calcAverage(data);
        this.maximum = calcMaximum(data);
        this.median = calcMediana();
        this.percentile90 = calcPercentile(90);
        this.percentile99 = calcPercentile(99);

    }

    public double calcAverage(double [] arr)
    {
        double average = 0;
        if (arr.length > 0)
        {
            double sum = 0;
            for (int j = 0; j < arr.length; j++)
            {
                sum += arr[j];
            }
            average = sum / arr.length;
         }
         return average;
    }

    public double calcMaximum(double [] arr)
    {
        double max = arr[0];
        for (int j = 0; j < arr.length; j++)
        {
            if(arr[j] > max) max = arr[j];
        }
        return max;
    }

    public double calcMediana()
    {
        Percentile percentile50 = new Percentile(50);
        percentile50.setData(data);
        return percentile50.evaluate();
    }

    public double calcPercentile(double p)
    {
        Percentile percentile = new Percentile(p);
        percentile.setData(data);
        return percentile.evaluate();
    }

    @Override
    public String toString() {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        return    dateFormat.format(time) +
                "," + type +
                "," + count_operation +
                "," + avr +
                "," + median +
                "," + percentile90 +
                "," + percentile99 +
                "," + maximum
                ;
    }
}
