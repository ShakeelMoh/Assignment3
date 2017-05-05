package Assign3;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author shakeel
 */
public class HashFunctions {

    //Initialize the array to store the data
    String[] theArray;
    int arraySize;
    int itemsInArray = 0;

    public static void main(String[] args) {

        //User interface variable
        String choice;

        //Calls the read data class to read in the testdata file
        ReadData r = new ReadData();
        r.read();

        //Elements to hash (the keys/full names)
        //hashValues (the hash values the elements produce
        //occurances(how many times the value was produced
        ArrayList<String> elementsToHash = new ArrayList<>();
        ArrayList<Integer> hashValues = new ArrayList<>();
        ArrayList<Integer> occurances = new ArrayList<>(Collections.nCopies(20011, 0));
        

        //init hash map with 20011 size
        int hashMapSize = 20011;
        int items = 10000;
        HashFunctions hf = new HashFunctions(hashMapSize);

//        elementsToHash.add("ab");
//        elementsToHash.add("cd");
//        elementsToHash.add("ef");
//        elementsToHash.add("ef");

        //Fills elelemts to hash with data
        elementsToHash = r.getListName();
        
        //UI Print statements
        System.out.println("Which hash function would you like to use? (0, 1, 2, 3)");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextLine();
        switch (choice) {
            case "0":
                System.out.println("Worst Case Hash Function...");
                break;
            case "1":
                System.out.println("Hash Function 1...");
                break;
            case "2":
                System.out.println("Hash Function 2...");
                break;
            case "3":
                System.out.println("Hash Function 3...");
                break;
        }

        //Iterate over each element and hash them
        for (int i = 0; i < elementsToHash.size(); i++) {

            int hashVal = 0;
            String element = elementsToHash.get(i);
            switch (choice) {
                case "0":
                    hashVal = hf.WorstCaseHF(element); //Returns hash value
                    break;
                case "1":
                    hashVal = hf.HashFunction1(element); //Returns hash value
                    break;
                case "2":
                    hashVal = hf.HashFunction2(element); //Returns hash value 
                    break;
                case "3":
                    hashVal = hf.HashFunction3(element); //Returns hash value
                    break;
                default:
                    System.out.println("Invalid selection");
                    System.exit(0);
            }
            hashValues.add(hashVal); //Add hash value to array for entropy calculation
            //Fills array with entire data element
            hf.theArray[hashVal] = element + " | " + r.getListNumber().get(i) + " | " + r.getListAddress().get(i);
            //Updates occurances of the hash value
            occurances.set(hashVal, occurances.get(hashVal) + 1);

        }

        // Locate the value 660 in the Hash Table
        //findKey returns hash value
        
        
        hf.displayTheStack();
        System.out.println("Entropy Value: " + calcEntropy(items, hashValues, occurances));
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

        //Big integer usage as the numbers were extremely large
        BigInteger hashValue = BigInteger.valueOf(hashVal);
        BigInteger arraySizeB = BigInteger.valueOf(arraySize);
        BigInteger returnVal = hashValue.mod(arraySizeB);
        return returnVal.intValue();
        //return hashVal % arraySize;

    }

    //Hash Function 3
    //Adds unicode of each letter and * 2 each time
    //SImilar to HF 2
    public int HashFunction3(String key) {

        int hashVal = 0;

        for (int i = 0; i < key.length(); i++) {

            hashVal = (2 * hashVal) + key.charAt(i);

        }

        BigInteger hashValue = BigInteger.valueOf(hashVal);
        BigInteger arraySizeB = BigInteger.valueOf(arraySize);
        BigInteger returnVal = hashValue.mod(arraySizeB);
        return returnVal.intValue();

    }

    //Method to calculate entropy of HF given its hash values and the occurances of those hash values
    public static double calcEntropy(int n, ArrayList h, ArrayList occur) {

        ArrayList<Integer> occurances = occur;
        ArrayList<BigDecimal> occurOverN = new ArrayList<>();

        //Dividing occurances by n
        for (int i = 0; i < occur.size(); i++) {
            
            if (occurances.get(i) != 0) {
                BigDecimal occurance = new BigDecimal(occurances.get(i));
                BigDecimal M = BigDecimal.valueOf(n);
                BigDecimal prob = occurance.divide(M, 30, BigDecimal.ROUND_HALF_UP);//30 decimal places

                occurOverN.add(prob);
            }

        }
        
        //Array list of all the values calculated after the log function is applied
        ArrayList<Double> logCalc = new ArrayList<>();

        double entropy = 0;

        for (int i = 0; i < occurOverN.size(); i++) {
            //-P(x) x Log(P(x))
            Double logVal = -1 * (occurOverN.get(i).doubleValue() * Math.log(occurOverN.get(i).doubleValue()));
            logCalc.add(logVal);

            entropy += logCalc.get(i);

        }
        //Print statements
//        System.out.println("OccurOverN double value: " + occurOverN.get(0).doubleValue());
//        System.out.println("Log value: " + Math.log10(occurOverN.get(0).doubleValue()));

        //DO the rest
        return entropy;

    }

    //This function finds the hash value given a key
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

    //This prints a visual representation of the array
    public void displayTheStack() {

        int increment = 0;

        for (int m = 0; m < 10; m++) {//Change 10 to how many rows you want of 10 length each to be displayed

            increment += 10;

            for (int n = 0; n < 211; n++) {
                System.out.print("-");
            }

            System.out.println();

            for (int n = increment - 10; n < increment; n++) {

                //Formatting...
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
