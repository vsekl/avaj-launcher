package avajlauncher.utils;

import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {

    private OutputWriter() {

    }

    public static void write(String msg) throws IOException {
        try(FileWriter writer = new FileWriter("src/simulation.txt", true)) {
            writer.write(msg);
        }
    }

    public static void create() throws IOException {
        try(FileWriter writer = new FileWriter("src/simulation.txt", false)) {
            writer.write("");
        }
    }
}
