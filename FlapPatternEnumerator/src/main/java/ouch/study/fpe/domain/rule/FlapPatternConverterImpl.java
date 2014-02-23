package ouch.study.fpe.domain.rule;

import java.util.LinkedList;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.rule.lib.LineGap;
import ouch.study.fpe.domain.rule.lib.LineGapCircle;
import ouch.study.fpe.domain.value.IntegerAngle;

public class FlapPatternConverterImpl implements FlapPatternConverter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ouch.study.fpe.domain.rule.FlapPatternConverter#toLineGaps(ouch.study
	 * .fpe.domain.AngleUnitFlapPattern)
	 */
	@Override
	public LinkedList<LineGap> toLineGaps(final Object patternObj) {
		AngleUnitFlapPattern pattern;

		if (!(patternObj instanceof AngleUnitFlapPattern)) {
			throw new IllegalArgumentException("patternObj should be an instance of "
					+ AngleUnitFlapPattern.class.getSimpleName());
		}

		pattern = (AngleUnitFlapPattern) patternObj;

		LinkedList<LineGap> lineGaps = new LinkedList<>();

		int firstIndex = pattern.findFirstLineIndex();

		int indexOfLastLine = firstIndex;

		for (int i = 1; i <= pattern.getDivisionSize(); i++) {
			int index = firstIndex + i;

			if (!pattern.isEmptyAt(index)) {
				int angle = index - indexOfLastLine;
				lineGaps.add(new LineGap(pattern.getAt(indexOfLastLine), new IntegerAngle(angle)));

				indexOfLastLine = index;
			}

		}

		return lineGaps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ouch.study.fpe.domain.rule.FlapPatternConverter#toLineGapCircle(ouch.
	 * study.fpe.domain.AngleUnitFlapPattern)
	 */
	@Override
	public LineGapCircle toLineGapCircle(final Object pattern) {
		LineGapCircle circle = new LineGapCircle(toLineGaps(pattern));

		return circle;
	}

}
