package ouch.study.fpe.domain.rule;

import java.util.HashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import oripa.util.collection.AbstractRule;
import ouch.study.fpe.domain.AngleUnitFlapPattern;

public class DuplicatedPatternHash extends AbstractRule<AngleUnitFlapPattern> {
	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(DuplicatedPatternHash.class);

	private HashMap<Long, PatternHashElement> hash = new HashMap<>();

	private int headIndex = 0;

	public DuplicatedPatternHash(final int headIndex) {
		this.headIndex = headIndex;
	}

	/**
	 * true if the unified pattern appears first time.
	 * if the result is false, this method stores duplications of given pattern.
	 */
	@Override
	public boolean holds(final AngleUnitFlapPattern pattern) {

		PatternHashElement element = new PatternHashElement(pattern);

		if (contains(element)) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("hit the duplicaiton cache.");
			}
			return true;
		}

		put(element);
		storeDuplications(element);

		return false;
	}

	/**
	 * store rotated patterns and mirrored patterns.
	 * 
	 * This method assumes head index is always same.
	 * that means, the same line should always be at the head index.
	 * 
	 * @param element
	 * @param rotationCount
	 */
	private void storeDuplications(final PatternHashElement element) {

		storeMirrors(element);
		storeRotations(element, element.getPattern().getDivisionSize());
	}

	private void storeMirrors(final PatternHashElement element) {

		put(element.createBitMirrored(headIndex));

		AngleUnitFlapPattern pattern = element.getPattern();
		int divisionSize = pattern.getDivisionSize();

		if (pattern.countLines() < 3) {
			return;

		}

		// find the case of other axis
		for (int i = 1; i < divisionSize / 2; i++) {
			int indexOfMirroredHead = headIndex + i * 2;

			// the pattern must always have the line at headIndex.
			if (pattern.getAt(indexOfMirroredHead) == pattern.getAt(headIndex)) {
				PatternHashElement bitMirrored = element.createBitMirrored(i);

				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace("mirror " + headIndex + "->" + indexOfMirroredHead + ": " + bitMirrored.getKey()
							+ pattern.createMirroredPattern(i));
				}
				put(bitMirrored);
			}
		}
	}

	private void storeRotations(final PatternHashElement element, final int rotationCount) {
		PatternHashElement bitRotated = element.cloneInstance();
		AngleUnitFlapPattern pattern = element.getPattern();

		for (int i = 1; i < rotationCount; i++) {
			// create lotated pattern
			bitRotated = bitRotated.createBitRotatedOneAngleUnit();

			// assuming head index is always same.
			// that means, the same type line should always be at the head
			// index.
			if (pattern.getAt(headIndex) == pattern.getAt(headIndex + i)) {
				put(bitRotated);
			}
		}

	}

	public boolean contains(final PatternHashElement element) {
		Long key = element.getKey();

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("key: " + key);
			LOGGER.trace("key hash: " + hash.keySet());
			LOGGER.trace("hash has the key? : " + hash.containsKey(key));
		}

		return hash.containsKey(key);
	}

	private void put(final PatternHashElement element) {
		Long key = element.getKey();
		hash.put(key, element);
	}

	public int getKeyCount() {
		return hash.size();
	}
}
