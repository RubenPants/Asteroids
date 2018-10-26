package asteroids.part3.programs.internal.example;

import java.io.IOException;

import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.internal.ParseOutcome;
import asteroids.part3.programs.internal.ProgramParser;

public class ExamplePrinter {
	public static void main(String[] args) throws IOException {
		IProgramFactory<PrintingObject, PrintingObject, PrintingObject, PrintingProgram> factory = PrintingObjectFactory.create();
		ProgramParser<?, ?, ?, PrintingProgram> parser = ProgramParser.create(factory);

		//ParseOutcome<PrintingProgram> outcome = parser.parseFile("src-provided/asteroids/resources/programs/syntax_test.txt");
		//ParseOutcome<PrintingProgram> outcome = parser.parseFile("src-provided/asteroids/resources/programs/program_simple.txt");
		//ParseOutcome<PrintingProgram> outcome = parser.parseFile("src-provided/asteroids/resources/programs/program.txt");
		//ParseOutcome<PrintingProgram> outcome = parser.parseFile("src-provided/asteroids/resources/programs/program_assignment.txt");
		ParseOutcome<PrintingProgram> outcome = parser.parseString("def sumfac { " + 
		"  a := $1; " + 
		"  t := 1.0; " + 
		"  while 1.5 < a { "
	        + "    t := t + (a*sumfac(a + -1.0));" +
		"    a := a + -1.0; " + "  }" + 
	        
		"  return t; " + "} "
	        + 
		"print sumfac(4.0); ");

		System.out.println(outcome);	
		
		
	
		
	}

}
