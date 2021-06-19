import java.io.IOException;
import java.util.ArrayList;

public class scheduler {

	
	public scheduler(ArrayList<MemoryBlock> procList) throws IOException {
		while(!procList.isEmpty()) {
			for(int procCount =0; procCount<procList.size();procCount++ ) {
				
				MemoryBlock thisPCB = procList.get(procCount);
				String thisInstuctionString = thisPCB.getInstruction();
			
	                String[] parts = thisInstuctionString.split(" ");
				Main.parsing(parts);
				
			
				
				
			}
		
		
		
		}
		}
	
	
	
	
}
