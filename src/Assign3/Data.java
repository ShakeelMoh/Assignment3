package Assign3;

//STORES 3 ITEMS OF DATA PER ENTRY
public class Data {

    String name;
    String number;
    String address;

    public Data(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;

    }

    //Default constructor
    public Data() {
    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
