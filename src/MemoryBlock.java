
public class MemoryBlock 
{
	Word[] block;
	int varPointer = 50;
	int instructionPointer = 0;
	public MemoryBlock(int id ,int indx) 
	{
		block = new Word[104];
		setID(id);
		setState(ProcessState.NotRunning);
		setBoundary(indx);
		setPc(0);
	}
	
	public void setID(int id)
	{
		block[100] = new Word("ProcessID", id);
	}
	public int getID()
	{
		return (int) block[100].data;
	}
	public void setState(ProcessState state)
	{
		block[101] = new Word("ProcessState", state);
	}
	
	public ProcessState getState()
	{
		return (ProcessState) block[101].data;
	}
	
	public void setPc(int pc)
	{
		block[102] = new Word("PC", pc);
	}
	
	public void incrementPc()
	{
		int currPC = (int) block[102].data;
		currPC++;
		block[102] = new Word("PC", currPC);
	}
	
	public void setBoundary(int indx)
	{
		block[103] = new Word("Boundary", indx);
	}
	
	public void addVar(String varName, Object data)
	{
		try {
			if(varPointer <100) {
				block[varPointer] = new Word(varName, data);
				varPointer++;
			}
			else
				throw new Exception();
		} catch (Exception e) {
			System.out.println("variable memory of process "+block[100]+" ran out of space");
		}
			
	}
	
	public Object getVar(String key)
	{
		try {
			for (int i = 50; i < varPointer; i++) 
				if(block[varPointer].key.equals(key))
					return block[varPointer].data;
			throw new Exception();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("variable is not in memory");
		}
		return null;
	}
	
	public void addInstruction(String instruction)
	{
		try {
			if(instructionPointer <50) {
				block[instructionPointer] = new Word(instructionPointer+"", instruction);
				instructionPointer++;
			}
			else
				throw new Exception();
		} catch (Exception e) {
			System.out.println("instruction memory of process "+block[100]+" ran out of space");
		}
			
	}
	
	public String getInstruction()
	{
		try {
			int pos = (int) block[102].data;
			if(pos<50)
				return (String) block[pos].data;
			else
				throw new Exception();
		} catch (Exception e) {
			System.out.println("instruction memory of process" +block[100] +" ran out of space");
		}
		
		return "";
	}
	
	public boolean hasNextInstruction()
	{
		return (int)block[102].data <= instructionPointer;
	}
	
	
}
