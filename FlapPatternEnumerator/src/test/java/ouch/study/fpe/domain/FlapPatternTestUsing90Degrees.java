package ouch.study.fpe.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlapPatternTestUsing90Degrees {

	private static final Integer DIVISION_SIZE = 4;

	private final PatternFactoryForTest factory = new PatternFactoryForTest();

	@Test
	public void testConstructor() {

		AngleUnitFlapPattern pattern = new AngleUnitFlapPattern(DIVISION_SIZE);

		assertEquals(DIVISION_SIZE, pattern.getDivisionSize());

		for (int i = 0; i < DIVISION_SIZE; i++) {
			assertTrue(pattern.isEmptyAt(i));
		}
	}

	// @Test
	// public void testIsMirrorOf() {
	// // ...
	// // .oM
	// // .V.
	// AngleUnitFlapPattern pattern = factory.createPattern(new LineType[] {
	// LineType.EMPTY,
	// LineType.MOUNTAIN, LineType.VALLEY, LineType.EMPTY });
	// // ...
	// // Mo.
	// // .V.
	// AngleUnitFlapPattern mirrored = factory.createPattern(new LineType[] {
	// LineType.EMPTY,
	// LineType.EMPTY, LineType.VALLEY, LineType.MOUNTAIN });
	//
	// assertTrue(mirrored.isMirrorOf(pattern, 0));
	// assertFalse(mirrored.isMirrorOf(pattern, 1));
	// assertTrue(mirrored.isMirrorOf(pattern, 2));
	//
	// }

	// @Test
	// public void testIsRotationOf() {
	// // ...
	// // .oM
	// // .V.
	// AngleUnitFlapPattern pattern = factory.createPattern(new LineType[] {
	// LineType.EMPTY,
	// LineType.MOUNTAIN, LineType.VALLEY, LineType.EMPTY });
	// // ...
	// // Vo.
	// // .M.
	// AngleUnitFlapPattern rotated = factory.createPattern(new LineType[] {
	// LineType.EMPTY,
	// LineType.EMPTY, LineType.MOUNTAIN, LineType.VALLEY });
	//
	// assertFalse(rotated.isRotationOf(pattern, 0));
	// assertTrue(rotated.isRotationOf(pattern, 1));
	// assertFalse(rotated.isRotationOf(pattern, 2));
	// assertFalse(rotated.isRotationOf(pattern, 3));
	//
	// }

	@Test
	public void testFindFirstLineIndex() {
		// .M.
		// .o.
		// .V.
		assertEquals(
				0,
				factory.createPattern(
						new LineType[] { LineType.MOUNTAIN, LineType.EMPTY,
								LineType.VALLEY, LineType.EMPTY }).findFirstLineIndex());

		// ...
		// .oM
		// .V.
		assertEquals(
				1,
				factory.createPattern(
						new LineType[] { LineType.EMPTY, LineType.MOUNTAIN,
								LineType.VALLEY, LineType.EMPTY }).findFirstLineIndex());
	}

	@Test
	public void maekawaTheoremShouldFail() {
		// .M.
		// .o.
		// .V.
		assertFalse(factory.createPattern(
				new LineType[] { LineType.MOUNTAIN, LineType.EMPTY, LineType.VALLEY, LineType.EMPTY })
				.holdsMaekawaTheorem());

	}

	@Test
	public void kawasakiTheoremShouldFail() {
		// .M.
		// .oM
		// .V.
		assertFalse(factory.createPattern(
				new LineType[] { LineType.MOUNTAIN, LineType.EMPTY, LineType.VALLEY, LineType.EMPTY })
				.holdsMaekawaTheorem());

	}

	@Test
	public void testIsFoldable() {
		// .M.
		// .o.
		// .M.
		assertIsFoldable(factory.createPattern(new LineType[] { LineType.MOUNTAIN,
				LineType.EMPTY, LineType.MOUNTAIN, LineType.EMPTY }));

		// .M.
		// VoM
		// .M.
		assertIsFoldable(factory.createPattern(new LineType[] { LineType.MOUNTAIN,
				LineType.MOUNTAIN, LineType.MOUNTAIN, LineType.VALLEY }));

	}

	private void assertIsFoldable(final AngleUnitFlapPattern pattern) {
		assertTrue(pattern.holdsMaekawaTheorem());
		assertTrue(pattern.holdsKawasakiTheorem());
		assertTrue(pattern.isProbablyFoldable());

	}

}
