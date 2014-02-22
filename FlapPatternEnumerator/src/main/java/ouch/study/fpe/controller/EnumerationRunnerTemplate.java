package ouch.study.fpe.controller;

import java.util.Collection;
import java.util.List;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.domain.rule.FlapPatternEnumerator;

public class EnumerationRunnerTemplate implements
		EnumerationRunner {
	private final IntegerGettable divisionSizeHolder;
	private final FlapPatternsSettable patternHolder;
	private final FlapPatternEnumerator enumerator;

	public EnumerationRunnerTemplate(
			final FlapPatternEnumerator aEnumerator,
			final IntegerGettable divisionSizeHolder,
			final FlapPatternsSettable patternHolder) {
		this.enumerator = aEnumerator;
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

		Collection<AngleUnitFlapPattern> result = enumerator.enumerateUniquePatterns(divisionSizeHolder.getInteger(),
				elements);

		patternHolder.setFlapPatterns(result);

		return result;
	}
}
