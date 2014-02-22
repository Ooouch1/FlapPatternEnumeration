package ouch.study.fpe.domain.rule;

import static org.junit.Assert.*;

import org.junit.Test;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.domain.PatternFactoryForTest;
import ouch.study.fpe.domain.rule.FlapPatternConverter;
import ouch.study.fpe.domain.rule.IntegerAngle;
import ouch.study.fpe.domain.rule.LineGap;
import ouch.study.fpe.domain.rule.LineGapCircle;
import ouch.study.fpe.util.CircleLinkPointer;

public class FlapPatternConverterTest {
	PatternFactoryForTest factory = new PatternFactoryForTest();

	FlapPatternConverter converter = new FlapPatternConverter();

	@Test
	public void test() {
		// MVV
		// .o.
		// MMM
		AngleUnitFlapPattern pattern = factory.createPattern(
				LineType.VALLEY, LineType.VALLEY,
				LineType.EMPTY,
				LineType.MOUNTAIN, LineType.MOUNTAIN, LineType.MOUNTAIN,
				LineType.EMPTY,
				LineType.MOUNTAIN
				);

		LineGapCircle circle = converter.toLineGapCircle(pattern);

		CircleLinkPointer<LineGap> pointer = circle.getHeadPointer();

		pointer = assertLineGap(LineType.VALLEY, 1, pointer);
		pointer = assertLineGap(LineType.VALLEY, 2, pointer);
		pointer = assertLineGap(LineType.MOUNTAIN, 1, pointer);
		pointer = assertLineGap(LineType.MOUNTAIN, 1, pointer);
		pointer = assertLineGap(LineType.MOUNTAIN, 2, pointer);
		pointer = assertLineGap(LineType.MOUNTAIN, 1, pointer);

	}

	private CircleLinkPointer<LineGap> assertLineGap(final LineType type, final int gap,
			final CircleLinkPointer<LineGap> actual) {
		assertEquals(type, actual.get().getLine());
		assertEquals(new IntegerAngle(gap), actual.get().getAngleToNextLine());

		return actual.getNext();
	}
}
