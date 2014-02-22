package ouch.study.fpe.domain.rule;

import ouch.study.fpe.domain.Angle;
import ouch.study.fpe.domain.LineType;

class LineGap {
	private final LineType line;
	private Angle angleToNext;

	public LineGap(final LineType l, final Angle a) {
		line = l;
		angleToNext = a;
	}

	public Angle getAngleToNextLine() {
		return angleToNext;
	}

	public void setAngleToNextLine(final Angle a) {
		angleToNext = a;
	}

	public LineType getLine() {
		return line;
	}

	public void subtractAngleBy(final LineGap gap) {
		angleToNext = angleToNext.subtract(gap.angleToNext);
	}

	public void addAngleBy(final LineGap gap) {
		angleToNext = angleToNext.add(gap.angleToNext);
	}

	public boolean angleIsLessThanOrEqual(final LineGap gap) {
		return angleToNext.isLessThanOrEqual(gap.angleToNext);
	}
}