package pliance.sdk;

@FunctionalInterface
public interface Func<T, E extends Throwable> {
	T accept() throws E;
}
