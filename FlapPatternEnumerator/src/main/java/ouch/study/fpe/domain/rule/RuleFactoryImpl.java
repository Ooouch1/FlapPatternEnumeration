package ouch.study.fpe.domain.rule;

import org.springframework.stereotype.Component;

import oripa.util.collection.Rule;
import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.RuleFactory;

@Component
public class RuleFactoryImpl implements RuleFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ouch.study.fpe.domain.RuleFactory#createFoldablilityRuleAsConjunctionable
	 * ()
	 */
	@Override
	public ConjunctionRule<AngleUnitFlapPattern> createFoldablilityRuleAsConjunctionable() {
		return new ConjunctionRule<>(new OrigamiFoldability());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ouch.study.fpe.domain.RuleFactory#createFoldabilityRule()
	 */
	@Override
	public Rule<AngleUnitFlapPattern> createFoldabilityRule() {
		return new OrigamiFoldability();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ouch.study.fpe.domain.RuleFactory#createDuplicationDetector(int)
	 */
	@Override
	public Rule<AngleUnitFlapPattern> createDuplicationDetector(final int headIndex) {
		return new DuplicatedPatternHash(headIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ouch.study.fpe.domain.RuleFactory#createNoPruning()
	 */
	@Override
	public Rule<AngleUnitFlapPattern> createNoPruning() {
		return new AlwaysTrue().asDenied();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ouch.study.fpe.domain.RuleFactory#createAcceptableByLineCount(int)
	 */
	@Override
	public Rule<AngleUnitFlapPattern> createAcceptableByLineCount(final int expectedCount) {
		return new AcceptableByLineCount(expectedCount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ouch.study.fpe.domain.RuleFactory#createAlwaysAcceptable()
	 */
	@Override
	public Rule<AngleUnitFlapPattern> createAlwaysAcceptable() {
		return new AlwaysTrue();
	}
}
