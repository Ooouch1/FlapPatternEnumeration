package ouch.study.fpe.domain;

import static org.junit.Assert.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import ouch.study.fpe.domain.rule.PatternHashElement;

/**
 * tests that hash element generates its key in intended form.
 * 
 * @author Koji
 * 
 */
public class PatternHashElementTest {

	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(PatternHashElementTest.class);

	private final PatternFactoryForTest factory = new PatternFactoryForTest();

	/**
	 * ...
	 * .oM
	 * .V.
	 */
	private final AngleUnitFlapPattern pattern = factory.createPattern(new LineType[] { LineType.EMPTY,
			LineType.MOUNTAIN, LineType.VALLEY, LineType.EMPTY });
	/**
	 * key = 00 01 10 00
	 */
	private final Long expectedKey = LineType.MOUNTAIN.getBit() << 2 * LineType.BIT_LENGTH
			| LineType.VALLEY.getBit() << 1 * LineType.BIT_LENGTH;

	@Test
	public void testKeyGeneration() {
		LOGGER.info("construction test");
		PatternHashElement element = new PatternHashElement(pattern);

		Long key = element.getKey();

		assertBitEquals(expectedKey, key);
	}

	/**
	 * ensures all bit blocks shift left to right and the highest block goes to
	 * lowest.
	 */
	@Test
	public void testRotation() {
		LOGGER.info("rotation test");

		PatternHashElement element = new PatternHashElement(pattern);

		element.getKey();

		PatternHashElement rotated = element;
		Long expectedRotatedKey = 0L;
		// rotated 00 01 10 00 -> 01 10 00 00
		rotated = rotated.createBitRotatedOneAngleUnit();
		expectedRotatedKey = Long.parseLong("01100000", 2);
		assertBitEquals(expectedRotatedKey, rotated.getKey());

		// rotated 01 10 00 00 -> 10 00 00 01
		rotated = rotated.createBitRotatedOneAngleUnit();
		expectedRotatedKey = Long.parseLong("10000001", 2);
		assertBitEquals(expectedRotatedKey, rotated.getKey());

	}

	private void assertBitEquals(final Long expected, final Long actual) {
		String expectedBits = asBitString(expected);
		String actualBits = asBitString(actual);
		LOGGER.info("expected: " + expectedBits + ", actual: " + actualBits);
		assertEquals(expectedBits, actualBits);

	}

	private String asBitString(final Long n) {
		return Long.toBinaryString(n);
	}

}
