package ouch.study.fpe.domain;

import java.util.LinkedList;
import java.util.List;

public class FoldabilityChecker {

	public boolean foldable(final AngleUnitFlapPattern pattern) {
		FlapPatternConverter converter = new FlapPatternConverter();

		LinkedList<LineGap<Integer>> lineGaps = converter.toLineGaps(pattern);

		List<LineGap<Integer>> minimalGaps = new LinkedList<>();

		return false;
	}

}
