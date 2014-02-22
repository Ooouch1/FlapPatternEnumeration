package ouch.study.fpe.domain.bf;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.test.TesterConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = TesterConfig.class,
		loader = SpringApplicationContextLoader.class)
public class BruteForcePatternSetFactoryTestUsing90Degrees {
	private static final Integer DIVISION_SIZE = 4;

	@Test
	public void test() {
		BruteForcePatternSetFactory factory = new BruteForcePatternSetFactory(DIVISION_SIZE - 1,
				false);

		AngleUnitFlapPattern seed = new AngleUnitFlapPattern(DIVISION_SIZE);

		// *M*
		// .o.
		// *.*
		seed.set(0, LineType.MOUNTAIN);
		assertEquals(
				4,
				factory.createPatternsByAddingLineRecursively(seed,
						LineType.MOUNTAIN, 1).size());

		assertEquals(4, factory.advance(seed, 0, LineType.MOUNTAIN).size());

		assertEquals(
				4 + 3,
				factory.createPatternsByAddingLineRecursively(seed,
						LineType.MOUNTAIN, 2).size());
	}

}
