package ouch.study.fpe.domain;

import ouch.study.fpe.domain.bf.BruteForceFlapPatternEnumerator;
import ouch.study.fpe.domain.puzzle.PuzzlingFlapPatternEnumerator;

public class EnumeratorFactoryImpl {
	/**
	 * 
	 * @return an enumerator which generates patterns by adding single line one
	 *         by one.
	 */
	public FlapPatternEnumerator createBruteForceEnumerator() {
		return new BruteForceFlapPatternEnumerator();
	}

	/**
	 * 
	 * @return an enumerator which generates patterns by tiling given pieces
	 *         like a puzzle.
	 */
	public FlapPatternEnumerator createPuzzlingEnumerator() {
		return new PuzzlingFlapPatternEnumerator();
	}
}
