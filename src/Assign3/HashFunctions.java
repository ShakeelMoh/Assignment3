package Assign3;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author shakeel
 */
public class HashFunctions {

    String[] theArray;
    int arraySize;
    int itemsInArray = 0;

    public static void main(String[] args) {

        ReadData r = new ReadData();
        r.read();

        ArrayList<String> elementsToAdd = new ArrayList<>();
        ArrayList<Integer> hashValues = new ArrayList<>();

        HashFunctions hf = new HashFunctions(20011);

//        elementsToAdd .add("Shakeel");
//        elementsToAdd.add("Raeez");
//        elementsToAdd.add("adam");
//        elementsToAdd.add("adam");
        elementsToAdd = r.getListName();

        for (int i = 0; i < elementsToAdd.size(); i++) {

            String element = elementsToAdd.get(i);
            int hashVal = hf.WorstCaseHF(element); //Returns hash value
            hashValues.add(hashVal); //Add hash value to array for entropy calculation
            hf.theArray[hashVal] = element + " | " + r.getListNumber().get(i) + " | " + r.getListAddress().get(i);

        }

        // Locate the value 660 in the Hash Table
        //hf.findKey("660");
        //hf.displayTheStack();
        System.out.println("Entropy Value: " + calcEntropy(20011, hashValues));
        //hf.findKey("Lueilwitz Candelario");
    }

    HashFunctions(int size) {

        arraySize = size;
        theArray = new String[size];
        Arrays.fill(theArray, "-1");

    }

    //Worst case Hash Function
    public int WorstCaseHF(String key) {

        return 1;

    }

    //Hash Function 1
    //Add unicode values of string and mod table size
    //abc = cba = cab etc...
    public int HashFunction1(String key) {

        int hashVal = 0;

        for (int i = 0; i < key.length(); i++) {

            hashVal += key.charAt(i);

        }

        BigInteger hashValue = BigInteger.valueOf(hashVal);
        BigInteger arraySizeB = BigInteger.valueOf(arraySize);
        BigInteger returnVal = hashValue.mod(arraySizeB);
        return returnVal.intValue();

    }

    //Hash Function 2
    //Adds unicode of each letter and * 37 each time
    //So abc != cba != bac etc...
    public int HashFunction2(String key) {

        long hashVal = 0;

        for (int i = 0; i < key.length(); i++) {

            hashVal = (37 * hashVal) + key.charAt(i);

        }

        BigInteger hashValue = BigInteger.valueOf(hashVal);
        BigInteger arraySizeB = BigInteger.valueOf(arraySize);
        BigInteger returnVal = hashValue.mod(arraySizeB);
        return returnVal.intValue();
        //return hashVal % arraySize;

    }

    public int HashFunction3(String key) {

        int hashVal = 0;

        for (int i = 0; i < key.length(); i++) {

            hashVal = (3 * hashVal) + key.charAt(i);

        }

        BigInteger hashValue = BigInteger.valueOf(hashVal);
        BigInteger arraySizeB = BigInteger.valueOf(arraySize);
        BigInteger returnVal = hashValue.mod(arraySizeB);
        return returnVal.intValue();

    }

    public static double calcEntropy(int m, ArrayList h) {

        ArrayList<String> unique = new ArrayList<>();
        ArrayList<Integer> occurances = new ArrayList<>(Collections.nCopies(20011, 0));

        for (int i = 0; i < h.size(); i++) {

            if (unique.contains(h.get(i).toString())) {
                
                occurances.set(unique.indexOf(h.get(i).toString()), occurances.get(unique.indexOf(h.get(i).toString())) + 1);

            } else {
                unique.add(h.get(i).toString());
                occurances.set(unique.indexOf(h.get(i).toString()), occurances.get(i) + 1);
            }

        }

        System.out.println("HashVal 1: " + unique.toString());
        System.out.println("Occurrances: " + occurances.toString());
      
        ArrayList<BigDecimal> occurOverN = new ArrayList<>();

        for (int i = 0; i < occurances.size(); i++) {

            if (occurances.get(i) != 0) {
                BigDecimal occurance = new BigDecimal(occurances.get(i));
                BigDecimal M = BigDecimal.valueOf(10000);
                BigDecimal prob = occurance.divide(M, 30, BigDecimal.ROUND_HALF_UP);
                
                occurOverN.add(prob);
            }

        }
        System.out.println("Occurances/n: " + occurOverN.toString());

        ArrayList<Double> logCalc = new ArrayList<>();

        double entropy = 0;

        for (int i = 0; i < occurOverN.size(); i++) {

            
            Double logVal = -1 * (occurOverN.get(i).doubleValue() * Math.log10(occurOverN.get(i).doubleValue()));
            logCalc.add(logVal);

            entropy += logCalc.get(i);

        }
        System.out.println("OccurOverN double value: " + occurOverN.get(0).doubleValue());
        System.out.println("Log value: " + Math.log10(occurOverN.get(0).doubleValue()));
        

        //DO the rest
        return entropy;

    }

    public String findKey(String key) {

        // Find the keys original hash key
        int arrayIndexHash = HashFunction2(key);

        while (theArray[arrayIndexHash] != "-1") {

            if (theArray[arrayIndexHash] == key) {

                // Found the key so return it
                System.out.println(key + " was found in index "
                        + arrayIndexHash);

                return theArray[arrayIndexHash];

            }

            // Look in the next index
            ++arrayIndexHash;

            // If we get to the end of the array go back to index 0
            arrayIndexHash %= arraySize;

        }

        // Couldn't locate the key
        return null;

    }

    public void displayTheStack() {

        int increment = 0;

        for (int m = 0; m < 10; m++) {

            increment += 10;

            for (int n = 0; n < 211; n++) {
                System.out.print("-");
            }

            System.out.println();

            for (int n = increment - 10; n < increment; n++) {

                System.out.format("| %18s " + "", n);

            }

            System.out.println("|");

            for (int n = 0; n < 211; n++) {
                System.out.print("-");
            }

            System.out.println();

            for (int n = increment - 10; n < increment; n++) {

                if (theArray[n].equals("-1")) {
                    System.out.print("|                    ");
                } else {
                    if (theArray[n].length() > 18) {
                        System.out.print(String.format("| %18s " + "", theArray[n]).substring(0, 21));
                    } else {
                        System.out.print(String.format("| %18s " + "", theArray[n]));
                    }
                }

            }

            System.out.println("|");

            for (int n = 0; n < 211; n++) {
                System.out.print("-");
            }

            System.out.println();

        }

    }

}
