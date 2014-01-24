package ouch.study.fpe.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import oripa.util.collection.Rule;
import ouch.study.fpe.domain.rule.AlwaysTrue;
import ouch.study.fpe.domain.rule.OrigamiFoldability;

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
	/** ロガー */
	private static final Logger LOGGER = LogManager
			.getLogger(PatternSetFactory.class);

	private HashSet<AngleUnitFlapPattern> patterns = new HashSet<>();

	private final Integer tailIndex;

	private Rule<AngleUnitFlapPattern> acceptablePatternCondition = new AlwaysTrue();

	/**
	 * 
	 * 
	 * @param tailIndex
	 *            the index at which this object stops adding line.
	 * @param enableFoldabilityTest
	 *            true if you want to get foldables only.
	 */
	@SuppressWarnings("unchecked")
	public PatternSetFactory(Integer tailIndex, boolean enableFoldabilityTest) {
		this.tailIndex = tailIndex;

		if (enableFoldabilityTest) {
			acceptablePatternCondition = new OrigamiFoldability();
		}
	}

	/**
	 * 
	 * @param tailIndex
	 *            the index at which this object stops adding.
	 * @param acceptablePatternCondition
	 *            any {@link Rule} object which describes your preferrable
	 *            feature.
	 */
	public PatternSetFactory(Integer tailIndex,
			Rule<AngleUnitFlapPattern> acceptablePatternCondition) {
		this.tailIndex = tailIndex;
		this.acceptablePatternCondition = acceptablePatternCondition;
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
	public Set<AngleUnitFlapPattern> createPatternsByAddingLineRecursively(
			AngleUnitFlapPattern seed, LineType typeToBeAdded,
			int aimedAdditionCount) {
		patterns = new HashSet<>();

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
	public Set<AngleUnitFlapPattern> advance(final AngleUnitFlapPattern seed,
			final int startIndex, final LineType typeToBeAdded) {
		patterns = new HashSet<>();
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
	public Set<AngleUnitFlapPattern> advanceAll(final Set<AngleUnitFlapPattern> seeds, final LineType typeToBeAdded) {
		patterns = new HashSet<>();
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
	 * @param lastIndex
	 *            index of lastly added line
	 * @param additionCount
	 *            the count of added lines
	 * @param aimedAdditionCount
	 *            the number that additionCount should reaches.
	 */
	private void createPatternsImpl(final AngleUnitFlapPattern seed,
			final LineType typeToBeAdded, final int lastIndex, final int additionCount,
			int aimedAdditionCount) {

		if (additionCount == aimedAdditionCount) {
			if (acceptablePatternCondition.holds(seed)) {
				patterns.add(seed.cloneInstance());
				return;
			}
		}

		if (lastIndex == tailIndex) {
			return;
		}

		for (int i = lastIndex + 1; i <= tailIndex; i++) {
			if (!seed.isEmptyAt(i)) {
				continue;
			}
			seed.set(i, typeToBeAdded);
			createPatternsImpl(seed, typeToBeAdded, i, additionCount + 1,
					aimedAdditionCount);
			seed.set(i, LineType.EMPTY);
		}
	}

	private void logDebug(final Set<AngleUnitFlapPattern> patterns) {
		LOGGER.debug("#patterns = " + patterns.size());
		LOGGER.debug(patterns);
	}

}
