package ouch.study.fpe.domain.puzzle;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import oripa.util.collection.Rule;
import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.domain.RuleFactory;
import ouch.study.fpe.domain.rule.FlapPatternEnumerator;

@Configurable
public class PuzzlingFlapPatternEnumerator implements FlapPatternEnumerator {
	@Autowired
	private RuleFactory ruleFactory;

	@Override
	public Collection<AngleUnitFlapPattern> enumerateUniquePatterns(final int divisionSize,
			final Collection<List<LineType>> pieces) {
		Rule<AngleUnitFlapPattern> acceptionRule = ruleFactory
				.createDuplicationDetector(0).asDenied();

		Rule<AngleUnitFlapPattern> pruningRule = ruleFactory
				.createNoPruning();

		PuzzlingFlapPatternSetFactory factory = new PuzzlingFlapPatternSetFactory(acceptionRule,
				pruningRule);
		return factory.createPatterns(divisionSize, pieces);
	}

}
