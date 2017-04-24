package Assign3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

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
        
        HashFunctions hf = new HashFunctions(20011);
        
        //String[] elementsToAdd = {"Shakeel", "Adam", "Raeez", "Ryan", "Josh"};
        
        elementsToAdd = r.getListName();
                
        for (int i = 0; i < elementsToAdd.size(); i++) {
            
            String element = elementsToAdd.get(i);
            int hashVal = hf.HashFunction2(element); //Returns hash value
            hf.theArray[hashVal] = element + " | " + r.getListNumber().get(i) + " | " + r.getListAddress().get(i);
            
        }

        // Locate the value 660 in the Hash Table
        //hf.findKey("660");

        hf.displayTheStack();
        //hf.findKey("Lueilwitz Candelario");
    }
    
    HashFunctions(int size) {

        arraySize = size;
        theArray = new String[size];
        Arrays.fill(theArray, "-1");

    }

    //Worst case Hash Function
    public int WorstCaseHF(String key){
        
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
    
    public int HashFunction3(String key){
        
        int hashVal = 0;
        
        for (int i = 0; i < key.length(); i++) {
            
            hashVal = (3 * hashVal) + key.charAt(i);
            
        }
        
        BigInteger hashValue = BigInteger.valueOf(hashVal);
        BigInteger arraySizeB = BigInteger.valueOf(arraySize);
        BigInteger returnVal = hashValue.mod(arraySizeB);
        return returnVal.intValue();
        
        
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

        for (int m = 0; m <10; m++) {

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
                    if (theArray[n].length() > 18){
                    System.out.print(String.format("| %18s " + "", theArray[n]).substring(0,21));
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
