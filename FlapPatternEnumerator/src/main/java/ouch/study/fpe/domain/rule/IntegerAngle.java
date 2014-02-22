package ouch.study.fpe.domain.rule;

import ouch.study.fpe.domain.Angle;

public class IntegerAngle implements Angle {

	private final Integer angle;

	public IntegerAngle(final Integer a) {
		angle = a;
	}

	@Override
	public Angle add(final Angle a) {
		IntegerAngle anotherAngle = cast(a);

		return new IntegerAngle(angle + anotherAngle.angle);
	}

	@Override
	public Angle subtract(final Angle a) {
		IntegerAngle anotherAngle = cast(a);

		return new IntegerAngle(angle - anotherAngle.angle);
	}

	@Override
	public boolean isLessThanOrEqual(final Angle a) {
		IntegerAngle anotherAngle = cast(a);

		return angle <= anotherAngle.angle;
	}

	private IntegerAngle cast(final Angle a) {
		throwIllegalArgumentExceptionIfDifferentType(a);

		return (IntegerAngle) a;

	}

	private void throwIllegalArgumentExceptionIfDifferentType(final Angle a) {
		if (!(a instanceof IntegerAngle)) {
			throw new IllegalArgumentException("Different value type!");
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof IntegerAngle) {
			return angle == ((IntegerAngle) obj).angle;
		}

		return false;
	}
}
