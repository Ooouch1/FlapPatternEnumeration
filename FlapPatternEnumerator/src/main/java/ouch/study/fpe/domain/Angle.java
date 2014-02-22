package ouch.study.fpe.domain;

public interface Angle {

	Angle subtract(Angle a);

	Angle add(Angle a);

	boolean isLessThanOrEqual(Angle a);

}
