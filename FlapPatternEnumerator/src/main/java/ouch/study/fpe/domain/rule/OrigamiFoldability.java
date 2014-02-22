package ouch.study.fpe.domain.rule;

import oripa.util.collection.AbstractRule;
import ouch.study.fpe.domain.AngleUnitFlapPattern;

public class OrigamiFoldability extends AbstractRule<AngleUnitFlapPattern> {
	@Override
	public boolean holds(final AngleUnitFlapPattern pattern) {
		if (!pattern.isProbablyFoldable()) {
			return false;
		}

		LayerCollisionChecker checker = new LayerCollisionChecker();
		return checker.patternCanBeFlatten(pattern);
	}
}
