package ouch.study.fpe.domain;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import ouch.study.fpe.domain.value.LineType;

public class FlapPatternBasicFunctionalityTest {

	@Test
	public void testEqualsAndCanBeHashElement() {
		final int divisionSize = 4;
		final int index = 1;
		AngleUnitFlapPattern p1 = new AngleUnitFlapPattern(divisionSize);
		p1.set(index, LineType.MOUNTAIN);

		AngleUnitFlapPattern p2 = new AngleUnitFlapPattern(divisionSize);
		p2.set(index, LineType.MOUNTAIN);

		assertEquals(p1, p2);

		HashSet<AngleUnitFlapPattern> set = new HashSet<>();
		set.add(p2);
		set.add(p1);
	}
}
