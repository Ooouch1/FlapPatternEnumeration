package ouch.study.fpe.domain.rule;

import oripa.util.collection.AbstractRule;
import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.rule.lib.LayerFlattenability;
import ouch.study.fpe.domain.rule.lib.LineGapCircle;

public class OrigamiFoldability extends AbstractRule<AngleUnitFlapPattern> {

	private FlapPatternConverter converter;

	public OrigamiFoldability(final FlapPatternConverter conv) {
		converter = conv;
	}

	@Override
	public boolean holds(final AngleUnitFlapPattern pattern) {
		if (!pattern.isProbablyFoldable()) {
			return false;
		}

		return patternCanBeFlat(pattern);
	}

	/**
	 * 
	 * @param pattern
	 *            a pattern which holds Maekawa and Kawasaki theorems.
	 * @return
	 *         true if the pattern can be folded flatten.
	 */
	public boolean patternCanBeFlat(final AngleUnitFlapPattern pattern) {
		LayerFlattenability flattenability = new LayerFlattenability();

		LineGapCircle lineGaps = converter.toLineGapCircle(pattern);

		return flattenability.holds(lineGaps);
	}

}
