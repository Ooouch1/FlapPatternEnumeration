package ouch.study.fpe.domain;

import static org.junit.Assert.*;

import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

public class EnumeratorTest {
	/** Logger. */
	private static final Logger LOGGER = LogManager
			.getLogger(EnumeratorTest.class);

	@Test
	public void test90Degrees() {
		LOGGER.debug("start 90 degrees test");
		FlapPatternEnumerator enumerator = new FlapPatternEnumerator();

		Set<AngleUnitFlapPattern> patterns = enumerator
				.enumerateUniquePatterns(360 / 90, true, true);

		assertEquals(1, patterns.size());
		LOGGER.info("#patterns = " + patterns.size());
		LOGGER.info(patterns);
	}

	@Test
	public void test45Degrees() {
		LOGGER.debug("start 45 degrees test");
		FlapPatternEnumerator enumerator = new FlapPatternEnumerator();

		Set<AngleUnitFlapPattern> patterns = enumerator
				.enumerateUniquePatterns(360 / 45, true, true);

		// assertEquals(1, patterns.size());
		LOGGER.info("#patterns = " + patterns.size());
		LOGGER.info(patterns);
	}
}
