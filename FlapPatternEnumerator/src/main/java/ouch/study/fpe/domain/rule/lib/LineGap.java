package ouch.study.fpe.domain.rule.lib;

import ouch.study.fpe.domain.value.Angle;
import ouch.study.fpe.domain.value.LineType;

/**
 * Holds a radial line and the angle between that line and the next line.
 * 
 * @author Koji
 * 
 */
public class LineGap {
	private final LineType line;
	private Angle angleToNext;

	public LineGap(final LineType l, final Angle a) {
		line = l;
		angleToNext = a;
	}

	public Angle getAngleToNextLine() {
		return angleToNext;
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