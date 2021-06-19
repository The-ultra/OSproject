enum process_status{ready, not_ready, finshed};
public class PCB {
	public int getProcess_id() {
		return process_id;
	}

	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}

	public int getProgram_counter() {
		return program_counter;
	}

	public void setProgram_counter(int program_counter) {
		this.program_counter = program_counter;
	}

	public process_status getProcess_current_status() {
		return process_current_status;
	}

	public void setProcess_current_status(process_status process_current_status) {
		this.process_current_status = process_current_status;
	}

	public int process_id;
	
	public int program_counter;
	public process_status process_current_status; 
	
	public PCB() {
		this.process_id++;
		this.program_counter=0;
		this.process_current_status = process_status.not_ready;

	}

}
