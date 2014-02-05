package ouch.study.fpe.controller;

import java.util.Collection;
import java.util.List;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.EnumeratorFatory;
import ouch.study.fpe.domain.FlapPatternEnumerator;
import ouch.study.fpe.domain.LineType;

public class BruteForceEnumerationRunner implements
		EnumerationRunner {
	private final IntegerGettable divisionSizeHolder;
	private final FlapPatternsSettable patternHolder;

	public BruteForceEnumerationRunner(final IntegerGettable divisionSizeHolder,
			final FlapPatternsSettable patternHolder) {
		this.divisionSizeHolder = divisionSizeHolder;
		this.patternHolder = patternHolder;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * ouch.study.fpe.controller.EnumerationRunner#run(java.util.Collection)
	 */
	@Override
	public Collection<AngleUnitFlapPattern> run(final Collection<List<LineType>> elements) {
		EnumeratorFatory factory = new EnumeratorFatory();

		FlapPatternEnumerator enumerator = factory.createBruteForceEnumerator();

		Collection<AngleUnitFlapPattern> result = enumerator.enumerateUniquePatterns(divisionSizeHolder.getInteger(),
				elements);

		patternHolder.setFlapPatterns(result);

		return result;
	}
}
