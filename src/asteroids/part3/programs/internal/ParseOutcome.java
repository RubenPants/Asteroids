package asteroids.part3.programs.internal;

import java.util.List;

import asteroids.util.internal.SuccessOrFail;
import asteroids.util.internal.SuccessOrFail.WrappedSuccessOrFail;

public class ParseOutcome<P> extends WrappedSuccessOrFail<P, List<String>> {
	
	private ParseOutcome(SuccessOrFail<P, List<String>> outcome) {
		super(outcome);
	}

	public static <P> ParseOutcome<P> success(P p) {
		return new ParseOutcome<>(DefaultSuccessOrFail.success(p));
	}

	public static <P> ParseOutcome<P> failure(List<String> errors) {
		return new ParseOutcome<>(DefaultSuccessOrFail.failure(errors));
	}
}
