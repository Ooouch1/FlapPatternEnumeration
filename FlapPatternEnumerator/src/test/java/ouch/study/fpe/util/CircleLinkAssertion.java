package ouch.study.fpe.util;

import static org.junit.Assert.*;

class CircleLinkAssertion {
	private CircleLinkAssertion() {

	}

	/**
	 * tests the value order in forward loop.
	 * 
	 * @param head
	 *            head element
	 * @param expected
	 *            expected value order.
	 */
	public static void assertValueOrder(final CircleLinkElement<Integer> head, final Integer... expected) {
		CircleLinkElement<Integer> element = head;
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], element.getValue());
			element = element.getNext();
		}
	}

	public static void assertTwoValuesOnly(final CircleLinkElement<Integer> head, final CircleLinkElement<Integer> tail) {
		assertEquals(tail, head.getNext());
		assertEquals(tail, head.getPrevious());

		assertEquals(head, tail.getNext());
		assertEquals(head, tail.getPrevious());
	}

	public static void assertThreeValuesOnly(
			final CircleLinkElement<Integer> head,
			final CircleLinkElement<Integer> middle,
			final CircleLinkElement<Integer> tail) {
		assertEquals(tail, head.getPrevious());
		assertMiddleValueInserted(head, middle, tail);
		assertEquals(head, tail.getNext());

	}

	public static void assertMiddleValueInserted(
			final CircleLinkElement<Integer> head,
			final CircleLinkElement<Integer> middle,
			final CircleLinkElement<Integer> tail) {

		assertEquals(middle, head.getNext());

		assertEquals(tail, middle.getNext());
		assertEquals(head, middle.getPrevious());

		assertEquals(middle, tail.getPrevious());

	}

}
