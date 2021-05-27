import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
 LinkedList<String> reservedwords = new LinkedList<String>;


    public static void main(String[]Args){
            parser("C:\\Users\\Ahmed Hamouda\\Documents\\GitHub\\OSProject\\OSproject\\Milestone1\\src\\try.txt");

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
                parsing(parts);


                System.out.println(sc.nextLine());      //returns the line that was skipped
            }
            sc.close();     //closes the scanner
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static void parsing(String[] parts){

        String[] currString;
        int i = 0;
        for(int count = 1; count<parts.length;count++){
            currString[i]= parts[count];
        }
        switch (parts[0]) {
                case "add":
                    int k = 1;
                    String addstring= "";

                        add(parts[1], addstring);
                    break;
                case "writeFile":
                    if (parts[1].equals("input") && parts[2].equals("input")) {
                        writeFile(input(), input());

                    } else if (parts[1].equals("input") && !parts[2].equals("input")) {
                        String writestring = "";
                        for (int i = 2; i < parts.length; i++) {
                            writestring = writestring + " " + parts[i];
                        }
                        writeFile(input(), writestring);

                    } else if (!parts[1].equals("input") && parts[2].equals("input")) {
                        writeFile(parts[1], input());
                    } else {

                        String writestring = "";
                        for (int i = 2; i < parts.length; i++) {
                            writestring = writestring + parts[i];
                        }
                        writeFile(parts[1], writestring);
                        break;

                    }

                case "readFile":
                    if (parts[1].equals("input"))
                        readFile(input());
                    else
                        readFile(parts[1]);

                    break;
                case "assign":
                    assign(parts[1], parts[2]);
                    break;
                case "print":
                    String printstring = "";
                    for (int i = 1; i < parts.length; i++) {
                        printstring = printstring + parts[i];
                    }
                    print(printstring);

                    break;

                default:
                    // code block
            }
        }



    public static void print(String[] text){
    System.out.print("I am print");
    System.out.print(text);
    }


    public static String input(){
        System.out.print("I am input");
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        String str= sc.nextLine();              //reads string
        return str;




    }
    // ----------------------------------------------------------

    public static void assign(String[] array){
        System.out.print("I am Assign");





    }





    // ----------------------------------------------------------


    public static void add(String[] array){
        System.out.print("I am add");

    }

    public static void writeFile(String[] array ){
        System.out.print("I am writeFile");

    }

    public static void readFile(String[] filename){
        System.out.print("I am readFile");

    }
    // ----------------------------------------------------------






}
