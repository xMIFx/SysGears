package TaskOne;

import org.ho.yaml.YamlDecoder;

import java.io.*;


/**
 * Created by Vlad on 16.04.2015.
 */
public class FinderFromYAML {
    public static void main(String[] args) throws IOException {
        File file = new File("D://SysGearsTest.txt");

        InputStream inp = new FileInputStream(file);
        YamlDecoder decoder = new YamlDecoder(inp);

        Integer[] obj = decoder.readObjectOfType(Integer[].class);
        int result = 0; // Here is one moment, that if the right answer can be 0 and if we will not find anything result = 0; so we need to count zero number
        int countZero = 0;
        //Since we have only one single number, we will approach the operation XOR, because x ^ x = 0, a 0 ^ y = y.
        for (Integer anObj : obj) {
            if (anObj == 0) {
                countZero++;
            }
            result = result ^ anObj;
        }
        if (result == 0 && countZero != 1) {
            System.out.println("couldn't be found.");
        } else {
            System.out.println("find: " + result);
        }
        decoder.close();
        inp.close();
    }
}

