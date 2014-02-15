package ouch.study.fpe.domain.rule;

import static org.junit.Assert.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.domain.PatternFactoryForTest;

/**
 * tests that ensures {@link DuplicatedPatternHash} finds the duplications of
 * given
 * pattern.
 * 
 * @author Koji
 * 
 */
public class DuplicatedPatternHashTest {

	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(DuplicatedPatternHashTest.class);

	private PatternFactoryForTest factory = new PatternFactoryForTest();

	@Test
	public void testWith90Degrees() {
		/**
		 * .M.
		 * .oM
		 * .V.
		 */
		final AngleUnitFlapPattern pattern = factory.createPattern(new LineType[] {
				LineType.MOUNTAIN, LineType.MOUNTAIN, LineType.VALLEY, LineType.EMPTY });

		/**
		 * .M.
		 * MoV
		 * ...
		 */
		final AngleUnitFlapPattern duplicationByRotation = factory.createPattern(new LineType[] {
				LineType.MOUNTAIN, LineType.VALLEY, LineType.EMPTY, LineType.MOUNTAIN });

		/**
		 * .M.
		 * Mo.
		 * .V.
		 */
		final AngleUnitFlapPattern duplicationByMirrorAt0 = factory.createPattern(new LineType[] {
				LineType.MOUNTAIN, LineType.EMPTY, LineType.VALLEY, LineType.MOUNTAIN });

		DuplicatedPatternHash hash = new DuplicatedPatternHash(0);

		// false for pattern which comes the first time
		assertFalse(hash.holds(pattern));
		// true for second time or or more
		assertTrue(hash.holds(pattern));

		// false because rotations are already decected.
		assertTrue(hash.holds(duplicationByRotation));

		// false because mirrors are already decected.
		assertTrue(hash.holds(duplicationByMirrorAt0));

	}

	@Test
	public void testMirrorWhenAxisIsNot0() {

		LOGGER.info("test mirror hash for axis = 1, 7");

		DuplicatedPatternHash hash = new DuplicatedPatternHash(0);
		/**
		 * .M.
		 * .oM
		 * .MV
		 */
		final AngleUnitFlapPattern pattern = factory.createPattern(new LineType[] {
				LineType.MOUNTAIN, LineType.EMPTY, LineType.MOUNTAIN, LineType.VALLEY,
				LineType.MOUNTAIN, LineType.EMPTY, LineType.EMPTY, LineType.EMPTY });

		/**
		 * VM.
		 * MoM
		 * ...
		 */
		final AngleUnitFlapPattern duplicationByMirrorAt1 = factory.createPattern(new LineType[] {
				LineType.MOUNTAIN, LineType.EMPTY, LineType.MOUNTAIN, LineType.EMPTY,
				LineType.EMPTY, LineType.EMPTY, LineType.MOUNTAIN, LineType.VALLEY });

		/**
		 * .MV
		 * .oM
		 * .M.
		 */
		final AngleUnitFlapPattern duplicationByMirrorAt6 = factory.createPattern(new LineType[] {
				LineType.MOUNTAIN, LineType.VALLEY, LineType.MOUNTAIN, LineType.EMPTY,
				LineType.MOUNTAIN, LineType.EMPTY, LineType.EMPTY, LineType.EMPTY });

		// false for pattern which comes the first time
		assertFalse(hash.holds(pattern));
		// true for second time or or more
		assertTrue(hash.holds(pattern));

		// false because mirrors are already decected.
		assertTrue(hash.holds(duplicationByMirrorAt1));
		assertTrue(hash.holds(duplicationByMirrorAt6));

		final int rotationCount = 3;
		final int mirrorCount = 2 + 1; // axis is 0 and not 0
		assertEquals(rotationCount + mirrorCount, hash.getKeyCount());

	}
}
