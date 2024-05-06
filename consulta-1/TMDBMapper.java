/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcialc1;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author USUARIO
 */
public class TMDBMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {    

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        if (key.get() == 0) {
            return;
        }
        String valueString = value.toString();
        String[] singleRowData = valueString.split(";");
        
        String title = singleRowData[0];
        String adult = singleRowData[7];
        double popularity = Double.parseDouble(singleRowData[9]);
        String prod_countries = singleRowData[12];
        String[] countries = prod_countries.split(",");
        
        if (adult.equals("False")) {
            for (String country : countries) {
                output.collect(new Text(country.trim()), new Text(title + "," + popularity));
            }
        }
    }
}

class TMDBMapper2 extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {    

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        String[] rowData = value.toString().split("\t");
        String[] values = rowData[0].split(",");
        double pop_prom = Double.parseDouble(rowData[1]);
        
        output.collect(new Text(values[0] + ":"), new Text(values[1] + "," + values[2] + "," + pop_prom));
    }
}
