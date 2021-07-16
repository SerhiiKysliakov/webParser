package parser.service;

import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProductsToFileWriter {
    public void writeToFile(String[][] report) {
        try (CSVWriter csvWriter = new CSVWriter(new BufferedWriter
                (new FileWriter(System.getProperty("user.dir") + "\\output.csv", true)))) {
            for (String[] product : report) {
                csvWriter.writeNext(product);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write products to file");
        }
    }
}
