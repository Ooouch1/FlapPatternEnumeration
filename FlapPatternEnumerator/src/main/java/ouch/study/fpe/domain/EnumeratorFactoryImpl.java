package ouch.study.fpe.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;

import ouch.study.fpe.domain.rule.FlapPatternEnumerator;

@Configurable
public class EnumeratorFactoryImpl {
	@Autowired
	@Qualifier("bruteForce")
	private EnumeratorFactory bruteForceFactory;
	@Autowired
	@Qualifier("puzzling")
	private EnumeratorFactory puzzlingFactory;

	/**
	 * 
	 * @return an enumerator which generates patterns by adding single line one
	 *         by one.
	 */
	public FlapPatternEnumerator createBruteForceEnumerator() {
		return bruteForceFactory.create();
	}

	/**
	 * 
	 * @return an enumerator which generates patterns by tiling given pieces
	 *         like a puzzle.
	 */
	public FlapPatternEnumerator createPuzzlingEnumerator() {
		return puzzlingFactory.create();
	}
}
