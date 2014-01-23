package ouch.study.fpe.domain;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class FlapPatternTestUsing90Degrees {

	private static final Integer DIVISION_SIZE = 4;

	@Test
	public void testConstructor() {

		AngleUnitFlapPattern pattern = new AngleUnitFlapPattern(DIVISION_SIZE);

		assertEquals(DIVISION_SIZE, pattern.getDivisionSize());

		for (int i = 0; i < DIVISION_SIZE; i++) {
			assertTrue(pattern.isEmptyAt(i));
		}
	}

	@Test
	public void testIsMirrorOf() {
		// ...
		// .oM
		// .V.
		AngleUnitFlapPattern pattern = createPattern(new LineType[] { null,
				LineType.MOUNTAIN, LineType.VALLEY, null });
		// ...
		// Mo.
		// .V.
		AngleUnitFlapPattern mirrored = createPattern(new LineType[] { null,
				null, LineType.VALLEY, LineType.MOUNTAIN });

		assertTrue(mirrored.isMirrorOf(pattern, 0));
		assertFalse(mirrored.isMirrorOf(pattern, 1));
		assertTrue(mirrored.isMirrorOf(pattern, 2));

	}

	@Test
	public void testIsRotationOf() {
		// ...
		// .oM
		// .V.
		AngleUnitFlapPattern pattern = createPattern(new LineType[] { null,
				LineType.MOUNTAIN, LineType.VALLEY, null });
		// ...
		// Vo.
		// .M.
		AngleUnitFlapPattern rotated = createPattern(new LineType[] { null,
				null, LineType.MOUNTAIN, LineType.VALLEY });

		assertFalse(rotated.isRotationOf(pattern, 0));
		assertTrue(rotated.isRotationOf(pattern, 1));
		assertFalse(rotated.isRotationOf(pattern, 2));
		assertFalse(rotated.isRotationOf(pattern, 3));

	}

	@Test
	public void testFindFirstLineIndex() {
		// .M.
		// .o.
		// .V.
		assertEquals(
				0,
				createPattern(
						new LineType[] { LineType.MOUNTAIN, null,
								LineType.VALLEY, null }).findFirstLineIndex());

		// ...
		// .oM
		// .V.
		assertEquals(
				1,
				createPattern(
						new LineType[] { null, LineType.MOUNTAIN,
								LineType.VALLEY, null }).findFirstLineIndex());
	}

	@Test
	public void maekawaTheoremShouldFail() {
		// .M.
		// .o.
		// .V.
		assertFalse(createPattern(
				new LineType[] { LineType.MOUNTAIN, null, LineType.VALLEY, null })
				.holdsMaekawaTheorem());

	}

	@Test
	public void kawasakiTheoremShouldFail() {
		// .M.
		// .oM
		// .V.
		assertFalse(createPattern(
				new LineType[] { LineType.MOUNTAIN, null, LineType.VALLEY, null })
				.holdsMaekawaTheorem());

	}

	@Test
	public void testIsFoldable() {
		// .M.
		// .o.
		// .M.
		assertIsFoldable(createPattern(new LineType[] { LineType.MOUNTAIN,
				null, LineType.MOUNTAIN, null }));

		// .M.
		// VoM
		// .M.
		assertIsFoldable(createPattern(new LineType[] { LineType.MOUNTAIN,
				LineType.MOUNTAIN, LineType.MOUNTAIN, LineType.VALLEY }));

	}

	private void assertIsFoldable(AngleUnitFlapPattern pattern) {
		assertTrue(pattern.holdsMaekawaTheorem());
		assertTrue(pattern.holdsKawasakiTheorem());
		assertTrue(pattern.isFoldable());

	}

	private AngleUnitFlapPattern createPattern(LineType[] lineArray) {
		if (lineArray.length != DIVISION_SIZE) {
			throw new RuntimeException();
		}

		return new AngleUnitFlapPattern(DIVISION_SIZE, Arrays.asList(lineArray));

	}
}
