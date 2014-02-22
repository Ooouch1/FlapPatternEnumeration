package ouch.study.fpe.domain.rule;

import static org.junit.Assert.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.domain.PatternFactoryForTest;
import ouch.study.fpe.domain.rule.LayerCollisionChecker;

public class LayerCollisionCheckerTest {
	PatternFactoryForTest factory = new PatternFactoryForTest();

	LayerCollisionChecker checker = new LayerCollisionChecker();

	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(LayerCollisionCheckerTest.class);

	@Test
	public void testWhenFoldAsRolling() {
		// this pattern is the result of:
		// fold paper mountain, then fold valley 3 times like as rolling the
		// paper.
		AngleUnitFlapPattern pattern = factory.createPattern(
				LineType.MOUNTAIN, LineType.MOUNTAIN, LineType.MOUNTAIN, LineType.MOUNTAIN, LineType.MOUNTAIN,
				LineType.VALLEY, LineType.VALLEY, LineType.VALLEY);

		assertTrue(checker.patternCanBeFlatten(pattern));
	}

	@Test
	public void testWhenUnfoldable() {
		// MVV
		// .o.
		// MMM
		AngleUnitFlapPattern pattern = factory.createPattern(
				LineType.VALLEY, LineType.VALLEY,
				LineType.EMPTY,
				LineType.MOUNTAIN, LineType.MOUNTAIN, LineType.MOUNTAIN,
				LineType.EMPTY,
				LineType.MOUNTAIN
				);

		assertFalse(checker.patternCanBeFlatten(pattern));

	}
}
