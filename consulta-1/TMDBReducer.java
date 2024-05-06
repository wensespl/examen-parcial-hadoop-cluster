/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcialc1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author USUARIO
 */
public class TMDBReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

    public void reduce(Text t_key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        Text key = t_key;
        String country = key.toString();
        
        List<Double> list_popularity = new ArrayList<Double>();
        List<String> list_title = new ArrayList<String>();
        
        double popularity_sum = 0;
        int count = 0;
        
        while (values.hasNext()) {
            Text value = (Text) values.next();
            String[] vals = value.toString().split(",");
            
            double popularity = Double.parseDouble(vals[1]);
            
            list_popularity.add(popularity);
            list_title.add(vals[0]);
            
            popularity_sum += popularity;
            count += 1;
        }
        
        double popularity_prom = popularity_sum/count;
        
        for (int i = 0; i < count; i++) {
            output.collect(new Text(country + "," + list_title.get(i) + "," + list_popularity.get(i)), new Text(String.valueOf(popularity_prom)));
        }
    }
}

class TMDBReducer2 extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

    public void reduce(Text t_key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        Text key = t_key;
        String country = key.toString();
        
        double min = Double.POSITIVE_INFINITY;
        String arg_title = "";

        while (values.hasNext()) {
            Text value = (Text) values.next();
            String[] vals = value.toString().split(",");
            
            double popularity = Double.parseDouble(vals[1]);
            double pop_prom = Double.parseDouble(vals[2]);
            double dif = Math.abs(popularity - pop_prom);
            
            if (dif < min) {
                min = dif;
                arg_title = vals[0];
            }
        }
        
        output.collect(new Text(country), new Text(arg_title));
    }
}
