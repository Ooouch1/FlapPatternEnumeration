package ouch.study.fpe.domain.puzzle;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.domain.PatternFactoryForTest;
import ouch.study.fpe.domain.RuleFactory;

public class PuzzlingPatternSetFactoryTest {
	private PatternFactoryForTest patternFactory = new PatternFactoryForTest();
	private RuleFactory ruleFactory = new RuleFactory();
	private static final int DIVISION_SIZE = 8;

	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(PuzzlingPatternSetFactoryTest.class);

	@Test
	public void testVMVOnly() {

		PuzzlingFlapPatternSetFactory setFactory = new PuzzlingFlapPatternSetFactory(
				ruleFactory.createAlwaysAcceptable(), ruleFactory.createNoPruning());

		List<AngleUnitFlapPattern> patterns =
				setFactory.createPatterns(DIVISION_SIZE, createVMVPiece());

		for (AngleUnitFlapPattern p : patterns) {
			LOGGER.info(p);
		}
		assertNotNull(patterns);
		assertEquals(1, patterns.size());

	}

	@Test
	public void testVMVAndVEMEV() {

		PuzzlingFlapPatternSetFactory setFactory = new PuzzlingFlapPatternSetFactory(
				ruleFactory.createAlwaysAcceptable(), ruleFactory.createNoPruning());

		List<AngleUnitFlapPattern> patterns =
				setFactory.createPatterns(DIVISION_SIZE, createVMVAndVEMEV());

		for (AngleUnitFlapPattern p : patterns) {
			LOGGER.info(p);
		}

		assertNotNull(patterns);

		// MVM .VM MVM MV. .V.
		// VoV MoV VoV VoM MoM
		// MVM .VM .M. MV. .V.
		assertEquals(5, patterns.size());

	}

	private Collection<List<LineType>> createVMVPiece() {
		List<List<LineType>> pieces = new LinkedList<>();

		pieces.add(patternFactory.createPattern(new LineType[] {
				LineType.VALLEY, LineType.MOUNTAIN, LineType.VALLEY
		}).getLines());

		// pieces.add(patternFactory.createPattern(new LineType[] {
		// LineType.VALLEY, LineType.MOUNTAIN, LineType.VALLEY
		// }).getLines());

		return pieces;
	}

	private Collection<List<LineType>> createVMVAndVEMEV() {
		Collection<List<LineType>> pieces = createVMVPiece();

		pieces.add(patternFactory.createPattern(new LineType[] {
				LineType.VALLEY, LineType.EMPTY, LineType.MOUNTAIN, LineType.EMPTY, LineType.VALLEY
		}).getLines());

		// pieces.add(patternFactory.createPattern(new LineType[] {
		// LineType.VALLEY, LineType.MOUNTAIN, LineType.VALLEY
		// }).getLines());

		return pieces;
	}

}
