package ouch.study.fpe.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.EnumeratorFactory;
import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.domain.rule.FlapPatternEnumerator;

@Configurable
public class PuzzlingEnumerationRunner implements EnumerationRunner {
	@Autowired
	@Qualifier("puzzling")
	EnumeratorFactory factory;

	private final IntegerGettable divisionSizeHolder;
	private final FlapPatternsSettable patternHolder;

	public PuzzlingEnumerationRunner(final IntegerGettable divisionSizeHolder,
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

		FlapPatternEnumerator enumerator = factory.create();

		Collection<AngleUnitFlapPattern> result = enumerator.enumerateUniquePatterns(divisionSizeHolder.getInteger(),
				elements);

		patternHolder.setFlapPatterns(result);

		return result;
	}

}
