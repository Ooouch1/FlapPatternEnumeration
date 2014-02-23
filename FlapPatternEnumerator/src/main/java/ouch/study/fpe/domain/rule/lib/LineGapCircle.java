package ouch.study.fpe.domain.rule.lib;

import java.util.List;

import ouch.study.fpe.util.CircleLinkedList;

public class LineGapCircle extends CircleLinkedList<LineGap> {

	public LineGapCircle(final LineGap first) {
		super(first);
	}

	public LineGapCircle(final List<LineGap> gaps) {
		super(gaps);
	}
}
