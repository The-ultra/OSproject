import java.io.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;


public class Main {
	static Hashtable<String, Object> variables = new Hashtable<String, Object>();
	

	
    public static void main(String[]Args){
    		parser("Program 1.txt");
            parser("Program 2.txt");
            parser("Program 3.txt");

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
        public static void parsing(String[] parts) throws IOException{

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
                        assignstring = assignstring+parts[x] +" " ;
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




  


    public static String input(){
        @SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        String str= sc.nextLine();              //reads string
        return str;




    }
    // ----------------------------------------------------------

    public static void assign(String one, String two) {
		String[] arg2 = two.split(" ");
		
		String varName = "";
		String dataString = "";
		if (one.contains("readFile")) {
			arg2 = one.split(" ");
			if(variables.containsKey(arg2[1]))
				varName = readFile((String)variables.get(arg2[1]));
			else
				varName = readFile((arg2[1]));
		} else
			varName = one;

		if (two.contains("readFile")) {
			arg2 = two.split(" ");

			if(variables.containsKey(arg2[1]))
				dataString = readFile((String)variables.get(arg2[1]));
			else
				dataString = readFile((arg2[1]));
			
		} else
			dataString = two;


		if (variables.containsKey(varName))
			variables.replace(varName, dataString);
		else
			variables.put(varName, dataString);
	}


    public static void print(String statement){
        if(variables.containsKey(statement))
            System.out.println(variables.get(statement));
        else
            System.out.println(statement);
    }



    // ----------------------------------------------------------


    public static void add(String first, String second ){
   		int a = Integer.parseInt((String) variables.get(first));
    	int b = Integer.parseInt((String) variables.get(second));
    	int res = a + b;
    	String ress = ""+res;
        variables.replace(first, ress);
    }

    public static void writeFile(String fileName,String data ) throws IOException{
    	if(variables.containsKey(fileName))
            fileName = (String) variables.get(fileName);
    	if(variables.containsKey(data))
            data = (String) variables.get(data);
    	
        File f = new File(fileName);
        FileWriter writer = new FileWriter(f);
        if(f.exists()) {
        	writer.write(data);
        	writer.close();
        }
        else {
        	f.createNewFile();
        	writer.write(data);
        	writer.close();
        }
  
    }

    public static String readFile(String filename){
    	String res = "";
    	try {
    		File f = new File(filename);
    		if(f.exists()) {
    			BufferedReader b = new BufferedReader(new FileReader(f));
    			String reader = b.readLine();
    			while(reader!=null) {
    				res = res+reader;
    				reader = b.readLine();
    			}
    		}
    		else {
    			throw new FileNotFoundException();
    		}
    		
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    	return res;
    }

    // ----------------------------------------------------------
    




}