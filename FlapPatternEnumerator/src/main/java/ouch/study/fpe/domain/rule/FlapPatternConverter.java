package ouch.study.fpe.domain.rule;

import java.util.LinkedList;

import ouch.study.fpe.domain.AngleUnitFlapPattern;

public class FlapPatternConverter {

	public LinkedList<LineGap> toLineGaps(final AngleUnitFlapPattern pattern) {

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

	public LineGapCircle toLineGapCircle(final AngleUnitFlapPattern pattern) {
		LineGapCircle circle = new LineGapCircle(toLineGaps(pattern));

		return circle;
	}

}
