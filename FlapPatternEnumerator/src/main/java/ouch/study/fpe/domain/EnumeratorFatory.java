package ouch.study.fpe.domain;

import ouch.study.fpe.domain.bf.BruteForceFlapPatternEnumerator;

public class EnumeratorFatory {
	public FlapPatternEnumerator createBruteForceEnumerator() {
		return new BruteForceFlapPatternEnumerator();
	}
}
