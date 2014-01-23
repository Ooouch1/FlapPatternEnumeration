package ouch.study.fpe.controller;

import java.util.Set;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.FlapPatternEnumerator;
import ouch.study.fpe.util.Command;

public class RunEnumeration implements Command<Set<AngleUnitFlapPattern>> {
	private boolean mirrorTestEnabled;
	private boolean rotationTestEnabled;
	private int divisionSize;

	public RunEnumeration(int divisionSize, boolean mirrorTestEnabled,
			boolean rotationTestEnabled) {
		this.divisionSize = divisionSize;
		this.mirrorTestEnabled = mirrorTestEnabled;
		this.rotationTestEnabled = rotationTestEnabled;
	}

	@Override
	public Set<AngleUnitFlapPattern> run() {

		FlapPatternEnumerator enumerator = new FlapPatternEnumerator();

		return enumerator.enumerateUniquePatterns(divisionSize,
				mirrorTestEnabled, rotationTestEnabled);
	}
}
