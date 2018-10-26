package asteroids.part3.programs.internal.example;

import java.util.List;

class PrintingProgram {

	private List<PrintingObject> functions;
	private PrintingObject main;

	public PrintingProgram(List<PrintingObject> functions, PrintingObject main) {
		if (main == null) {
			throw new NullPointerException("main null");
		}
		this.main = main;
		this.functions = functions;
	}

	@Override
	public String toString() {
		return "Functions: " + functions.toString() + "\nMain:" + main.toString();
	}
}
