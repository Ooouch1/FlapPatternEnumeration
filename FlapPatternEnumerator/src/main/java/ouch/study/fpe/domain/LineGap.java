package ouch.study.fpe.domain;

class LineGap<Angle> {
	private final LineType line;
	private final Angle angleToNext;

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
}