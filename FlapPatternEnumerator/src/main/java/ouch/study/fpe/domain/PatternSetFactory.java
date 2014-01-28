package ouch.study.fpe.domain;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import oripa.util.collection.Rule;
import ouch.study.fpe.domain.rule.AlwaysTrue;

/**
 * This object generates the all patterns according to given condition.
 * The generation creates a new pattern by adding lines to seed pattern one by
 * one.
 * the addition is done between given start index and given tail index.
 * 
 * @author Koji
 * 
 */
public class PatternSetFactory {
	/** Logger. */
	private static final Logger LOGGER = LogManager
			.getLogger(PatternSetFactory.class);

	private List<AngleUnitFlapPattern> patterns = new LinkedList<>();

	private final Integer tailIndex;

	@SuppressWarnings("unchecked")
	private Rule<AngleUnitFlapPattern> acceptablePatternCondition = new AlwaysTrue();

	@SuppressWarnings("unchecked")
	private Rule<AngleUnitFlapPattern> pruningCondition = new AlwaysTrue().asDenied();

	private int recursionCount = 0;

	/**
	 * 
	 * 
	 * @param tailIndex
	 *            the index at which this object stops adding line.
	 * @param enableFoldabilityTest
	 *            true if you want to get foldables only.
	 */
	public PatternSetFactory(final Integer tailIndex, final boolean enableFoldabilityTest) {
		this.tailIndex = tailIndex;

		if (enableFoldabilityTest) {
			RuleFactory factory = new RuleFactory();
			acceptablePatternCondition = factory.createFoldabilityRule();
		}
	}

	/**
	 * More detailed setting is enabled.
	 * 
	 * @param tailIndex
	 *            the index at which this object stops adding.
	 * @param acceptablePatternCondition
	 *            any {@link Rule} object which describes your preferrable
	 *            feature.
	 */
	public PatternSetFactory(final Integer tailIndex,
			final Rule<AngleUnitFlapPattern> acceptablePatternCondition,
			final Rule<AngleUnitFlapPattern> pruningCondition) {
		this.tailIndex = tailIndex;
		this.acceptablePatternCondition = acceptablePatternCondition;
		this.pruningCondition = pruningCondition;
	}

	/**
	 * 
	 * @param seed
	 *            seed of patterns
	 * @param typeToBeAdded
	 *            line type for new lines
	 * @param aimedAdditionCount
	 *            the number that additionCount should reaches.
	 * @return patterns
	 */
	public List<AngleUnitFlapPattern> createPatternsByAddingLineRecursively(
			final AngleUnitFlapPattern seed, final LineType typeToBeAdded,
			final int aimedAdditionCount) {
		patterns = new LinkedList<>();

		createPatternsImpl(
				seed.cloneInstance(), typeToBeAdded, 0, 0,
				aimedAdditionCount);

		logDebug(patterns);

		return patterns;
	}

	/**
	 * creates patterns by adding one line.
	 * 
	 * @param seed
	 *            seed pattern
	 * @param startIndex
	 *            start index of adding line
	 * @param typeToBeAdded
	 *            the line type to be added
	 * @return
	 *         patterns that each of them has one more line than given seed.
	 */
	public List<AngleUnitFlapPattern> advance(final AngleUnitFlapPattern seed,
			final int startIndex, final LineType typeToBeAdded) {
		patterns = new LinkedList<>();
		createPatternsImpl(
				seed.cloneInstance(), typeToBeAdded, startIndex, 0, 1);
		return patterns;
	}

	/**
	 * {@link #advance(AngleUnitFlapPattern, int, LineType)} for each.
	 * 
	 * @param seeds
	 * @param typeToBeAdded
	 * @return
	 *         merged set of advanced patterns for every seeds.
	 */
	public List<AngleUnitFlapPattern> advanceAll(final List<AngleUnitFlapPattern> seeds, final LineType typeToBeAdded) {
		patterns = new LinkedList<>();
		for (AngleUnitFlapPattern seed : seeds) {
			createPatternsImpl(
					seed.cloneInstance(), typeToBeAdded, seed.findLastIndexOf(typeToBeAdded), 0, 1);
		}
		return patterns;
	}

	/**
	 * Adds lines with desired amount into seed then put it to the pattern
	 * storage.
	 * 
	 * @param seed
	 *            seed of patterns
	 * @param typeToBeAdded
	 *            line type for new lines
	 * @param indexToAdd
	 *            index of lastly added line
	 * @param additionCount
	 *            the count of added lines
	 * @param aimedAdditionCount
	 *            the number that additionCount should reaches.
	 */
	private void createPatternsImpl(final AngleUnitFlapPattern seed,
			final LineType typeToBeAdded, final int indexToAdd, final int additionCount,
			final int aimedAdditionCount) {

		recursionCount++;

		if (acceptablePatternCondition.holds(seed)) {
			patterns.add(seed);
		}

		if (additionCount == aimedAdditionCount) {
			LOGGER.info(seed);
			return;
		}

		int remainChances = tailIndex + 1 - indexToAdd;
		int possibleMaxAdditionCount = additionCount + remainChances;
		if (possibleMaxAdditionCount < aimedAdditionCount) {
			return;
		}

		if (indexToAdd > tailIndex) {
			return;
		}

		for (int i = indexToAdd; i <= tailIndex; i++) {
			if (!seed.isEmptyAt(i)) {
				continue;
			}
			AngleUnitFlapPattern nextSeed = seed.cloneInstance();

			nextSeed.set(i, typeToBeAdded);
			// seed.set(i, typeToBeAdded);
			if (pruningCondition.holds(nextSeed)) {
				continue;
			}
			createPatternsImpl(nextSeed, typeToBeAdded, i + 1, additionCount + 1,
					aimedAdditionCount);

			// seed.set(i, LineType.EMPTY);
		}
	}

	private void logDebug(final List<AngleUnitFlapPattern> patterns) {
		LOGGER.trace("#patterns = " + patterns.size());
		LOGGER.trace(patterns);
	}

	public int getRecursionCount() {
		return recursionCount;
	}

}
