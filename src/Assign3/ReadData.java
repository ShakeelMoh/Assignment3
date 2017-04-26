package Assign3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shakeel
 */
public class ReadData {

    //Initialize 3 arraylists to store all the data
    private static ArrayList<String> address;
    private static ArrayList<String> name;
    private static ArrayList<String> number;

    //Read the test data text file
    public void read() {

        address = new ArrayList<>();
        name = new ArrayList<>();
        number = new ArrayList<>();

        try {

            //Read data from textfile
            Scanner sc = new Scanner(new File("/home/shakeel/NetBeansProjects/Assignment2/Data/testdata"));

            while (sc.hasNextLine()) {

                String s = sc.nextLine();

                //Split each line into 3 pieces
                String[] line = s.split("\\|");

                address.add(line[0]);
                number.add(line[1]);
                name.add(line[2]);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //Given a file name, read the contents of the file
     public void read(String fileName) {

        address = new ArrayList<>();
        name = new ArrayList<>();
        number = new ArrayList<>();

        try {

            //Read data from textfile
            Scanner sc = new Scanner(new File("Data/" + fileName));

            while (sc.hasNextLine()) {

                String s = sc.nextLine();

                //Split each line into 3 pieces
                String[] line = s.split("\\|");

                address.add(line[0]);
                number.add(line[1]);
                name.add(line[2]);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    //Getters for the array lists
    public ArrayList<String> getListAddress() {
        return address;

    }

    public ArrayList<String> getListName() {
        return name;

    }

    public ArrayList<String> getListNumber() {
        return number;

    }

}
