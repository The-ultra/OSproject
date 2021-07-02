import java.util.ArrayList;
import java.util.Arrays;

public class Memory {
	Word[] memory;
	private int pointer = 0;
	public Memory() {
		// TODO Auto-generated constructor stub
		memory = new Word[1080];
	}

	public void addProcess(int id) {
		try {
			memory[pointer] = new Word("TopBoundary", pointer);
			memory[pointer+1] = new Word("ProcessID", id);
			memory[pointer+2] = new Word("InstructionPointer", 6);
			memory[pointer + 3] = new Word("ProcessID", id);
			memory[pointer + 4] = new Word("ProcessState",ProcessState.NotRunning);
			memory[pointer + 5] = new Word("PC", 6);
			memory[pointer + 106] = new Word("VarPointer", 56);
			memory[pointer + 107] = new Word("BottomBoundary", pointer + 107);
			pointer+= 108;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("process limit reached");
		}
	}
	
//	public MemoryBlock getBlock(int indx) {
//		// TODO Auto-generated method stub
//		return memory[indx];
//	}
	public int getRecentlyCreated()
	{
		return pointer-108;
	}
	
	public ArrayList<Integer> getProcesses() {
		// TODO Auto-generated method stub
		ArrayList<Integer> porcesses = new ArrayList<Integer>();
		int i = 0;
		while(memory[i] != null && i < memory.length) {
			porcesses.add((int)memory[i].data);
			i+=108;
		}
		return porcesses;
	}
	
	public void setID(int id,int bound)
	{
		memory[1+bound] = new Word("ProcessID", id);
	}
	public int getID(int bound)
	{
		return (int) memory[1+bound].data;
	}
	public void setState(ProcessState state,int bound)
	{
		memory[4+ bound] = new Word("ProcessState", state);
		System.out.println("State at indx: "+(4+bound)+" is changed to "+ state);
	}
	
	public ProcessState getState(int bound)
	{
		return (ProcessState) memory[4 + bound].data;
	}
	
	public void setPc(int pc,int bound)
	{
		memory[bound+5] = new Word("PC", pc);
	}
	
	public void incrementPc(int bound)
	{
		int currPC = (int) memory[bound+5].data;
		currPC++;
		memory[bound+5] = new Word("PC", currPC);
	}
	
	public void addVar(String varName, Object data, int bound)
	{
		int varPointer = (int)memory[bound+106].data;
		try {
			if(varPointer <106) {
				boolean flag = false;
				for (int i = 56; i < varPointer; i++) 
					if(memory[i+bound].key.equals(varName)) {
						 memory[i+bound] = new Word(varName, data);
						 flag = true;
						 System.out.println("written "+varName+" from indx: "+(i+bound));
					}
				if(!flag) {
					memory[varPointer+bound] = new Word(varName, data);
					varPointer++;
					memory[bound+106].data = varPointer;
					System.out.println("written "+varName+" from indx: "+(varPointer-1+bound));}
				
			}
			else
				throw new Exception();
		} catch (Exception e) {
			System.out.println("variable memory of process "+memory[100].data+" ran out of space");
		}
			
	}
	
	public Object getVar(String key,int bound)
	{
		int varPointer = (int)memory[bound+106].data;
		try {
			for (int i = 56; i <= varPointer && memory[i+bound] != null; i++) {
				if(memory[i+bound].key.equals(key)) {
					System.out.println("read "+key+" from indx: "+(i+bound));
					return memory[i+bound].data;
				}
			}
			throw new Exception();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("variable is not in memory");
		}
		return null;
	}
	
	public boolean contains(String varName, int bound)
	{
		int varPointer = (int)memory[bound+106].data;
		for (int i = 56; i < varPointer && memory[i+bound] != null; i++) 
			if(memory[i+bound].key.equals(varName))
				return true;
		return false;
		
	}
	
	public void addInstruction(String instruction,int bound)
	{
		int instructionPointer = (int)memory[bound+2].data;
		try {
			if(instructionPointer <56) {
				memory[instructionPointer+bound] = new Word(instructionPointer+"", instruction);
				instructionPointer++;
			}
			else
				throw new Exception();
			memory[bound+2].data = instructionPointer;
		} catch (Exception e) {
			System.out.println("instruction memory of process "+memory[1+bound].data+" ran out of space");
		}
			
	}
	
	public String getInstruction(int bound)
	{
		try {
			int pos = (int) memory[5+bound].data;
			//System.out.println(pos);
			if(pos<56)
				return (String) memory[pos+bound].data;
			else
				throw new Exception();
		} catch (Exception e) {
			System.out.println("instruction memory of process" +memory[bound+1].data +" ran out of space");
		}
		
		return "";
	}
	
	public boolean hasNextInstruction(int bound)
	{
		return (int)memory[5+bound].data < (int) memory[2+bound].data;
	}
	
	public void printPCB(int bound)
	{
		System.out.println("PCB:");
		System.out.println("ID: "+getID(bound) +"Indx: " + (bound+1));
		System.out.println("State: " +getState(bound)+  "Indx: " + (bound+4));
		System.out.println("PC: " + memory[5+bound].data + "Indx: " + (bound+5));
		System.out.println("Top Bound: " + memory[bound].data + "Indx: " + (bound));
		System.out.println("Bottom Bound: "+memory[bound+107].data + "Indx: " + (bound+107));
		
	}
}
