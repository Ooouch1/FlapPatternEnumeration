package ouch.study.fpe.domain.value;

public interface Angle {

	Angle subtract(Angle a);

	Angle add(Angle a);

	boolean isLessThanOrEqual(Angle a);

}
