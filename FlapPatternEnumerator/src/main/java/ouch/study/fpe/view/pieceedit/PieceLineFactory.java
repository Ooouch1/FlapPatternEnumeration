package ouch.study.fpe.view.pieceedit;

import java.util.LinkedList;
import java.util.List;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.value.LineType;

public class PieceLineFactory {

	public List<LineType> createPieceLines(final AngleUnitFlapPattern pattern) {
		LinkedList<LineType> lines = new LinkedList<>(pattern.getLines().subList(
				pattern.findFirstLineIndex(), pattern.getDivisionSize()));

		while (lines.getLast() == LineType.EMPTY) {
			lines.removeLast();
		}

		return lines;
	}
}
