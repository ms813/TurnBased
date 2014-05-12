package Generic;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Matthew on 12/03/14.
 */
public class CSVLoader {

    public static ArrayList<String[]> load(String path) throws IOException{
        String contents = "";

        contents = readFile(path + ".csv");

        ArrayList<String[]> toReturn = new ArrayList<String[]>();

        String[] lines = contents.split("\n");

        for(String line : lines){
            //chop off the separator character
            line = line.substring(0, line.length() -1);
            toReturn.add(line.split(","));
        }

        return toReturn;
    }

    private static String readFile(String path) throws IOException {
        Charset encoding = Charset.defaultCharset();
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }
}
