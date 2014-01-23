package ouch.study.fpe.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class PatternSetFactoryTestUsing90Degrees {
	private static final Integer DIVISION_SIZE = 4;

	@Test
	public void test() {
		PatternSetFactory factory = new PatternSetFactory(DIVISION_SIZE - 1,
				false);

		AngleUnitFlapPattern seed = new AngleUnitFlapPattern(DIVISION_SIZE);

		//.M.
		//.o.
		//...
		seed.set(0, LineType.MOUNTAIN);
		assertEquals(
				3,
				factory.createPatternsByAddingLineRecursively(seed,
						LineType.MOUNTAIN, 1).size());

		assertEquals(3, factory.advance(seed, 0, LineType.MOUNTAIN).size());

		assertEquals(
				3,
				factory.createPatternsByAddingLineRecursively(seed,
						LineType.MOUNTAIN, 2).size());
	}

}
