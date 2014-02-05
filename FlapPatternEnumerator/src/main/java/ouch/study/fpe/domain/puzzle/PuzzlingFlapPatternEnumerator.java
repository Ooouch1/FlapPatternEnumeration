package ouch.study.fpe.domain.puzzle;

import java.util.Collection;
import java.util.List;

import oripa.util.collection.Rule;
import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.FlapPatternEnumerator;
import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.domain.RuleFactory;

public class PuzzlingFlapPatternEnumerator implements FlapPatternEnumerator {
	RuleFactory ruleFactory = new RuleFactory();

	Rule<AngleUnitFlapPattern> acceptionRule = ruleFactory
			.createDuplicationDetector(0).asDenied();

	Rule<AngleUnitFlapPattern> pruningRule = ruleFactory
			.createNoPruning();

	@Override
	public Collection<AngleUnitFlapPattern> enumerateUniquePatterns(final int divisionSize,
			final Collection<List<LineType>> pieces) {
		PuzzlingFlapPatternSetFactory factory = new PuzzlingFlapPatternSetFactory(acceptionRule,
				pruningRule);
		return factory.createPatterns(divisionSize, pieces);
	}

}
