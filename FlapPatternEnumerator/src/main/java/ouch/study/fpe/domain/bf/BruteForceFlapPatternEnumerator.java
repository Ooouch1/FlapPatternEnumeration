package ouch.study.fpe.domain.bf;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import oripa.util.collection.Rule;
import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.FlapPatternEnumerator;
import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.domain.RuleFactory;

/**
 * Enumerator.
 * 
 * 
 * 
 * @author Koji
 * 
 */
public class BruteForceFlapPatternEnumerator implements FlapPatternEnumerator {
	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(BruteForceFlapPatternEnumerator.class);

	private final static RuleFactory ruleFactory = new RuleFactory();

	int recursionCount = 0;

	/**
	 * @param partialPatterns
	 *            never used.
	 */
	@Override
	public Collection<AngleUnitFlapPattern> enumerateUniquePatterns(final int divisionSize,
			final Collection<List<LineType>> partialPatterns) {
		return enumerateUniquePatterns(divisionSize, true, true);
	}

	/**
	 * 
	 * @param divisionSize
	 *            should be even. odd number causes
	 *            {@link IllegalArgumentException}.
	 * 
	 * 
	 * @param mirrorTestEnabled
	 *            true if mirrored shapes should be removed.
	 * 
	 * @param rotationTestEnabled
	 *            true if rotated shapes should be removed.
	 * 
	 * 
	 * @return a set of all unique patterns.
	 */
	public Collection<AngleUnitFlapPattern> enumerateUniquePatterns(
			final int divisionSize, final boolean mirrorTestEnabled, final boolean rotationTestEnabled) {

		if (divisionSize % 2 == 1) {
			throw new IllegalArgumentException(
					"divisionSize should be even number.");
		}

		// prepare storages
		Collection<AngleUnitFlapPattern> patterns = createPatterns(
				divisionSize, mirrorTestEnabled, rotationTestEnabled);

		LOGGER.info("# unique patterns: " + patterns.size());
		LOGGER.info("#call of reursion: " + recursionCount);
		return patterns;
	}

	private Collection<AngleUnitFlapPattern> createPatterns(
			final int divisionSize, final boolean mirrorTestEnabled, final boolean rotationTestEnabled) {

		List<AngleUnitFlapPattern> patterns = new LinkedList<>();

		LOGGER.info("create mountain only, degree ");

		Collection<AngleUnitFlapPattern> uniqueMountainOnlys =
				createAllMountainOnlyPatterns(new AngleUnitFlapPattern(divisionSize), divisionSize / 2 - 1);

		LOGGER.debug("#all of mountain only: " + uniqueMountainOnlys.size());
		LOGGER.info("#uniques of mountain only: "
				+ uniqueMountainOnlys.size());
		LOGGER.debug(uniqueMountainOnlys);

		Rule<AngleUnitFlapPattern> pruningRule = ruleFactory
				.createNoPruning();
		// Rule<AngleUnitFlapPattern> pruningRule = ruleFactory
		// .createDuplicationDetector(0);

		LOGGER.info("add valleys for each");
		for (AngleUnitFlapPattern mountainOnly : uniqueMountainOnlys) {
			int mountainCount = mountainOnly.countLines();
			patterns.addAll(
					createValleyAddedPatterns(mountainOnly,
							mountainCount + 2, mountainCount * 2 + 2, pruningRule));
		}

		return patterns;

	}

	/**
	 * 
	 * @param seed
	 *            seed of new patterns
	 * @param additonCount
	 *            ammount of new lines
	 * @param pruningRule
	 *            rule which holds true if the recursion should skip to next
	 *            pattern.
	 * @return
	 *         patterns with new valley lines.
	 */
	private List<AngleUnitFlapPattern> createValleyAddedPatterns(
			final AngleUnitFlapPattern seed, final int additonCount, final int expectedTotalLineCount,
			final Rule<AngleUnitFlapPattern> pruningRule) {

		Rule<AngleUnitFlapPattern> acceptionRule = ruleFactory
				.createFoldablilityRuleAsConjunctionable()
				.addRule(ruleFactory.createAcceptableByLineCount(expectedTotalLineCount))
				.addRule(ruleFactory.createDuplicationDetector(0).asDenied());

		BruteForcePatternSetFactory factory = new BruteForcePatternSetFactory(
				// seed.getTailIndex(), acceptionRule,
				// ruleFactory.createNoPruning());
				seed.getTailIndex(), acceptionRule, pruningRule);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("start adding VALLEY to: " + seed);

		}

		List<AngleUnitFlapPattern> result = factory.createPatternsByAddingLineRecursively(seed,
				LineType.VALLEY, additonCount);

		recursionCount += factory.getRecursionCount();

		return result;
	}

	/**
	 * 
	 * @param seed
	 *            empty seed of new patterns
	 * @param maxMountainCount
	 *            ammount of new lines
	 * 
	 *            pattern.
	 * @return
	 *         patterns with new valley lines.
	 */
	private List<AngleUnitFlapPattern> createAllMountainOnlyPatterns(
			final AngleUnitFlapPattern seed, final int maxMountainCount) {

		Rule<AngleUnitFlapPattern> acceptionRule = ruleFactory.createAlwaysAcceptable();
		// Rule<AngleUnitFlapPattern> acceptionRule =
		// ruleFactory.createAcceptableByLineCount(expectedTotalLineCount);

		Rule<AngleUnitFlapPattern> pruningRule = ruleFactory
				.createDuplicationDetector(0);

		BruteForcePatternSetFactory factory = new BruteForcePatternSetFactory(
				seed.getTailIndex(), acceptionRule, pruningRule);

		seed.set(0, LineType.MOUNTAIN);
		List<AngleUnitFlapPattern> result = factory.createPatternsByAddingLineRecursively(seed,
				LineType.MOUNTAIN, maxMountainCount - 1);

		recursionCount += factory.getRecursionCount();

		return result;
	}

}
