import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
 LinkedList<String> reservedWords = new LinkedList<String>();


    public static void main(String[]Args){
            parser("C:\\Users\\Ahmed Hamouda\\Documents\\GitHub\\OSProject\\OSproject\\Milestone1\\Program 1.txt");

    }
    public static void parser(String filename) {




        try {
            //the file to be opened for reading
            FileInputStream fis = new FileInputStream(filename);
            Scanner sc = new Scanner(fis);    //file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" ");
                if(parts!= null) {
                    parsing(parts);

                    //returns the line that was skipped
                }
                }
            sc.close();     //closes the scanner
        } catch (IOException e) {

            System.out.print("end of lines");
        }
    }
        public static void parsing(String[] parts){

        switch (parts[0]) {


            case "add":

                if (parts[1].equals("input") && parts[2].equals("input")) {
                    add(input(), input());
                    break;

                } else if (parts[1].equals("input") && !parts[2].equals("input")) {
                    String addString = "";
                    for (int x = 2; x < parts.length; x++) {
                        addString = addString + " " + parts[x];
                    }
                    add(input(), addString);
                    break;

                } else if (!parts[1].equals("input") && parts[2].equals("input")) {
                    add(parts[1], input());
                    break;
                } else {

                    String addString = "";
                    for (int x = 2; x < parts.length; x++) {
                        addString = addString + parts[x];
                    }
                    add(parts[1], addString);
                    break;

                }



            case "writeFile":

                if (parts[1].equals("input") && parts[2].equals("input")) {
                    writeFile(input(), input());
                    break;
                } else if (parts[1].equals("input") && !parts[2].equals("input")) {
                    String writestring = "";
                    for (int x = 2; x < parts.length; x++) {
                        writestring = writestring + " " + parts[x];
                    }
                    writeFile(input(), writestring);
                    break;
                } else if (!parts[1].equals("input") && parts[2].equals("input")) {
                    writeFile(parts[1], input());
                    break;
                } else {

                    String writestring = "";
                    for (int x = 2; x < parts.length; x++) {
                        writestring = writestring + parts[x];
                    }
                    writeFile(parts[1], writestring);
                    break;

                }


            case "readFile":

                if (parts[1].equals("input")) {
                    readFile(input());
                    break;

                }else {
                    String readString = "";
                    for (int x = 2; x < parts.length; x++) {
                        readString = readString + parts[x];
                    }
                    readFile(readString);
                    break;
                }


            case "assign":

                if (parts[1].equals("input") && parts[2].equals("input")) {
                    assign(input(), input());
                    break;
                }
                if (parts[1].equals("input") && !parts[2].equals("input")) {
                    String assignString = "";
                    for (int x = 2; x < parts.length; x++) {
                        assignString = assignString + " " + parts[x];
                    }
                    assign(input(), assignString);
                    break;

                }
                if (!parts[1].equals("input") && parts[2].equals("input")) {
                    assign(parts[1], input());
                    break;
                }else {

                    String assignstring = "";
                    for (int x = 2; x < parts.length; x++) {
                        assignstring = assignstring + parts[x];
                    }
                    assign(parts[1], assignstring);

                    break;
                }




                case "print":

                    String printString = "";
                    for (int x = 1; x < parts.length; x++) {
                        printString = printString + parts[x];
                    }
                    print(printString);

                    break;

                default:
                    System.out.print(Arrays.toString(parts));
                    break;
                    // code block
            }
        }




    public static void print(String text){
    System.out.println("I am in print");
    System.out.println(text);
    }


    public static String input(){
        System.out.println("I am in input");
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        String str= sc.nextLine();              //reads string
        return str;




    }
    // ----------------------------------------------------------

    public static void assign(String one, String two){
        System.out.println("I am in Assign");
        return;




    }





    // ----------------------------------------------------------


    public static void add(String first, String second ){
        System.out.println("I am in add");

    }

    public static void writeFile(String write,String fileLocation ){
        System.out.println("I am in writeFile");

    }

    public static void readFile(String filename){
        System.out.println("I am in readFile");

    }

    // ----------------------------------------------------------





}
