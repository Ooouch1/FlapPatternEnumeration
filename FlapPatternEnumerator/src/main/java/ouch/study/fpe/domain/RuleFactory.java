package ouch.study.fpe.domain;

import oripa.util.collection.Rule;
import ouch.study.fpe.domain.rule.ConjunctionableRule;

public interface RuleFactory {

	public abstract ConjunctionableRule<AngleUnitFlapPattern> createFoldablilityRuleAsConjunctionable();

	public abstract Rule<AngleUnitFlapPattern> createFoldabilityRule();

	public abstract Rule<AngleUnitFlapPattern> createDuplicationDetector(int headIndex);

	public abstract Rule<AngleUnitFlapPattern> createNoPruning();

	public abstract Rule<AngleUnitFlapPattern> createAcceptableByLineCount(int expectedCount);

	public abstract Rule<AngleUnitFlapPattern> createAlwaysAcceptable();

}