package ouch.study.fpe.domain;

import oripa.util.collection.Rule;
import ouch.study.fpe.domain.rule.AcceptableByLineCount;
import ouch.study.fpe.domain.rule.AlwaysTrue;
import ouch.study.fpe.domain.rule.ConjunctionRule;
import ouch.study.fpe.domain.rule.DuplicatedPatternHash;
import ouch.study.fpe.domain.rule.OrigamiFoldability;

public class RuleFactory {

	public ConjunctionRule<AngleUnitFlapPattern> createFoldablilityRuleAsConjunctionable() {
		return new ConjunctionRule<>(new OrigamiFoldability());
	}

	public Rule<AngleUnitFlapPattern> createFoldabilityRule() {
		return new OrigamiFoldability();
	}

	public Rule<AngleUnitFlapPattern> createDuplicationDetector(final int headIndex) {
		return new DuplicatedPatternHash(headIndex);
	}

	public Rule<AngleUnitFlapPattern> createNoPruning() {
		return new AlwaysTrue().asDenied();
	}

	public Rule<AngleUnitFlapPattern> createAcceptableByLineCount(final int expectedCount) {
		return new AcceptableByLineCount(expectedCount);
	}

	public Rule<AngleUnitFlapPattern> createAlwaysAcceptable() {
		return new AlwaysTrue();
	}
}
