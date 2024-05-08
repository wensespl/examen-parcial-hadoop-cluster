import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

// Split the data into subgroups and move to the next Mapper.
public class ParcialP3Reducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        while (values.hasNext()) {
            output.collect(key, values.next());
        }
    }
}

class P3Reducer2 extends MapReduceBase implements Reducer<Text, IntWritable, Text, Text> {

    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        int countHigh = 0;
        int countLow = 0;

        while (values.hasNext()) {
            int value = Integer.parseInt(values.next().toString());
            if (value == 1) {
                countHigh++;
            } else {
                countLow++;
            }
        }

        // Define el resultado final basado en los recuentos
        String decision = countHigh > countLow ? "High Popularity" : "Low Popularity";
        output.collect(key, new Text(decision));
    }
}
