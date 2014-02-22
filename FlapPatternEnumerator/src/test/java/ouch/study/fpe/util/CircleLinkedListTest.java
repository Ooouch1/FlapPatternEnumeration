package ouch.study.fpe.util;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class CircleLinkedListTest {

	private final Integer headVal = 0;
	private final Integer middleVal = 1;
	private final Integer tailVal = 2;

	@Test
	public void testWithThreeElements() {

		CircleLinkedList<Integer> list = createThreeElementListWithAssertions();

		assertFalse(list.isEmpty());
		assertNotNull(list.getHead());
		assertEquals(3, list.size());

		for (int i = 0; i < 3; i++) {
			CircleLinkElement<Integer> head = list.getHead();
			list.leaveFromCircle(head, false);
		}

		assertTrue(list.isEmpty());
		assertNull(list.getHead());
		assertEquals(0, list.size());

		final Integer aVal = 5;
		assertNotNull(list.insertIfEmpty(aVal));
		assertEquals(aVal, list.getHead().getValue());

	}

	@Test
	public void testConstructionWithList() {
		Integer[] values = { headVal, middleVal, tailVal };
		CircleLinkedList<Integer> list = new CircleLinkedList<Integer>(
				Arrays.asList(values));

		assertEquals(values.length, list.size());
		CircleLinkAssertion.assertValueOrder(list.getHead(), values);
	}

	private CircleLinkedList<Integer> createThreeElementListWithAssertions() {
		CircleLinkedList<Integer> list = new CircleLinkedList<Integer>(headVal);

		CircleLinkElement<Integer> head = list.getHead();
		CircleLinkElement<Integer> middle = list.insertAsNext(head, middleVal);
		CircleLinkElement<Integer> tail = list.insertAsNext(middle, tailVal);

		CircleLinkAssertion.assertThreeValuesOnly(head, middle, tail);

		CircleLinkAssertion.assertValueOrder(head, headVal, middleVal, tailVal);

		return list;
	}
}
