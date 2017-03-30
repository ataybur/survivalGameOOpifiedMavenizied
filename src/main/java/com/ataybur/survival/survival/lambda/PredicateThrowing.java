package com.ataybur.survival.survival.lambda;

import java.util.function.Predicate;

public interface PredicateThrowing<T> extends Predicate<T> {
	@Override
	default boolean test(T t) {
		boolean result;
		try {
			result = testThrowing(t);
		} catch (Exception e) {
			result = false;
			throwAsUnchecked(e);
		}
		return result;
	}

	boolean testThrowing(T t) throws Exception;

	@SuppressWarnings("unchecked")
	static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E {
		throw (E) exception;
	}
}
