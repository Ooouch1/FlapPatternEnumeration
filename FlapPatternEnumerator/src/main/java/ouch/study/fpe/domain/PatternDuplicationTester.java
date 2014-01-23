package ouch.study.fpe.domain;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PatternDuplicationTester {
	/** ロガー */
	private static final Logger LOGGER = LogManager
			.getLogger(PatternDuplicationTester.class);

	private boolean mirrorEnabled = true;
	private boolean rotationEnabled = true;

	public PatternDuplicationTester(boolean mirrorTestEnabled,
			boolean rotationTestEnabled) {
		this.mirrorEnabled = mirrorTestEnabled;
		this.rotationEnabled = rotationTestEnabled;
	}

	public boolean isDuplicate(final AngleUnitFlapPattern pattern,
			final AngleUnitFlapPattern unique) {
		return isMirror(pattern, unique) || isRotation(pattern, unique);
	}

	public boolean isMirror(final AngleUnitFlapPattern pattern,
			final AngleUnitFlapPattern unique) {
		if (mirrorEnabled && pattern.isMirrorOf(unique)) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("find mirror of " + unique);
			}
			return true;
		}

		return false;
	}

	public boolean isRotation(final AngleUnitFlapPattern pattern,
			final AngleUnitFlapPattern unique) {
		if (rotationEnabled && pattern.isRotationOf(unique)) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("find rotation of " + unique);
			}
			return true;
		}
		return false;
	}
}
