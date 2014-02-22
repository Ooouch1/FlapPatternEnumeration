package ouch.study.fpe.domain.bf;

import static org.junit.Assert.*;

import java.util.Collection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.test.TesterConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = TesterConfig.class,
		loader = SpringApplicationContextLoader.class)
public class BruteForceEnumeratorTest {
	/** Logger. */
	private static final Logger LOGGER = LogManager
			.getLogger(BruteForceEnumeratorTest.class);

	@Test
	public void test90Degrees() {
		runEnumeration(4, 1);
	}

	@Test
	public void test45Degrees() {
		runEnumeration(8, -1);
		// not confirmed
		// runEnumeration(8, 34);
	}

	@Test
	public void test22_5Degrees() {
		runEnumeration(16, -1);
	}

	private void runEnumeration(final int divisionSize, final int expectedPatternCount) {
		LOGGER.debug("start " + 360.0 / divisionSize + " degrees test");
		BruteForceFlapPatternEnumerator enumerator = new BruteForceFlapPatternEnumerator();

		long startMillis = System.currentTimeMillis();

		Collection<AngleUnitFlapPattern> patterns = enumerator
				.enumerateUniquePatterns(divisionSize, true, true);

		LOGGER.info("SPENT TIME = " + (System.currentTimeMillis() - startMillis) / 1000 + "[s]");

		if (expectedPatternCount > 0) {
			assertEquals(expectedPatternCount, patterns.size());
		}
		LOGGER.info("VERIFY: #patterns of all obtained = " + patterns.size());

		if (patterns.size() < 30) {
			LOGGER.info(patterns);
		}

	}
}
