package com.pliance.app;

@FunctionalInterface
public interface Action<EX extends Throwable> {
	void accept() throws EX;
}