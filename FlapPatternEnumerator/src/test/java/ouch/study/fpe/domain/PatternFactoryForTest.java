package ouch.study.fpe.domain;

import java.util.Arrays;

public class PatternFactoryForTest {

	/**
	 * with verification.
	 * 
	 * @param divisionSize
	 *            division size
	 * 
	 * @param lines
	 *            line types
	 * @return Pattern object
	 */
	public AngleUnitFlapPattern createPattern(final Integer divisionSize, final LineType... lines) {
		if (lines.length != divisionSize) {
			throw new RuntimeException();
		}

		return new AngleUnitFlapPattern(divisionSize, Arrays.asList(lines));

	}

	/**
	 * division size is set to given array's length.
	 * 
	 * 
	 * @param lines
	 *            line types
	 * @return Pattern object
	 */
	public AngleUnitFlapPattern createPattern(final LineType... lines) {
		int divisionSize = lines.length;

		return new AngleUnitFlapPattern(divisionSize, Arrays.asList(lines));

	}

}
