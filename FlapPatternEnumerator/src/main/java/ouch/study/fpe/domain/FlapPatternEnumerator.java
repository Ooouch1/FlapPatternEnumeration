package ouch.study.fpe.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Enumerator.
 * 
 * 
 * 
 * @author Koji
 *
 */
public class FlapPatternEnumerator {
	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(FlapPatternEnumerator.class);

	/**
	 * 
	 * @param divisionSize
	 *            should be even. odd number causes
	 *            {@link IllegalArgumentException}.
	 *            
	 *            
	 *  @param mirrorTestEnabled
	 *  true if mirrored shapes should be removed.
	 *  
	 *  @param rotationTestEnabled
	 *  true if rotated shapes should be removed.
	 *  
	 *            
	 * @return a set of all unique patterns.
	 */
	public Set<AngleUnitFlapPattern> enumerateUniquePatterns(
			final int divisionSize, final boolean mirrorTestEnabled, final boolean rotationTestEnabled) {

		if (divisionSize % 2 == 1) {
			throw new IllegalArgumentException(
					"divisionSize should be even number.");
		}

		AngleUnitFlapPattern seed = new AngleUnitFlapPattern(divisionSize);

		// prepare storages
		Set<AngleUnitFlapPattern> patterns = new HashSet<>();
		Set<AngleUnitFlapPattern> mountainOnlyPatterns = new HashSet<>();
		mountainOnlyPatterns.add(seed);

		// enumeration loop
		for (int mountainCount = 1; mountainCount <= divisionSize / 2 - 1; mountainCount++) {
			PatternDuplicationTester tester = new PatternDuplicationTester(
					mirrorTestEnabled, rotationTestEnabled);

			LOGGER.debug("mountain line count is " + mountainCount);

			LOGGER.debug("create mountain only");

			mountainOnlyPatterns =
					advanceByMountain(mountainOnlyPatterns, false);

			Set<AngleUnitFlapPattern> uniqueMountainOnlys = removeDuplications(
					mountainOnlyPatterns,
					tester);

			LOGGER.debug("#all of mountain only: " + mountainOnlyPatterns.size());
			LOGGER.info("#uniques of mountain only: "
					+ uniqueMountainOnlys.size());
			LOGGER.debug(uniqueMountainOnlys);

			LOGGER.debug("add valleys for each");
			for (AngleUnitFlapPattern mountainOnly : uniqueMountainOnlys) {
				patterns.addAll(removeDuplications(
						createValleyAddedPatterns(mountainOnly,
								mountainCount + 2, true), tester));
			}
		}

		LOGGER.info("# unique patterns: " + patterns.size());
		return patterns;
	}

	public Set<AngleUnitFlapPattern> removeDuplications(
			Set<AngleUnitFlapPattern> patterns, PatternDuplicationTester tester) {

		Set<AngleUnitFlapPattern> uniques = new HashSet<>();
		List<AngleUnitFlapPattern> notDetermined = new LinkedList<>(patterns);
		//Collections.sort(notDetermined, new FlapPatternComparator());

		while (!notDetermined.isEmpty()) {

			Iterator<AngleUnitFlapPattern> seeker = notDetermined.iterator();
			AngleUnitFlapPattern unique = seeker.next();
			seeker.remove();
			uniques.add(unique);

			for (; seeker.hasNext();) {
				AngleUnitFlapPattern pattern = seeker.next();
				if (tester.isDuplicate(pattern, unique)) {
					// if (pattern.isMirrorOf(unique, 0)) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("remove " + pattern);
					}
					seeker.remove();
				}
			}

		}

		return uniques;
	}

	//	private class FlapPatternComparator implements Comparator<AngleUnitFlapPattern> {
	//		@Override
	//		public int compare(AngleUnitFlapPattern p1, AngleUnitFlapPattern p2) {
	//			if (p1.equals(p2)) {
	//				return 0;
	//			}
	//
	//			if (p1.getDivisionSize() < p2.getDivisionSize()) {
	//				return -1;
	//			}
	//
	//			for (int i = 0; i < p1.getDivisionSize(); i++) {
	//				Comparator<OrigamiLine> lineComparator = new LineTypeComparator();
	//				int cmp = lineComparator.compare(p1.getLines().get(i), p2.getLines().get(i));
	//				if (cmp != 0) {
	//					return cmp;
	//				}
	//			}
	//
	//			return 0;
	//		}
	//	}

	private Set<AngleUnitFlapPattern> createMountainAddedPatterns(
			AngleUnitFlapPattern seed, int additionCount,
			boolean testFoldability) {
		PatternSetFactory adder = new PatternSetFactory(
				seed.getTailIndex(), testFoldability);

		return adder.createPatternsByAddingLineRecursively(seed,
				LineType.MOUNTAIN, additionCount);
	}

	private Set<AngleUnitFlapPattern> createValleyAddedPatterns(
			AngleUnitFlapPattern seed, int additonCount, boolean testFoldability) {
		PatternSetFactory adder = new PatternSetFactory(
				seed.getTailIndex(), testFoldability);

		return adder.createPatternsByAddingLineRecursively(seed,
				LineType.VALLEY, additonCount);
	}

	private Set<AngleUnitFlapPattern> advanceByMountain(Set<AngleUnitFlapPattern> seeds, boolean testFoldability) {
		PatternSetFactory factory = new PatternSetFactory(
				seeds.iterator().next().getTailIndex(), testFoldability);

		if (seeds.size() == 1) {
			// set line if seed is empty.
			// set direction unique.
			AngleUnitFlapPattern seed = seeds.iterator().next().cloneInstance();
			if (seed.isEmpty()) {
				// set direction unique.
				seed.set(0, LineType.MOUNTAIN);
				HashSet<AngleUnitFlapPattern> first = new HashSet<>();
				first.add(seed);
				return first;
			}
		}

		return factory.advanceAll(seeds, LineType.MOUNTAIN);
	}

}
