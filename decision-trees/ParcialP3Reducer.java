import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class ParcialP3Reducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, Text> {

    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

        int countHigh = 0;
        int countLow = 0;

        while (values.hasNext()) {
            // replace type of value with the actual type of our value
            IntWritable value = (IntWritable) values.next(); // 1 o 0

            if (value.get() == 1) {
                countHigh++;
            } else {
                countLow++;
            }
        }
        // Usa alguna lógica para decidir el siguiente paso del árbol
        String decision = (countHigh > countLow) ? "High Popularity" : "Low Popularity";
        output.collect(key, new Text(decision));
    }
}

//class P3Reducer2 extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
//
//    public void reduce(Text t_key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
//        Text key = t_key;
//        int max = 0;
//        String argmax = "";
//        while (values.hasNext()) {
//            // replace type of value with the actual type of our value
//            Text value = (Text) values.next();
//            String[] vals = value.toString().split(",");
//            int price = Integer.parseInt(vals[1]);
//            if (price > max) {
//                max = price;
//                argmax = vals[0];
//            }
//        }
//        output.collect(key, new Text(argmax));
//    }
//}
