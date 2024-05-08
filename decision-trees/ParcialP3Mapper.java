import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

// Clasifica las películas en función de vote_average
public class ParcialP3Mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {

    private Text category = new Text();

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        if (key.get() == 0) {
            return;
        }
        String valueString = value.toString();
        String[] singleRowData = valueString.split(";");

        // Índice correspondiente a "vote_average"
        float voteAverage = Float.parseFloat(singleRowData[1]);
        String row = String.join(";", singleRowData);

        // Classify by vote_average (average threshold: 4.2)
        String category = (voteAverage >= 4.2) ? "High Vote Average" : "Low Vote Average";

        output.collect(new Text(category), new Text(row));
    }
}

// Classify movies based on budget.
class P3Mapper2 extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

    private Text category = new Text();
    private final static IntWritable highPopularity = new IntWritable(1);
    private final static IntWritable lowPopularity = new IntWritable(0);

    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {

        String[] rowData = value.toString().split(";");
        // Index of relevant columns
        float budget = Float.parseFloat(rowData[8]);
        float popularity = Float.parseFloat(rowData[9]);

        // Classify by budget (average threshold: 1558221)
        String budgetCategory = (budget >= 1558221) ? "High Budget" : "Low Budget";

        // Define the popularity (average threshold: 3.7)
        IntWritable popularityCategory = popularity >= 3.7 ? highPopularity : lowPopularity;

        output.collect(new Text(budgetCategory), popularityCategory);
    }
}
