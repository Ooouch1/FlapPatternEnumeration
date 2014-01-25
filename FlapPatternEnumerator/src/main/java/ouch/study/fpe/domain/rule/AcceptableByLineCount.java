package ouch.study.fpe.domain.rule;

import oripa.util.collection.AbstractRule;
import ouch.study.fpe.domain.AngleUnitFlapPattern;

public class AcceptableByLineCount extends AbstractRule<AngleUnitFlapPattern> {
	private final int expectedCount;

	public AcceptableByLineCount(final int expectedCount) {
		this.expectedCount = expectedCount;
	}

	@Override
	public boolean holds(final AngleUnitFlapPattern pattern) {
		return pattern.countLines() == expectedCount;
	}
}
