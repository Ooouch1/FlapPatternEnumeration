package ouch.study.fpe.domain;

import java.util.Arrays;

public class PatternFactoryForTest {

	/**
	 * with verification.
	 * 
	 * @param divisionSize
	 *            division size
	 * 
	 * @param lineArray
	 *            line types
	 * @return Pattern object
	 */
	public AngleUnitFlapPattern createPattern(Integer divisionSize, LineType[] lineArray) {
		if (lineArray.length != divisionSize) {
			throw new RuntimeException();
		}

		return new AngleUnitFlapPattern(divisionSize, Arrays.asList(lineArray));

	}

	/**
	 * division size is set to given array's length.
	 * 
	 * 
	 * @param lineArray
	 *            line types
	 * @return Pattern object
	 */
	public AngleUnitFlapPattern createPattern(LineType[] lineArray) {
		int divisionSize = lineArray.length;

		return new AngleUnitFlapPattern(divisionSize, Arrays.asList(lineArray));

	}

}
