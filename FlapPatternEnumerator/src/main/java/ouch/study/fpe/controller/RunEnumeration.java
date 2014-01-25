package ouch.study.fpe.controller;

import java.util.Collection;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.FlapPatternEnumerator;
import ouch.study.fpe.util.Command;

public class RunEnumeration implements Command<Collection<AngleUnitFlapPattern>> {
	private boolean mirrorTestEnabled;
	private boolean rotationTestEnabled;
	private int divisionSize;

	public RunEnumeration(final int divisionSize, final boolean mirrorTestEnabled,
			final boolean rotationTestEnabled) {
		this.divisionSize = divisionSize;
		this.mirrorTestEnabled = mirrorTestEnabled;
		this.rotationTestEnabled = rotationTestEnabled;
	}

	@Override
	public Collection<AngleUnitFlapPattern> run() {

		FlapPatternEnumerator enumerator = new FlapPatternEnumerator();

		return enumerator.enumerateUniquePatterns(divisionSize,
				mirrorTestEnabled, rotationTestEnabled);
	}
}
