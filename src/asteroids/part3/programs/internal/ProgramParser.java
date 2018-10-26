package asteroids.part3.programs.internal;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.internal.generated.AsteroidsProgramLexer;
import asteroids.part3.programs.internal.generated.AsteroidsProgramParser;
import asteroids.program.Program;
import asteroids.util.internal.ResourceUtils;

/**
 * Parser for Asteroids programs.
 * 
 * To use this class, first create an implementation of {@link IProgramFactory}:
 * 
 * <pre>
 * <code>
 * IProgramFactory&lt;MyExpression, MyStatement, Task&gt; factory = new ProgramFactory();
 * </code>
 * </pre>
 * 
 * The easiest way to use this class for parsing a Program given as a String is
 * via the {@link #parseProgramFromString(String, IProgramFactory)} method:
 * 
 * <pre>
 * <code>
 * Program program = ProgramParser.parseTask(text, factory);
 * </code>
 * </pre>
 * 
 * For more control, create an instance of this class:
 * 
 * <pre>
 * <code>
 * ProgramParser&lt;MyExpression, MyStatement, Task&gt; parser = new ProgramParser<>(factory);
 * </code>
 * </pre>
 * 
 * Finally, parse a string or file: <code><pre>
 * Optional&lt;Program&gt; parseResult = parser.parse(textToParse);
 * </pre></code>
 * 
 * If parsing is successful, <code>parseResult.isPresent()</code> returns true
 * and <code>parseResult.get()</code> returns the created task.
 * 
 * If parsing was not successful, <code>parseResult.isPresent()</code> returns
 * false and <code>parser.getErrors()</code> can be used to retrieve the list of
 * errors during parsing.
 * 
 *
 * @param E
 *            The type of expressions
 * @param S
 *            The type of statements
 * @param T
 *            The type of types
 * @param P
 *            The type of Program
 */
public class ProgramParser<E, S, F, P> {

	private final IProgramFactory<E, S, F, P> factory;

	private final List<String> errors = new ArrayList<>();

	protected ProgramParser(IProgramFactory<E, S, F, P> factory) {
		this.factory = factory;
	}

	public IProgramFactory<E, S, F, P> getFactory() {
		return factory;
	}

	/**
	 * Returns the program that results from parsing the given string, or
	 * Optional.empty() if parsing has failed.
	 * 
	 * When parsing has failed, the error messages can be retrieved with the
	 * getErrors() method.
	 *
	 */
	public ParseOutcome<P> parseString(String string) {
		return parse(CharStreams.fromString(string));
	}

	/**
	 * Returns the program that results from parsing the file with the given
	 * name, or Optional.empty() if parsing has failed.
	 * 
	 * When parsing has failed, the error messages can be retrieved with the
	 * getErrors() method.
	 */
	public ParseOutcome<P> parseFile(String filename) throws IOException {
		return parse(CharStreams.fromStream(ResourceUtils.openResource(filename)));
	}
	
	/**
	 * Returns the program that results from parsing the file with the given
	 * URL, or Optional.empty() if parsing has failed.
	 * 
	 * When parsing has failed, the error messages can be retrieved with the
	 * getErrors() method.
	 */
	public ParseOutcome<P> parseFile(URL url) throws IOException {
		return parse(CharStreams.fromStream(ResourceUtils.openResource(url)));
	}

	/**
	 * Returns the program that results from parsing the given CharStream, or
	 * Optional.empty() if parsing has failed.
	 * 
	 * When parsing has failed, the error messages can be retrieved with the
	 * getErrors() method.
	 * 
	 * @note For more convenient methods, see the
	 *       {@link #parseProgramFromString(String, ITaskFactory, List)},
	 *       {@link #parseProgramFromFile(String, ITaskFactory, List)},
	 *       {@link #parseString(String, List)} and
	 *       {@link #parseFile(String, List)} methods.
	 */
	protected ParseOutcome<P> parse(CharStream input) {
		reset();

		AsteroidsProgramLexer lexer = new AsteroidsProgramLexer(input);
		AsteroidsProgramParser parser = new AsteroidsProgramParser(new CommonTokenStream(lexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				errors.add(msg + " (" + line + ", " + charPositionInLine + ")");
			}
		});
		ParserVisitor<E, S, F, P> visitor = new ParserVisitor<>(factory);
		try {
			visitor.visitProgram(parser.program());
			if (errors.isEmpty()) {
			  assert visitor.getMain() != null;
				P program = factory.createProgram(visitor.getFunctions(),visitor.getMain());
				if (program != null) {
					return ParseOutcome.success(program);
				}
				errors.add("Factory did not return a Program object");
			}
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(e.toString());
		}
		return ParseOutcome.failure(errors);
	}

	protected void reset() {
		this.errors.clear();
	}

	public List<String> getErrors() {
		return Collections.unmodifiableList(errors);
	}

	/**
	 * Create a new parser from the given factory.
	 * 
	 * @param factory
	 * @return
	 */
	public static <E, S, T, P> ProgramParser<E, S, T, P> create(IProgramFactory<E, S, T, P> factory) {
		return new ProgramParser<>(factory);
	}

	/**
	 * Parse program text using the given factory.
	 * 
	 * @param text
	 *            The text to parse
	 * @param factory
	 *            The factory to use
	 * @return The parsed program, if any, or null if an error occurred during
	 *         parsing.
	 */
	public static Program parseProgramFromString(String text, IProgramFactory<?, ?, ?, Program> factory) {
		ProgramParser<?, ?, ?, Program> parser = create(factory);
		ParseOutcome<Program> outcome = parser.parseString(text);
		if (outcome.isFail()) {
			System.out.println("Parsing failed: " + outcome.getFailValue());
			return null;
		}
		return outcome.getSuccessValue();
	}

	/**
	 * Parse program from a file using the given factory.
	 * 
	 * @param filename
	 *            The filename from which to read the program text
	 * @param factory
	 *            The factory to use
	 * @return The parsed program, if any, or null if an error occurred during
	 *         parsing.
	 */
	public static Program parseTasksFromFile(String filename, IProgramFactory<?, ?, ?, Program> factory)
			throws IOException {
		ProgramParser<?, ?, ?, Program> parser = create(factory);
		ParseOutcome<Program> outcome = parser.parseFile(filename);
		if (outcome.isFail()) {
			System.out.println("Parsing failed: " + outcome.getFailValue());
			return null;
		}
		return outcome.getSuccessValue();
	}
	
	/**
	 * Parse program from a file using the given factory.
	 * 
	 * @param filename
	 *            The filename from which to read the program text
	 * @param factory
	 *            The factory to use
	 * @return The parsed program, if any, or null if an error occurred during
	 *         parsing.
	 */
	public static Program parseTasksFromURL(URL url, IProgramFactory<?, ?, ?, Program> factory)
			throws IOException {
		ProgramParser<?, ?, ?, Program> parser = create(factory);
		ParseOutcome<Program> outcome = parser.parseFile(url);
		if (outcome.isFail()) {
			System.out.println("Parsing failed: " + outcome.getFailValue());
			return null;
		}
		return outcome.getSuccessValue();
	}
}
