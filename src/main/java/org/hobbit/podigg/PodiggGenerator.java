package org.hobbit.podigg;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hobbit.core.Constants;
import org.hobbit.core.components.AbstractComponent;
import org.hobbit.core.components.AbstractDataGenerator;
import org.hobbit.core.rabbit.SimpleFileSender;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Calls the podigg-lc generator.
 * @author Ruben Taelman (ruben.taelman@ugent.be)
 */
public class PodiggGenerator extends AbstractComponent {

    public static final String DEFAULT_QUEUE_NAME = "hobbit.podigg.queuename";

    protected void generate() throws Exception {
        // Call the generator
        FileUtils.forceMkdir(new File("output_data"));
        String memory = System.getenv().getOrDefault("NODE_MEM", "2056");
        ProcessBuilder processBuilder = new ProcessBuilder("node", "--max_old_space_size=" + memory, "bin/generate-env", "output_data");
        processBuilder.inheritIO();
        processBuilder.start().waitFor();
    }

    @Override
    public void run() throws Exception {
        String queueName = System.getenv().getOrDefault(Constants.DATA_QUEUE_NAME_KEY, DEFAULT_QUEUE_NAME);
        SimpleFileSender sender = SimpleFileSender.create(this, queueName);

        generate();

        // Forward the output
        InputStream is = new FileInputStream("output_data/lc.ttl");
        try {
            sender.streamData(is, "lc.ttl");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static void main(String[] args) throws Exception {
        new PodiggGenerator().generate();
    }
}
