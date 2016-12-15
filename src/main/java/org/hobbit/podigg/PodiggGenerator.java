package org.hobbit.podigg;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hobbit.core.components.AbstractDataGenerator;

import java.io.File;
import java.io.FileInputStream;

/**
 * Calls the podigg-lc generator.
 * @author Ruben Taelman (ruben.taelman@ugent.be)
 */
public class PodiggGenerator extends AbstractDataGenerator {
    @Override
    protected void generateData() throws Exception {
        // Call the generator
        FileUtils.forceMkdir(new File("output_data"));
        Process process = Runtime.getRuntime().exec("bin/generate.sh output_data");
        process.waitFor();
        System.err.println(IOUtils.toString(process.getErrorStream()));
        System.out.println(IOUtils.toString(process.getInputStream()));

        // Forward the output
        byte[] data = IOUtils.toByteArray(new FileInputStream("output_data/lc.ttl"));
        sendDataToSystemAdapter(data);
        sendDataToTaskGenerator(data);
    }
}
