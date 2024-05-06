package parciallogistic;


import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
public class TMDBReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

    public void reduce(Text t_key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        Text key = t_key;
        double Sxy = 0.0, Sx = 0.0, Sy = 0.0, Sx2 = 0.0;
        int n = 0;
        double x,y;
            
        while (values.hasNext()) {
            Text value = (Text) values.next();
            String[] vals = value.toString().split(",");
            
            x = Double.parseDouble(vals[0]);
            y = Double.parseDouble(vals[1]);
            
            Sx += x;
            Sy += y;
            Sxy += x*y;
            Sx2 += x*x;
            
            n += 1;
        }
        
        double B0, B1, u, s;
        
        B0 = ((Sy*Sx2) - (Sx*Sxy)) / ((n*Sx2) - (Sx*Sx));
        B1 = ((n*Sxy) - (Sx*Sy)) / ((n*Sx2) - (Sx*Sx));
        
        u = -B0/B1;
        s = 1/B1;
        
        String[] key_values = key.toString().split(",");
        
        output.collect(new Text(key_values[0]), new Text(String.valueOf(u)));
        output.collect(new Text(key_values[1]), new Text(String.valueOf(s)));
    }
}