package TaskThree;


import java.util.*;

/**
 * Created by bukatinvv on 17.04.2015.
 */
public class BiggestConcatWord {
    private static final String[] array = CreateArray();
    private static int counterIteration = array.length;

    public static void main(String[] args) {


        //System.out.println(Arrays.toString(array));
        //sort by alphabetical order
        Arrays.sort(array);
        //System.out.println(Arrays.toString(array));
        //Now sort by word length
        SortArrayByLength(array);
        //System.out.println(Arrays.toString(array));
        String s = FindBiggestConcatWord(array);
        System.out.println(s);

    }


    private static String[] CreateArray() {
        // I don't now is it a problems, but in my example:
        // concatWord can include one word unlimited number of times
        // if we have same word, it think that one word include other.
        //return new String[]{"hi", "abc", "acd", "to", "toabcdevf", "de", "vf", "devf", "ads", "rrt","toabcdevfs","adsacdtoabcdevfr","hihihihihihihihihihihihi"};
        return new String[]{"hi", "abc", "acd", "to", "toabcdevf", "de", "vf", "devf", "ads", "rrt", "toabcdevfs", "adsacdtoabcdevfr"};
        //return new String[]{"abcd","abc","abcde","ab","cd","abcdefghjklmnop","abcdefgh","abcdefghjklmnop"};
    }

    private static void SortArrayByLength(String[] array) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
        Arrays.sort(array, comparator);
    }

    private static String FindBiggestConcatWord(String[] array) {
        // We will remove word from array, so use List
        String result = null;
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, array);
        // We need to loop this list in reverse order
        Collections.reverse(strings);
        //System.out.println(strings);
        // int counterIteration = array.length;
        for (String s : strings) {
            counterIteration--;
            if (isItBiggestConcatWorld(s)) {
                result = s;
                break;
            }
        }
        return result;
    }

    private static boolean isItBiggestConcatWorld(String s) {
       /* System.out.println("String to find: "+s);
        System.out.println();*/
        int firstLetterToFind = 0;
        int oldFirstLetterToFind = 0;
        boolean biggest = false;
        //find letter by letter in array. first one letter, then two letters...
        for (int i = 1; i <= s.length(); i++) {
            String finderPart = s.substring(firstLetterToFind, i);
            //System.out.println("part to find: "+finderPart);
            for (int j = 0; j < counterIteration; j++) {
                if (array[j].equals(finderPart)) {
                    oldFirstLetterToFind = firstLetterToFind;
                    firstLetterToFind = i;
                    break;
                }
            }
            //if find some substring, then check the rest of the word. if finder part = rest of the word, then all is ok.
            if (s.equals(finderPart) && oldFirstLetterToFind != firstLetterToFind) {
                // System.out.println("part is find: "+finderPart);
                biggest = true;
                break;
            } else if (oldFirstLetterToFind != firstLetterToFind && isItBiggestConcatWorld(s.substring(firstLetterToFind))) {
                // System.out.println("part is find: "+finderPart);
                biggest = true;
                break;
            } else {
                // if can't find the rest of the word, zero out all and append next letter
                firstLetterToFind = 0;
                oldFirstLetterToFind = 0;
                //System.out.println("part not find: "+finderPart);
                biggest = false;
            }
        }
        return biggest;
    }

}
