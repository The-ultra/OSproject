public class Memory {
	MemoryBlock[] memory;
	private int pointer = 0;

	public Memory() {
		// TODO Auto-generated constructor stub
		memory = new MemoryBlock[10];
	}

	public int addProcess(int id) {
		try {
			memory[pointer] = new MemoryBlock(id, pointer);
			pointer++;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("process limit reached");
		}
		
		return pointer-1;
	}
	
	public MemoryBlock getBlock(int indx) {
		// TODO Auto-generated method stub
		return memory[indx];
		
	}
	
	
}
