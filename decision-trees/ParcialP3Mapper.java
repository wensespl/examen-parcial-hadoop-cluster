import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class ParcialP3Mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

    private Text voteAverageCategory = new Text();
    private final static IntWritable highPopularity = new IntWritable(1);
    private final static IntWritable lowPopularity = new IntWritable(0);

    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        if (key.get() == 0) {
            return;
        }
        String valueString = value.toString();
        String[] singleRowData = valueString.split(";");

        // Supongamos que "adult" es el atributo que se quiere predecir
        // Índice correspondiente a "vote_average" y "popularity"
        float voteAverage = Float.parseFloat(singleRowData[1]);
        float popularity = Float.parseFloat(singleRowData[9]);

        // Clasificar popularidad según el umbral
        IntWritable popularityClass = (popularity >= 3.72) ? highPopularity : lowPopularity;

        // Dividir según `vote_average` (umbral arbitrario: 7.5)
        String category = (voteAverage >= 4.2) ? "High Vote Average" : "Low Vote Average";

        output.collect(new Text(category), popularityClass);
    }
}

//class P3Mapper2 extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
//
//    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
//
//        String[] rowData = value.toString().split("\t");
//        String[] rowValue = rowData[0].split(",");
//        int valueInt = Integer.parseInt(rowData[1]);
//        output.collect(new Text(rowValue[0] + "," + rowValue[1]), new Text(rowValue[2] + "," + valueInt));
//    }
//}
