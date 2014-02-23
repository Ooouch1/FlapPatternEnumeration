package ouch.study.fpe.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import ouch.study.fpe.domain.value.LineType;

public class FoldabilityTest {
	private PatternFactoryForTest factory = new PatternFactoryForTest();

	@Test
	public void testRegularRectangleBase() {
		// MVM
		// .o.
		// MVM
		AngleUnitFlapPattern pattern = factory.createPattern(
				LineType.VALLEY, LineType.MOUNTAIN, LineType.EMPTY, LineType.MOUNTAIN,
				LineType.VALLEY, LineType.MOUNTAIN, LineType.EMPTY, LineType.MOUNTAIN);

		assertTrue(pattern.isProbablyFoldable());

	}
}
