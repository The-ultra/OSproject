public class Memory {
	MemoryBlock[] memory;
	private int pointer;

	public Memory() {
		// TODO Auto-generated constructor stub
		memory = new MemoryBlock[10];
	}

	public void addProcess(int id) {
		try {
			memory[pointer] = new MemoryBlock(id, pointer);
			pointer++;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("process limit reached");
		}
	}
	
	public MemoryBlock getBlock(int indx) {
		// TODO Auto-generated method stub
		return memory[indx];
	}
}
