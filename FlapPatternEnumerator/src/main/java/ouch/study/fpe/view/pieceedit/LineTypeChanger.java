package ouch.study.fpe.view.pieceedit;

import java.awt.geom.Rectangle2D;

import ouch.study.fpe.domain.AngleUnitFlapPattern;

public class LineTypeChanger {
	private final double epsRadian;

	public LineTypeChanger(final double epsDegrees) {
		epsRadian = epsDegrees * Math.PI / 180;
	}

	public void changeIfCloseEnough(final AngleUnitFlapPattern pattern, final Rectangle2D box, final double px,
			final double py) {

		double x = px - box.getCenterX();
		double y = py - box.getCenterY();

		double direction = pattern.getDivisionSize() * Math.atan2(y, x) / (2 * Math.PI);

		if (!isAlmostInteger(direction)) {
			return;
		}

		int index = nearestInteger(direction);

		pattern.set(index, pattern.getAt(index).nextType());

	}

	public int nearestInteger(final double v) {
		// avoid machine epsilon error
		return (int) (Math.rint(v) + Math.signum(v) * 0.1);
	}

	private boolean isAlmostInteger(final double v) {
		if (Math.abs(v - Math.ceil(v)) < epsRadian) {
			return true;
		}
		if (Math.abs(v - Math.floor(v)) < epsRadian) {
			return true;
		}
		return false;
	}
}
