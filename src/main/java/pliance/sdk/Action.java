package pliance.sdk;

@FunctionalInterface
public interface Action<E extends Exception> {
	void accept() throws E;
}