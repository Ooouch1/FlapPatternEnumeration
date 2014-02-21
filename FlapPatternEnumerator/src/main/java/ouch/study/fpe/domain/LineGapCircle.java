package ouch.study.fpe.domain;

import ouch.study.fpe.util.CircleLinkedList;

public class LineGapCircle<Angle> extends CircleLinkedList<LineGap<Angle>> {

	public LineGapCircle(final LineGap<Angle> first) {
		super(first);
	}

}
