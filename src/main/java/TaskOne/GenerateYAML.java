package TaskOne;

import org.ho.yaml.YamlEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vlad on 16.04.2015.
 */
public class GenerateYAML {
    public static void main(String[] args) throws IOException {

        // Generate array
        //Sets the range for the random number
        int min = 0;
        int maxCountOfNumbers = 500000; //800 000 generate about 3 minutes
        int max = Integer.MAX_VALUE / 2;

        int countOfNumbers = min + (int) (Math.random() * ((maxCountOfNumbers - min) + 1));
        // We create array with even count of Numbers. All numbers have a pair. then we remove one.
        if (countOfNumbers % 2 != 0) {
            countOfNumbers = countOfNumbers + 1;
        }
        System.out.println(countOfNumbers);
        // I need to remove one element and add every element in random position, so i use ArrayList, not array.
       List<Integer> array = new ArrayList<>(countOfNumbers);
        //fill an array with random numbers
        for (int i = 0; i < countOfNumbers; i = i + 2) {
            int newNumber = min + (int) (Math.random() * ((max - min) + 1));
            array.add(newNumber);
            int position = 0 + (int) (Math.random() * (((array.size() - 1) - 0) + 1));
            array.add(position, newNumber);
        }
        //remove random element
        int positionForRemove = 0 + (int) (Math.random() * (((array.size() - 1) - 0) + 1));
        array.remove(positionForRemove);
        // create array
        int[] arrayToFile =  new int[countOfNumbers - 1];
        int contIter = 0;
        for (Integer res : array) {
            arrayToFile[contIter++] = res.intValue();
        }
        File file = new File("D://SysGearsTest.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream out = new FileOutputStream(file);
        YamlEncoder enc = new YamlEncoder(out);
        enc.writeObject(arrayToFile);
        enc.close();
        out.close();
    }
}
