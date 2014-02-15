package ouch.study.fpe.controller;

import ouch.study.fpe.domain.EnumeratorFatory;

public class EnumerationRunnerFactory {

	EnumeratorFatory factory = new EnumeratorFatory();

	public EnumerationRunner createBruteForceRunner(
			final IntegerGettable divisionSizeHolder,
			final FlapPatternsSettable patternHolder) {

		return new EnumerationRunnerTemplate(factory.createBruteForceEnumerator(),
				divisionSizeHolder, patternHolder);
	}

	public EnumerationRunner createPuzzlingRunner(
			final IntegerGettable divisionSizeHolder,
			final FlapPatternsSettable patternHolder) {

		return new EnumerationRunnerTemplate(factory.createPuzzlingEnumerator(),
				divisionSizeHolder, patternHolder);

	}
}
