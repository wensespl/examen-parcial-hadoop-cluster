/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parcialc1;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

/**
 *
 * @author USUARIO
 */
public class ParcialC1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JobClient my_client = new JobClient();
        JobConf job_conf1 = new JobConf(ParcialC1.class);
        job_conf1.setJobName("Consulta1 1");
        job_conf1.setOutputKeyClass(Text.class);
        job_conf1.setOutputValueClass(Text.class);
        job_conf1.setMapperClass(parcialc1.TMDBMapper.class);
        job_conf1.setReducerClass(parcialc1.TMDBReducer.class);
        job_conf1.setInputFormat(TextInputFormat.class);
        job_conf1.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job_conf1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job_conf1, new Path(args[1]));
        my_client.setConf(job_conf1);
        //#########################################

        JobClient my_client2 = new JobClient();
        JobConf job_conf2 = new JobConf(ParcialC1.class);
        job_conf2.setJobName("Consulta1 2");
        job_conf2.setOutputKeyClass(Text.class);
        job_conf2.setOutputValueClass(Text.class);
        job_conf2.setMapperClass(parcialc1.TMDBMapper2.class);
        job_conf2.setReducerClass(parcialc1.TMDBReducer2.class);
        job_conf2.setInputFormat(TextInputFormat.class);
        job_conf2.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job_conf2, new Path(args[1]));
        FileOutputFormat.setOutputPath(job_conf2, new Path(args[2]));
        my_client2.setConf(job_conf2);

        try {
            JobClient.runJob(job_conf1);
            JobClient.runJob(job_conf2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
