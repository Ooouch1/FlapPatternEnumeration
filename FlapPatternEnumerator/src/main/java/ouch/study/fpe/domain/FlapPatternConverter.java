package ouch.study.fpe.domain;

import java.util.LinkedList;

import ouch.study.fpe.util.CircleLinkElement;

public class FlapPatternConverter {

	public LinkedList<LineGap<Integer>> toLineGaps(final AngleUnitFlapPattern pattern) {

		LinkedList<LineGap<Integer>> lineGaps = new LinkedList<>();

		int firstIndex = pattern.findFirstLineIndex();

		int indexOfLastLine = firstIndex;

		for (int i = 1; i <= pattern.getDivisionSize(); i++) {
			int index = firstIndex + i;

			if (!pattern.isEmptyAt(index)) {
				int angle = index - indexOfLastLine;
				lineGaps.add(new LineGap<Integer>(pattern.getAt(indexOfLastLine), angle));

				indexOfLastLine = index;
			}

		}

		return lineGaps;
	}

	public LineGapCircle<Integer> toLineGapCircle(final AngleUnitFlapPattern pattern) {
		LinkedList<LineGap<Integer>> list = toLineGaps(pattern);

		LineGapCircle<Integer> circle = new LineGapCircle<>(list.getFirst());
		list.removeFirst();

		CircleLinkElement<LineGap<Integer>> element = circle.getHead();
		for (LineGap<Integer> lineGap : list) {
			element = circle.insertAsNext(element, lineGap);
		}

		return circle;
	}

}
