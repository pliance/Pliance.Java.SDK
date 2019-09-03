package pliance.sdk;

@FunctionalInterface
public interface Action<E extends Throwable> {
	void accept() throws E;
}