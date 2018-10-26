package asteroids.util.internal;

/**
 * An interface that holds either a success value of type S or a failure value of type F.
 */
public interface SuccessOrFail<S, F> {

	public boolean isSuccess();

	public S getSuccessValue();

	public F getFailValue();

	public default boolean isFail() {
		return !isSuccess();
	}

	public static class DefaultSuccessOrFail<S, F> implements SuccessOrFail<S, F> {
		private final boolean success;
		private final F failValue;
		private final S successValue;

		private DefaultSuccessOrFail(boolean success, S successValue, F failValue) {
			if (success && failValue != null) {
				throw new IllegalArgumentException();
			}
			if (!success && successValue != null) {
				throw new IllegalArgumentException();
			}
			this.success = success;
			this.successValue = successValue;
			this.failValue = failValue;
		}

		@Override
		public boolean isSuccess() {
			return success;
		}

		@Override
		public F getFailValue() {
			return failValue;
		}

		@Override
		public S getSuccessValue() {
			return successValue;
		}

		public static <S, F> SuccessOrFail<S, F> success(S successValue) {
			return new DefaultSuccessOrFail<>(true, successValue, null);
		}

		public static <S, F> SuccessOrFail<S, F> failure(F failValue) {
			return new DefaultSuccessOrFail<>(false, null, failValue);
		}

		@Override
		public String toString() {
			if (isSuccess()) {
				return "SUCCESS value: " + getSuccessValue();
			} else {
				return "FAIL value: " + getFailValue();
			}
		}

	}

	public static class WrappedSuccessOrFail<S, F> implements SuccessOrFail<S, F> {
		private final SuccessOrFail<S, F> wrapped;

		protected WrappedSuccessOrFail(SuccessOrFail<S, F> wrapped) {
			if (wrapped == null) {
				throw new NullPointerException();
			}
			this.wrapped = wrapped;
		}

		@Override
		public boolean isSuccess() {
			return wrapped.isSuccess();
		}

		@Override
		public S getSuccessValue() {
			return wrapped.getSuccessValue();
		}

		@Override
		public F getFailValue() {
			return wrapped.getFailValue();
		}

		@Override
		public String toString() {
			return wrapped.toString();
		}

	}
}
