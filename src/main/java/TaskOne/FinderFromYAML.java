package TaskOne;

import org.ho.yaml.YamlDecoder;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Vlad on 16.04.2015.
 */
public class FinderFromYAML {
    public static void main(String[] args) throws IOException {
        File file = new File("D://SysGearsTest.txt");

        InputStream inp = new FileInputStream(file);
        YamlDecoder decoder = new YamlDecoder(inp);

        Integer[] obj = decoder.readObjectOfType(Integer[].class);
        int result = 0;
        //Since we have only one single number, we will approach the operation XOR, because x ^ x = 0, a 0 ^ y = y.
        for (int i = 0; i < obj.length; i++) {
            result = result ^ obj[i];
        }
        System.out.println(result);
        decoder.close();
        inp.close();
    }
}

