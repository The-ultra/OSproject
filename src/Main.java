import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {
	static Memory mem = new Memory();
	static Hashtable<String, Object> variables = new Hashtable<String, Object>();

	public static void main(String[] Args)  {
		String[] processes = new String[] {"Program 1.txt","Program 2.txt","Program 3.txt"};
		for (int i = 0; i < processes.length; i++) {
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(processes[i]);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scanner sc = new Scanner(fis);
				mem.addProcess((int)(Math.random()*10000)+1);
				String inst = "";
				//System.out.println(mem.getRecentlyCreated());
				int instCount = 1;
				while (sc.hasNextLine()) {
					String curr = sc.nextLine();
					inst += curr + "Indx: " + (mem.getRecentlyCreated()+6+instCount) + "\n";
					mem.addInstruction(curr,mem.getRecentlyCreated());
					instCount++;
				}
				mem.printPCB(mem.getRecentlyCreated());
				System.out.println("Instructions: ");
				System.out.println(inst);
				System.out.println("------------------");
				sc.close(); //closes the scanner
			
		}
		scheduler(mem.getProcesses());

	}

	public static void scheduler(ArrayList<Integer> memoryIndex) {
		Hashtable<Integer, Integer> quanta = new Hashtable<Integer, Integer>();
		for (int i = 0; i < memoryIndex.size(); i++) 
			quanta.put(memoryIndex.get(i), 0);
		while(!memoryIndex.isEmpty())
		{
			for (int i = 0; i < memoryIndex.size();i++) {
				System.out.println("Process "+mem.getID(memoryIndex.get(i))+" is in scheduler");
				quanta.replace(memoryIndex.get(i), quanta.get(memoryIndex.get(i))+1);
				mem.setState(ProcessState.Running,memoryIndex.get(i));
				int ranFor = 0;
				for (int j = 0; j < 2 && mem.hasNextInstruction(memoryIndex.get(i)); j++) {
					System.out.println("Instruction: "+mem.getInstruction(memoryIndex.get(i)));
					parser(mem.getInstruction(memoryIndex.get(i)),memoryIndex.get(i));
					mem.incrementPc(memoryIndex.get(i));
					ranFor++;
				}
				System.out.println("Process "+mem.getID(memoryIndex.get(i))+" ran "+ranFor+ " instructions");
				if (!mem.hasNextInstruction(memoryIndex.get(i))) {
					mem.setState(ProcessState.Finished,memoryIndex.get(i));
					System.out.println("Process "+mem.getID(memoryIndex.get(i))+" took " +quanta.get(memoryIndex.get(i))+" quanta");
				} else {
					mem.setState(ProcessState.NotRunning,memoryIndex.get(i));
				}
				if (mem.getState(memoryIndex.get(i)) == ProcessState.Finished) {
					memoryIndex.remove(memoryIndex.get(i));
					i = -1;
				}
				System.out.println("-----------------");
			}
		}
	}


	public static void parser(String instruction, Integer memoryBound) {
		String[] parts = instruction.split(" ");
		parsing(parts,memoryBound);
	}

	public static void parsing(String[] parts,int memoryBound ){

		switch (parts[0]) {

			case "add":

			if (parts[1].equals("input") && parts[2].equals("input")) {
				add(input(), input(), memoryBound);
				break;

			} else if (parts[1].equals("input") && !parts[2].equals("input")) {
				String addString = "";
				for (int x = 2; x < parts.length; x++) {
					addString = addString + " " + parts[x];
				}
				add(input(), addString,memoryBound);
				break;

			} else if (!parts[1].equals("input") && parts[2].equals("input")) {
				add(parts[1], input(),memoryBound);
				break;
			} else {

				String addString = "";
				for (int x = 2; x < parts.length; x++) {
					addString = addString + parts[x];
				}
				add(parts[1], addString,memoryBound);
				break;

			}

		case "writeFile":

			if (parts[1].equals("input") && parts[2].equals("input")) {
				writeFile(input(), input(), memoryBound);
				break;
			} else if (parts[1].equals("input") && !parts[2].equals("input")) {
				String writestring = "";
				for (int x = 2; x < parts.length; x++) {
					writestring = writestring + " " + parts[x];
				}
				writeFile(input(), writestring,memoryBound);
				break;
			} else if (!parts[1].equals("input") && parts[2].equals("input")) {
				writeFile(parts[1], input(),memoryBound);
				break;
			} else {

				String writestring = "";
				for (int x = 2; x < parts.length; x++) {
					writestring = writestring + parts[x];
				}
				writeFile(parts[1], writestring,memoryBound);
				break;

			}
			case "readFile":

			if (parts[1].equals("input")) {
				readFile(input(),memoryBound);
				break;

			}else {
				String readString = "";
				for (int x = 2; x < parts.length; x++) {
					readString = readString + parts[x];
				}
				readFile(readString,memoryBound);
				break;
			}

		case "assign":

			if (parts[1].equals("input") && parts[2].equals("input")) {
				assign(input(), input(),memoryBound);
				break;
			}
			if (parts[1].equals("input") && !parts[2].equals("input")) {
				String assignString = "";
				for (int x = 2; x < parts.length; x++) {
					assignString = assignString + " " + parts[x];
				}
				assign(input(), assignString,memoryBound);
				break;

			}
			if (!parts[1].equals("input") && parts[2].equals("input")) {
				assign(parts[1], input(),memoryBound);
				break;
			} else {

				String assignstring = "";
				for (int x = 2; x < parts.length; x++) {
					assignstring = assignstring + parts[x] + " ";
				}
				assign(parts[1], assignstring,memoryBound);

				break;
			}

		case "print":

			String printString = "";
			for (int x = 1; x < parts.length; x++) {
				printString = printString + parts[x];
			}
			print(printString,memoryBound);

			break;

		default:
			System.out.print(Arrays.toString(parts));
			break;
		// code block
		}
	}

	public static String input(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in); //System.in is a standard input stream
		String str = sc.nextLine(); // reads string
		return str;
	}
	// ----------------------------------------------------------

	public static void assign(String one, String two, int memoryBound) {
		String[] arg2 = two.split(" ");

		String varName = "";
		String dataString = "";
		if (one.contains("readFile")) {
			arg2 = one.split(" ");
			if (mem.contains(arg2[1],memoryBound))
				varName = readFile((String) mem.getVar(arg2[1],memoryBound),memoryBound);
			else
				varName = readFile((arg2[1]),memoryBound);
		} else
			varName = one;

		if (two.contains("readFile")) {
			arg2 = two.split(" ");

			if (mem.contains(arg2[1],memoryBound))
				dataString = readFile((String) mem.getVar(arg2[1],memoryBound),memoryBound);
			else
				dataString = readFile((arg2[1]),memoryBound);

		} else
			dataString = two;
		mem.addVar(varName, dataString,memoryBound);
	}

	public static void print(String statement,int memoryBound) {
		if (mem.contains(statement,memoryBound))
			System.out.println("Printed on Screen "  + mem.getVar(statement,memoryBound));
		else
			System.out.println("Printed on Screen "+ statement);
	}

	// ----------------------------------------------------------

	public static void add(String first, String second, int memoryBound) {
		System.out.println(first + " " + second);
		System.out.println(memoryBound);
		int a = Integer.parseInt((String) mem.getVar(first,memoryBound));
		int b = Integer.parseInt((String) mem.getVar(second,memoryBound));
		int res = a + b;
		String ress = "" + res;
		mem.addVar(first, ress,memoryBound);
	}

	public static void writeFile(String fileName,String data , int memoryBound){
        if(mem.contains(fileName,memoryBound)) {
            fileName = (String) mem.getVar(fileName,memoryBound);
        }
        if(mem.contains(data,memoryBound)) {
             data = (String) mem.getVar(data,memoryBound);
        }

        try {
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	public static String readFile(String fileName, int memoryBound){
    	String res = "";
    	if(mem.contains(fileName,memoryBound)) {
    		fileName = (String) mem.getVar(fileName,memoryBound);
    	}
    	try {
    		File f = new File(fileName);
    		if(f.exists()) {
    			@SuppressWarnings("resource")
				BufferedReader b = new BufferedReader(new FileReader(f));
    			String reader = b.readLine();
    			while(reader!=null) {
    				res = res+reader;
    				reader = b.readLine();
    			}
    		}
    		else {
    			System.out.println("file does not exist");
    		}
    		
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    	return res;
    }

	// ----------------------------------------------------------

}