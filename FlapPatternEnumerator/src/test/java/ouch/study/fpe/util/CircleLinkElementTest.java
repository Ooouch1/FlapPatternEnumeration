package ouch.study.fpe.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircleLinkElementTest {
	final Integer headVal = 0;
	final Integer middleVal = 1;
	final Integer tailVal = 2;

	/**
	 * Tests the first time linking and inserting element into the middle place.
	 */
	@Test
	public void testInsertAsNext() {
		CircleLinkElement<Integer> head = new CircleLinkElement<Integer>(headVal);

		CircleLinkElement<Integer> tail = head.insertAsNext(tailVal);
		CircleLinkAssertion.assertTwoValuesOnly(head, tail);

		CircleLinkElement<Integer> middle = head.insertAsNext(middleVal);
		CircleLinkAssertion.assertThreeValuesOnly(head, middle, tail);

		CircleLinkAssertion.assertValueOrder(head, headVal, middleVal, tailVal);
	}

	/**
	 * Tests the first time linking and inserting element into the middle place.
	 */
	@Test
	public void testInsertAsPrevious() {
		CircleLinkElement<Integer> tail = new CircleLinkElement<Integer>(tailVal);

		CircleLinkElement<Integer> head = tail.insertAsPrevious(headVal);
		CircleLinkAssertion.assertTwoValuesOnly(head, tail);

		CircleLinkElement<Integer> middle = tail.insertAsPrevious(middleVal);
		CircleLinkAssertion.assertThreeValuesOnly(head, middle, tail);

		CircleLinkAssertion.assertValueOrder(head, headVal, middleVal, tailVal);
	}

	@Test
	public void testWhenHeadLeavesFromCircle() {
		CircleLinkElement<Integer> head = new CircleLinkElement<Integer>(headVal);
		CircleLinkElement<Integer> tail = head.insertAsNext(tailVal);

		CircleLinkElement<Integer> returned = head.leaveFromCircle(false);
		assertEquals(tail, returned);

		assertEquals(tail, tail.getNext());
		assertEquals(tail, tail.getPrevious());

	}

	@Test
	public void testWhenTailLeavesFromCircle() {
		CircleLinkElement<Integer> head = new CircleLinkElement<Integer>(headVal);
		CircleLinkElement<Integer> tail = head.insertAsNext(tailVal);

		CircleLinkElement<Integer> returned = tail.leaveFromCircle(false);
		assertEquals(head, returned);

		assertEquals(head, head.getNext());
		assertEquals(head, head.getPrevious());

	}

	@Test
	public void testWhenMiddleLeavesFromCircle() {
		CircleLinkElement<Integer> head = new CircleLinkElement<Integer>(headVal);
		CircleLinkElement<Integer> tail = head.insertAsNext(tailVal);
		CircleLinkElement<Integer> middle = head.insertAsNext(middleVal);

		CircleLinkElement<Integer> returned = middle.leaveFromCircle(false);
		assertEquals(tail, returned);

		CircleLinkAssertion.assertTwoValuesOnly(head, tail);

	}

}
