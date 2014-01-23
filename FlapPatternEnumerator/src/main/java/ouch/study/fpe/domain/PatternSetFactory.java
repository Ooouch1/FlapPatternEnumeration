package ouch.study.fpe.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PatternSetFactory {
	/** ロガー */
	private static final Logger LOGGER = LogManager
			.getLogger(PatternSetFactory.class);

	private HashSet<AngleUnitFlapPattern> patterns = new HashSet<>();

	private Integer tailIndex;
	private boolean foldabilityTestEnabled;

	public PatternSetFactory(Integer tailIndex, boolean enableFoldabilityTest) {
		this.tailIndex = tailIndex;
		this.foldabilityTestEnabled = enableFoldabilityTest;
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

	public Set<AngleUnitFlapPattern> advance(AngleUnitFlapPattern seed,
			int startIndex, LineType typeToBeAdded) {
		patterns = new HashSet<>();
		createPatternsImpl(
				seed.cloneInstance(), typeToBeAdded, startIndex, 0, 1);
		return patterns;
	}

	public Set<AngleUnitFlapPattern> advanceAll(Set<AngleUnitFlapPattern> seeds, LineType typeToBeAdded) {
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
	private void createPatternsImpl(AngleUnitFlapPattern seed,
			LineType typeToBeAdded, int lastIndex, int additionCount,
			int aimedAdditionCount) {

		if (additionCount == aimedAdditionCount) {
			if (foldabilityTestEnabled) {
				if (seed.isFoldable()) {
					patterns.add(seed.cloneInstance());
				}
				return;
			}

			patterns.add(seed.cloneInstance());
			return;
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
			seed.set(i, null);
		}
	}

	private void logDebug(Set<AngleUnitFlapPattern> patterns) {
		LOGGER.debug("#patterns = " + patterns.size());
		LOGGER.debug(patterns);
	}

}
